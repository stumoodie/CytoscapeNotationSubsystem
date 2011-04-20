/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/
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
import org.pathwayeditor.notations.cytoscape.CytoscapeSyntaxService;

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.ding.DingNetworkView;
import cytoscape.giny.CytoscapeFingRootGraph;
import cytoscape.giny.CytoscapeRootGraph;

public class CytoscapeNdomBuilder implements INdomBuilder {
	private static final int MIN_LINE_WIDTH = 1;
	private final ICanvas canvas;
	private CytoscapeNdom ndom;
	private final Map<Integer, Integer> nodeMapping;
	private final Map<Integer, Integer> edgeMapping;

	public CytoscapeNdomBuilder(ICanvas canvas) {
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
			nodeView.setBorderWidth(convertLineWidth(node.getAttribute().getLineWidth()));
			nodeView.setShape(NodeView.ELLIPSE);
			nodeView.setBorderPaint(CytoscapeAttributeMapper.getInstance().getPaintFromColour(node.getAttribute().getLineColour()));
			nodeView.setUnselectedPaint(CytoscapeAttributeMapper.getInstance().getPaintFromColour(node.getAttribute().getFillColour()));
		}
	}

	private static int convertLineWidth(double lineWidth){
		int width = (int)Math.round(lineWidth);
		return width < MIN_LINE_WIDTH ? MIN_LINE_WIDTH : width;
	}
	
	private void createEdgeAttributes(DingNetworkView localView) {
		Iterator<ILinkEdge> edgeIterator = this.canvas.getModel().linkEdgeIterator();
		while(edgeIterator.hasNext()){
			ILinkEdge edge = edgeIterator.next();
			int idx = edge.getIndex();
			int cyEdgeIdx = this.edgeMapping.get(idx);
			EdgeView edgeView = localView.getEdgeView(cyEdgeIdx);
			edgeView.setLineType(EdgeView.STRAIGHT_LINES);
			edgeView.setStrokeWidth(convertLineWidth(edge.getAttribute().getLineWidth()));
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
			cyNode.setIdentifier(node.getAttribute().getProperty(CytoscapeSyntaxService.NODE_NAME_PROP).getValue().toString());

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
