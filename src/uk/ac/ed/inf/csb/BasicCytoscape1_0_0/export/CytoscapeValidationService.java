package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.export;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.pathwayeditor.businessobjectsAPI.IMap;
import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.IContextAdapterServiceProvider;
import org.pathwayeditor.contextadapter.publicapi.IValidationReport;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleDefinition;
import org.pathwayeditor.contextadapter.toolkit.ndom.IModelObject;
import org.pathwayeditor.contextadapter.toolkit.ndom.AbstractNDOMParser.NdomException;
import org.pathwayeditor.contextadapter.toolkit.validation.IToolkitValidationService;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.NDOMFactory;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.INode;

public class CytoscapeValidationService implements  IToolkitValidationService {

	private NDOMFactory factory;
	private IMap map;
	private IContextAdapterServiceProvider serviceProvider;
	private IContext context;
	private boolean mapValid=true;

	public CytoscapeValidationService(IContextAdapterServiceProvider provider) {
		this.serviceProvider = provider;
		context = provider.getContext();
	}

	public IContext getContext() {
		return context;
	}

	public IMap getMapBeingValidated() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IValidationRuleDefinition> getRules() {
		// TODO Auto-generated method stub
		return null;
	}

	public IContextAdapterServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public IValidationReport getValidationReport() {
		return new CytoscapeValidationReport(mapValid);
	}

	public boolean hasMapBeenValidated() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isImplemented() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isReadyToValidate() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setMapToValidate(IMap map) {
		this.map = map;
	}

	public void validateMap() {
		mapValid=true;
		factory = new NDOMFactory(map.getTheSingleRootMapObject());
		try {
			factory.parse();
			List <INode>nodesList = factory.getGraph().getNodes();
			Set <INode> nodesSet = new HashSet<INode>(nodesList);
			if(nodesList.size()!=nodesSet.size()) //FIXME - NH - but real cytoscape maps may have nodes with the same name....
				mapValid=false;
		} catch (NdomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public IModelObject getNDOM() {
		return factory.getGraph();
	}

}
