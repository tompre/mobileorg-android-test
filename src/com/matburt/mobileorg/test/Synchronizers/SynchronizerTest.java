package com.matburt.mobileorg.test.Synchronizers;

import java.io.IOException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLHandshakeException;

import android.content.ContentResolver;
import android.content.Context;
import android.test.ProviderTestCase2;

import com.matburt.mobileorg.Synchronizers.Synchronizer;
import com.matburt.mobileorg.provider.OrgDatabase;
import com.matburt.mobileorg.provider.OrgProvider;
import com.matburt.mobileorg.test.Synchronizers.OrgTestFiles.SimpleOrgFiles;

public class SynchronizerTest extends ProviderTestCase2<OrgProvider> {

	private Synchronizer synchronizer;
	private OrgFileParserStub parserStub;
	private OrgDatabase db;
	private SynchronizerStub synchronizerStub;
	private SynchronizerNotificationStub notifyStub;

	public SynchronizerTest() {
		super(OrgProvider.class, OrgProvider.class.getName());
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Context context = getMockContext();
		ContentResolver resolver = getMockContentResolver();
		this.db = new OrgDatabase(context);
		this.parserStub = new OrgFileParserStub(db, resolver);
		this.synchronizerStub = new SynchronizerStub();
		this.notifyStub = new SynchronizerNotificationStub(getMockContext());
		this.synchronizer = new Synchronizer(context, synchronizerStub, notifyStub);
	}
	
	@Override
	protected void tearDown() throws Exception {
		db.close();
	}

	public void testSynchronizeSimple() throws SSLHandshakeException, CertificateException, IOException, Exception {
		synchronizerStub.addFile("index.org", SimpleOrgFiles.indexFile);
		synchronizerStub.addFile("checksums.dat", SimpleOrgFiles.checksumsFile);
		synchronizerStub.addFile("GTD.org", SimpleOrgFiles.orgFile);
		synchronizer.pull(parserStub);
		assertTrue(parserStub.filesParsed.contains("GTD.org"));
		assertEquals(1, parserStub.filesParsed.size());
	}
	
	public void testPullWithMissingIndex() throws CertificateException, Exception {
		synchronizerStub.addFile("checksums.dat", SimpleOrgFiles.checksumsFile);
		synchronizerStub.addFile("GTD.org", SimpleOrgFiles.orgFile);
		try {
			synchronizer.pull(parserStub);
			fail("Should have thrown IOException");
		} catch (IOException e) {}
		assertEquals(0, parserStub.filesParsed.size());
	}
	
	public void testPullWithMissingChecksums() throws CertificateException, Exception {
		synchronizerStub.addFile("index.org", SimpleOrgFiles.indexFile);
		synchronizerStub.addFile("GTD.org", SimpleOrgFiles.orgFile);
		try {
			synchronizer.pull(parserStub);
			fail("Should have thrown IOException");
		} catch (IOException e) {}
		assertEquals(0, parserStub.filesParsed.size());
	}

	public void testPullWithMissingOrgfile() throws CertificateException, Exception {
		synchronizerStub.addFile("index.org", SimpleOrgFiles.indexFile);
		synchronizerStub.addFile("checksums.dat", SimpleOrgFiles.checksumsFile);
		try {
			synchronizer.pull(parserStub);
			fail("Should have thrown IOException");
		} catch (IOException e) {}
		assertEquals(0, parserStub.filesParsed.size());
	}
}
