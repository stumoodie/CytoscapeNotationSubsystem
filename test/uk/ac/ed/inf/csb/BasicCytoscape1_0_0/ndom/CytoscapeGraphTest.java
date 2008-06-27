package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.stubs.NodeStub;

public class CytoscapeGraphTest {

	@Test
	public void testAddNodeIncreasesNumNodes(){
		CytoscapeGraph graph = new CytoscapeGraph("aaa","1", "1");
		assertEquals(graph.getNodes().size(),0);
		graph.addNode(new NodeStub());
		assertEquals(graph.getNodes().size(),1);
	}
	
	@Test
	public void testLinkNodesAddsToSrcNodesSrcEdges(){
		CytoscapeGraph graph = new CytoscapeGraph("aaa","1", "1");
		Node src = new Node("src");
		assertEquals(src.getSrcEdges().size(),0);
		Node targ = new Node("targ");
		Edge edge = new Edge("edge");
		graph.linkNodes(src, targ, edge);
		assertEquals(src.getSrcEdges().size(),1);
		assertEquals(src.getTargetEdges().size(),0);
	}
	
	@Test
	public void testLinkNodesAddsToTargetNodesTargetEdges(){
		CytoscapeGraph graph = new CytoscapeGraph("aaa","1", "1");
		Node src = new Node("src");
		Node targ = new Node("targ");
		assertEquals(targ.getTargetEdges().size(),0);
		Edge edge = new Edge("edge");
		graph.linkNodes(src, targ, edge);
		assertEquals(targ.getTargetEdges().size(),1);
		assertEquals(targ.getSrcEdges().size(),0);
	}
	
	@Test
	public void testLinkNodesAddsTargetAndSrcNodeToEdge(){
		CytoscapeGraph graph = new CytoscapeGraph("aaa","1", "1");
		Node src = new Node("src");
		Node targ = new Node("targ");
		Edge edge = new Edge("edge");
		assertNull(edge.getSrcNode());
		assertNull(edge.getTargetNode());
		graph.linkNodes(src, targ, edge);
		assertEquals(targ,edge.getTargetNode());
		assertEquals(src,edge.getSrcNode());
	}
	
}
