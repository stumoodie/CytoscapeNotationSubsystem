package org.pathwayeditor.notations.cytoscape.importservice;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.management.NonPersistentCanvasFactory;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.notations.cytoscape.CytoscapeNotationSubsystem;
import org.pathwayeditor.notations.cytoscape.CytoscapeSyntaxService;

public class XGMMLImportServiceTest {
	private static final String EXPECTED_SUFFIX = "xgmml";
	private static final String TEST_CANVAS_NAME = "Test Canvas";
	private XGMMLImportService testInstance;
	private INotationSubsystem notationSubsystem;
	private ICanvas testCanvas;
	private static final File TEST_INPUT_FILE = new File("test/org/pathwayeditor/notations/cytoscape/importservice/expected_input.xgmml");
	private static final String EXPECTED_DISPLAY_NAME = "Cytoscape XGMML File"; 
	private static final String EXPECTED_CODE = "cytoscape_xgmml";
	private static final int EXPECTED_NUM_NODES = 2;
	private static final int EXPECTED_NUM_EDGES = 3;
	private static final int NODE1_ATTRIB_IDX = 2;
	private static final int NODE2_ATTRIB_IDX = 3;
	private static final int EDGE1_ATTRIB_IDX = 4;
	private static final int EDGE2_ATTRIB_IDX = 7;
	private static final int EDGE3_ATTRIB_IDX = 10;
	private static final Point EXPECTED_NODE1_LOCATION = new Point(46, 52); 
	private static final Dimension EXPECTED_NODE1_SIZE = new Dimension(35, 35);
	private static final String EXPECTED_NODE1_SHAPE_TYPE = "curbounds oval";
	private static final RGB EXPECTED_NODE1_FILL_COLOUR = new RGB(0xff, 0x66, 0x66); 
	private static final RGB EXPECTED_NODE1_LINE_COLOUR = new RGB(0x33, 0x33, 0xff);
	private static final int EXPECTED_NODE1_LINE_WIDTH = 1; 
	private static final LineStyle EXPECTED_NODE1_LINE_STYLE = LineStyle.SOLID;
	private static final String EXPECTED_NODE1_NAME = "59524";
	private static final String EXPECTED_CANVAS_NAME = "BRCA1_MOUSE_Network";
	private static final Point EXPECTED_NODE2_LOCATION = new Point(-26, 29); 
	private static final Dimension EXPECTED_NODE2_SIZE = new Dimension(35, 35);
	private static final String EXPECTED_NODE2_SHAPE_TYPE = "curbounds oval";
	private static final RGB EXPECTED_NODE2_FILL_COLOUR = new RGB(0x66, 0x0, 0x66); 
	private static final RGB EXPECTED_NODE2_LINE_COLOUR = new RGB(0xff, 0xff, 0x0);
	private static final int EXPECTED_NODE2_LINE_WIDTH = 2; 
	private static final LineStyle EXPECTED_NODE2_LINE_STYLE = LineStyle.SOLID;
	private static final String EXPECTED_NODE2_NAME = "60178";
	private static final String EXPECTED_EDGE1_NAME = "";
	private static final RGB EXPECTED_EDGE1_LINE_COLOUR = new RGB(0x70, 0x30, 0xa0);
	private static final LineStyle EXPECTED_EDGE1_LINE_STYLE = LineStyle.SOLID;
	private static final int EXPECTED_EDGE1_LINE_WIDTH = 4;
	
	private static final String EXPECTED_EDGE2_NAME = "";
	private static final RGB EXPECTED_EDGE2_LINE_COLOUR = new RGB(0x0, 0x66, 0x0);
	private static final LineStyle EXPECTED_EDGE2_LINE_STYLE = LineStyle.DASHED;
	private static final int EXPECTED_EDGE2_LINE_WIDTH = 1;
	
