package com.matburt.mobileorg.test.Synchronizers;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLHandshakeException;

import android.content.Context;

import com.matburt.mobileorg.Synchronizers.Synchronizer;

public class SynchronizerStub extends Synchronizer {

	int putRemoteFileCount = 0;
	int getRemoteFileCount = 0;
	
	SynchronizerStub(Context context) {
		super(context);
	}

	@Override
	protected boolean isConfigured() {
		return true;
	}

	@Override
	protected void putRemoteFile(String filename, String contents)
			throws Exception, IOException {
		putRemoteFileCount++;
	}

	@Override
	public BufferedReader getRemoteFile(String filename) throws Exception,
			IOException, CertificateException, SSLHandshakeException {
		getRemoteFileCount++;
		return null;
	}

	@Override
	protected void postSynchronize() {		
	}

}
