package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.pathwayeditor.contextadapter.publicapi.IValidationRuleConfig;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleDefinition;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleDefinition.RuleLevel;
import org.pathwayeditor.contextadapter.toolkit.validation.IDefaultValidationRuleConfigLoader;
import org.pathwayeditor.contextadapter.toolkit.validation.ValidationRuleConfig;


/**
 * Default implementation of a <code>IValidationRuleStore</code>.<br>
 * @author Richard Adams 
 *
 */
public class RuleStore implements IValidationRuleStore {
   
    
    private Set<IValidationRuleConfig>configs;
    private IDefaultValidationRuleConfigLoader loader;
   
	public RuleStore(IDefaultValidationRuleConfigLoader loader) {
		this.loader=loader;
	}

	public boolean containsRule(int ruleNumber) {
		checkIsInitialized();
		for(IValidationRuleConfig config: configs){
			if(config.getValidationRuleDefinition().getRuleNumber()==ruleNumber){
				return true;
			}
		}
		return false;
	}
	private void checkIsInitialized() {
		if(!isInitialized()){
			throw new IllegalStateException("Not initialized");
		}
	}


	
	public List<IValidationRuleDefinition> getAllRuleDefinitions() {
		checkIsInitialized();
		List <IValidationRuleDefinition> definitions = new ArrayList<IValidationRuleDefinition>();
		for(IValidationRuleConfig config: configs){
			definitions.add(config.getValidationRuleDefinition());
		}
		return definitions;
	}
    
	/**
	 * Return those configs whose type is not Mandatory
	 */
	public Set<IValidationRuleConfig> getConfigurableRules() {
		Set<IValidationRuleConfig> configurableRules = new HashSet<IValidationRuleConfig>();
		for(IValidationRuleConfig config: configs) {
			if(!(config.getValidationRuleDefinition().getRuleLevel().equals(RuleLevel.MANDATORY))){
				configurableRules.add(config);
			}
		}
		return Collections.unmodifiableSet(configurableRules);
	}
	
	public Set<IValidationRuleConfig> getAllRuleConfigurations() {
		checkIsInitialized();
		return Collections.unmodifiableSet(configs);
	}
	
	public IValidationRuleDefinition getRuleById(int ruleNumber) {
		checkIsInitialized();
		for(IValidationRuleConfig config: configs){
			if(config.getValidationRuleDefinition().getRuleNumber()==ruleNumber){
				return config.getValidationRuleDefinition();
			}
		}
		return null;
	}

	public IValidationRuleConfig getRuleConfigByID(int ruleNumber) {
		checkIsInitialized();
		for(IValidationRuleConfig config: configs){
			if(config.getValidationRuleDefinition().getRuleNumber()==ruleNumber){
				return config;
			}
		}
		return null;
	}

	public Set<IValidationRuleConfig> getDefaultRuleConfigurations() {
		checkIsInitialized();
		return Collections.unmodifiableSet(loader.loadDefaultRuleConfigurations());
	}

	public boolean isInitialized() {
		if(configs!=null && loader!=null){
			return true;
		}
		return false;
	}
	
	public void initializeStore(IDefaultValidationRuleConfigLoader loader) {
		if(loader == null){
			throw new IllegalArgumentException("Loader can't be null");
		}
		 this.loader = loader;
		 if(configs == null)
		     configs=getCopyOfDefaults(this.loader.loadDefaultRuleConfigurations());
		 
	}
	
	private Set<IValidationRuleConfig> getCopyOfDefaults(
			Set<IValidationRuleConfig> defaults) {
		Set<IValidationRuleConfig> copies = new HashSet<IValidationRuleConfig>();
		for (IValidationRuleConfig originalConfig: defaults){
			IValidationRuleDefinition originalDefn = originalConfig.getValidationRuleDefinition();
			ValidationRuleDefinition defnCopy= new ValidationRuleDefinition(originalDefn.getContext(), originalDefn.getName(),
					                                            originalDefn.getRuleCategory(), originalDefn.getRuleNumber(), 
					                                               originalDefn.getRuleLevel());
			defnCopy.setDesc(originalDefn.getDescription());
			defnCopy.setDetailedDesc(originalDefn.getDetailedDescription());
			ValidationRuleConfig configCopy= new ValidationRuleConfig(defnCopy,originalConfig.mustBeRun(), originalConfig.isErrorRule());
		   copies.add(configCopy);
		}
		return copies;
	}

	



  

}
