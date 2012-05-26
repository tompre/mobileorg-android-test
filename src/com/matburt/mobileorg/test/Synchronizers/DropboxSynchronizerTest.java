package com.matburt.mobileorg.test.Synchronizers;

import java.io.BufferedReader;
import java.io.IOException;

import android.test.AndroidTestCase;

import com.matburt.mobileorg.Synchronizers.DropboxSynchronizer;

public class DropboxSynchronizerTest extends AndroidTestCase {

	private DropboxSynchronizer synchronizer;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.synchronizer = new DropboxSynchronizer(null);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetFileSimple() {
		try {
			BufferedReader remoteFile = synchronizer.getRemoteFile("index.org");
			String firstLine = remoteFile.readLine();
			assertEquals("#+READONLY", firstLine);
		} catch (IOException e) {
			fail("Couldn't get file");
		}
	}
}
