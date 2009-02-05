package org.pathwayeditor.notations.cytoscape.validation;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
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
			if(usedNames.contains(edge.getAttribute().getName())){
				this.reportBuilder.setRuleFailed(edge, CytoscapeRuleStore.RULE1_NUM, NAME_NOT_UNIQUE);
			}
			else{
				usedNames.add(edge.getAttribute().getName());
			}
		}
	}

	private void validateNodes() {
		Set<String> usedNames = new TreeSet<String>();
		Iterator<IShapeNode> nodeIterator = this.getCanvas().getModel().shapeNodeIterator();
		while(nodeIterator.hasNext()){
			IShapeNode node = nodeIterator.next();
			if(usedNames.contains(node.getAttribute().getName())){
				this.reportBuilder.setRuleFailed(node, CytoscapeRuleStore.RULE1_NUM, NAME_NOT_UNIQUE);
			}
			else{
				usedNames.add(node.getAttribute().getName());
			}
		}
	}

	public IValidationRuleStore getRuleStore() {
		return this.ruleStore;
	}
}
