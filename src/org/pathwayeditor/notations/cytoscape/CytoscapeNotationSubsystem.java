package org.pathwayeditor.notations.cytoscape;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelEdgeChangeEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelNodeChangeEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ModelStructureChangeType;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationAutolayoutService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationConversionService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationExportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationImportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationPluginService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.notations.cytoscape.export.SIFExportService;
import org.pathwayeditor.notations.cytoscape.export.XGMMLExportService;
import org.pathwayeditor.notations.cytoscape.importservice.XGMMLImportService;
import org.pathwayeditor.notationsubsystem.toolkit.definition.GeneralNotation;

public class CytoscapeNotationSubsystem implements INotationSubsystem {
	private static final String GLOBAL_ID = "org.pathwayeditor.notations.cytoscape";
	public static final String DISPLAY_NAME = "Cytoscape Notation";
	private static final String NAME = "Cytoscape Context";
	private static final Version VERS = new Version(1, 0, 0);
	private static final String DEFAULT_EDGE_NAME = "Edge";
	private static final String DEFAULT_NODE_NAME = "Node";
	
	private final CytoscapeSyntaxService syntaxService;
	private final INotation context;
	private final INotationValidationService cytoscapeValidationService;
	private final Set<INotationExportService> exportServices;
	private final Set<INotationImportService> importServices;
	private final IModelChangeListener modelListener;
	

	public CytoscapeNotationSubsystem() {
	    this.context = new GeneralNotation(GLOBAL_ID, DISPLAY_NAME, NAME, VERS);
		this.syntaxService = new CytoscapeSyntaxService(this);
		this.cytoscapeValidationService = new CytoscapeNotationValidationService(this);
		this.exportServices = new HashSet<INotationExportService>();
		this.exportServices.add(new SIFExportService(this));
		this.exportServices.add(new XGMMLExportService(this));
		this.importServices = new HashSet<INotationImportService>();
		this.importServices.add(new XGMMLImportService(this));
		this.modelListener = new IModelChangeListener(){

			public void edgeStructureChange(IModelEdgeChangeEvent event) {
				if(event.getChangeType().equals(ModelStructureChangeType.ADDED)){
					ILinkAttribute att = event.getChangedItem().getAttribute();
					IPlainTextAnnotationProperty prop = (IPlainTextAnnotationProperty)att.getProperty(CytoscapeSyntaxService.EDGE_NAME_PROP);
					StringBuilder newName = new StringBuilder(DEFAULT_EDGE_NAME);
					newName.append(att.getCreationSerial());
					prop.setValue(newName.toString());
				}
			}

			public void nodeStructureChange(IModelNodeChangeEvent event) {
				if(event.getChangeType().equals(ModelStructureChangeType.ADDED)){
					if(event.getChangedItem() instanceof IShapeNode){
						IShapeAttribute att = (IShapeAttribute)event.getChangedItem().getAttribute();
						IPlainTextAnnotationProperty prop = (IPlainTextAnnotationProperty)att.getProperty(CytoscapeSyntaxService.NODE_NAME_PROP);
						StringBuilder newName = new StringBuilder(DEFAULT_NODE_NAME);
						newName.append(att.getCreationSerial());
						prop.setValue(newName.toString());
					}
				}
			}
			
		};
	}
	

	public INotation getNotation() {
		return this.context;
	}

	public Set<INotationExportService> getExportServices() {
		return this.exportServices;
	}

	public Set<INotationImportService> getImportServices() {
		return this.importServices;
	}

	public Set<INotationPluginService> getPluginServices() {
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


	public void registerCanvas(ICanvas canvasToRegister) {
		canvasToRegister.getModel().addModelChangeListener(this.modelListener);
	}
	
	public void unregisterCanvas(ICanvas canvasToRegister) {
		canvasToRegister.getModel().removeModelChangeListener(this.modelListener);
	}
}
