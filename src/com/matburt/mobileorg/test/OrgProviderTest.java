package com.matburt.mobileorg.test;

import android.content.ContentValues;
import android.database.Cursor;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;

import com.matburt.mobileorg.provider.OrgDatabaseNew;
import com.matburt.mobileorg.provider.OrgFile;
import com.matburt.mobileorg.provider.OrgProvider;
import com.matburt.mobileorg.provider.OrgContract.Files;
import com.matburt.mobileorg.provider.OrgProviderUtil;

public class OrgProviderTest extends ProviderTestCase2<OrgProvider> {

	private MockContentResolver resolver;
	private OrgDatabaseNew db;

	public OrgProviderTest() {
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

	public void testAddFile() {
		OrgFile orgFile = new OrgFile();
		orgFile.name = "name";
		orgFile.filename = "filename";
		orgFile.checksum = "checksum";
		OrgProviderUtil.addOrUpdateFile(orgFile, resolver);
		
		Cursor cursor = resolver.query(Files.CONTENT_URI, Files.DEFAULT_COLUMNS, "filename=?", new String[] {"file"}, null);
		assertEquals(1, cursor.getCount());
		OrgFile insertedFile = new OrgFile(cursor);
		assertTrue(orgFile.equals(insertedFile));
	}
}	
