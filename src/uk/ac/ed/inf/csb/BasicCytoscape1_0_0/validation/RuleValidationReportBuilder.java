package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pathwayeditor.businessobjectsAPI.IMap;
import org.pathwayeditor.businessobjectsAPI.IMapObject;
import org.pathwayeditor.contextadapter.publicapi.IValidationReport;
import org.pathwayeditor.contextadapter.publicapi.IValidationReportItem;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleConfig;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleDefinition;
import org.pathwayeditor.contextadapter.toolkit.validation.DefaultValidationReport;
import org.pathwayeditor.contextadapter.toolkit.validation.IValidationRuleStore;
import org.pathwayeditor.contextadapter.toolkit.validation.ValidationReportItem;
/**
 * Builds a report.
 * Can be in 3 states:
 * <ul>
 * <li> Ready to validate
 * <li> Validating
 * <li>Completed
 * </ul>
 * When the first call to setRuleFailed() or setRulePassed() is called by the validator,
 * a copy of the rule configuration is taken. This is to prevent
 * @author Richard Adams
 *
 */
 class RuleValidationReportBuilder implements IRuleValidationReportBuilder {
     int state;
    // states
    static final int READY_TO_VALIDATE = 1;
    static final int VALIDATING        = 2;
    static final int COMPLETED         = 3;
    
    List<IValidationReportItem> reportItems = new ArrayList<IValidationReportItem>();
    private uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.IValidationRuleStore validationRuleStore;
    private IMap mapToValidate;
    private Map<Integer, Boolean> checkedRules= new HashMap<Integer, Boolean>();

    
    public RuleValidationReportBuilder(uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.IValidationRuleStore ruleStore, IMap map) {
    	if(map == null || ruleStore == null){
    		throw new IllegalArgumentException("Arguments must not be null");
    	}
    	this.validationRuleStore=ruleStore;
    	this.mapToValidate=map;
    	state=READY_TO_VALIDATE;
    }
	public void createValidationReport() {
		if(!isValidating() && !isComplete() && !isReadyToValidate()){
			throw new IllegalStateException("");
		}
		
		state=COMPLETED;
	}
	


	public uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.IValidationRuleStore getRuleStore() {
		return validationRuleStore;
	}

	public IValidationReport getValidationReport() {
		if(getState()!=COMPLETED){
			throw new IllegalStateException("CAnnot access report until validation completed");
		}
		return new DefaultValidationReport(mapToValidate, reportItems);
	}

	public boolean isComplete() {
		return getState()==COMPLETED;
	}

	public boolean isReadyToValidate() {
		return getState()==READY_TO_VALIDATE;
	}

	public boolean isValidating() {
		return getState()==VALIDATING;
	}

	public void reset() {
		if(state==VALIDATING){
			throw new IllegalStateException("Cannot clear during validation");
		}
		reportItems.clear();
		checkedRules.clear();
		state=READY_TO_VALIDATE;
	}

	public void setRuleFailed(IMapObject mapObject, IValidationRuleDefinition ruleDefinition, String message) {
		
		checkRuleDefinition(ruleDefinition);
		state=VALIDATING;
	
		IValidationRuleConfig config = validationRuleStore.getRuleConfigByID(ruleDefinition.getRuleNumber());
		if(!config.mustBeRun()){
			return;
		}
		
		IValidationReportItem.Severity severity = config.isErrorRule()?IValidationReportItem.Severity.ERROR:IValidationReportItem.Severity.WARNING;
		reportItems.add(new ValidationReportItem(mapObject, ruleDefinition, severity, message));

	}


	private void checkRuleDefinition(IValidationRuleDefinition ruleDefinition) {
		if(getState()==COMPLETED){
			throw new IllegalStateException("validation completed");
		}
		if(!validationRuleStore.containsRule(ruleDefinition.getRuleNumber())){
			throw new IllegalArgumentException("Rule not in rule store");
		}
	}

	public void setRulePassed(IValidationRuleDefinition ruleDefinition) {
		state=VALIDATING;
	}
	int getState() {
		return state;
	}

}
