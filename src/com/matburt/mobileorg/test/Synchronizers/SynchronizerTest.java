package com.matburt.mobileorg.test.Synchronizers;

import android.test.ProviderTestCase2;

import com.matburt.mobileorg.provider.OrgProvider;

public class SynchronizerTest extends ProviderTestCase2<OrgProvider> {

	public SynchronizerTest(Class<OrgProvider> providerClass,
			String providerAuthority) {
		super(OrgProvider.class, OrgProvider.class.getName());
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
	}

	public void testSynchronizeSimple() {
		
	}
}
