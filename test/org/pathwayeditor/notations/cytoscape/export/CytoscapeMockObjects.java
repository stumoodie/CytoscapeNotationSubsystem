package org.pathwayeditor.notations.cytoscape.export;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;


public class CytoscapeMockObjects {
	private static final String NODE1_NAME = "node1";
	private static final Location NODE1_LOCATION = new Location(10, 11);
	private static final Size NODE1_SIZE = new Size(12, 13);
	private static final int NODE1_WIDTH = 1;
	private static final PrimitiveShapeType NODE1_SHAPE_TYPE = PrimitiveShapeType.ELLIPSE;
	private static final String NODE1_DESCRIPTION = "Node1 Descn";
	private static final RGB NODE1_LINE_COLOUR = new RGB(255, 0, 0);
	private static final RGB NODE1_FILL_COLOUR = new RGB(0, 0, 0);
	private static final String NODE2_NAME = "node2";
	private static final Location NODE2_LOCATION = new Location(20, 21);
	private static final Size NODE2_SIZE = new Size(13, 15);
	private static final int NODE2_WIDTH = 2;
	private static final PrimitiveShapeType NODE2_SHAPE_TYPE = PrimitiveShapeType.HEXAGON;
	private static final String NODE2_DESCRIPTION = "Node2 Descn";
	private static final RGB NODE2_LINE_COLOUR = new RGB(0, 255, 0);
	private static final RGB NODE2_FILL_COLOUR = new RGB(25, 25, 25);
	private static final String NODE3_NAME = "node3";
	private static final Location NODE3_LOCATION = new Location(30, 31);
	private static final Size NODE3_SIZE = new Size(17, 16);
	private static final int NODE3_WIDTH = 3;
	private static final PrimitiveShapeType NODE3_SHAPE_TYPE = PrimitiveShapeType.UP_CHEVRON;
	private static final String NODE3_DESCRIPTION = "Node3 Descn";
	private static final RGB NODE3_LINE_COLOUR = new RGB(0, 0, 255);
	private static final RGB NODE3_FILL_COLOUR = new RGB(125, 125, 125);
	private static final int NODE1_IDX = 1; 
	private static final int NODE2_IDX = 2; 
	private static final int NODE3_IDX = 3; 
	private static final String EDGE1_NAME = "edge1";
	private static final String EDGE2_NAME = "edge2";
	private static final String EDGE3_NAME = "edge3";
	private static final int EDGE1_IDX = 1; 
	private static final int EDGE2_IDX = 2; 
	private static final int EDGE3_IDX = 3; 
	private static final String EDGE1_DESCRIPTION = "Edge1 Descn";
	private static final LineStyle EDGE1_LINE_STYLE = LineStyle.DASH_DOT;
	private static final RGB EDGE1_LINE_COLOUR = RGB.GREEN;
	private static final int EDGE1_LINE_WIDTH = 2;
	private static final LinkEndDecoratorShape EDGE1_SOURCE_DEC_TYPE = LinkEndDecoratorShape.ARROW;
	private static final LinkEndDecoratorShape EDGE1_TARGET_DEC_TYPE = LinkEndDecoratorShape.DIAMOND;
	private static final String EDGE2_DESCRIPTION = "Edge2 Descn";
	private static final LineStyle EDGE2_LINE_STYLE = LineStyle.SOLID;
	private static final RGB EDGE2_LINE_COLOUR = RGB.BLACK;
	private static final int EDGE2_LINE_WIDTH = 1;
	private static final LinkEndDecoratorShape EDGE2_SOURCE_DEC_TYPE = LinkEndDecoratorShape.NONE;
	private static final LinkEndDecoratorShape EDGE2_TARGET_DEC_TYPE = LinkEndDecoratorShape.EMPTY_TRIANGLE;
	private static final String EDGE3_DESCRIPTION = "Edge3 Descn";
	private static final LineStyle EDGE3_LINE_STYLE = LineStyle.DASH_DOT_DOT;
	private static final RGB EDGE3_LINE_COLOUR = RGB.RED;
	private static final int EDGE3_LINE_WIDTH = 3;
	private static final LinkEndDecoratorShape EDGE3_SOURCE_DEC_TYPE = LinkEndDecoratorShape.EMPTY_CIRCLE;
	private static final LinkEndDecoratorShape EDGE3_TARGET_DEC_TYPE = LinkEndDecoratorShape.NONE;
//	private static final String EXPECTED_MAP_NAME = "TestExportMap";

