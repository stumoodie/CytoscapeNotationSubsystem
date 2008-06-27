package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.export;

import org.pathwayeditor.contextadapter.publicapi.IContextAdapterServiceProvider;
import org.pathwayeditor.contextadapter.toolkit.ndom.IModelObject;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.GraphStub;

public class CytoscapeValidatorStub extends CytoscapeValidationService{

	public boolean mapValidated;
	public CytoscapeValidatorStub(IContextAdapterServiceProvider provider) {
		super(provider);
	}
	@Override
	public void validateMap() {
		mapValidated=true;
	}
	
	@Override
	public IModelObject getNDOM(){
		return new GraphStub();
	}

}
