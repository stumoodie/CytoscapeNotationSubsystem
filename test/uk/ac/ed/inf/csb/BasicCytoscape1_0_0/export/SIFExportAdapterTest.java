package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.export;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.contextadapter.toolkit.ndom.ExportAdapterCreationException;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.Edge;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.GraphStub;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.Node;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.stubs.OutStreamStub;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IEdge;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IGraph;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.INode;

public class SIFExportAdapterTest {

	private SIFExportAdapter<IGraph> test;
	private GraphStub graph;

	@Before
	public void setup() {
		test = new SIFExportAdapter<IGraph>();
		graph = new GraphStub();

	}

	@Test
	public void testNDomWithOneNodeIsWrittenToDataStore() throws ExportAdapterCreationException {
		INode one = new Node("one");
		graph.nodes = Collections.singletonList(one);
		test.createTarget(graph);
		assertEquals("one", test.sifFile.keySet().iterator().next());
	}
	
	@Test
	public void testCreateTargetMakesNewDataStructure() throws ExportAdapterCreationException{
		Map<String, Map<String, String>> preSifFile = test.sifFile;
		test.createTarget(graph);
		Map<String, Map<String, String>> postSifFile = test.sifFile;
		assertFalse(preSifFile==postSifFile);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testNDomWithTwoLinkedNodesIsWrittenToDataStore() throws ExportAdapterCreationException {
		INode one = new Node("one");
		INode two = new Node("two");
		List<INode> nodes = new ArrayList<INode>();
		nodes.add(one);
		nodes.add(two);
		IEdge edge = new Edge("stinks");
		graph.linkNodes(one, two, edge);
		graph.nodes = nodes;
		graph.edges = new HashSet(Collections.singletonList(edge));
		test.createTarget(graph);
		assertEquals("one", test.sifFile.keySet().iterator().next());
		assertEquals("stinks", test.sifFile.get("one").keySet().iterator().next());
		assertEquals("two", test.sifFile.get("one").get("stinks"));
	}

	@Test
	public void testNDomWithOneSourceNodeAndTwoTargetNodesWithTwoEdgeTypesIsWrittenToDataStore() throws ExportAdapterCreationException {
		setupGraphWithThreeNodesAndTwoInteractions();
		assertEquals("one", test.sifFile.keySet().iterator().next());
		assertTrue(test.sifFile.get("one").keySet().contains("stinks"));
		assertTrue(test.sifFile.get("one").keySet().contains("badly"));
		assertEquals("two", test.sifFile.get("one").get("stinks"));
		assertEquals("three", test.sifFile.get("one").get("badly"));
	}

	@Test
	public void testWriteTargetOneNode() throws ExportAdapterCreationException, IOException{
		INode one = new Node("one");
		graph.nodes = Collections.singletonList(one);
		test.createTarget(graph);
		OutStreamStub out= new OutStreamStub();
		test.writeTarget(out);
		List<String>data = out.data;
		assertTrue(data.contains("one"+"\n"));
	}
	
	
	
	@Test
	public void testWriteTargetWithTwoLinkedNodesContainsTheLinkMappings() throws ExportAdapterCreationException, IOException{
		setupGraphWithThreeNodesAndTwoInteractions();
		OutStreamStub out= new OutStreamStub();
		test.writeTarget(out);
		List<String>data = out.data;
		assertTrue(data.contains("one"+"\t"+"stinks"+"\t"+"two"+"\n"));
		assertTrue(data.contains("one"+"\t"+"badly"+"\t"+"three"+"\n"));
	}
	
	@Test
	public void testWriteTargetWithTwoLinkedNodesDoesNotContainTheIndividualNodes() throws ExportAdapterCreationException, IOException{
		setupGraphWithThreeNodesAndTwoInteractions();
		OutStreamStub out= new OutStreamStub();
		test.writeTarget(out);
		List<String>data = out.data;
		assertFalse(data.contains("one"+"\n"));
		assertFalse(data.contains("two"+"\n"));
		assertFalse(data.contains("three"+"\n"));
	}
	
	private void setupGraphWithThreeNodesAndTwoInteractions() throws ExportAdapterCreationException {
		INode one = new Node("one");
		INode two = new Node("two");
		INode three = new Node("three");
		List<INode> nodes = new ArrayList<INode>();
		nodes.add(one);
		nodes.add(two);
		nodes.add(three);
		IEdge edge = new Edge("stinks");
		IEdge edge2 = new Edge("badly");
		graph.linkNodes(one, two, edge);
		graph.linkNodes(one, three, edge2);
		graph.nodes = nodes;
		Set<IEdge> edges = new HashSet<IEdge>();
		edges.add(edge);
		edges.add(edge2);
		graph.edges = edges;
		test.createTarget(graph);
	}
}
