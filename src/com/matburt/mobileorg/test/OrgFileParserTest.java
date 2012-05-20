package com.matburt.mobileorg.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.TestCase;

import org.junit.Test;

import android.test.IsolatedContext;

import com.matburt.mobileorg.Parsing.OrgDatabase;
import com.matburt.mobileorg.Parsing.OrgFileParser;
import static org.easymock.EasyMock.*;

public class OrgFileParserTest {

	String testFile = "* new \n* test";
	
	@Test
	public void testMocks() {
		OrgDatabase orgdbMock = createMock(OrgDatabase.class);
		replay(orgdbMock);

		OrgFileParser parser = new OrgFileParser(orgdbMock);
		IsolatedContext context = new IsolatedContext(null, null);
		
		InputStream is = new ByteArrayInputStream(testFile.getBytes());
		BufferedReader breader = new BufferedReader(new InputStreamReader(is));
		parser.parse("filename", "file Alias", "000000000", breader, context);

		verify(orgdbMock);
	}
}
