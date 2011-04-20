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
package org.pathwayeditor.notations.cytoscape.validation;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.notations.cytoscape.CytoscapeSyntaxService;
import org.pathwayeditor.notationsubsystem.toolkit.validation.IRuleValidationReportBuilder;
import org.pathwayeditor.notationsubsystem.toolkit.validation.IValidationRuleStore;
import org.pathwayeditor.notationsubsystem.toolkit.validation.RuleValidationReportBuilder;

public class CytoscapeValidator {
	private static final String NAME_NOT_UNIQUE = "Name is not unique within this diagram";
	private static CytoscapeValidator anInstance = null;
	
	private final IValidationRuleStore ruleStore;
	private ICanvas canvas;
	private IRuleValidationReportBuilder reportBuilder;
	
	public static CytoscapeValidator getInstance(){
		if(anInstance == null){
			anInstance = new CytoscapeValidator();
		}
		return anInstance;
	}
	
	private CytoscapeValidator(){
		this.ruleStore = new CytoscapeRuleStore();
		this.reportBuilder = null;
	}

	public void setCanvas(ICanvas canvas){
		this.canvas = canvas;
	}
	
	public ICanvas getCanvas(){
		return this.canvas;
	}
	
	public void validateCanvas(){
		if(this.canvas == null) throw new IllegalStateException("Canvas not set. Cannot proceed with validation");
		
		reportBuilder = new RuleValidationReportBuilder(this.ruleStore, this.canvas);
		validateNodes();
		validatedEdges();
		this.reportBuilder.createValidationReport();
	}

	/**
	 * Checks if the current canvas has been validated. This means that a report should have been generated and that
	 * that was for the currently set canvas.
	 * @return true if the current canvas was validated last, false otherwise.
	 */
	public boolean hasBeenValidated(){
		return this.reportBuilder.isComplete() && this.canvas.equals(reportBuilder.getValidationReport().getCanvas());
	}
	
	public IValidationReport getValidationReport(){
		if(!hasBeenValidated()) throw new IllegalStateException("Canvas has not been validated, so no report");
		
		return this.reportBuilder.getValidationReport();
	}
	
	private void validatedEdges() {
		Set<String> usedNames = new TreeSet<String>();
		Iterator<ILinkEdge> edgeIterator = this.getCanvas().getModel().linkEdgeIterator();
		while(edgeIterator.hasNext()){
			ILinkEdge edge = edgeIterator.next();
			String name = edge.getAttribute().getProperty(CytoscapeSyntaxService.EDGE_NAME_PROP).getValue().toString();
			if(usedNames.contains(name)){
				this.reportBuilder.setRuleFailed(edge, CytoscapeRuleStore.RULE1_NUM, NAME_NOT_UNIQUE);
			}
			else{
				usedNames.add(name);
			}
		}
	}

	private void validateNodes() {
		Set<String> usedNames = new TreeSet<String>();
		Iterator<IShapeNode> nodeIterator = this.getCanvas().getModel().shapeNodeIterator();
		while(nodeIterator.hasNext()){
			IShapeNode node = nodeIterator.next();
			String name = node.getAttribute().getProperty(CytoscapeSyntaxService.NODE_NAME_PROP).getValue().toString();
			if(usedNames.contains(name)){
				this.reportBuilder.setRuleFailed(node, CytoscapeRuleStore.RULE1_NUM, NAME_NOT_UNIQUE);
			}
			else{
				usedNames.add(name);
			}
		}
	}

	public IValidationRuleStore getRuleStore() {
		return this.ruleStore;
	}
}
