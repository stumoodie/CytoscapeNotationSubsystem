package plugin;

import org.pathwayeditor.contextadapter.publicapi.IContextAdapterServiceProvider;
import org.pathwayeditor.contextadapter.publicapi.IContextAdapterServiceProviderLoader;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.BasicCytoscapeContextAdapterServiceProvider;

public class CytoscapeServiceProviderLoader implements IContextAdapterServiceProviderLoader {
    /*
     * Default no-arg constructor
     */
	public CytoscapeServiceProviderLoader() {
		
	}

	public IContextAdapterServiceProvider getContextAdapterServiceProvider() {
		return new BasicCytoscapeContextAdapterServiceProvider();
	}

}
