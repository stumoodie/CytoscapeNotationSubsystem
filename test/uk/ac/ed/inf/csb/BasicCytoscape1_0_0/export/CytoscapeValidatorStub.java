package org.pathwayeditor.notations.cytoscape.export;

import java.util.Set;

import org.pathwayeditor.businessobjectsAPI.IMap;
import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.IContextAdapterServiceProvider;
import org.pathwayeditor.contextadapter.publicapi.IValidationReport;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleConfig;
import org.pathwayeditor.contextadapter.toolkit.ndom.INDOMValidationService;
import org.pathwayeditor.contextadapter.toolkit.ndom.INdomModel;
import org.pathwayeditor.contextadapter.toolkit.validation.IValidationRuleStore;

import org.pathwayeditor.notations.cytoscape.ndom.GraphStub;
import org.pathwayeditor.notations.cytoscape.ndom.stubs.ValidationReportStub;

public class CytoscapeValidatorStub implements INDOMValidationService{

	private IContextAdapterServiceProvider contextService;
	private boolean hasbeenvalidated;
	public boolean ready=true;

	public CytoscapeValidatorStub(IContextAdapterServiceProvider contextService) {
		this.contextService=contextService;
	}

	public INdomModel getNDOM(){
		return new GraphStub();
	}

	public IContext getContext() {
		return contextService.getContext();
	}

	public Set<IValidationRuleConfig> getDefaultRuleConfigurations() {
		// TODO Auto-generated method stub
		return null;
	}

	public IMap getMapBeingValidated() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<IValidationRuleConfig> getRuleConfigurations() {
		// TODO Auto-generated method stub
		return null;
	}

	public IContextAdapterServiceProvider getServiceProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	public IValidationReport getValidationReport() {
		return new ValidationReportStub(true);
	}

	public boolean hasMapBeenValidated() {
		return hasbeenvalidated;
	}

	public boolean isImplemented() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isReadyToValidate() {
		return ready;
	}

	public void setMapToValidate(IMap mapToValidate) {
		// TODO Auto-generated method stub
		
	}

	public void validateMap() {
		hasbeenvalidated=true;
	}

	public IValidationRuleStore getRuleStore() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasBeenValidated() {
		return hasbeenvalidated;
	}

	public boolean ndomWasCreated() {
		// TODO Auto-generated method stub
		return false;
	}

}
