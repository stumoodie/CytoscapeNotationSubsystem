package org.pathwayeditor.notations.cytoscape.ndom.stubs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.pathwayeditor.notations.cytoscape.export.StreamService;

public class StreamServiceStub extends StreamService {

	@Override
	public OutputStream makeOutStream(File exportFile) throws FileNotFoundException {
		return new OutStreamStub();
	}
}
