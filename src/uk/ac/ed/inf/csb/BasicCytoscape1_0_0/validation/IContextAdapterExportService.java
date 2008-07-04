package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation;

import java.io.File;

import org.pathwayeditor.businessobjectsAPI.IMap;
import org.pathwayeditor.contextadapter.publicapi.ExportServiceException;
import org.pathwayeditor.contextadapter.publicapi.IContext;

public interface IContextAdapterExportService {
    
	/**
	 * 
	 * @return the {@link IContext} to which this {@link ExportServiceException} service belongs.
	 *  Must not return  null
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
	 * A method to return a human-readable form of the 
	 * @return A <code>String</code>. Must not return null.
	 */
	String getDisplayName();
	
	/**
	 * Returns the unique code for this export service. M
	 * @return A <code>String</code>, must not be null.
	 */
	String getCode();
	
	/**
	 * Returns the recommended suffix. This  information is for the client so that it knows
     * what extension might be preferred and so it can display it in a file dialogue. The
     * export(IMap, File ) method will take the file as it comes and need not append the suffix.
	 * @return A <code>String</code>. Must not be null.
	 */
	String getRecommendedSuffix();
	
	/**
	 * Exports a map to file. It is the client's responsibility to ensure the
	 * <code>exportFile</code> parameter is a valid writable file. Inclusion of a suffix is optional.
	 * Implementations do not have to enforce that  getRecommendedSuffix() is appended.
	 * @param map An {@link IMap} object
	 * @param exportFile A {@link File}
	 * @throws ExportServiceException
	 */
	void exportMap(IMap map, File exportFile) throws ExportServiceException;
	
}
