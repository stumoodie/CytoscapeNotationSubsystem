package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation;

/**
 * 
 * This interface is used by an application context to load the
 * context adapter.
 * Implementations need just implement one method which will return
 * the context's {@link IContextAdapterServiceProvider}.
 * 
 * Typical implementations of {@link IContextAdapterServiceProvider} will provide
 * a singleton.
 *  
 * @author Richard Adams
 *
 */
public interface IContextAdapterServiceProviderLoader {
    
	/**
	 * @return A non-null {@link IContextAdapterServiceProvider}
	 */
	public IContextAdapterServiceProvider getContextAdapterServiceProvider();
}
