package com.matburt.mobileorg.test;

import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;

import com.matburt.mobileorg.provider.OrgDatabaseNew;
import com.matburt.mobileorg.provider.OrgProvider;

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
		
	}
}	
