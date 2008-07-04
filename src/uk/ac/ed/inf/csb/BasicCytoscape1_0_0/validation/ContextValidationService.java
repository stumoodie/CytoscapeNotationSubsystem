package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation;

import java.util.Set;

import org.pathwayeditor.businessobjectsAPI.IMap;
import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.IContextAdapterServiceProvider;
import org.pathwayeditor.contextadapter.publicapi.IValidationReport;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleConfig;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleConfigurer;


public final class ContextValidationService implements IContextAdapterValidationService {

	private INDOMValidationService ndomValidation;
	private IContextAdapterServiceProvider serviceProvider;
	private IValidationRuleConfigurer configurer;
	
	/**
	 * @param provider service provider for the context
	 * @param ndomValidation validation for theNDOM for this context
	 * @throws IllegalArgumentException if provider or ndomValidator are null
	 */
	public  ContextValidationService(IContextAdapterServiceProvider provider, INDOMValidationService ndomValidation) {
		this.serviceProvider= provider;
		if(provider==null)
			throw new IllegalArgumentException("provider cannot be null");
		if(ndomValidation==null)
			throw new IllegalArgumentException("ndomValidation cannot be null");
		this.ndomValidation=ndomValidation;
	}
	
	public IContextAdapterServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public IMap getMapBeingValidated() {
		return ndomValidation.getMapBeingValidated();
	}
    
	public IValidationReport getValidationReport() {
		if(!(hasMapBeenValidated())){
			throw new IllegalStateException("Map has not been validated - no report available");
		}
		return ndomValidation.getValidationReport();
	}
    
	public boolean hasMapBeenValidated() {
		return ndomValidation.hasBeenValidated();
	}

	
    /**
     * Template pattern. Subclasses add specialized handling of:
     * <ul>
     * <li> creating an Ndom factory
     * <li> Generating an NDom
     * <li> Handling exceptions thrown by the parser
     * @throw {@link IllegalStateException} if service isReadyToValidate == false
     */
	public final void validateMap() {
		if(!isReadyToValidate())
			throw new IllegalStateException("Service not ready to validate");

		configureRulesFromUserPreferences();
		this.ndomValidation.validateMap();		
	}

	private void configureRulesFromUserPreferences() {
		// configurer is optional - no config = default settings
		if( configurer != null){
			configurer.configureRules(ndomValidation.getRuleStore().getConfigurableRules());
		}
		
	}
    
	public void setRuleConfigurer(IValidationRuleConfigurer configurer) {
		if(configurer == null){
			throw new IllegalArgumentException("Rule configurer cannot be null!");
		}
		this.configurer = configurer;
	}
	
	public IContext getContext() {
		return serviceProvider.getContext();
	}
    
	public boolean isReadyToValidate() {
		return ndomValidation.isReadyToValidate();
	}

	public void setMapToValidate(IMap mapToValidate) {
		if(mapToValidate==null) throw new IllegalArgumentException("Map to be validated should not be null");
		ndomValidation.setMapToValidate(mapToValidate);
	}
	
	public Set<IValidationRuleConfig> getDefaultRuleConfigurations() {
		return ndomValidation.getRuleStore().getDefaultRuleConfigurations();
	}

	public Set<IValidationRuleConfig> getRuleConfigurations() {
		return ndomValidation.getRuleStore().getDefaultRuleConfigurations();
	}


	public boolean isImplemented() {
		return true;
	}

}
