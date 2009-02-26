package org.pathwayeditor.notations.cytoscape.importservice;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.ImportServiceException;
import org.pathwayeditor.notations.cytoscape.export.CytoscapeNdom;

public interface IBusinessObjectBuilder {
	
	void setCanvas(ICanvas canvas);
	
	ICanvas getCanvas();
	
	void build() throws ImportServiceException;
	
	CytoscapeNdom getNDom();
}