	private final Mockery mockery;
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
	private ILinkTerminus mockLink1Source;
	private ILinkTerminus mockLink1Target;
	private ILinkEdge mockLinkEdge2;
	private ILinkAttribute mockLinkAttribute2;
	private ILinkTerminus mockLink2Source;
	private ILinkTerminus mockLink2Target;
	private ILinkEdge mockLinkEdge3;
	private ILinkAttribute mockLinkAttribute3;
	private ILinkTerminus mockLink3Source;
	private ILinkTerminus mockLink3Target;

	public CytoscapeMockObjects(Mockery mockery, final String expectedMapName){
//		this.expectedMapName = expectedMapName;
		this.mockery = mockery;
		this.contextService = mockery.mock(INotationSubsystem.class);
		this.mockCanvas = mockery.mock(ICanvas.class, "mockCanvas");
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
		this.mockLink1Source = mockery.mock(ILinkTerminus.class, "mockLink1Source");
		this.mockLink1Target = mockery.mock(ILinkTerminus.class, "mockLink1Target");
		this.mockLink2Source = mockery.mock(ILinkTerminus.class, "mockLink2Source");
		this.mockLink2Target = mockery.mock(ILinkTerminus.class, "mockLink2Target");
		this.mockLink3Source = mockery.mock(ILinkTerminus.class, "mockLink3Source");
		this.mockLink3Target = mockery.mock(ILinkTerminus.class, "mockLink3Target");
		mockery.checking(new Expectations() {{
			ignoring(contextService).getNotation();
			
			allowing(mockCanvas).getModel(); will(returnValue(mockModel));
			allowing(mockCanvas).getName(); will(returnValue(expectedMapName));

			allowing(mockModel).shapeNodeIterator(); will(returnIterator(mockShapeNode1, mockShapeNode2, mockShapeNode3));
			allowing(mockModel).linkEdgeIterator(); will(returnIterator(mockLinkEdge1, mockLinkEdge2, mockLinkEdge3));
			
			allowing(mockShapeNode1).getAttribute(); will(returnValue(mockShapeAttribute1));
			allowing(mockShapeNode1).getIndex(); will(returnValue(NODE1_IDX));
			
			allowing(mockShapeNode2).getAttribute(); will(returnValue(mockShapeAttribute2));
			allowing(mockShapeNode2).getIndex(); will(returnValue(NODE2_IDX));
			
			allowing(mockShapeNode3).getAttribute(); will(returnValue(mockShapeAttribute3));
			allowing(mockShapeNode3).getIndex(); will(returnValue(NODE3_IDX));
			
			allowing(mockShapeAttribute1).getName(); will(returnValue(NODE1_NAME));
			allowing(mockShapeAttribute1).getLocation(); will(returnValue(NODE1_LOCATION));
			allowing(mockShapeAttribute1).getSize(); will(returnValue(NODE1_SIZE));
			allowing(mockShapeAttribute1).getLineWidth(); will(returnValue(NODE1_WIDTH));
			allowing(mockShapeAttribute1).getPrimitiveShape(); will(returnValue(NODE1_SHAPE_TYPE));
			allowing(mockShapeAttribute1).getDescription(); will(returnValue(NODE1_DESCRIPTION));
			allowing(mockShapeAttribute1).getLineColour(); will(returnValue(NODE1_LINE_COLOUR));
			allowing(mockShapeAttribute1).getFillColour(); will(returnValue(NODE1_FILL_COLOUR));
			
			allowing(mockShapeAttribute2).getName(); will(returnValue(NODE2_NAME));
			allowing(mockShapeAttribute2).getLocation(); will(returnValue(NODE2_LOCATION));
			allowing(mockShapeAttribute2).getSize(); will(returnValue(NODE2_SIZE));
			allowing(mockShapeAttribute2).getLineWidth(); will(returnValue(NODE2_WIDTH));
			allowing(mockShapeAttribute2).getPrimitiveShape(); will(returnValue(NODE2_SHAPE_TYPE));
			allowing(mockShapeAttribute2).getDescription(); will(returnValue(NODE2_DESCRIPTION));
			allowing(mockShapeAttribute2).getLineColour(); will(returnValue(NODE2_LINE_COLOUR));
			allowing(mockShapeAttribute2).getFillColour(); will(returnValue(NODE2_FILL_COLOUR));
			
			allowing(mockShapeAttribute3).getName(); will(returnValue(NODE3_NAME));
			allowing(mockShapeAttribute3).getLocation(); will(returnValue(NODE3_LOCATION));
			allowing(mockShapeAttribute3).getSize(); will(returnValue(NODE3_SIZE));
			allowing(mockShapeAttribute3).getLineWidth(); will(returnValue(NODE3_WIDTH));
			allowing(mockShapeAttribute3).getPrimitiveShape(); will(returnValue(NODE3_SHAPE_TYPE));
			allowing(mockShapeAttribute3).getDescription(); will(returnValue(NODE3_DESCRIPTION));
			allowing(mockShapeAttribute3).getLineColour(); will(returnValue(NODE3_LINE_COLOUR));
			allowing(mockShapeAttribute3).getFillColour(); will(returnValue(NODE3_FILL_COLOUR));

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
			allowing(mockLinkAttribute1).getDescription(); will(returnValue(EDGE1_DESCRIPTION));
			allowing(mockLinkAttribute1).getLineStyle(); will(returnValue(EDGE1_LINE_STYLE));
			allowing(mockLinkAttribute1).getLineColor(); will(returnValue(EDGE1_LINE_COLOUR));
			allowing(mockLinkAttribute1).getLineWidth(); will(returnValue(EDGE1_LINE_WIDTH));
			allowing(mockLinkAttribute1).getSourceTerminus(); will(returnValue(mockLink1Source));
			allowing(mockLinkAttribute1).getTargetTerminus(); will(returnValue(mockLink1Target));

			allowing(mockLink1Source).getEndDecoratorType(); will(returnValue(EDGE1_SOURCE_DEC_TYPE));
			
			allowing(mockLink1Target).getEndDecoratorType(); will(returnValue(EDGE1_TARGET_DEC_TYPE));
			
			allowing(mockLinkAttribute2).getName(); will(returnValue(EDGE2_NAME));
			allowing(mockLinkAttribute2).getDescription(); will(returnValue(EDGE2_DESCRIPTION));
			allowing(mockLinkAttribute2).getLineStyle(); will(returnValue(EDGE2_LINE_STYLE));
			allowing(mockLinkAttribute2).getLineColor(); will(returnValue(EDGE2_LINE_COLOUR));
			allowing(mockLinkAttribute2).getLineWidth(); will(returnValue(EDGE2_LINE_WIDTH));
			allowing(mockLinkAttribute2).getSourceTerminus(); will(returnValue(mockLink2Source));
			allowing(mockLinkAttribute2).getTargetTerminus(); will(returnValue(mockLink2Target));

			allowing(mockLink2Source).getEndDecoratorType(); will(returnValue(EDGE2_SOURCE_DEC_TYPE));
			
			allowing(mockLink2Target).getEndDecoratorType(); will(returnValue(EDGE2_TARGET_DEC_TYPE));
			
			allowing(mockLinkAttribute3).getName(); will(returnValue(EDGE3_NAME));
			allowing(mockLinkAttribute3).getDescription(); will(returnValue(EDGE3_DESCRIPTION));
			allowing(mockLinkAttribute3).getLineStyle(); will(returnValue(EDGE3_LINE_STYLE));
			allowing(mockLinkAttribute3).getLineColor(); will(returnValue(EDGE3_LINE_COLOUR));
			allowing(mockLinkAttribute3).getLineWidth(); will(returnValue(EDGE3_LINE_WIDTH));
			allowing(mockLinkAttribute3).getSourceTerminus(); will(returnValue(mockLink3Source));
			allowing(mockLinkAttribute3).getTargetTerminus(); will(returnValue(mockLink3Target));

			allowing(mockLink3Source).getEndDecoratorType(); will(returnValue(EDGE3_SOURCE_DEC_TYPE));
			
			allowing(mockLink3Target).getEndDecoratorType(); will(returnValue(EDGE3_TARGET_DEC_TYPE));
			
		}});
	}


	public ICanvas getCanvas(){
		return this.mockCanvas;
	}
	
	public Mockery getMockery(){
		return this.mockery;
	}


	public INotationSubsystem getNotationSubsystem() {
		return this.contextService;
	}
}
	
