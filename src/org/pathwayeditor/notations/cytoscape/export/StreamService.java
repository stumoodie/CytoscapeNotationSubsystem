package org.pathwayeditor.notations.cytoscape.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author nhanlon
 * A factory for making outputstreams
 */
public class StreamService {

	public OutputStream makeOutStream(File exportFile) throws FileNotFoundException {
		return new FileOutputStream(exportFile);
	}
	

}
