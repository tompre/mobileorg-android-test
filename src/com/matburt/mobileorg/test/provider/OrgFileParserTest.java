package com.matburt.mobileorg.test.provider;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.matburt.mobileorg.Parsing.OrgFileParser;
import com.matburt.mobileorg.provider.OrgFile;
import com.matburt.mobileorg.provider.OrgNode;
import com.matburt.mobileorg.provider.OrgProvider;
import com.matburt.mobileorg.provider.OrgContract.OrgData;
import com.matburt.mobileorg.test.Synchronizers.OrgTestFiles.SimpleOrgFiles;

import android.database.Cursor;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;

public class OrgFileParserTest extends ProviderTestCase2<OrgProvider> {

	private MockContentResolver resolver;
	private OrgDatabaseStub db;
	private OrgFileParser parser;
	
	public OrgFileParserTest() {
		super(OrgProvider.class, OrgProvider.class.getName());
	}

	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.resolver = getMockContentResolver();
		this.db = new OrgDatabaseStub(getMockContext());
		this.parser = new OrgFileParser(db, resolver);
	}
	
	@Override
	protected void tearDown() throws Exception {
		this.db.close();
		super.tearDown();
	}
	
	public void testNodeToStringSimple() {
		OrgNode node = new OrgNode();
		node.name = "my simple test";
		node.todo = "TODO";
		node.level = 3;
		
		assertEquals("*** TODO my simple test", node.toString());
	}
	
	public void testParseLineIntoNodeSimple() {
		OrgNode node = new OrgNode();
		node.name = "my simple test";
		node.todo = "TODO";
		node.level = 3;
		OrgNode parsedNode = new OrgNode();
		final String testHeading = "*** TODO my simple test";
		parsedNode.parseLine(testHeading, 3, true);
		
		assertTrue(node.equals(parsedNode));
	}
	
	public void testParseSimple() {
		Cursor cursor = resolver.query(OrgData.CONTENT_URI, OrgData.DEFAULT_COLUMNS, 
				null, null, null);
		assertNotNull(cursor);
		cursor.close();
		
		InputStream is = new ByteArrayInputStream(SimpleOrgFiles.orgFile.getBytes());
		BufferedReader breader = new BufferedReader(new InputStreamReader(is));
		OrgFile orgFile = new OrgFile("new file", "file alias", "");
		parser.parse(orgFile, breader);
		assertEquals(2, db.fastInsertNodeCalls);
		assertEquals(3, db.fastInsertNodePayloadCalls);

		cursor = resolver.query(OrgData.CONTENT_URI, OrgData.DEFAULT_COLUMNS,
				null, null, null);
		assertEquals(3, cursor.getCount());
		cursor.close();

		assertTrue(orgFile.id > -1);
		cursor = resolver.query(OrgData.CONTENT_URI, OrgData.DEFAULT_COLUMNS, OrgData.FILE_ID + "=?",
				new String[] { Long.toString(orgFile.id) }, OrgData.ID + " DESC");
		assertEquals(3, cursor.getCount());
		cursor.close();
		

	}
	
	public void testParseParentChildRelation() {
		InputStream is = new ByteArrayInputStream(SimpleOrgFiles.orgFile.getBytes());
		BufferedReader breader = new BufferedReader(new InputStreamReader(is));
		final String name = "file alias";
		OrgFile orgFile = new OrgFile("GTD.org", name, "");
		parser.parse(orgFile, breader);

		Cursor cursor = resolver.query(OrgData.CONTENT_URI, OrgData.DEFAULT_COLUMNS, OrgData.NAME + "=?",
				new String[] { name }, null);
		OrgNode fileNode = new OrgNode(cursor);
		cursor.close();
		
		cursor = resolver.query(OrgData.CONTENT_URI, OrgData.DEFAULT_COLUMNS, OrgData.NAME + "=?",
				new String[] { SimpleOrgFiles.orgFileTopHeading }, null);
		OrgNode topNode = new OrgNode(cursor);
		cursor.close();
		
		cursor = resolver.query(OrgData.CONTENT_URI, OrgData.DEFAULT_COLUMNS, OrgData.NAME + "=?",
				new String[] { SimpleOrgFiles.orgFileChildHeading }, null);
		OrgNode childNode = new OrgNode(cursor);
		cursor.close();

		assertEquals(-1, fileNode.parentId);
		assertEquals(fileNode.id, topNode.parentId);
		assertEquals(topNode.id, childNode.parentId);
	}
	
	public void testGetChecksums() {
		// TODO Implement
	}
	
	public void testGetFilesFromIndex() {
		// TODO Implement
	}
	
	public void testGetTodosFromIndex() {
		// TODO Implement
	}
	
	public void testGetPrioritiesFromIndex() {
		// TODO Implement
	}
	
	public void testGetTagsFromIndex() {
		// TODO Implement
	}
}
