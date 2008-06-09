package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom;

import org.pathwayeditor.businessobjectsAPI.IRootMapObject;
import org.pathwayeditor.contextadapter.toolkit.ndom.AbstractNDOMParser;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.ICytoscapeValidator;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IGraph;

/**
 * @author nhanlon
 *
 */
public class NDOMFactory extends AbstractNDOMParser{

	private IGraph graph;
	private ICytoscapeValidator cytoscapeValidator;

	public NDOMFactory(IRootMapObject rmo) {
		super(rmo);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.contextadapter.toolkit.ndom.AbstractNDOMParser#connectivity()
	 * calculate the average cardinality of nodes in the graph, the max cardinality and the minimum
	 */
	@Override
	protected void connectivity() {
		//calculate the average cardinality of nodes in the graph, the max cardinality and the minimum
	}

	@Override
	protected void ndom() {
		graph = new CytoscapeGraph(getRmo());
		
	}

    
	/* (non-Javadoc)
	 * @see org.pathwayeditor.contextadapter.toolkit.ndom.AbstractNDOMParser#rmo()
	 */
	@Override
	protected void rmo() {
		//Iterate over IMapObject children of rmo and add them to graph as either nodes of edges depending on object type enum
		// using the IGraph addNode() and linkNodes() methods
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.contextadapter.toolkit.ndom.AbstractNDOMParser#semanticValidation()
	 */
	@Override
	protected void semanticValidation() {
	    if(!cytoscapeValidator.isValidGraph(graph))
	    	throw new RuntimeException("JUST A PLACEHOLDER FOR A CONCRETE INVALID GRAPH EXCEPTION");
	}

}
