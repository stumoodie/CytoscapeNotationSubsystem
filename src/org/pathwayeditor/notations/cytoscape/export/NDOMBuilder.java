package org.pathwayeditor.notations.cytoscape.export;

import giny.model.RootGraph;
import giny.view.EdgeView;
import giny.view.NodeView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.ding.DingNetworkView;
import cytoscape.giny.CytoscapeFingRootGraph;
import cytoscape.giny.CytoscapeRootGraph;

public class NDOMBuilder implements INdomBuilder {
	private final ICanvas canvas;
	private CytoscapeNdom ndom;
	private final Map<Integer, Integer> nodeMapping;
	private final Map<Integer, Integer> edgeMapping;

	public NDOMBuilder(ICanvas canvas) {
		this.canvas = canvas;
		this.nodeMapping = new HashMap<Integer, Integer>();
		this.edgeMapping = new HashMap<Integer, Integer>();
	}

	public void buildNdom() {
		CytoscapeRootGraph rootGraph = new CytoscapeFingRootGraph();
		createdNodes(rootGraph);
		createEdges(rootGraph);
		CyNetwork network =  rootGraph.createNetwork(rootGraph.getNodeIndicesArray(), rootGraph.getEdgeIndicesArray());
		DingNetworkView view = createAttributes(network);
		this.ndom = new CytoscapeNdom(network, view);
	}
	
	private DingNetworkView createAttributes(CyNetwork network) {
		String mapName = this.canvas.getName();
		DingNetworkView localView = new DingNetworkView(network, mapName);
		createNodeAttributes(localView);
		createEdgeAttributes(localView);
		return localView;
	}

	private void createNodeAttributes(DingNetworkView localView) {
		Iterator<IShapeNode> shapeIterator = this.canvas.getModel().shapeNodeIterator();
		while(shapeIterator.hasNext()){
			IShapeNode node = shapeIterator.next();
			int idx = node.getIndex();
			int cyNodeIdx = this.nodeMapping.get(idx);
			NodeView nodeView = localView.getNodeView(cyNodeIdx);
			nodeView.setXPosition(node.getAttribute().getLocation().getX());
			nodeView.setYPosition(node.getAttribute().getLocation().getX());
			nodeView.setHeight(node.getAttribute().getSize().getHeight());
			nodeView.setWidth(node.getAttribute().getSize().getWidth());
			nodeView.setBorderWidth(node.getAttribute().getLineWidth());
			nodeView.setShape(CytoscapeAttributeMapper.getInstance().getCytoscapeShape(node.getAttribute().getPrimitiveShape()));
			nodeView.setToolTip(node.getAttribute().getDescription());
			nodeView.setBorderPaint(CytoscapeAttributeMapper.getInstance().getPaintFromColour(node.getAttribute().getLineColour()));
			nodeView.setUnselectedPaint(CytoscapeAttributeMapper.getInstance().getPaintFromColour(node.getAttribute().getFillColour()));
		}
	}

	private void createEdgeAttributes(DingNetworkView localView) {
		Iterator<ILinkEdge> edgeIterator = this.canvas.getModel().linkEdgeIterator();
		while(edgeIterator.hasNext()){
			ILinkEdge edge = edgeIterator.next();
			int idx = edge.getIndex();
			int cyEdgeIdx = this.edgeMapping.get(idx);
			EdgeView edgeView = localView.getEdgeView(cyEdgeIdx);
			edgeView.setLineType(EdgeView.STRAIGHT_LINES);
			edgeView.setStrokeWidth(edge.getAttribute().getLineWidth());
			edgeView.setToolTip(edge.getAttribute().getDescription());
			edgeView.setUnselectedPaint(CytoscapeAttributeMapper.getInstance().getPaintFromColour(edge.getAttribute().getLineColor()));
			edgeView.setSourceEdgeEnd(CytoscapeAttributeMapper.getInstance().getEndShapeFrom(edge.getAttribute().getSourceTerminus().getEndDecoratorType()));
			edgeView.setSourceEdgeEndPaint(CytoscapeAttributeMapper.getInstance().getPaintFromColour(edge.getAttribute().getLineColor()));
			edgeView.setTargetEdgeEnd(CytoscapeAttributeMapper.getInstance().getEndShapeFrom(edge.getAttribute().getTargetTerminus().getEndDecoratorType()));
			edgeView.setTargetEdgeEndPaint(CytoscapeAttributeMapper.getInstance().getPaintFromColour(edge.getAttribute().getLineColor()));
			edgeView.setStroke(CytoscapeAttributeMapper.getInstance().getStrokeFromLineStyle(edge.getAttribute().getLineStyle()));
		}
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
			int cyEdgeIdx = rootGraph.createEdge(cytOutEdge, cytInEdge);
			this.edgeMapping.put(edge.getIndex(), cyEdgeIdx);
		}
	}
	
	public ICanvas getCanvas() {
		return this.canvas;
	}

	public CytoscapeNdom getNDom() {
		return this.ndom;
	}

}
