package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.pathwayeditor.businessobjectsAPI.IMap;
import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.IContextAdapterServiceProvider;
import org.pathwayeditor.contextadapter.publicapi.IValidationReport;
import org.pathwayeditor.contextadapter.publicapi.IValidationReportItem;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleConfigurer;
import org.pathwayeditor.contextadapter.toolkit.ndom.AbstractNDOMParser;
import org.pathwayeditor.contextadapter.toolkit.ndom.INDOMValidationService;
import org.pathwayeditor.contextadapter.toolkit.ndom.INdomModel;
import org.pathwayeditor.contextadapter.toolkit.ndom.NdomException;
import org.pathwayeditor.contextadapter.toolkit.validation.IDefaultValidationRuleConfigLoader;
import org.pathwayeditor.contextadapter.toolkit.validation.IValidationRuleStore;
import org.pathwayeditor.contextadapter.toolkit.validation.RuleStore;
import org.pathwayeditor.contextadapter.toolkit.validation.RuleValidationReportBuilder;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.NDOMFactory;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IEdge;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.INode;

public class CytoscapeNDOMValidationService implements INDOMValidationService {

	private static final String UNIQUE_NODE_NAME_ERROR = "Node names must be unique";
	private static final String UNIQUE_EDGE_NAME_ERROR = "Edges between two nodes must have unqiue interaction names";
	private List<INode> errorNodeEdgesList = new ArrayList<INode>();
	private List<INode> errorNodeNamesList = new ArrayList<INode>();
	private String errorMessage;
	private boolean ndomcreated;
	private boolean mapValidated;
	private IContext context;
	private IValidationReport validationReport;
	List<IValidationReportItem> reportItems;
	private boolean beenValidated = false;
	private IMap mapToValidate;
	private IValidationRuleConfigurer configurer;
	private AbstractNDOMParser factory;
	private RuleValidationReportBuilder reportBuilder;
	private IContextAdapterServiceProvider serviceProvider;
	private boolean readyToValidate=true;
	
	private static CytoscapeNDOMValidationService instance;

	private CytoscapeNDOMValidationService(IContextAdapterServiceProvider provider) {
		this.serviceProvider= provider;
		this.context        = provider.getContext();
	}
	
	public static synchronized CytoscapeNDOMValidationService getInstance(IContextAdapterServiceProvider provider){
		if(instance==null){
			instance=new CytoscapeNDOMValidationService(provider);
		}
		return instance;
	}

	public INdomModel getNDOM() {
		return ((NDOMFactory)getFactory()).getGraph();
	}
	
	protected AbstractNDOMParser getFactory() {
		return factory;
	}

	protected AbstractNDOMParser createNdomFactory() {
		AbstractNDOMParser ret=new NDOMFactory(getMapBeingValidated().getTheSingleRootMapObject());
		return ret;
	}
	
	public IMap getMapBeingValidated() {
		return mapToValidate;
	}
	
	protected IDefaultValidationRuleConfigLoader getRuleLoader() {
		return CytoscapeRuleLoader.getInstance();
	}
	
	public final void validateMap() {
		factory = createNdomFactory();
		reportBuilder = new RuleValidationReportBuilder(getRuleStore(), mapToValidate);
		factory.setReportBuilder(reportBuilder);
		factory.setRmo(mapToValidate.getTheSingleRootMapObject());
		try {
			generateNdom();
		} catch (NdomException e) {
			handleNdomException(e);
		} finally{
			beenValidated=true;
			reportBuilder.createValidationReport();
		    validationReport = reportBuilder.getValidationReport();
		}
	}
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
			if (hasNonUniqueSrcInteraction(node.getSrcEdges()) || hasNonUniqueTargetInteraction(node.getTargetEdges())) {
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
			reportBuilder.setRuleFailed(null, getRuleStore().getRuleById(CytoscapeRuleLoader.UNIQUE_NODES),UNIQUE_NODE_NAME_ERROR);
		if(errorMessage.indexOf(UNIQUE_EDGE_NAME_ERROR)!=-1)
			reportBuilder.setRuleFailed(null, getRuleStore().getRuleById(CytoscapeRuleLoader.UNIQUE_EDGES),UNIQUE_EDGE_NAME_ERROR);
	}

	private boolean hasNonUniqueSrcInteraction(Set<IEdge> edges) {
		Set <String>edgeNames=new HashSet<String>();
		for (IEdge edge: edges){
			edgeNames.add(edge.getInteraction()+edge.getTargetNode().getName());
		}
		if(edges.size()!=edgeNames.size())
			return true;
		return false;
	}
	
	private boolean hasNonUniqueTargetInteraction(Set<IEdge> edges) {
		Set <String>edgeNames=new HashSet<String>();
		for (IEdge edge: edges){
			edgeNames.add(edge.getInteraction()+edge.getSrcNode().getName());
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

	protected void handleNdomException(NdomException e) {
		throw new RuntimeException(e);//no ndomExceptions should ever be thrown in this basic context
	}

	public boolean hasBeenValidated() {
		return mapValidated;
	}

	public boolean ndomWasCreated() {
		return ndomcreated;
	}

	public IValidationRuleStore getRuleStore() {
		return RuleStore.getInstance(getRuleLoader());
	}

	public IValidationReport getValidationReport() {
		return validationReport;
	}

	public boolean isReadyToValidate() {
		return readyToValidate;
	}

	public void setMapToValidate(IMap map) {
		this.mapToValidate=map;
	}


}
