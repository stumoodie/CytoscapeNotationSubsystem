package org.pathwayeditor.notations.cytoscape.importservice;


import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.figure.geometry.Dimension;

@RunWith(JMock.class)
public class DogLegBuilderTest {
	private static final Point NODE_LOCATION = new Point(1, -1);
	private static final Dimension NODE_SIZE = new Dimension(11, 21);
	private static final Point BP1_POSITION = new Point(-4, -11);
	private static final Point BP1_SRC_OFFSET = new Point(-5, 0);
	private static final Point BP1_TGT_OFFSET = new Point(-10, -10);
	private static final Point BP2_POSITION = new Point(-4, 9);
	private static final Point BP2_SRC_OFFSET = new Point(-5, 20);
	private static final Point BP2_TGT_OFFSET = new Point(-10, 10);
	private static final Point BP3_POSITION = new Point(6, 9);
	private static final Point BP3_SRC_OFFSET = new Point(5, 20);
	private static final Point BP3_TGT_OFFSET = new Point(0, 10);

	private final Mockery mockery = new JUnit4Mockery();
	
	private DogLegBuilder testInstance;
	private ILinkEdge mockLinkEdge;
	private ILinkAttribute mockLinkAttribute;
	private IShapeNode mockSourceShape;
	private IShapeAttribute mockSourceAttribute;
	
	@Before
	public void setUp() throws Exception {
		this.mockLinkEdge = this.mockery.mock(ILinkEdge.class, "mockLinkEdge");
		this.mockLinkAttribute = this.mockery.mock(ILinkAttribute.class, "mockLinkAttribute");
		this.mockSourceShape = this.mockery.mock(IShapeNode.class, "mockSourceShape");
		this.mockSourceAttribute = this.mockery.mock(IShapeAttribute.class, "mockSourceAttribute");
		
		this.mockery.checking(new Expectations(){{
			allowing(mockLinkEdge).getSourceShape(); will(returnValue(mockSourceShape));
			allowing(mockLinkEdge).getAttribute(); will(returnValue(mockLinkAttribute));
			
			allowing(mockSourceShape).getAttribute(); will(returnValue(mockSourceAttribute));
			
			allowing(mockSourceAttribute).getLocation(); will(returnValue(NODE_LOCATION));
			allowing(mockSourceAttribute).getSize(); will(returnValue(NODE_SIZE));
		}});
		this.testInstance = new DogLegBuilder(this.mockLinkEdge);
	}

	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
	}

	@Test
	public final void testDogLegCreation() {
		this.mockery.checking(new Expectations(){{
			exactly(1).of(mockLinkAttribute).createNewBendPoint(with(equal(BP1_POSITION)), with(equal(BP1_SRC_OFFSET)), with(equal(BP1_TGT_OFFSET)));
			exactly(1).of(mockLinkAttribute).createNewBendPoint(with(equal(BP2_POSITION)), with(equal(BP2_SRC_OFFSET)), with(equal(BP2_TGT_OFFSET)));
			exactly(1).of(mockLinkAttribute).createNewBendPoint(with(equal(BP3_POSITION)), with(equal(BP3_SRC_OFFSET)), with(equal(BP3_TGT_OFFSET)));
		}});
		
		this.testInstance.buildDogLeg();
	}
	
}