	private static final String EXPECTED_EDGE3_NAME = "";
	private static final RGB EXPECTED_EDGE3_LINE_COLOUR = new RGB(0x66, 0x0, 0xff);
	private static final LineStyle EXPECTED_EDGE3_LINE_STYLE = LineStyle.SOLID;
	private static final int EXPECTED_EDGE3_LINE_WIDTH = 2;
	private static final LinkEndDecoratorShape EXPECTED_EDGE1_SOURCE_END_DEC = LinkEndDecoratorShape.NONE;
	private static final LinkEndDecoratorShape EXPECTED_EDGE1_TARGET_END_DEC = LinkEndDecoratorShape.NONE;
	private static final LinkEndDecoratorShape EXPECTED_EDGE2_SOURCE_END_DEC = LinkEndDecoratorShape.NONE;
	private static final LinkEndDecoratorShape EXPECTED_EDGE2_TARGET_END_DEC = LinkEndDecoratorShape.ARROW;
	private static final LinkEndDecoratorShape EXPECTED_EDGE3_SOURCE_END_DEC = LinkEndDecoratorShape.NONE;
	private static final LinkEndDecoratorShape EXPECTED_EDGE3_TARGET_END_DEC = LinkEndDecoratorShape.EMPTY_CIRCLE;
	private static final int EXPECTED_NUM_EDGE3_BENDPOINTS = 0;
	private static final int EXPECTED_NUM_EDGE1_BENDPOINTS = 3;
	private static final int EXPECTED_NUM_EDGE2_BENDPOINTS = 0;
	private static final int EXPECTED_EDGE1_BP1 = 0;
	private static final Point EXPECTED_EDGE1_BP1_LOCATION = new Point(98.5, 69.5);
	private static final int EXPECTED_EDGE1_BP2 = 1;
	private static final Point EXPECTED_EDGE1_BP2_LOCATION = new Point(98.5, 104.5);
	private static final int EXPECTED_EDGE1_BP3 = 2;
	private static final Point EXPECTED_EDGE1_BP3_LOCATION = new Point(63.5, 104.5);
	private static final double DELTA = 0.0001;
	
	@Before
	public void setUp() throws Exception {
		this.notationSubsystem = new CytoscapeNotationSubsystem();
		this.testInstance = new XGMMLImportService(this.notationSubsystem);
		NonPersistentCanvasFactory fact = NonPersistentCanvasFactory.getInstance();
		fact.setCanvasName(TEST_CANVAS_NAME);
		fact.setNotationSubsystem(this.notationSubsystem);
		testCanvas = fact.createNewCanvas();
	}

	@After
	public void tearDown() throws Exception {
		this.testCanvas = null;
		this.notationSubsystem = null;
		this.testInstance = null;
	}

	@Test
	public final void testGetCode() {
		assertEquals("code correct", EXPECTED_CODE, this.testInstance.getCode());
	}

	@Test
	public final void testGetDisplayName() {
		assertEquals("code correct", EXPECTED_DISPLAY_NAME, this.testInstance.getDisplayName());
	}

	@Test
	public final void testGetRecommendedSuffix() {
		assertEquals("code correct", EXPECTED_SUFFIX, this.testInstance.getRecommendedSuffix());
	}

	@Test
	public final void testImportToCanvasHasExpectedTopology() throws Exception {
		this.testInstance.importToCanvas(TEST_INPUT_FILE, testCanvas);
		assertEquals("expected num nodes", EXPECTED_NUM_NODES, testCanvas.getModel().numShapeNodes());
		assertEquals("expected num nodes", EXPECTED_NUM_EDGES, testCanvas.getModel().numLinkEdges());
		IShapeNode node1 = testCanvas.getShapeAttribute(NODE1_ATTRIB_IDX).getCurrentDrawingElement(); 
		IShapeNode node2 = testCanvas.getShapeAttribute(NODE2_ATTRIB_IDX).getCurrentDrawingElement(); 
		ILinkEdge edge1 = testCanvas.getLinkAttribute(EDGE1_ATTRIB_IDX).getCurrentDrawingElement();
		ILinkEdge edge2 = testCanvas.getLinkAttribute(EDGE2_ATTRIB_IDX).getCurrentDrawingElement();
		ILinkEdge edge3 = testCanvas.getLinkAttribute(EDGE3_ATTRIB_IDX).getCurrentDrawingElement();
		assertEquals("expected edge1 source node", node1, edge1.getSourceShape());
		assertEquals("expected edge1 target node", node1, edge1.getTargetShape());
		assertEquals("expected edge2 source node", node2, edge2.getSourceShape());
		assertEquals("expected edge2 target node", node1, edge2.getTargetShape());
		assertEquals("expected edge3 source node", node2, edge3.getSourceShape());
		assertEquals("expected edge3 target node", node1, edge3.getTargetShape());
	}

	@Test
	public final void testCorrectCanvasSettings() throws Exception{
		this.testInstance.importToCanvas(TEST_INPUT_FILE, testCanvas);
		assertEquals("expected canvas name", EXPECTED_CANVAS_NAME, this.testCanvas.getName());
	}
		
