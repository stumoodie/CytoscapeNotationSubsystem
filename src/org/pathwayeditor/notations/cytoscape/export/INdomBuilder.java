package org.pathwayeditor.notations.cytoscape.export;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;

public interface INdomBuilder {
	
	ICanvas getCanvas();
	
	void buildNdom();
	
	Object getNDom();
}
