package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.stubs;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class OutStreamStub extends OutputStream{
	public List <String >data = new ArrayList<String>();
	public boolean isWritten;
	@Override
	public void write(int b) throws IOException {
		isWritten=true;
	}
	
	@Override
	public void write (byte [] b)throws java.io.IOException{
		data.add(new String(b));
		isWritten=true;
	}

}
