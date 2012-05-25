package com.matburt.mobileorg.test.Synchronizers;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLHandshakeException;

import android.content.Context;

import com.matburt.mobileorg.Parsing.MobileOrgApplication;
import com.matburt.mobileorg.Synchronizers.Synchronizer;

public class SynchronizerStub extends Synchronizer {

	SynchronizerStub(Context context, MobileOrgApplication appInst) {
		super(context, appInst);
	}

	@Override
	protected boolean isConfigured() {
		return true;
	}

	@Override
	protected void putRemoteFile(String filename, String contents)
			throws Exception, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BufferedReader getRemoteFile(String filename) throws Exception,
			IOException, CertificateException, SSLHandshakeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void postSynchronize() {
		// TODO Auto-generated method stub
		
	}

}
