package org.pathwayeditor.notations.cytoscape;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationAutolayoutService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationConversionService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationExportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.notations.cytoscape.export.GMMXLExportService;
import org.pathwayeditor.notations.cytoscape.export.SIFExportService;
import org.pathwayeditor.notationsubsystem.toolkit.definition.GeneralNotation;

public class CytoscapeNotationSubsystem implements INotationSubsystem {
	private static final String GLOBAL_ID = "org.pathwayeditor.notations.cytoscape";
	public static final String DISPLAY_NAME = "Cytoscape Notation";
	private static final String NAME = "Cytoscape Context";
	private static final Version VERS = new Version(1, 0, 0);
	
	private final CytoscapeSyntaxService syntaxService;
	private final INotation context;
	private final INotationValidationService cytoscapeValidationService;
	private final Set<INotationExportService> exportServices;
	

	public CytoscapeNotationSubsystem() {
	    this.context = new GeneralNotation(GLOBAL_ID, DISPLAY_NAME, NAME, VERS);
		this.syntaxService = new CytoscapeSyntaxService(this);
		this.cytoscapeValidationService = new CytoscapeNotationValidationService(this);
		this.exportServices = new HashSet<INotationExportService>();
		this.exportServices.add(new SIFExportService(this));
		this.exportServices.add(new GMMXLExportService(this));
	}
	

	public INotation getNotation() {
		return this.context;
	}

	public Set<INotationExportService> getExportServices() {
		return this.exportServices;
	}

	@SuppressWarnings("unchecked")
	public Set getImportServices() {
		return Collections.emptySet();
	}

	@SuppressWarnings("unchecked")
	public Set getPluginServices() {
		return Collections.emptySet();
	}

	public CytoscapeSyntaxService getSyntaxService() {
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
            return CytoscapeNotationSubsystem.this;
        }

	}

    public boolean isFallback() {
        return false;
    }
}
