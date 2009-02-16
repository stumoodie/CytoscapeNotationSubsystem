package org.pathwayeditor.notations.cytoscape.export;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.notationsubsystem.ExportServiceException;

@RunWith(JMock.class)
public class GMMXLExportServiceTest {
	private static final String TEST_FILE_PREFIX = "test";
	private static final String TEST_FILE_SUFFIX = ".xml";
//	private static final File EXPECTED_FILE = new File("test/org/pathwayeditor/notations/cytoscape/export/expected_gmmxl.xml");
	private static final String EXPECTED_SUFFIX = "xml";
	private static final String EXPECTED_MAP_NAME = "TestExportMap";

	private final Mockery mockery = new JUnit4Mockery();
	private GMMXLExportService service;
	private File exportFile;
	private CytoscapeMockObjects mockNdom;

	@Before
	public void setUp() throws IOException {
		exportFile = File.createTempFile(TEST_FILE_PREFIX, TEST_FILE_SUFFIX);
		this.mockNdom = new CytoscapeMockObjects(mockery, EXPECTED_MAP_NAME);
		service = new GMMXLExportService(this.mockNdom.getNotationSubsystem());
	}

	@After
	public void tearDown(){
		mockery.assertIsSatisfied();
		mockery.checking(new Expectations(){{}});
		exportFile.delete();
		this.exportFile = null;
		this.mockNdom = null;
		this.service = null;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExportMapWithNoMap() throws ExportServiceException {
		service.exportMap(null, exportFile);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testExportMapWithNoFile() throws ExportServiceException {
		service.exportMap(this.mockNdom.getCanvas(), null);
	}

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
	public void testSuffixAsExcpected(){
		assertEquals("expected suffix", EXPECTED_SUFFIX, this.service.getRecommendedSuffix());
	}
	
	@Test
	public void testExportWritesToOutPutStream() throws ExportServiceException, IOException {
		service.exportMap(this.mockNdom.getCanvas(), exportFile);
//		assertTrue("file as expected", readBytes(EXPECTED_FILE), readBytes(exportFile));
//		assertTrue("files as expected", compareFiles(EXPECTED_FILE, exportFile));
	}

//	private boolean compareFiles(File expectedFile, File actualFile) throws IOException {
////		System.out.println("File=" + actualFile);
//		byte expectedBytes[] = readBytes(expectedFile);
//		byte actualBytes[] = readBytes(actualFile);
//		boolean retVal = expectedBytes.length == actualBytes.length;
//		for(int i = 0; i < expectedBytes.length && retVal; i++){
//			retVal = expectedBytes[i] == actualBytes[i];
//		}
//		return retVal;
//	}

	
//	private byte[] readBytes(File file) throws IOException{
//		final InputStream stream = new FileInputStream(file);
//		int availableBytes = stream.available();
//		byte retVal[] = new byte[availableBytes];
//		int bytesRead = stream.read(retVal);
//		if(bytesRead != availableBytes) throw new RuntimeException("Not all of the file may have been read");
//		
//		stream.close();
//		return retVal;
//	}
}