	@Test
	public final void testNode1BuildCorrectly() throws Exception{
		this.testInstance.importToCanvas(TEST_INPUT_FILE, testCanvas);
		IShapeAttribute actualAttrib = this.testCanvas.getShapeAttribute(NODE1_ATTRIB_IDX);
		assertEquals("expected location", EXPECTED_NODE1_LOCATION, actualAttrib.getLocation());
		assertEquals("expected size", EXPECTED_NODE1_SIZE, actualAttrib.getSize());
		assertEquals("expected name", EXPECTED_NODE1_NAME, actualAttrib.getProperty(CytoscapeSyntaxService.NODE_NAME_PROP).getValue().toString());
		assertEquals("expected fill colour", EXPECTED_NODE1_FILL_COLOUR, actualAttrib.getFillColour());
		assertEquals("expected line colour", EXPECTED_NODE1_LINE_COLOUR, actualAttrib.getLineColour());
		assertEquals("expected line style", EXPECTED_NODE1_LINE_STYLE, actualAttrib.getLineStyle());
		assertEquals("expected line width", EXPECTED_NODE1_LINE_WIDTH, actualAttrib.getLineWidth(), DELTA);
		assertEquals("expected shape_type", EXPECTED_NODE1_SHAPE_TYPE, actualAttrib.getShapeDefinition());
	}
	
	@Test
	public final void testNode2BuildCorrectly() throws Exception{
		this.testInstance.importToCanvas(TEST_INPUT_FILE, testCanvas);
		assertEquals("expected num nodes", EXPECTED_NUM_NODES, testCanvas.getModel().numShapeNodes());
		assertEquals("expected num nodes", EXPECTED_NUM_EDGES, testCanvas.getModel().numLinkEdges());
		IShapeAttribute actualAttrib = this.testCanvas.getShapeAttribute(NODE2_ATTRIB_IDX);
		assertEquals("expected location", EXPECTED_NODE2_LOCATION, actualAttrib.getLocation());
		assertEquals("expected size", EXPECTED_NODE2_SIZE, actualAttrib.getSize());
		assertEquals("expected name", EXPECTED_NODE2_NAME, actualAttrib.getProperty(CytoscapeSyntaxService.NODE_NAME_PROP).getValue().toString());
		assertEquals("expected fill colour", EXPECTED_NODE2_FILL_COLOUR, actualAttrib.getFillColour());
		assertEquals("expected line colour", EXPECTED_NODE2_LINE_COLOUR, actualAttrib.getLineColour());
		assertEquals("expected line style", EXPECTED_NODE2_LINE_STYLE, actualAttrib.getLineStyle());
		assertEquals("expected line width", EXPECTED_NODE2_LINE_WIDTH, actualAttrib.getLineWidth(), DELTA);
		assertEquals("expected shape_type", EXPECTED_NODE2_SHAPE_TYPE, actualAttrib.getShapeDefinition());
	}
	
	@Test
	public final void testEdge1BuildCorrectly() throws Exception{
		this.testInstance.importToCanvas(TEST_INPUT_FILE, testCanvas);
		assertEquals("expected num nodes", EXPECTED_NUM_NODES, testCanvas.getModel().numShapeNodes());
		assertEquals("expected num nodes", EXPECTED_NUM_EDGES, testCanvas.getModel().numLinkEdges());
		ILinkAttribute actualAttrib = this.testCanvas.getLinkAttribute(EDGE1_ATTRIB_IDX);
		assertEquals("expected name", EXPECTED_EDGE1_NAME, actualAttrib.getProperty(CytoscapeSyntaxService.NODE_NAME_PROP).getValue().toString());
		assertEquals("expected line colour", EXPECTED_EDGE1_LINE_COLOUR, actualAttrib.getLineColor());
		assertEquals("expected line style", EXPECTED_EDGE1_LINE_STYLE, actualAttrib.getLineStyle());
		assertEquals("expected line width", EXPECTED_EDGE1_LINE_WIDTH, actualAttrib.getLineWidth(), DELTA);
		assertEquals("expected source end dec", EXPECTED_EDGE1_SOURCE_END_DEC, actualAttrib.getSourceTerminus().getEndDecoratorType());
		assertEquals("expected target end dec", EXPECTED_EDGE1_TARGET_END_DEC, actualAttrib.getTargetTerminus().getEndDecoratorType());
		assertEquals("expected num bend points", EXPECTED_NUM_EDGE1_BENDPOINTS, actualAttrib.numBendPoints());
	}
	

