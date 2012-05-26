package com.matburt.mobileorg.test.provider;

import android.database.Cursor;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;

import com.matburt.mobileorg.provider.OrgContract.OrgData;
import com.matburt.mobileorg.provider.OrgDatabase;
import com.matburt.mobileorg.provider.OrgFile;
import com.matburt.mobileorg.provider.OrgNode;
import com.matburt.mobileorg.provider.OrgProvider;
import com.matburt.mobileorg.provider.OrgContract.Files;

public class OrgProviderTest extends ProviderTestCase2<OrgProvider> {

	private MockContentResolver resolver;
	private OrgDatabase db;

	public OrgProviderTest() {
		super(OrgProvider.class, OrgProvider.class.getName());
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.resolver = getMockContentResolver();
		this.db = new OrgDatabase(getMockContext());
	}
	
	@Override
	protected void tearDown() throws Exception {
		this.db.close();
	}

	public void testAddFileSimple() {
		OrgFile orgFile = new OrgFile("filename", "name", "checksum");
		orgFile.setResolver(resolver);
		orgFile.addFile();
		
		OrgFile insertedFile = new OrgFile(orgFile.id, resolver);
		assertTrue(orgFile.equals(insertedFile));
		
		Cursor dataCursor = resolver.query(
				OrgData.buildIdUri(insertedFile.nodeId),
				OrgData.DEFAULT_COLUMNS, null, null, null);
		assertNotNull(dataCursor);
		assertEquals(1, dataCursor.getCount());
		OrgNode node = new OrgNode(dataCursor);
		dataCursor.close();
		assertEquals(node.name, orgFile.name);
	}
	
	public void testRemoveFileSimple() {
		OrgFile orgFile = new OrgFile("filename", "name", "checksum");
		orgFile.setResolver(resolver);
		orgFile.addFile();
		OrgFile insertedFile = new OrgFile(orgFile.id, resolver);
		insertedFile.removeFile();
		
		Cursor filesCursor = resolver.query(Files.buildIdUri(orgFile.id),
				Files.DEFAULT_COLUMNS, null, null, null);
		assertEquals(0, filesCursor.getCount());
		filesCursor.close();
		
		Cursor dataCursor = resolver.query(OrgData.buildIdUri(insertedFile.nodeId),
				OrgData.DEFAULT_COLUMNS, null, null, null);
		assertEquals(0, dataCursor.getCount());
		dataCursor.close();
	}
	
	public void testRemoveFileWithChildren() {
		//TODO: Implement
	}
}	
