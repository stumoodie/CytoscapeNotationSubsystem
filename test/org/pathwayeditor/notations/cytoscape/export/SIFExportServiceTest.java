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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.notationsubsystem.ExportServiceException;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;


@RunWith(JMock.class)
public class SIFExportServiceTest {
	private static final String TEST_FILE_PREFIX = "test";
	private static final String TEST_FILE_SUFFIX = ".sif";
	private static final File EXPECTED_FILE = new File("test/org/pathwayeditor/notations/cytoscape/export/expected.sif");
	private static final String NODE1_NAME = "node";
	private static final String NODE2_NAME = "node";
	private static final String NODE3_NAME = "node3";
	private static final int NODE1_IDX = 1; 
	private static final int NODE2_IDX = 2; 
	private static final int NODE3_IDX = 3; 
	private static final String EDGE1_NAME = "edge";
	private static final String EDGE2_NAME = "edge";
	private static final String EDGE3_NAME = "node3";
	private static final int EDGE1_IDX = 1; 
	private static final int EDGE2_IDX = 2; 
	private static final int EDGE3_IDX = 3; 
	private static final String EXPECTED_SUFFIX = "sif";
	Mockery mockery = new JUnit4Mockery();
	private INotationSubsystem contextService;
	private ICanvas mockCanvas;
	private IModel mockModel;
	private IShapeNode mockShapeNode1;
	private IShapeAttribute mockShapeAttribute1;
	private IShapeNode mockShapeNode2;
	private IShapeAttribute mockShapeAttribute2;
	private IShapeNode mockShapeNode3;
	private IShapeAttribute mockShapeAttribute3;
	private ILinkEdge mockLinkEdge1;
	private ILinkAttribute mockLinkAttribute1;
	private ILinkEdge mockLinkEdge2;
	private ILinkAttribute mockLinkAttribute2;
	private ILinkEdge mockLinkEdge3;
	private ILinkAttribute mockLinkAttribute3;
	private SIFExportService service;
	private File exportFile;

	@Before
	public void setUp() throws IOException {
		contextService = mockery.mock(INotationSubsystem.class);
		exportFile = File.createTempFile(TEST_FILE_PREFIX, TEST_FILE_SUFFIX);
		mockCanvas = mockery.mock(ICanvas.class, "mockCanvas");
		this.mockModel = mockery.mock(IModel.class, "mockModel");
		this.mockShapeNode1 = mockery.mock(IShapeNode.class, "mockShapeNode1");
		this.mockShapeNode2 = mockery.mock(IShapeNode.class, "mockShapeNode2");
		this.mockShapeNode3 = mockery.mock(IShapeNode.class, "mockShapeNode3");
		this.mockShapeAttribute1 = mockery.mock(IShapeAttribute.class, "mockShapeAttribute1");
		this.mockShapeAttribute2 = mockery.mock(IShapeAttribute.class, "mockShapeAttribute2");
		this.mockShapeAttribute3 = mockery.mock(IShapeAttribute.class, "mockShapeAttribute3");
		this.mockLinkEdge1 = mockery.mock(ILinkEdge.class, "mockLinkEdge1");
		this.mockLinkEdge2 = mockery.mock(ILinkEdge.class, "mockLinkEdge2");
		this.mockLinkEdge3 = mockery.mock(ILinkEdge.class, "mockLinkEdge3");
		this.mockLinkAttribute1 = mockery.mock(ILinkAttribute.class, "mockLinkAttribute1");
		this.mockLinkAttribute2 = mockery.mock(ILinkAttribute.class, "mockLinkAttribute2");
		this.mockLinkAttribute3 = mockery.mock(ILinkAttribute.class, "mockLinkAttribute3");
		mockery.checking(new Expectations() {{
			ignoring(contextService).getNotation();
			
			allowing(mockCanvas).getModel(); will(returnValue(mockModel));

			allowing(mockModel).shapeNodeIterator(); will(returnIterator(mockShapeNode1, mockShapeNode2, mockShapeNode3));
			allowing(mockModel).linkEdgeIterator(); will(returnIterator(mockLinkEdge1, mockLinkEdge2, mockLinkEdge3));
			
			allowing(mockShapeNode1).getAttribute(); will(returnValue(mockShapeAttribute1));
			allowing(mockShapeNode1).getIndex(); will(returnValue(NODE1_IDX));
			
			allowing(mockShapeNode2).getAttribute(); will(returnValue(mockShapeAttribute2));
			allowing(mockShapeNode2).getIndex(); will(returnValue(NODE2_IDX));
			
			allowing(mockShapeNode3).getAttribute(); will(returnValue(mockShapeAttribute3));
			allowing(mockShapeNode3).getIndex(); will(returnValue(NODE3_IDX));
			
			allowing(mockShapeAttribute1).getName(); will(returnValue(NODE1_NAME));
			
			allowing(mockShapeAttribute2).getName(); will(returnValue(NODE2_NAME));
			
			allowing(mockShapeAttribute3).getName(); will(returnValue(NODE3_NAME));

			allowing(mockLinkEdge1).getAttribute(); will(returnValue(mockLinkAttribute1));
			allowing(mockLinkEdge1).getIndex(); will(returnValue(EDGE1_IDX));
			allowing(mockLinkEdge1).getSourceShape(); will(returnValue(mockShapeNode1));
			allowing(mockLinkEdge1).getTargetShape(); will(returnValue(mockShapeNode2));
			
			allowing(mockLinkEdge2).getAttribute(); will(returnValue(mockLinkAttribute2));
			allowing(mockLinkEdge2).getIndex(); will(returnValue(EDGE2_IDX));
			allowing(mockLinkEdge2).getSourceShape(); will(returnValue(mockShapeNode2));
			allowing(mockLinkEdge2).getTargetShape(); will(returnValue(mockShapeNode1));
			
			allowing(mockLinkEdge3).getAttribute(); will(returnValue(mockLinkAttribute3));
			allowing(mockLinkEdge3).getIndex(); will(returnValue(EDGE3_IDX));
			allowing(mockLinkEdge3).getSourceShape(); will(returnValue(mockShapeNode3));
			allowing(mockLinkEdge3).getTargetShape(); will(returnValue(mockShapeNode2));
			
			allowing(mockLinkAttribute1).getName(); will(returnValue(EDGE1_NAME));
			
			allowing(mockLinkAttribute2).getName(); will(returnValue(EDGE2_NAME));
			
			allowing(mockLinkAttribute3).getName(); will(returnValue(EDGE3_NAME));
		}});
		
		service = new SIFExportService(contextService);
	}

	@After
	public void tearDown(){
		exportFile.delete();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExportMapWithNoMap() throws ExportServiceException {
		service.exportMap(null, exportFile);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testExportMapWithNoFile() throws ExportServiceException {
		service.exportMap(this.mockCanvas, null);
	}

	@Test
	public void testExportMapWithInvalidFile(){
		boolean success = this.exportFile.setReadOnly();
		assertTrue("export file marked read only", success);
		try {
			service.exportMap(mockCanvas, exportFile);
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
		service.exportMap(mockCanvas, exportFile);
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
