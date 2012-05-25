package com.matburt.mobileorg.test.provider;

import android.content.Context;

import com.matburt.mobileorg.provider.OrgDatabaseNew;
import com.matburt.mobileorg.provider.OrgNode;

public class OrgDatabaseStub extends OrgDatabaseNew {

	int fastInsertNodeCalls = 0;
	int fastInsertNodePayloadCalls = 0;
	
	public OrgDatabaseStub(Context context) {
		super(context);
	}

	@Override
	public long fastInsertNode(OrgNode node) {
		fastInsertNodeCalls++;
		return fastInsertNodeCalls;
	}
	
	@Override
	public void fastInsertNodePayload(Long id, final String payload) {
		fastInsertNodePayloadCalls++;
	}
}
