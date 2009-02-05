package org.pathwayeditor.notations.cytoscape.validation;

import java.util.Set;

import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleConfig;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleEnforcement;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleLevel;
import org.pathwayeditor.notationsubsystem.toolkit.validation.IValidationRuleStore;
import org.pathwayeditor.notationsubsystem.toolkit.validation.RuleStore;
import org.pathwayeditor.notationsubsystem.toolkit.validation.ValidationRuleConfig;
import org.pathwayeditor.notationsubsystem.toolkit.validation.ValidationRuleDefinition;

public class CytoscapeRuleStore implements IValidationRuleStore {
	private static final String NAME_CONSISTENCY = "Semantics";
	private static final String RULE1_NAME = "UniqueEdgeNames";
	public  static final int RULE1_NUM = 1;
	private static final String RULE2_NAME = "UniqueEdgeNames";
	public static final int RULE2_NUM = 2;
	private final IValidationRuleStore ruleStore;
	
	
	public CytoscapeRuleStore(){
		RuleStore buildingRuleStore = new RuleStore();
		ValidationRuleDefinition rule1 = new ValidationRuleDefinition(RULE1_NAME, NAME_CONSISTENCY, RULE1_NUM, RuleLevel.OPTIONAL, RuleEnforcement.WARNING);
		rule1.setDesc("Checks that all graph nodes have a unique name assigned to them");
		buildingRuleStore.addConfiguredRule(new ValidationRuleConfig(rule1));
		ValidationRuleDefinition rule2 = new ValidationRuleDefinition(RULE2_NAME, NAME_CONSISTENCY, RULE2_NUM, RuleLevel.OPTIONAL, RuleEnforcement.WARNING);
		rule2.setDesc("Checks that all graph edges have a unique name assigned to them");
		buildingRuleStore.addConfiguredRule(new ValidationRuleConfig(rule2));
		this.ruleStore = buildingRuleStore;
	}
	
	
	public boolean containsRule(int ruleNumber) {
		return this.ruleStore.containsRule(ruleNumber);
	}

	public Set<IValidationRuleConfig> getAllRuleConfigurations() {
		return this.ruleStore.getAllRuleConfigurations();
	}

	public Set<IValidationRuleDefinition> getAllRuleDefinitions() {
		return this.ruleStore.getAllRuleDefinitions();
	}

	public Set<IValidationRuleConfig> getConfigurableRules() {
		return this.ruleStore.getConfigurableRules();
	}

	public IValidationRuleDefinition getRuleById(int ruleID) {
		return this.ruleStore.getRuleById(ruleID);
	}

	public IValidationRuleConfig getRuleConfigByID(int id) {
		return this.ruleStore.getRuleConfigByID(id);
	}
}
