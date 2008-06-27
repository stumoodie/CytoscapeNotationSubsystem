package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.stubs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.export.StreamService;

public class StreamServiceStub extends StreamService {

	@Override
	public OutputStream makeOutStream(File exportFile) throws FileNotFoundException {
		return new OutStreamStub();
	}
}
