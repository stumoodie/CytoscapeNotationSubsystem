package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation;

import java.util.Set;

import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.IContextAdapterConversionService;
import org.pathwayeditor.contextadapter.publicapi.IContextAdapterExportService;
import org.pathwayeditor.contextadapter.publicapi.IContextAdapterImportService;
import org.pathwayeditor.contextadapter.publicapi.IContextAdapterPluginService;

/**
 * This interfaces provides a client with access to the services provided by a context adapter. It will typically
 * be implemented behind an Eclipse extension point.  
 * @author smoodie
 *
 */
public interface IContextAdapterServiceProvider {

	/**
	 * The context (graphical notation) that is defined by this context adapter and for which the services
	 * are specifically for.  
	 * @return A context instance, which cannot be null.
	 */
	IContext getContext();
	
	/**
	 * Obtains a syntax service implementation that is used by clients (typically EPE) to define the appearance
	 * of the graphical notation and the syntactic rules of its shapes and links.
	 * @return an implementation of the syntax service, which cannot be null.
	 */
	IContextAdapterSyntaxService getSyntaxService();

	/**
	 * Obtains a set of export services from the context adapter. These provide implementations that
	 * can export a map that uses this context into different types of export format. The lists effectively
	 * informs a client with all the export services it can perform on a given context.
	 * @return A set of export services or an empty set if there are none.
	 */
	Set<IContextAdapterExportService> getExportServices();
	
	/**
	 * Obtains a set of import services from the context adapter. These provide implementations that
	 * can import information in a file and use it to create a new map that uses this context.
	 * @return A set of import services or an empty set if there are none.
	 */
	Set<IContextAdapterImportService> getImportServices();
	
	/**
	 * Obtains the autolayout services from the context adapter. This can be used by a client to automatically
	 * layout a map that uses the context supported by the context adapter.
	 * @return An auto-layout service guaranteed to be non-null.
	 */
	IContextAdapterAutolayoutService getAutolayoutService();
	
	/**
	 * Obtains a validation service that is used to validate a map.
	 * @return An instance of a validation service. Guaranteed to be non-null.
	 */
	IContextAdapterValidationService getValidationService();
	
	/**
	 * Obtains the plugin services from the context adapter. This can be used by a client to perfrom
	 * some actions using the information provided in a map that uses the context supported by this context adapter.
	 * @return A set of plugin services or an empty set if there are none.
	 */
	Set<IContextAdapterPluginService> getPluginServices();
	
	/**
	 * Obtains a set of conversion services. 
	 * @return
	 */
	Set<IContextAdapterConversionService> getConversionServices();
}
