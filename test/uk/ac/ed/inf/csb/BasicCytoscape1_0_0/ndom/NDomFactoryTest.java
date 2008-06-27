package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjectsAPI.IMapObject;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.stubs.LabelStub;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.stubs.LinkStub;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.stubs.RootStub;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.stubs.ShapeStub;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IEdge;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IGraph;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.INode;

public class NDomFactoryTest {
	List <IMapObject> testShapes;
	RootStub root;
	private NDOMFactory factory;

	@Before
	public void setUp(){
		testShapes = new ArrayList<IMapObject>();
		root = new RootStub();
		root.children=testShapes;
		factory = new NDOMFactory(root){
			@Override
			public IGraph makeGraph() {
				return new GraphStub();
			}
		};
	}
	@Test
	public void testRMOcreatesOneNodeWhenOnlyOnePresentOnMap(){
		testShapes.add(new ShapeStub("Dave"));
		factory.ndom();
		factory.rmo();
		assertEquals(factory.getGraph().getNodes().iterator().next().getName(),"Dave");
	}

	@Test
	public void testRMOcreatesTwoNodesAndLinksThemLinkHavingLabel(){
		ShapeStub dave = new ShapeStub("Dave");
		testShapes.add(dave);
		ShapeStub bob = new ShapeStub("Bob");
		testShapes.add(bob);
		LinkStub link = new LinkStub();
		link.addLabel(new LabelStub("swiftly"));
		testShapes.add(link);
		link.reconnect(dave, bob);
		factory.ndom();
		factory.rmo();
		List <INode>nodes = factory.getGraph().getNodes();
		Set<IEdge>edges = factory.getGraph().getEdges();
		assertEquals(nodes.get(0).getName(),"Dave");
		assertEquals(nodes.get(1).getName(),"Bob");
		assertEquals(1,nodes.get(0).getNumberOfConnections());
		assertEquals(1,nodes.get(1).getNumberOfConnections());
		nodes.get(0).getSrcEdges().iterator().next().equals(edges.iterator().next());
		nodes.get(1).getTargetEdges().iterator().next().equals(edges.iterator().next());
	}
	
	@Test
	public void testRMOcreatesTwoNodesAndLinksThemLinkHavingNoLabel(){
		ShapeStub dave = new ShapeStub("Dave");
		testShapes.add(dave);
		ShapeStub bob = new ShapeStub("Bob");
		testShapes.add(bob);
		LinkStub link = new LinkStub();
		testShapes.add(link);
		link.reconnect(dave, bob);
		factory.ndom();
		factory.rmo();
		List <INode>nodes = factory.getGraph().getNodes();
		Set<IEdge>edges = factory.getGraph().getEdges();
		assertEquals(nodes.get(0).getName(),"Dave");
		assertEquals(nodes.get(1).getName(),"Bob");
		assertEquals(1,nodes.get(0).getNumberOfConnections());
		assertEquals(1,nodes.get(1).getNumberOfConnections());
		nodes.get(0).getSrcEdges().iterator().next().equals(edges.iterator().next());
		nodes.get(1).getTargetEdges().iterator().next().equals(edges.iterator().next());
	}
	
	
	@Test
	public void testNdomSetsUpGraph(){
		assertNull(factory.getGraph());
		factory.ndom();
		assertNotNull(factory.getGraph());
	}
	
}
