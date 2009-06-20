package org.pathwayeditor.notations.cytoscape.importservice;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Alignment;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.management.NonPersistentCanvasFactory;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.notations.cytoscape.CytoscapeNotationSubsystem;

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
	private static final int NODE1_ATTRIB_IDX = 1;
	private static final int NODE2_ATTRIB_IDX = 2;
	private static final int EDGE1_ATTRIB_IDX = 1;
	private static final int EDGE2_ATTRIB_IDX = 2;
	private static final int EDGE3_ATTRIB_IDX = 3;
	private static final Location EXPECTED_NODE1_LOCATION = new Location(46, 52); 
	private static final Size EXPECTED_NODE1_SIZE = new Size(35, 35);
	private static final PrimitiveShapeType EXPECTED_NODE1_SHAPE_TYPE = PrimitiveShapeType.OCTAGON;
	private static final RGB EXPECTED_NODE1_FILL_COLOUR = new RGB(0xff, 0x66, 0x66); 
	private static final RGB EXPECTED_NODE1_LINE_COLOUR = new RGB(0x33, 0x33, 0xff);
	private static final int EXPECTED_NODE1_LINE_WIDTH = 1; 
	private static final LineStyle EXPECTED_NODE1_LINE_STYLE = LineStyle.SOLID;
	private static final String EXPECTED_NODE1_NAME = "59524";
	private static final String EXPECTED_CANVAS_NAME = "BRCA1_MOUSE_Network";
	private static final Alignment EXPECTED_NODE1_VERT_ALIGNMENT = Alignment.CENTER;
	private static final String EXPECTED_NODE1_DETAILED_DESCN = "";
	private static final String EXPECTED_NODE1_DESCN = "";
	private static final boolean EXPECTED_NODE1_NAME_VISIBILITY = true;
	private static final RGB EXPECTED_NODE1_TEXT_COLOUR = new RGB(0, 0, 0);
	private static final Alignment EXPECTED_NODE1_HORIZ_ALIGNMENT = Alignment.CENTER;
	private static final Location EXPECTED_NODE2_LOCATION = new Location(-26, 29); 
	private static final Size EXPECTED_NODE2_SIZE = new Size(35, 35);
	private static final PrimitiveShapeType EXPECTED_NODE2_SHAPE_TYPE = PrimitiveShapeType.ELLIPSE;
	private static final RGB EXPECTED_NODE2_FILL_COLOUR = new RGB(0x66, 0x0, 0x66); 
	private static final RGB EXPECTED_NODE2_LINE_COLOUR = new RGB(0xff, 0xff, 0x0);
	private static final int EXPECTED_NODE2_LINE_WIDTH = 2; 
	private static final LineStyle EXPECTED_NODE2_LINE_STYLE = LineStyle.SOLID;
	private static final String EXPECTED_NODE2_NAME = "60178";
	private static final Alignment EXPECTED_NODE2_VERT_ALIGNMENT = Alignment.CENTER;
	private static final String EXPECTED_NODE2_DETAILED_DESCN = "";
	private static final String EXPECTED_NODE2_DESCN = "";
	private static final boolean EXPECTED_NODE2_NAME_VISIBILITY = true;
	private static final RGB EXPECTED_NODE2_TEXT_COLOUR = new RGB(0, 0, 0);
	private static final Alignment EXPECTED_NODE2_HORIZ_ALIGNMENT = Alignment.CENTER;
	private static final String EXPECTED_EDGE1_NAME = "";
	private static final RGB EXPECTED_EDGE1_LINE_COLOUR = new RGB(0x70, 0x30, 0xa0);
	private static final LineStyle EXPECTED_EDGE1_LINE_STYLE = LineStyle.SOLID;
	private static final int EXPECTED_EDGE1_LINE_WIDTH = 4;
	private static final String EXPECTED_EDGE1_DESCN = "";
	private static final String EXPECTED_EDGE1_DETAILED_DESCN = "";
	
	private static final String EXPECTED_EDGE2_NAME = "";
	private static final RGB EXPECTED_EDGE2_LINE_COLOUR = new RGB(0x0, 0x66, 0x0);
	private static final LineStyle EXPECTED_EDGE2_LINE_STYLE = LineStyle.DASHED;
	private static final int EXPECTED_EDGE2_LINE_WIDTH = 1;
	private static final String EXPECTED_EDGE2_DESCN = "";
	private static final String EXPECTED_EDGE2_DETAILED_DESCN = "";
	
	private static final String EXPECTED_EDGE3_NAME = "";
	private static final RGB EXPECTED_EDGE3_LINE_COLOUR = new RGB(0x66, 0x0, 0xff);
	private static final LineStyle EXPECTED_EDGE3_LINE_STYLE = LineStyle.SOLID;
	private static final int EXPECTED_EDGE3_LINE_WIDTH = 2;
	private static final String EXPECTED_EDGE3_DESCN = "";
	private static final String EXPECTED_EDGE3_DETAILED_DESCN = "";
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
	private static final Location EXPECTED_EDGE1_BP1_LOCATION = new Location(29, 35);
	private static final Location EXPECTED_EDGE1_BP1_OFFSET1 = new Location(-17, 0);
	private static final Location EXPECTED_EDGE1_BP1_OFFSET2 = new Location(-34, -17);
	private static final int EXPECTED_EDGE1_BP2 = 1;
	private static final Location EXPECTED_EDGE1_BP2_LOCATION = new Location(29, 69);
	private static final Location EXPECTED_EDGE1_BP2_OFFSET1 = new Location(-17, 34);
	private static final Location EXPECTED_EDGE1_BP2_OFFSET2 = new Location(-34, 17);
	private static final int EXPECTED_EDGE1_BP3 = 2;
	private static final Location EXPECTED_EDGE1_BP3_LOCATION = new Location(63, 69);
	private static final Location EXPECTED_EDGE1_BP3_OFFSET1 = new Location(17, 34);
	private static final Location EXPECTED_EDGE1_BP3_OFFSET2 = new Location(0, 17);
	
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
		assertEquals("expected name", EXPECTED_NODE1_NAME, actualAttrib.getName());
		assertEquals("expected fill colour", EXPECTED_NODE1_FILL_COLOUR, actualAttrib.getFillColour());
		assertEquals("expected line colour", EXPECTED_NODE1_LINE_COLOUR, actualAttrib.getLineColour());
		assertEquals("expected line style", EXPECTED_NODE1_LINE_STYLE, actualAttrib.getLineStyle());
		assertEquals("expected line width", EXPECTED_NODE1_LINE_WIDTH, actualAttrib.getLineWidth());
		assertEquals("expected name horizontal alignment", EXPECTED_NODE1_HORIZ_ALIGNMENT, actualAttrib.getHorizontalAlignment());
		assertEquals("expected name vertical alignment", EXPECTED_NODE1_VERT_ALIGNMENT, actualAttrib.getVerticalAlignment());
		assertEquals("expected name visibility", EXPECTED_NODE1_NAME_VISIBILITY, actualAttrib.isNameVisible());
		assertEquals("expected detailed description", EXPECTED_NODE1_DESCN, actualAttrib.getDescription());
		assertEquals("expected detailed description", EXPECTED_NODE1_DETAILED_DESCN, actualAttrib.getDetailedDescription());
		assertEquals("expected text colour", EXPECTED_NODE1_TEXT_COLOUR, actualAttrib.getTextColour());
		assertEquals("expected shape_type", EXPECTED_NODE1_SHAPE_TYPE, actualAttrib.getPrimitiveShape());
	}
	
	@Test
	public final void testNode2BuildCorrectly() throws Exception{
		this.testInstance.importToCanvas(TEST_INPUT_FILE, testCanvas);
		assertEquals("expected num nodes", EXPECTED_NUM_NODES, testCanvas.getModel().numShapeNodes());
		assertEquals("expected num nodes", EXPECTED_NUM_EDGES, testCanvas.getModel().numLinkEdges());
		IShapeAttribute actualAttrib = this.testCanvas.getShapeAttribute(NODE2_ATTRIB_IDX);
		assertEquals("expected location", EXPECTED_NODE2_LOCATION, actualAttrib.getLocation());
		assertEquals("expected size", EXPECTED_NODE2_SIZE, actualAttrib.getSize());
		assertEquals("expected name", EXPECTED_NODE2_NAME, actualAttrib.getName());
		assertEquals("expected fill colour", EXPECTED_NODE2_FILL_COLOUR, actualAttrib.getFillColour());
		assertEquals("expected line colour", EXPECTED_NODE2_LINE_COLOUR, actualAttrib.getLineColour());
		assertEquals("expected line style", EXPECTED_NODE2_LINE_STYLE, actualAttrib.getLineStyle());
		assertEquals("expected line width", EXPECTED_NODE2_LINE_WIDTH, actualAttrib.getLineWidth());
		assertEquals("expected name horizontal alignment", EXPECTED_NODE2_HORIZ_ALIGNMENT, actualAttrib.getHorizontalAlignment());
		assertEquals("expected name vertical alignment", EXPECTED_NODE2_VERT_ALIGNMENT, actualAttrib.getVerticalAlignment());
		assertEquals("expected name visibility", EXPECTED_NODE2_NAME_VISIBILITY, actualAttrib.isNameVisible());
		assertEquals("expected description", EXPECTED_NODE2_DESCN, actualAttrib.getDescription());
		assertEquals("expected detailed description", EXPECTED_NODE2_DETAILED_DESCN, actualAttrib.getDetailedDescription());
		assertEquals("expected text colour", EXPECTED_NODE2_TEXT_COLOUR, actualAttrib.getTextColour());
		assertEquals("expected shape_type", EXPECTED_NODE2_SHAPE_TYPE, actualAttrib.getPrimitiveShape());
	}
	
	@Test
	public final void testEdge1BuildCorrectly() throws Exception{
		this.testInstance.importToCanvas(TEST_INPUT_FILE, testCanvas);
		assertEquals("expected num nodes", EXPECTED_NUM_NODES, testCanvas.getModel().numShapeNodes());
		assertEquals("expected num nodes", EXPECTED_NUM_EDGES, testCanvas.getModel().numLinkEdges());
		ILinkAttribute actualAttrib = this.testCanvas.getLinkAttribute(EDGE1_ATTRIB_IDX);
		assertEquals("expected name", EXPECTED_EDGE1_NAME, actualAttrib.getName());
		assertEquals("expected line colour", EXPECTED_EDGE1_LINE_COLOUR, actualAttrib.getLineColor());
		assertEquals("expected line style", EXPECTED_EDGE1_LINE_STYLE, actualAttrib.getLineStyle());
		assertEquals("expected line width", EXPECTED_EDGE1_LINE_WIDTH, actualAttrib.getLineWidth());
		assertEquals("expected description", EXPECTED_EDGE1_DESCN, actualAttrib.getDescription());
		assertEquals("expected detailed description", EXPECTED_EDGE1_DETAILED_DESCN, actualAttrib.getDetailedDescription());
		assertEquals("expected source end dec", EXPECTED_EDGE1_SOURCE_END_DEC, actualAttrib.getSourceTerminus().getEndDecoratorType());
		assertEquals("expected target end dec", EXPECTED_EDGE1_TARGET_END_DEC, actualAttrib.getTargetTerminus().getEndDecoratorType());
		assertEquals("expected num bend points", EXPECTED_NUM_EDGE1_BENDPOINTS, actualAttrib.numBendPoints());
		IBendPoint actualBp1 = actualAttrib.getBendPoint(EXPECTED_EDGE1_BP1);
		assertEquals("bend point 1 location", EXPECTED_EDGE1_BP1_LOCATION, actualBp1.getLocation());
		assertEquals("bend point 1 source offset", EXPECTED_EDGE1_BP1_OFFSET1, actualBp1.getFirstRelativeDimension());
		assertEquals("bend point 1 target offset", EXPECTED_EDGE1_BP1_OFFSET2, actualBp1.getSecondRelativeDimension());
		IBendPoint actualBp2 = actualAttrib.getBendPoint(EXPECTED_EDGE1_BP2);
		assertEquals("bend point 2 location", EXPECTED_EDGE1_BP2_LOCATION, actualBp2.getLocation());
		assertEquals("bend point 2 source offset", EXPECTED_EDGE1_BP2_OFFSET1, actualBp2.getFirstRelativeDimension());
		assertEquals("bend point 2 target offset", EXPECTED_EDGE1_BP2_OFFSET2, actualBp2.getSecondRelativeDimension());
		IBendPoint actualBp3 = actualAttrib.getBendPoint(EXPECTED_EDGE1_BP3);
		assertEquals("bend point 3 location", EXPECTED_EDGE1_BP3_LOCATION, actualBp3.getLocation());
		assertEquals("bend point 3 source offset", EXPECTED_EDGE1_BP3_OFFSET1, actualBp3.getFirstRelativeDimension());
		assertEquals("bend point 3 target offset", EXPECTED_EDGE1_BP3_OFFSET2, actualBp3.getSecondRelativeDimension());
	}
	
	@Test
	public final void testEdge2BuildCorrectly() throws Exception{
		this.testInstance.importToCanvas(TEST_INPUT_FILE, testCanvas);
		assertEquals("expected num nodes", EXPECTED_NUM_NODES, testCanvas.getModel().numShapeNodes());
		assertEquals("expected num nodes", EXPECTED_NUM_EDGES, testCanvas.getModel().numLinkEdges());
		ILinkAttribute actualAttrib = this.testCanvas.getLinkAttribute(EDGE2_ATTRIB_IDX);
		assertEquals("expected name", EXPECTED_EDGE2_NAME, actualAttrib.getName());
		assertEquals("expected line colour", EXPECTED_EDGE2_LINE_COLOUR, actualAttrib.getLineColor());
		assertEquals("expected line style", EXPECTED_EDGE2_LINE_STYLE, actualAttrib.getLineStyle());
		assertEquals("expected line width", EXPECTED_EDGE2_LINE_WIDTH, actualAttrib.getLineWidth());
		assertEquals("expected description", EXPECTED_EDGE2_DESCN, actualAttrib.getDescription());
		assertEquals("expected detailed description", EXPECTED_EDGE2_DETAILED_DESCN, actualAttrib.getDetailedDescription());
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
		assertEquals("expected name", EXPECTED_EDGE3_NAME, actualAttrib.getName());
		assertEquals("expected line colour", EXPECTED_EDGE3_LINE_COLOUR, actualAttrib.getLineColor());
		assertEquals("expected line style", EXPECTED_EDGE3_LINE_STYLE, actualAttrib.getLineStyle());
		assertEquals("expected line width", EXPECTED_EDGE3_LINE_WIDTH, actualAttrib.getLineWidth());
		assertEquals("expected detailed description", EXPECTED_EDGE3_DESCN, actualAttrib.getDescription());
		assertEquals("expected detailed description", EXPECTED_EDGE3_DETAILED_DESCN, actualAttrib.getDetailedDescription());
		assertEquals("expected source end dec", EXPECTED_EDGE3_SOURCE_END_DEC, actualAttrib.getSourceTerminus().getEndDecoratorType());
		assertEquals("expected target end dec", EXPECTED_EDGE3_TARGET_END_DEC, actualAttrib.getTargetTerminus().getEndDecoratorType());
		assertEquals("expected num bend points", EXPECTED_NUM_EDGE3_BENDPOINTS, actualAttrib.numBendPoints());
	}
	
}
