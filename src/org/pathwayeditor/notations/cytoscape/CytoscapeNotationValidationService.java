/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/
package org.pathwayeditor.notations.cytoscape;

import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;
import org.pathwayeditor.notations.cytoscape.validation.CytoscapeValidator;


public class CytoscapeNotationValidationService implements INotationValidationService {
    private final CytoscapeNotationSubsystem notationSubsystem;
    private final CytoscapeValidator validator;
    
    public CytoscapeNotationValidationService(CytoscapeNotationSubsystem notationSubsystem) {
        this.notationSubsystem = notationSubsystem;
        this.validator = CytoscapeValidator.getInstance();
    }
    
    public ICanvas getCanvasBeingValidated() {
        return this.validator.getCanvas();
    }

    public Set<IValidationRuleDefinition> getRules() {
    	return this.validator.getRuleStore().getAllRuleDefinitions();
    }

    public IValidationReport getValidationReport() {
        return this.validator.getValidationReport();
    }

    public boolean hasBeenValidated() {
    	return this.validator.hasBeenValidated();
    }

    public boolean isImplemented() {
        return true;
    }

    public boolean isReadyToValidate() {
        return this.validator.getCanvas() != null;
    }

    public void setCanvasToValidate(ICanvas mapToValidate) {
    	this.validator.setCanvas(mapToValidate);
    }

    public void validate() {
    	this.validator.validateCanvas();
    }

    public INotation getNotation() {
        return this.notationSubsystem.getNotation();
    }

    public INotationSubsystem getNotationSubsystem() {
        return this.notationSubsystem;
    }

}
