package org.pathwayeditor.notations.cytoscape.importservice;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

public class DogLegBuilder {
	private final ILinkEdge selfEdge;
	private Point srcAnchorPoint;
	private Point tgtAnchorPoint;
	private int verticalOffset;
	private int horizontalOffset;
	
	public DogLegBuilder(ILinkEdge selfEdge){
		this.selfEdge = selfEdge;
	}

	public ILinkEdge getSelfEdge() {
		return selfEdge;
	}

	
	public void buildDogLeg(){
		calculateOffset();
		calculateAnchorPoints();
		createBendPoint(-1, 0);
		createBendPoint(-1, 2);
		createBendPoint(1, 2);
	}

	private void calculateOffset() {
		// make sure that the offsets are divisible by 2, this makes sure everything is consistent 
		Dimension nodeSize = selfEdge.getSourceShape().getAttribute().getSize();
		this.horizontalOffset = (int)(nodeSize.getWidth()/2.0f);
		this.verticalOffset = (int)(nodeSize.getHeight()/2.0f);
	}

	/**
	 * Creates a bend point positioned at an offset from the source anchor. 
	 * @param i the number of units of 0.5 * the width of the shape to offset the bendpoint. This value can be negative. 
	 * @param j the number of units of 0.5 * the height of the shape to offset the bendpoint. This value can be negative.
	 */
	private void createBendPoint(int i, int j) {
		int xoffset = i * this.horizontalOffset;
		int yoffset = j * this.verticalOffset;
		final Point bp1Translation = new Point(xoffset, yoffset);
		final Point newBp1 = srcAnchorPoint.translate(bp1Translation);
		this.selfEdge.getAttribute().createNewBendPoint(newBp1, bp1Translation, newBp1.difference(this.tgtAnchorPoint));
	}

	private void calculateAnchorPoints() {
		Point nodeLocation = selfEdge.getSourceShape().getAttribute().getLocation();
		srcAnchorPoint = nodeLocation.newY(nodeLocation.getY() - this.verticalOffset);
		tgtAnchorPoint = nodeLocation.newX(nodeLocation.getX() + this.horizontalOffset);
	}
}
