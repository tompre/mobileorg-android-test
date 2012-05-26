package com.matburt.mobileorg.test.Synchronizers;

import android.test.ProviderTestCase2;

import com.matburt.mobileorg.provider.OrgDatabaseNew;
import com.matburt.mobileorg.provider.OrgProvider;

public class SynchronizerTest extends ProviderTestCase2<OrgProvider> {

	private SynchronizerStub synchronizer;
	private OrgFileParserStub parser;
	private OrgDatabaseNew db;

	public SynchronizerTest(Class<OrgProvider> providerClass,
			String providerAuthority) {
		super(OrgProvider.class, OrgProvider.class.getName());
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.synchronizer = new SynchronizerStub(getMockContext());
		this.db = new OrgDatabaseNew(getMockContext());
		this.parser = new OrgFileParserStub(db, getMockContentResolver());
	}
	
	@Override
	protected void tearDown() throws Exception {
		db.close();
	}

	public void testSynchronizeSimple() {
		synchronizer.sync(parser);
		//TODO Check output
	}
}