	@Ignore
	@Test
	public final void testEdge1SelfEdge() throws Exception{
		this.testInstance.importToCanvas(TEST_INPUT_FILE, testCanvas);
		ILinkAttribute actualAttrib = this.testCanvas.getLinkAttribute(EDGE1_ATTRIB_IDX);
		IBendPoint actualBp1 = actualAttrib.getBendPoint(EXPECTED_EDGE1_BP1);
		assertEquals("bend point 1 location", EXPECTED_EDGE1_BP1_LOCATION, actualBp1.getLocation());
		IBendPoint actualBp2 = actualAttrib.getBendPoint(EXPECTED_EDGE1_BP2);
		assertEquals("bend point 2 location", EXPECTED_EDGE1_BP2_LOCATION, actualBp2.getLocation());
		IBendPoint actualBp3 = actualAttrib.getBendPoint(EXPECTED_EDGE1_BP3);
		assertEquals("bend point 3 location", EXPECTED_EDGE1_BP3_LOCATION, actualBp3.getLocation());
	}
	
	@Test
	public final void testEdge2BuildCorrectly() throws Exception{
		this.testInstance.importToCanvas(TEST_INPUT_FILE, testCanvas);
		assertEquals("expected num nodes", EXPECTED_NUM_NODES, testCanvas.getModel().numShapeNodes());
		assertEquals("expected num nodes", EXPECTED_NUM_EDGES, testCanvas.getModel().numLinkEdges());
		ILinkAttribute actualAttrib = this.testCanvas.getLinkAttribute(EDGE2_ATTRIB_IDX);
		assertEquals("expected name", EXPECTED_EDGE2_NAME, actualAttrib.getProperty(CytoscapeSyntaxService.NODE_NAME_PROP).getValue().toString());
		assertEquals("expected line colour", EXPECTED_EDGE2_LINE_COLOUR, actualAttrib.getLineColor());
		assertEquals("expected line style", EXPECTED_EDGE2_LINE_STYLE, actualAttrib.getLineStyle());
		assertEquals("expected line width", EXPECTED_EDGE2_LINE_WIDTH, actualAttrib.getLineWidth(), DELTA);
		assertEquals("expected source end dec", EXPECTED_EDGE2_SOURCE_END_DEC, actualAttrib.getSourceTerminus().getEndDecoratorType());
		assertEquals("expected target end dec", EXPECTED_EDGE2_TARGET_END_DEC, actualAttrib.getTargetTerminus().getEndDecoratorType());
		assertEquals("expected num bend points", EXPECTED_NUM_EDGE2_BENDPOINTS, actualAttrib.numBendPoints());
	}
	
	@Test
	public final void testEdge3BuildCorrectly() throws Exception{
		this.testInstance.importToCanvas(TEST_INPUT_FILE, testCanvas);
		assertEquals("expected num nodes", EXPECTED_NUM_NODES, testCanvas.getModel().numShapeNodes());
		assertEquals("expected num nodes", EXPECTED_NUM_EDGES, testCanvas.getModel().numLinkEdges());
		ILinkAttribute actualAttrib = this.testCanvas.getLinkAttribute(EDGE3_ATTRIB_IDX);
		assertEquals("expected name", EXPECTED_EDGE3_NAME, actualAttrib.getProperty(CytoscapeSyntaxService.NODE_NAME_PROP).getValue().toString());
		assertEquals("expected line colour", EXPECTED_EDGE3_LINE_COLOUR, actualAttrib.getLineColor());
		assertEquals("expected line style", EXPECTED_EDGE3_LINE_STYLE, actualAttrib.getLineStyle());
		assertEquals("expected line width", EXPECTED_EDGE3_LINE_WIDTH, actualAttrib.getLineWidth(), DELTA);
		assertEquals("expected source end dec", EXPECTED_EDGE3_SOURCE_END_DEC, actualAttrib.getSourceTerminus().getEndDecoratorType());
		assertEquals("expected target end dec", EXPECTED_EDGE3_TARGET_END_DEC, actualAttrib.getTargetTerminus().getEndDecoratorType());
		assertEquals("expected num bend points", EXPECTED_NUM_EDGE3_BENDPOINTS, actualAttrib.numBendPoints());
	}
	
}
