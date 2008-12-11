package org.pathwayeditor.notations.cytoscape;

import java.util.Collections;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationAutolayoutService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationConversionService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationExportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.GeneralNotation;

public class BasicCytoscapeContextAdapterServiceProvider implements INotationSubsystem {
	private static final String GLOBAL_ID = "org.pathwayeditor.notations.cytoscape.BasicCytoscape";
	public static final String DISPLAY_NAME = "Cytoscape Notation";
	private static final String NAME = "Cytoscape Context";
	private static final Version VERS = new Version(1, 0, 0);
	
	private BasicCytoscapeContextAdapterSyntaxService syntaxService;
	private INotation context;
	private INotationValidationService cytoscapeValidationService;

	public BasicCytoscapeContextAdapterServiceProvider() {
	    this.context = new GeneralNotation(GLOBAL_ID, DISPLAY_NAME, NAME, VERS);
		this.syntaxService = new BasicCytoscapeContextAdapterSyntaxService(this);
		cytoscapeValidationService = new CytoscapeNotationValidationService(this);
	}
	

	public INotation getNotation() {
		return this.context;
	}

	public Set<INotationExportService> getExportServices() {
		return Collections.emptySet();
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


	public INotationValidationService getValidationService() {
		return cytoscapeValidationService;
	}

	public Set<INotationConversionService> getConversionServices() {
		return Collections.emptySet();
	}

	public INotationAutolayoutService getAutolayoutService() {
		return new DefaultAutolayoutService();
	}	

	private class DefaultAutolayoutService implements INotationAutolayoutService {

        public boolean isImplemented() {
            return false;
        }

        public void layout(ICanvas canvas) {
            throw new UnsupportedOperationException("Not implemented!");
        }

        public INotation getNotation() {
            return context;
        }

        public INotationSubsystem getNotationSubsystem() {
            return BasicCytoscapeContextAdapterServiceProvider.this;
        }

	}

    public boolean isFallback() {
        return false;
    }
}
