package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.pathwayeditor.contextadapter.toolkit.ndom.INdomModel;
import org.pathwayeditor.contextadapter.toolkit.ndom.NdomException;
import org.pathwayeditor.contextadapter.toolkit.validation.IDefaultValidationRuleConfigLoader;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.NDOMFactory;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IEdge;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.INode;

public class CytoscapeNDOMValidationService extends AbstractNDOMValidationService  {

	private static final String UNIQUE_NODE_NAME_ERROR = "Node names must be unique";
	private static final String UNIQUE_EDGE_NAME_ERROR = "Edges between two nodes must have unqiue interaction names";
	private List<INode> errorNodeEdgesList = new ArrayList<INode>();
	private List<INode> errorNodeNamesList = new ArrayList<INode>();
	private String errorMessage;
	private boolean ndomcreated;
	private boolean mapValidated;
	private static CytoscapeNDOMValidationService instance;

	private CytoscapeNDOMValidationService(IContextAdapterServiceProvider provider) {
		super(provider);
	}
	
	public static synchronized CytoscapeNDOMValidationService getInstance(IContextAdapterServiceProvider provider){
		if(instance==null){
			instance=new CytoscapeNDOMValidationService(provider);
		}
		return instance;
	}

	@Override
	public INdomModel getNDOM() {
		return ((NDOMFactory)getFactory()).getGraph();
	}

	@Override
	protected AbstractNDOMParser createNdomFactory() {
		return new NDOMFactory(getMapBeingValidated().getTheSingleRootMapObject());
	}
	
	@Override
	protected IDefaultValidationRuleConfigLoader getRuleLoader() {
		return CytoscapeRuleLoader.getInstance();
	}
	
	@Override
	protected void generateNdom() throws NdomException {
		ndomcreated=false;
		mapValidated=false;
		clearErrorNodesList();
		getFactory().parse();
		ndomcreated=true;
		List<INode> nodesList = ((NDOMFactory) getFactory()).getGraph().getNodes();
		Set<INode> nodesSet = new HashSet<INode>(nodesList);
		if (nodesList.size() != nodesSet.size()) { // Real cytoscape maps may have nodes with the same name, so this is an optional error....
			Collections.sort(nodesList);
			String prevName="";
			String currName="";
			for (INode node: nodesList){
				currName=node.getName();
				if(currName.equals(prevName))
					errorNodeNamesList .add(node);
				prevName=currName;
			}
			if(errorNodeNamesList.size()!=0)
				errorMessage+=UNIQUE_NODE_NAME_ERROR;
		}
		for (INode node : nodesList) {
			if (hasNonUniqueInteraction(node.getSrcEdges()) || hasNonUniqueInteraction(node.getTargetEdges())) {
				errorNodeEdgesList.add(node);
			}
		}
		if(errorNodeEdgesList.size()!=0)
			errorMessage+=UNIQUE_EDGE_NAME_ERROR;
		if(errorMessage.length()!=0)
			setRuleFailures();
		mapValidated=true;
	}

	void setRuleFailures() {
		if(errorMessage.indexOf(UNIQUE_NODE_NAME_ERROR)!=-1)
			getReportBuilder().setRuleFailed(null, getRuleStore().getRuleById(CytoscapeRuleLoader.UNIQUE_NODES),UNIQUE_NODE_NAME_ERROR);
		if(errorMessage.indexOf(UNIQUE_EDGE_NAME_ERROR)!=-1)
			getReportBuilder().setRuleFailed(null, getRuleStore().getRuleById(CytoscapeRuleLoader.UNIQUE_EDGES),UNIQUE_EDGE_NAME_ERROR);
	}

	private boolean hasNonUniqueInteraction(Set<IEdge> edges) {
		Set <String>edgeNames=new HashSet<String>();
		for (IEdge edge: edges){
			edgeNames.add(edge.getName());
		}
		if(edges.size()!=edgeNames.size())
			return true;
		return false;
	}

	private void clearErrorNodesList() {
		errorNodeEdgesList.clear();
		errorNodeNamesList.clear();
		errorMessage="";
	}

	@Override 
	protected void handleNdomException(NdomException e) {
		throw new RuntimeException(e);//no ndomExceptions should ever be thrown in this basic context
	}

	public boolean hasBeenValidated() {
		return mapValidated;
	}

	public boolean ndomWasCreated() {
		return ndomcreated;
	}


}
