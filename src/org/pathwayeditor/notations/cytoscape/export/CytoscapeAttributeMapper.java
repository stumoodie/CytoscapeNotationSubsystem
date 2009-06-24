package org.pathwayeditor.notations.cytoscape.export;

import giny.view.EdgeView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;

public class CytoscapeAttributeMapper {
	private static final int OPAQUE_ALPHA_VALUE = 255;
	private static CytoscapeAttributeMapper anInstance;
	private static final float DOT_LINE[] = { 3f, 3f }; 
	private static final float DASHED_DOT_LINE[] = { 21f, 9f, 3f, 9f }; 
	private static final float DASHED_LINE[] = { 25f, 25f }; 
	private static final float DASHED_DOT_DOT_LINE[] = { 21f, 9f, 3f, 9f, 3f, 9f };
	private static final float DEFAULT_LINE_WIDTH = 1.0f;
	private static final float DEFAULT_MITRE_LIMIT = 10f;
	private static final float DEFAULT_PHASE = 0f; 
		
	public static CytoscapeAttributeMapper getInstance(){
		if(anInstance == null){
			anInstance = new CytoscapeAttributeMapper();
		}
		return anInstance;
	}
	
	private CytoscapeAttributeMapper(){
		
	}
	
	public Paint getPaintFromColour(RGB colour){
		return new Color(colour.getRed(), colour.getGreen(), colour.getBlue(), OPAQUE_ALPHA_VALUE);
	}

	public int getEndShapeFrom(LinkEndDecoratorShape endDecoratorType) {
		int retVal = EdgeView.NO_END;
		if(endDecoratorType == LinkEndDecoratorShape.ARROW){
			retVal = EdgeView.EDGE_COLOR_ARROW;
		}
		else if(endDecoratorType == LinkEndDecoratorShape.EMPTY_CIRCLE){
			retVal = EdgeView.WHITE_CIRCLE;
		}
		else if(endDecoratorType == LinkEndDecoratorShape.BAR){
			retVal = EdgeView.BLACK_T;
		}
		else if(endDecoratorType == LinkEndDecoratorShape.EMPTY_DIAMOND){
			retVal = EdgeView.WHITE_DIAMOND;
		}
		else if(endDecoratorType == LinkEndDecoratorShape.DIAMOND){
			retVal = EdgeView.EDGE_COLOR_DIAMOND;
		}
		else if(endDecoratorType == LinkEndDecoratorShape.EMPTY_DIAMOND){
			retVal = EdgeView.WHITE_DIAMOND;
		}
		return retVal;
	}

	public LinkEndDecoratorShape getLinkEndDecoratorFrom(int endDecoratorType) {
		LinkEndDecoratorShape retVal = LinkEndDecoratorShape.NONE;
		if(endDecoratorType == EdgeView.EDGE_COLOR_ARROW){
			retVal = LinkEndDecoratorShape.ARROW;
		}
		else if(endDecoratorType == EdgeView.BLACK_ARROW){
			retVal = LinkEndDecoratorShape.ARROW;
		}
		else if(endDecoratorType == EdgeView.WHITE_ARROW){
			retVal = LinkEndDecoratorShape.ARROW;
		}
		else if(endDecoratorType == EdgeView.WHITE_CIRCLE){
			retVal = LinkEndDecoratorShape.EMPTY_CIRCLE;
		}
		else if(endDecoratorType == EdgeView.BLACK_CIRCLE){
			retVal = LinkEndDecoratorShape.EMPTY_CIRCLE;
		}
		else if(endDecoratorType == EdgeView.EDGE_COLOR_CIRCLE){
			retVal = LinkEndDecoratorShape.EMPTY_CIRCLE;
		}
		else if(endDecoratorType == EdgeView.BLACK_T){
			retVal = LinkEndDecoratorShape.BAR;
		}
		else if(endDecoratorType == EdgeView.EDGE_COLOR_T){
			retVal = LinkEndDecoratorShape.BAR;
		}
		else if(endDecoratorType == EdgeView.WHITE_T){
			retVal = LinkEndDecoratorShape.BAR;
		}
		else if(endDecoratorType == EdgeView.WHITE_DIAMOND){
			retVal = LinkEndDecoratorShape.EMPTY_DIAMOND;
		}
		else if(endDecoratorType == EdgeView.EDGE_COLOR_DIAMOND){
			retVal = LinkEndDecoratorShape.DIAMOND;
		}
		else if(endDecoratorType == EdgeView.BLACK_DIAMOND){
			retVal = LinkEndDecoratorShape.DIAMOND;
		}
		else if(endDecoratorType == EdgeView.BLACK_DELTA){
			retVal = LinkEndDecoratorShape.ARROW;
		}
		else if(endDecoratorType == EdgeView.EDGE_COLOR_DELTA){
			retVal = LinkEndDecoratorShape.ARROW;
		}
		else if(endDecoratorType == EdgeView.WHITE_DELTA){
			retVal = LinkEndDecoratorShape.ARROW;
		}
		return retVal;
	}

	public Stroke getStrokeFromLineStyle(LineStyle lineStyle) {
		Stroke retVal = new BasicStroke();
		if(lineStyle == LineStyle.DASHED){
			retVal = createDashedStroke(DASHED_LINE);
		}
		else if(lineStyle == LineStyle.DASH_DOT){
			retVal = createDashedStroke(DASHED_DOT_LINE);
		}
		else if(lineStyle == LineStyle.DASH_DOT_DOT){
			retVal = createDashedStroke(DASHED_DOT_DOT_LINE);
		}
		else if(lineStyle == LineStyle.DOT){
			retVal = createDashedStroke(DOT_LINE);
		}
		return retVal;
	}
	
	private Stroke createDashedStroke(float[] dashArray){
		return new BasicStroke(DEFAULT_LINE_WIDTH, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, DEFAULT_MITRE_LIMIT, dashArray, DEFAULT_PHASE);
	}
	
}
