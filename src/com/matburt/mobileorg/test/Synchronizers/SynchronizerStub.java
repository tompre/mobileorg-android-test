package com.matburt.mobileorg.test.Synchronizers;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLHandshakeException;

import android.content.Context;

import com.matburt.mobileorg.Synchronizers.SynchronizerInterface;

public class SynchronizerStub implements SynchronizerInterface {

	int putRemoteFileCount = 0;
	int getRemoteFileCount = 0;
	
	SynchronizerStub() {
	}

	@Override
	public boolean isConfigured() {
		return true;
	}

	@Override
	public void putRemoteFile(String filename, String contents)
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
	public void postSynchronize() {		
	}

}
