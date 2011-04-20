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
		selfEdge.getAttribute().makeSelfBendPoints(srcAnchorPoint, tgtAnchorPoint);
//		createBendPoint(-1, 0);
//		createBendPoint(-1, 2);
//		createBendPoint(1, 2);
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
//	private void createBendPoint(double i, double j) {
//		double xoffset = i * this.horizontalOffset;
//		double yoffset = j * this.verticalOffset;
//		this.selfEdge.getAttribute().createNewBendPoint(newBp1, bp1Translation, newBp1.difference(this.tgtAnchorPoint));
//	}

	private void calculateAnchorPoints() {
		Point nodeLocation = selfEdge.getSourceShape().getAttribute().getLocation();
		srcAnchorPoint = nodeLocation.newY(nodeLocation.getY() - this.verticalOffset);
		tgtAnchorPoint = nodeLocation.newX(nodeLocation.getX() + this.horizontalOffset);
	}
}
