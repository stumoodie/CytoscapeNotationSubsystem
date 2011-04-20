/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/
package org.pathwayeditor.notations.cytoscape.export;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.notationsubsystem.ExportServiceException;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.notations.cytoscape.CytoscapeNotationSubsystem;

@RunWith(JMock.class)
public class SIFExportServiceTest {
	private static final String TEST_FILE_PREFIX = "test";
	private static final String TEST_FILE_SUFFIX = ".sif";
	private static final File EXPECTED_FILE = new File("test/org/pathwayeditor/notations/cytoscape/export/expected.sif");
	private static final String EXPECTED_SUFFIX = "sif";
	private static final String EXPECTED_MAP_NAME = "TestExportMap";

	private final Mockery mockery = new JUnit4Mockery();
	private SIFExportService service;
	private File exportFile;
	private CytoscapeMockObjects mockNdom;
	private INotationSubsystem notationSubsystem;

	@Before
	public void setUp() throws IOException {
		this.notationSubsystem = new CytoscapeNotationSubsystem();
		exportFile = File.createTempFile(TEST_FILE_PREFIX, TEST_FILE_SUFFIX);
		this.mockNdom = new CytoscapeMockObjects(mockery, EXPECTED_MAP_NAME);
		service = new SIFExportService(this.notationSubsystem);
	}

	@After
	public void tearDown(){
		mockery.assertIsSatisfied();
		mockery.checking(new Expectations(){{}});
		exportFile.delete();
		this.service = null;
		this.exportFile = null;
		this.mockNdom = null;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExportMapWithNoMap() throws ExportServiceException {
		service.exportMap(null, exportFile);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testExportMapWithNoFile() throws ExportServiceException {
		service.exportMap(this.mockNdom.getCanvas(), null);
	}

	@Ignore
	@Test
	public void testExportMapWithInvalidFile(){
		boolean success = this.exportFile.setReadOnly();
		assertTrue("export file marked read only", success);
		try {
			service.exportMap(this.mockNdom.getCanvas(), exportFile);
		} catch (ExportServiceException e) {
			assertTrue("IOException caught", e.getCause() instanceof IOException);
		}
	}

	@Test
	public void testSuffixAsExpected(){
		assertEquals("expected suffix", EXPECTED_SUFFIX, this.service.getRecommendedSuffix());
	}
	
	@Ignore
	@Test
	public void testExportWritesToOutPutStream() throws ExportServiceException, IOException {
		service.exportMap(this.mockNdom.getCanvas(), exportFile);
//		assertTrue("file as expected", readBytes(EXPECTED_FILE), readBytes(exportFile));
		assertTrue("files as expected", compareFiles(EXPECTED_FILE, exportFile));
	}

	private boolean compareFiles(File expectedFile, File actualFile) throws IOException {
//		System.out.println("File=" + actualFile);
		byte expectedBytes[] = readBytes(expectedFile);
		byte actualBytes[] = readBytes(actualFile);
		boolean retVal = expectedBytes.length == actualBytes.length;
		for(int i = 0; i < expectedBytes.length && retVal; i++){
			retVal = expectedBytes[i] == actualBytes[i];
		}
		return retVal;
	}

	
	private byte[] readBytes(File file) throws IOException{
		final InputStream stream = new FileInputStream(file);
		int availableBytes = stream.available();
		byte retVal[] = new byte[availableBytes];
		int bytesRead = stream.read(retVal);
		if(bytesRead != availableBytes) throw new RuntimeException("Not all of the file may have been read");
		
		stream.close();
		return retVal;
	}
}
