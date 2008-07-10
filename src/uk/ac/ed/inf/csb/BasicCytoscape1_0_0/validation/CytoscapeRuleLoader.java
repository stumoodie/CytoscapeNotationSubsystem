package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleConfig;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleDefinition;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleDefinition.RuleLevel;
import org.pathwayeditor.contextadapter.toolkit.validation.IDefaultValidationRuleConfigLoader;
import org.pathwayeditor.contextadapter.toolkit.validation.ValidationRuleConfig;
import org.pathwayeditor.contextadapter.toolkit.validation.ValidationRuleDefinition;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.BasicCytoscapeContextAdapterServiceProvider;


public class CytoscapeRuleLoader implements IDefaultValidationRuleConfigLoader {
	
	private CytoscapeRuleLoader(){
		
	}
	public static final int UNIQUE_EDGES = 1;
	public static final int UNIQUE_NODES=2;
	static IValidationRuleDefinition uniqueEdges, uniqueNodes;
	
	static IValidationRuleConfig configUniqueEdges ,configUniqueNodes;
	static IContext context = BasicCytoscapeContextAdapterServiceProvider.getInstance().getContext();
	private static IDefaultValidationRuleConfigLoader instance;
	static {
		uniqueEdges = new ValidationRuleDefinition(context, "Edges between two nodes must have unique interaction names", "UNIQUE_EDGES", UNIQUE_EDGES, RuleLevel.MANDATORY);
		uniqueNodes = new ValidationRuleDefinition(context,  "Node names must be unique","UNIQUE_NODES", UNIQUE_NODES, RuleLevel.OPTIONAL);
		
		configUniqueEdges = new ValidationRuleConfig(uniqueEdges, false, true);// not run, error
		configUniqueNodes = new ValidationRuleConfig(uniqueNodes, true, false); // run, warning
		
	}

	public Set<IValidationRuleConfig> loadDefaultRuleConfigurations() {
		Set <IValidationRuleConfig> rc =  new HashSet<IValidationRuleConfig>(Arrays.asList(new IValidationRuleConfig[]{configUniqueEdges, configUniqueNodes}));
		return rc;
	}

	public static IDefaultValidationRuleConfigLoader getInstance() {
		if(instance == null){
			instance= new CytoscapeRuleLoader();
		}
		return instance;
	}
}
