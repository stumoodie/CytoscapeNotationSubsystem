package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation;

import org.pathwayeditor.contextadapter.publicapi.IContext;

public interface IContextAdapterAutolayoutService {

	/**
	 * Get the context of for which this service applied.
	 * @return An non-null context instance.
	 */
	IContext getContext();
	
	/**
	 * Get Service provider which was be used to instantiate this service. 
	 * This method could be used to get access to other services, which are registered for
	 * that context. 
	 * @return An non-null service provider which this service is registered with
	 */
	IContextAdapterServiceProvider getServiceProvider();
	
	/**
	 * The CA does not need to implement the auto-layout service. This method tests is it is implemented.
	 * @return true if service is implemented, false otherwise.
	 */
	boolean isImplemented();
	
}
