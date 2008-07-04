package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation;

import java.util.List;
import java.util.Set;

import org.pathwayeditor.contextadapter.publicapi.IValidationRuleConfig;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleDefinition;


/**
 * Defines a store of rules and their configurations. 
 * There should only be one RuleStore per validation service. A rule store should
 * be able to retrieve default rule configurations and user-customized configurations.
 * All methods except getDefaultRuleConfigurations will return configurable Rule configurations,
 *  the default settings should remain unaffected.
@author Richard Adams /Stuart Moodie
 */
public interface IValidationRuleStore {
	
	/**
	 * Test if the store contains a rule of a given id.
	 * Precondition: isInitialized == true
	 * @param ruleNumber The unique identifier of the rule
	 * @return <code>true</code> if the rule set contains this rule, false otherwise.
	 */
	boolean containsRule (int ruleNumber);
	
	/**
	 * Accessor for a rule of a givenID
	 *   Precondition: isInitialized == true
	 * @param ruleID The unique rule identifier
	 * @return The {@link IValidationRule} with the requested ID, or <code>null</code> if no rule is found.
	 */
	IValidationRuleDefinition getRuleById(int ruleID);
	
	/**
	 * Accessor for a rule configuration  of a givenID
	 *  Precondition: isInitialized == true
	 * @param ruleID The unique rule identifier
	 * @return The {@link IValidationRuleConfig} with the requested ID, or <code>null</code> if no rule is found.
	 */
	IValidationRuleConfig getRuleConfigByID(int id);
	
	/**
	 * Precondition: isInitialized == true
	 * Obtains <code>Set</code> of configurable rules, i.e., those rules whose execution is not mandatory.
	 * @return An immutable {@link Set}of {@link IValidationRuleConfig}s, not null. 
	 */
	Set<IValidationRuleConfig> getConfigurableRules();
	
	/**
	 * Precondition: isInitialized == true
	 * Obtains a <code>Set</code> of all the rule configurations.
	 * @return An immutable {@link Set}of {@link IValidationRuleConfig}s, not null. 
	 */
	Set<IValidationRuleConfig> getAllRuleConfigurations();
	
	/**
	 *  Precondition: isInitialized == true
	 * Obtains <code>Set</code> of rules with default configuration.
	 * @return An immutable {@link Set}of {@link IValidationRuleConfig}s, not null. 
	 */
	Set<IValidationRuleConfig> getDefaultRuleConfigurations();
	
	/**
	 * Precondition: isInitialized == true
	 * Obtains <code>List</code> of all rule definition rules, both mandatory and optional.
	 * @return An immutable {@link List}of {@link IValidationRuleDefinition}s, not null. 
	 */
	List<IValidationRuleDefinition>getAllRuleDefinitions();
	
}
