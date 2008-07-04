package plugin;


import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.BasicCytoscapeContextAdapterServiceProvider;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.IContextAdapterServiceProvider;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.IContextAdapterServiceProviderLoader;

public class CytoscapeServiceProviderLoader implements IContextAdapterServiceProviderLoader {
    /*
     * Default no-arg constructor
     */
	public CytoscapeServiceProviderLoader() {
		
	}

	public IContextAdapterServiceProvider getContextAdapterServiceProvider() {
		return BasicCytoscapeContextAdapterServiceProvider.getInstance();
	}

}
