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
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;

@RunWith(JMock.class)
public class DogLegBuilderTest {
	private static final Location NODE_LOCATION = new Location(1, -1);
	private static final Size NODE_SIZE = new Size(11, 21);
	private static final Location BP1_POSITION = new Location(-4, -11);
	private static final Location BP1_SRC_OFFSET = new Location(-5, 0);
	private static final Location BP1_TGT_OFFSET = new Location(-10, -10);
	private static final Location BP2_POSITION = new Location(-4, 9);
	private static final Location BP2_SRC_OFFSET = new Location(-5, 20);
	private static final Location BP2_TGT_OFFSET = new Location(-10, 10);
	private static final Location BP3_POSITION = new Location(6, 9);
	private static final Location BP3_SRC_OFFSET = new Location(5, 20);
	private static final Location BP3_TGT_OFFSET = new Location(0, 10);

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
