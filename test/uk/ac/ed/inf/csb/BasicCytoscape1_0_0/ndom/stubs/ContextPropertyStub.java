/**
 * 
 */
package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.stubs;

import org.pathwayeditor.businessobjectsAPI.IContextProperty;

public class ContextPropertyStub implements IContextProperty {

	private String stringValue;

	public ContextPropertyStub(String value) {
		this.stringValue=value;
		// TODO Auto-generated constructor stub
	}
	
	public IContextProperty getCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		return "interacts";
	}

	public String getValue() {
		return stringValue;
	}

	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

}