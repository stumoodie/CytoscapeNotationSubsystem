package org.pathwayeditor.notations.cytoscape.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.ExportServiceException;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationExportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.notations.cytoscape.validation.CytoscapeValidator;

import cytoscape.data.writers.InteractionWriter;

public class SIFExportService implements INotationExportService {
	public static final String DISPLAYNAME = "Cytoscape SIF File";
	private static final String SUFFIX = "sif";
	private static final String VALIDATION_ERROR = "The map is invalid and cannot be exported. Run validation to identify problem";
	private final INotationSubsystem serviceProvider;
	private final String CODE = "cytoscape_sif";
	private Writer fos;

	public SIFExportService(INotationSubsystem notationService) {
		this.serviceProvider = notationService;
	}

	public void exportMap(ICanvas map, File exportFile) throws ExportServiceException {
		if(map == null || exportFile == null) throw new IllegalArgumentException("parameters cannot be null");
		
		try {
			validate(map);
			this.fos = new FileWriter(exportFile);
			INdomBuilder ndomBuilder = new CytoscapeNdomBuilder(map);
			ndomBuilder.buildNdom();
			CytoscapeNdom network = (CytoscapeNdom)ndomBuilder.getNDom();
			InteractionWriter.writeInteractions(network.getNetwork(), this.fos);
		} catch (IOException e) {
			throw new ExportServiceException(e);
		}
		finally{
			if(this.fos != null){
				try {
					this.fos.close();
				} catch (IOException e) {
					// suppress exception from close as we are in a finally clause 
				}
			}
		}
	}

	private void validate(ICanvas map) throws ExportServiceException {
		CytoscapeValidator validator = CytoscapeValidator.getInstance();
		validator.setCanvas(map);
		validator.validateCanvas();
		IValidationReport report = validator.getValidationReport();
		if(!report.isValid()){
			throw new ExportServiceException(VALIDATION_ERROR);
		}
	}

	public String getCode() {
		return CODE;
	}

	public String getDisplayName() {
		return DISPLAYNAME;
	}

	public String getRecommendedSuffix() {
		return SUFFIX;
	}

	public INotation getNotation() {
		return this.serviceProvider.getNotation();
	}

	public INotationSubsystem getNotationSubsystem() {
		return this.serviceProvider;
	}

}
