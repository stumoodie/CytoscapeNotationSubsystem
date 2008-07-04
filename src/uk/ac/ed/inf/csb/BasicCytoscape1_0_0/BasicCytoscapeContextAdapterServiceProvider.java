package uk.ac.ed.inf.csb.BasicCytoscape1_0_0;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.GeneralContext;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.export.SIFExportAdapter;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.export.SIFExportService;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IGraph;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.ContextValidationService;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.CytoscapeNDOMValidationService;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.IContextAdapterAutolayoutService;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.IContextAdapterServiceProvider;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.IContextAdapterValidationService;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.IToolKitContextAdapterServiceProvider;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.IToolKitContextAdapterValidationService;

public class BasicCytoscapeContextAdapterServiceProvider implements IToolKitContextAdapterServiceProvider {
	private static final String GLOBAL_ID = "uk.ac.ed.inf.csb.BasicCytoscape1_0_0.BasicCytoscape";
	//private static final String GLOBAL_ID = "12635452516346262546";
	private static final String DISPLAY_NAME = "Context to test code generation of a basic cytoscape";
	private static final String NAME = "Basic Cytoscape Context";
	private static final int[] VERS = getVersion("1_0_0");
	private static BasicCytoscapeContextAdapterServiceProvider instance;
	
	private static int[] getVersion(String ver) {
		String[] l = ver.split("_");
		int majorVersion = Integer.parseInt(l[0]);
		int minorVersion = Integer.parseInt(l[1]);
		int patchVersion = Integer.parseInt(l[2]);

		return new int[] { majorVersion, minorVersion, patchVersion };
	}
	private BasicCytoscapeContextAdapterSyntaxService syntaxService;
	private IContext context;
	private IToolKitContextAdapterValidationService cytoscapeValidationService;

	 BasicCytoscapeContextAdapterServiceProvider() {
		this.context = new GeneralContext(GLOBAL_ID, DISPLAY_NAME, NAME,
				VERS[0], VERS[1], VERS[2]);
		this.syntaxService = new BasicCytoscapeContextAdapterSyntaxService(this);
		cytoscapeValidationService = new ContextValidationService(this,CytoscapeNDOMValidationService.getInstance(this));
	}
	

	public IContext getContext() {
		return this.context;
	}

	@SuppressWarnings("unchecked")
	public Set getExportServices() {
		return new HashSet(Collections.singletonList(new SIFExportService(this,new SIFExportAdapter<IGraph>())));
	}

	@SuppressWarnings("unchecked")
	public Set getImportServices() {
		return Collections.emptySet();
	}

	@SuppressWarnings("unchecked")
	public Set getPluginServices() {
		return Collections.emptySet();
	}

	public BasicCytoscapeContextAdapterSyntaxService getSyntaxService() {
		return this.syntaxService;
	}


	public IContextAdapterValidationService getValidationService() {
		return cytoscapeValidationService;
	}

	public Set getConversionServices() {
		return Collections.emptySet();
	}

	public IContextAdapterAutolayoutService getAutolayoutService() {
		return new DefaultAutolayoutService();
	}	

	private class DefaultAutolayoutService implements IContextAdapterAutolayoutService {

		public IContext getContext() {
			return context;
		}

		public boolean isImplemented() {
			return false;
		}

		public IContextAdapterServiceProvider getServiceProvider() {
			return  BasicCytoscapeContextAdapterServiceProvider.this;
		}
		
	}

	public static IContextAdapterServiceProvider getInstance() {
		if(instance == null){
			instance= new BasicCytoscapeContextAdapterServiceProvider();
		}
		return instance;
	}


	public IToolKitContextAdapterValidationService getToolKitValidationService() {
		return cytoscapeValidationService;
	}
}
