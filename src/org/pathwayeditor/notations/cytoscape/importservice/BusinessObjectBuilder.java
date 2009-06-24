package org.pathwayeditor.notations.cytoscape.importservice;

import giny.model.Edge;
import giny.model.Node;
import giny.view.EdgeView;
import giny.view.NodeView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.notationsubsystem.ImportServiceException;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.notations.cytoscape.CytoscapeSyntaxService;
import org.pathwayeditor.notations.cytoscape.export.CytoscapeAttributeMapper;
import org.pathwayeditor.notations.cytoscape.export.CytoscapeNdom;

import cytoscape.CyNetwork;
import cytoscape.view.CyNetworkView;

public class BusinessObjectBuilder implements IBusinessObjectBuilder {
	private static final int MIN_LINE_WIDTH = 1;
	private static final String NODE_SHAPE = "curbounds oval";
	private ICanvas canvas;
	private final CytoscapeNdom ndom;
	private final INotationSyntaxService syntaxService;
	private Map<Node, IShapeNode> cyNodeMapping = new HashMap<Node, IShapeNode>();
	private IShapeNodeFactory shapeFactory;

	public BusinessObjectBuilder(INotationSyntaxService notationSyntaxService, CytoscapeNdom ndom) {
		this.syntaxService = notationSyntaxService;
		this.ndom = ndom;
	}

	public void build() throws ImportServiceException {
		if(this.canvas == null) throw new IllegalStateException("Canvas must be set");
		if(!canvas.isEmpty()) throw new IllegalStateException("Canvas must be empty");
		setupNetwork();
	}
	
	
	
	
	private void setupNetwork() throws ImportServiceException {
		defineCanvas();
		this.cyNodeMapping.clear();
		createNodes();
		createEdges();
	}

	private void createEdges() {
		CyNetwork cyNetwork = ndom.getNetwork();
		final ILinkObjectType linkObjectType = this.syntaxService.getLinkObjectType(CytoscapeSyntaxService.EDGE_UID);
		int edgeIndices[] = cyNetwork.getEdgeIndicesArray();
		for(int i : edgeIndices){
			Edge cyEdge = cyNetwork.getEdge(i);
			ILinkEdge linkEdge = createEdge(cyEdge, linkObjectType);
			populateLinkAttributes(linkEdge.getAttribute(), cyEdge);
			if(linkEdge.isSelfEdge()){
				createDogLeg(linkEdge);
			}
		}
	}

	
	
	private void createDogLeg(ILinkEdge linkEdge) {
		DogLegBuilder builder = new DogLegBuilder(linkEdge);
		builder.buildDogLeg();
	}
	
	private void populateLinkAttributes(ILinkAttribute linkAttribute, Edge cyEdge) {
		EdgeView edgeView = ndom.getView().getEdgeView(cyEdge.getRootGraphIndex());
		int lineWidth = Math.round(edgeView.getStrokeWidth());
		linkAttribute.setLineWidth(convertLineWidth(lineWidth));
		linkAttribute.setLineColor(convertToRGB((Color)edgeView.getUnselectedPaint()));
		linkAttribute.setLineStyle(convertToLineStyle((BasicStroke)edgeView.getStroke()));
		((IPlainTextAnnotationProperty)linkAttribute.getProperty(CytoscapeSyntaxService.EDGE_NAME_PROP)).setValue(edgeView.getLabel().getText());
		linkAttribute.getSourceTerminus().setEndDecoratorType(convertToLinkEndDecorator(edgeView.getSourceEdgeEnd()));
		linkAttribute.getTargetTerminus().setEndDecoratorType(convertToLinkEndDecorator(edgeView.getTargetEdgeEnd()));
	}

	private LinkEndDecoratorShape convertToLinkEndDecorator(int linkEndType) {
		return CytoscapeAttributeMapper.getInstance().getLinkEndDecoratorFrom(linkEndType);
	}

	private ILinkEdge createEdge(Edge cyEdge, ILinkObjectType objectType) {
		ILinkEdgeFactory edgeFactory = null;
		IShapeNode sourceNode = cyNodeMapping.get(cyEdge.getSource());
		IShapeNode targetNode = cyNodeMapping.get(cyEdge.getTarget());
		if(sourceNode.equals(targetNode)){
			// self edge so the edge will be a child of the node it is linked to
			edgeFactory = sourceNode.getSubModel().linkEdgeFactory();
		}
		else{
			edgeFactory = this.canvas.getModel().getRootNode().getSubModel().linkEdgeFactory();
		}
		edgeFactory.setObjectType(objectType);
		edgeFactory.setShapeNodePair(sourceNode, targetNode);
		return edgeFactory.createLinkEdge();
	}

