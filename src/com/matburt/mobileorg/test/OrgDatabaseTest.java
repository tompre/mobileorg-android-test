package com.matburt.mobileorg.test;

import com.matburt.mobileorg.provider.OrgNode;
import com.matburt.mobileorg.provider.OrgProvider;
import com.matburt.mobileorg.provider.OrgContract.OrgData;
import com.matburt.mobileorg.provider.OrgDatabaseNew;

import android.database.Cursor;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;

public class OrgDatabaseTest extends ProviderTestCase2<OrgProvider> {

	private MockContentResolver resolver;
	private OrgDatabaseNew db;

	public OrgDatabaseTest() {
		super(OrgProvider.class, OrgProvider.class.getName());
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.resolver = getMockContentResolver();
		this.db = new OrgDatabaseNew(getMockContext());
	}
	
	@Override
	protected void tearDown() throws Exception {
		this.db.close();
	}
	
	
	public void testNodeConstructor() {	
		try { 
			new OrgNode(null);
			fail("IllegalArgumentException not thrown");
		} catch(IllegalArgumentException e) {	
		}
	}
	
	public void testNodeEquals() {
		OrgNode node1 = getSimpleOrgNode();
		OrgNode node2 = getSimpleOrgNode();
		
		node1.fileId = 300;
		node1.parentId = 400;
		node1.level = 3;
		node1.id = 1000;
		
		assertTrue(node1.equals(node2));
		
		node1.name = "hej";
		assertFalse(node1.equals(node2));
	}
	
	public void testFastInsertNodeSimple() {
		OrgNode node = getSimpleOrgNode();
		long id = db.fastInsertNode(node);
		Cursor cursor = resolver.query(OrgData.buildIdUri(id),
				OrgData.DEFAULT_COLUMNS, null, null, null);
		assertNotNull(cursor);
		assertEquals(1, cursor.getCount());
		assertTrue(cursor.getColumnCount() > 0);

		OrgNode insertedNode = new OrgNode(cursor);
		cursor.close();
		
		assertNull(insertedNode.getPayload());
		insertedNode.payload = "";
		assertTrue(node.equals(insertedNode));
	}
	
	public void testFastInsertNodePayloadSimple() {
		OrgNode node = getSimpleOrgNode();
		long id = db.fastInsertNode(node);
		final String testPayload = "this is a test payload";
		db.fastInsertNodePayload(id, testPayload);
		
		Cursor cursor = resolver.query(OrgData.buildIdUri(Long.toString(id)),
				OrgData.DEFAULT_COLUMNS, null, null, null);
		assertNotNull(cursor);
		assertEquals(1, cursor.getCount());
		assertTrue(cursor.getColumnCount() > 0);

		OrgNode insertedNode = new OrgNode(cursor);
		cursor.close();
		
		assertEquals(testPayload, insertedNode.getPayload());
	}
	
	public void testFastInsertNodePayloadUpdate() {
		final String testPayload = "second payload";

		OrgNode node = getSimpleOrgNode();
		long id = db.fastInsertNode(node);
		db.fastInsertNodePayload(id, "first payload");
		db.fastInsertNodePayload(id, testPayload);
		
		Cursor cursor = resolver.query(OrgData.buildIdUri(Long.toString(id)),
				OrgData.DEFAULT_COLUMNS, null, null, null);
		OrgNode insertedNode = new OrgNode(cursor);
		cursor.close();
		
		assertEquals(testPayload, insertedNode.getPayload());
	}
	
	private OrgNode getSimpleOrgNode() {
		OrgNode node = new OrgNode();
		node.name = "Node name";
		return node;
	}
}	
