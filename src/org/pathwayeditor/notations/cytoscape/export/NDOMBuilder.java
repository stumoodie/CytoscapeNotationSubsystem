package org.pathwayeditor.notations.cytoscape.export;

import giny.model.RootGraph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.giny.CytoscapeRootGraph;

public class NDOMBuilder implements INdomBuilder {
	private final ICanvas canvas;
	private CyNetwork ndom;
	private Map<Integer, Integer> nodeMapping;

	public NDOMBuilder(ICanvas canvas) {
		this.canvas = canvas;
		this.nodeMapping = new HashMap<Integer, Integer>();
	}

	public void buildNdom() {
		CytoscapeRootGraph rootGraph = Cytoscape.getRootGraph();
		createdNodes(rootGraph);
		createEdges(rootGraph);
		this.ndom = rootGraph.createNetwork(rootGraph.getNodeIndicesArray(), rootGraph.getEdgeIndicesArray());
	}
	
	private void createdNodes(RootGraph rootGraph){
		Iterator<IShapeNode> shapeIterator = this.canvas.getModel().shapeNodeIterator();
		while(shapeIterator.hasNext()){
			IShapeNode node = shapeIterator.next();
			int idx = node.getIndex();
			int newNodeIdx = rootGraph.createNode();
			CyNode cyNode = (CyNode) rootGraph.getNode(newNodeIdx);
			cyNode.setIdentifier(node.getAttribute().getName());

//			// create the CANONICAL_NAME attribute
//			if (getNodeAttributes().getStringAttribute(nodeID, Semantics.CANONICAL_NAME) == null) {
//				getNodeAttributes().setAttribute(nodeID, Semantics.CANONICAL_NAME, nodeID);
//			}
			this.nodeMapping.put(idx, newNodeIdx);
		}
	}

	private void createEdges(RootGraph rootGraph){
		Iterator<ILinkEdge> edgeIterator = this.canvas.getModel().linkEdgeIterator();
		while(edgeIterator.hasNext()){
			ILinkEdge edge = edgeIterator.next();
			int cytOutEdge = this.nodeMapping.get(edge.getSourceShape().getIndex());
			int cytInEdge = this.nodeMapping.get(edge.getTargetShape().getIndex());
			rootGraph.createEdge(cytOutEdge, cytInEdge);
		}
	}
	
	public ICanvas getCanvas() {
		return this.canvas;
	}

	public CyNetwork getNDom() {
		return this.ndom;
	}

}
