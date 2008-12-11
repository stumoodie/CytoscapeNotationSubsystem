package org.pathwayeditor.notations.cytoscape;

import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;


public class CytoscapeNotationValidationService implements INotationValidationService {
    private final CytoscapeNotationSubsystem notationSubsystem;
    
    public CytoscapeNotationValidationService(CytoscapeNotationSubsystem notationSubsystem) {
        this.notationSubsystem = notationSubsystem;
    }
    
    public ICanvas getMapBeingValidated() {
        throw new UnsupportedOperationException("validation not implemented for this notation");
    }

    public List<IValidationRuleDefinition> getRules() {
        throw new UnsupportedOperationException("validation not implemented for this notation");
    }

    public IValidationReport getValidationReport() {
        throw new UnsupportedOperationException("validation not implemented for this notation");
    }

    public boolean hasMapBeenValidated() {
        throw new UnsupportedOperationException("validation not implemented for this notation");
    }

    public boolean isImplemented() {
        return false;
    }

    public boolean isReadyToValidate() {
        throw new UnsupportedOperationException("validation not implemented for this notation");
    }

    public void setMapToValidate(ICanvas mapToValidate) {
        throw new UnsupportedOperationException("validation not implemented for this notation");
    }

    public void validateMap() {
        throw new UnsupportedOperationException("validation not implemented for this notation");
    }

    public INotation getNotation() {
        return this.notationSubsystem.getNotation();
    }

    public INotationSubsystem getNotationSubsystem() {
        return this.notationSubsystem;
    }

}
