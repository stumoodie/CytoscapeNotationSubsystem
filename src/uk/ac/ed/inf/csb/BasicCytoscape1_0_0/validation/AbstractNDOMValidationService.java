package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation;

import java.util.List;
import java.util.Set;

import org.pathwayeditor.businessobjectsAPI.IMap;
import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.IValidationReport;
import org.pathwayeditor.contextadapter.publicapi.IValidationReportItem;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleConfig;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleConfigurer;
import org.pathwayeditor.contextadapter.toolkit.ndom.INdomModel;
import org.pathwayeditor.contextadapter.toolkit.ndom.NdomException;
import org.pathwayeditor.contextadapter.toolkit.validation.IDefaultValidationRuleConfigLoader;


public abstract class AbstractNDOMValidationService implements INDOMValidationService {

	private IContext context;
	private IValidationReport validationReport;
	List<IValidationReportItem> reportItems;
	private boolean beenValidated = false;
	private boolean readyToValidate = false;
	private IMap mapToValidate;
	

	
	private IValidationRuleConfigurer configurer;
	private IValidationRuleStore ruleStore;
	
	private AbstractNDOMParser factory;
	private RuleValidationReportBuilder reportBuilder;

	
	public AbstractNDOMValidationService(IContextAdapterServiceProvider provider) {
		this.serviceProvider= provider;
		this.context        = provider.getContext();
		this.ruleStore      = new RuleStore(getRuleLoader()); 
	}
	protected abstract IDefaultValidationRuleConfigLoader getRuleLoader();
	
	private IContextAdapterServiceProvider serviceProvider;

	public IContextAdapterServiceProvider getServiceProvider() {
		return serviceProvider;
	}
	
	public abstract INdomModel getNDOM();
	
    /**
     * Subclasses should not override this method
     */
	public IMap getMapBeingValidated() {
		return mapToValidate;
	}
    
	 /**
     * Subclasses should not override this method
     */
	public IValidationReport getValidationReport() {
		if(!(hasMapBeenValidated())){
			throw new IllegalStateException("Map has not been validated - no report available");
		}
		return validationReport;
	}
    
	 /**
     * Subclasses should not override this method
     */
	public boolean hasMapBeenValidated() {
		return beenValidated;
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
		configurer.configureRules(getRuleStore().getConfigurableRules());// FIXME - NH this is also present in the framework service - Im unsure if it also needs to exist here
		factory = createNdomFactory();
		reportBuilder = new RuleValidationReportBuilder(ruleStore, mapToValidate);
		factory.setReportBuilder(reportBuilder);
		factory.setRmo(mapToValidate.getTheSingleRootMapObject());
		try {
			generateNdom();
		} catch (NdomException e) {
			handleNdomException(e);
		} finally{
			beenValidated=true;
			reportBuilder.createValidationReport();
		    validationReport = reportBuilder.getValidationReport();
		}
		
		
	}
	/**
	 * Implement behaviour when NDOM creation fails completely
	 */
	protected abstract void handleNdomException(NdomException e);
	
	/**
	 * Subclasses should implement and provide an {@link AbstractNDOMParser} which performs
	 * validation. 
	 * @param reportBuilder. An {@link IRuleValidationReportBuilder} for use by the created parser.
	 * @return A non-null {@link AbstractNDOMParser}.
	 */
	protected abstract AbstractNDOMParser createNdomFactory();
	
	/**
	 * Subclasses should implement the generattion of a notation domain object model.
	 * @throws NdomException If Ndom generation cannot proceed.
	 */
	protected abstract void generateNdom() throws NdomException;
	
    
	/**
     * Subclasses should not override this method
     */
	public IContext getContext() {
		return context;
	}
    
	/**
     * Subclasses should not override this method
     */
	public boolean isReadyToValidate() {
		return readyToValidate;
	}

	public void setMapToValidate(IMap mapToValidate) {
		if(mapToValidate==null) throw new IllegalArgumentException("Map to be validated should not be null");
		this.mapToValidate = mapToValidate;
		beenValidated=false;
		readyToValidate=(this.mapToValidate!=null); 
	}


	public Set<IValidationRuleConfig> getDefaultRuleConfigurations() {
		return ruleStore.getDefaultRuleConfigurations();
	}

	public Set<IValidationRuleConfig> getRuleConfigurations() {
		return ruleStore.getAllRuleConfigurations();
	}

	/**
     * Subclasses should not override this method.
     * @return The {@link IValidationRuleStore} for this export service.
     */
	public IValidationRuleStore getRuleStore() {
		return ruleStore;
	}

	/**
     * Provides access for subclasses to the parser factory. Guaranteed to be the same parser as the 
     * last invocation of createNdomFactory()
     * @return An {@link AbstractNDOMParser}
     */
	protected AbstractNDOMParser getFactory() {
		return factory;
	}

	/**
     * Provides access for subclasses to the report builder.
     * @return The {@link IRuleValidationReportBuilder} used to build the validation report.
     */
	protected IRuleValidationReportBuilder getReportBuilder() {
		return reportBuilder;
	}

}
