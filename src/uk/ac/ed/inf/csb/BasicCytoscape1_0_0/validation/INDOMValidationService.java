package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation;

import org.pathwayeditor.businessobjectsAPI.IMap;
import org.pathwayeditor.contextadapter.publicapi.IValidationReport;
import org.pathwayeditor.contextadapter.toolkit.ndom.INdomModel;

public interface INDOMValidationService {
	
	/**
	 * @return true if during validation an NDOM was constructed
	 */
	public boolean ndomWasCreated();
	
	
	/**
	 * @param map map to be validated by this class
	 */
	public void setMapToValidate(IMap map);

	/**
	 *  implementers must generate a validation report and (if possible) an NDOM during validation 
	 */
	void validateMap();

	boolean isReadyToValidate();
	
	public IValidationReport getValidationReport();
	
	public INdomModel getNDOM();

	public IValidationRuleStore getRuleStore();

	public boolean hasBeenValidated();

	public IMap getMapBeingValidated();

}