	private void createNodes() throws ImportServiceException {
		final IModel model = this.canvas.getModel();
		final CyNetwork cyNetwork = ndom.getNetwork();
		int nodeIndices[] = cyNetwork.getNodeIndicesArray();
		shapeFactory = model.getRootNode().getSubModel().shapeNodeFactory();
		final IShapeObjectType shapeObjectType = this.syntaxService.getShapeObjectType(CytoscapeSyntaxService.NODE_UID);
		shapeFactory.setObjectType(shapeObjectType);
		for(int i : nodeIndices){
			final Node cyNode = cyNetwork.getNode(i);
			IShapeNode shape = createNode(cyNode);
			populateNodeAttribute(shape.getAttribute(), cyNode);
		}
	}

	private void populateNodeAttribute(IShapeAttribute attribute, Node cyNode) throws ImportServiceException {
		CyNetworkView cyNetworkView = ndom.getView();
		NodeView nodeView = cyNetworkView.getNodeView(cyNode.getRootGraphIndex());
		attribute.setLineWidth(convertLineWidth(nodeView.getBorderWidth()));
		attribute.setLineStyle(convertToLineStyle((BasicStroke)nodeView.getBorder()));
		attribute.setLineColour(convertToRGB((Color)nodeView.getBorderPaint()));
		attribute.setFillColour(convertToRGB((Color)nodeView.getUnselectedPaint()));
		attribute.setLocation(convertToLocation(nodeView.getXPosition(), nodeView.getYPosition()));
		attribute.setSize(convertSize(nodeView.getWidth(), nodeView.getHeight()));
		attribute.setShapeDefinition(NODE_SHAPE);
		((IPlainTextAnnotationProperty)attribute.getProperty(CytoscapeSyntaxService.NODE_NAME_PROP)).setValue(nodeView.getLabel().getText());
	}

	private IShapeNode createNode(Node cyNode) {
		IShapeNode shape = shapeFactory.createShapeNode();
		cyNodeMapping.put(cyNode, shape);
		return shape;
	}

	private void defineCanvas(){
		this.canvas.setName(ndom.getNetwork().getIdentifier());
	}
	
	
	public ICanvas getCanvas() {
		return this.canvas;
	}

	public void setCanvas(ICanvas canvas) {
		this.canvas = canvas;
	}

	public CytoscapeNdom getNDom() {
		return this.ndom;
	}

	private static int convertLineWidth(float floatingLineWidth){
		int lineWidth = Math.round(floatingLineWidth);
		return lineWidth < MIN_LINE_WIDTH ? MIN_LINE_WIDTH : lineWidth;
	}
	
	private static void validateIntegerRange(long value) throws ImportServiceException{
		if(value > Integer.MAX_VALUE || value < Integer.MIN_VALUE){
			throw new ImportServiceException("Value too large or small to be converted to a integer");
		}
	}
	
	private static Dimension convertSize(double floatingWidth, double floatingHeight) throws ImportServiceException{
		long width = Math.round(floatingWidth);
		long height = Math.round(floatingHeight);
		validateIntegerRange(width);
		validateIntegerRange(height);
		return new Dimension((int)width, (int)height); 
	}

	private static Point convertToLocation(double floatingWidth, double floatingHeight) throws ImportServiceException{
		long width = Math.round(floatingWidth);
		long height = Math.round(floatingHeight);
		validateIntegerRange(width);
		validateIntegerRange(height);
		return new Point((int)width, (int)height); 
	}

	private static RGB convertToRGB(Color borderPaint) {
		int red = (borderPaint).getRed();
		int green = (borderPaint).getGreen();
		int blue = (borderPaint).getBlue();
		return new RGB(red, green, blue);
	}

	private static LineStyle convertToLineStyle(BasicStroke border) {
		float dashArr[] = border.getDashArray();
		LineStyle retVal = LineStyle.SOLID;
		if(dashArr != null){
			// at the moment it seems thet Cytoscape only supports long dashes for lines so we will just look
			// for any kind of dashed patern and map it to a dashed line style
			retVal = LineStyle.DASHED;
		}
		return retVal;
	}
}
