package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pathwayeditor.businessobjectsAPI.ILabel;
import org.pathwayeditor.businessobjectsAPI.ILink;
import org.pathwayeditor.businessobjectsAPI.IMapObject;
import org.pathwayeditor.businessobjectsAPI.IRootMapObject;
import org.pathwayeditor.businessobjectsAPI.IShape;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.BasicCytoscapeContextAdapterSyntaxService;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IEdge;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IGraph;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.INode;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.AbstractNDOMParser;

/**
 * @author nhanlon
 * 
 */
public class NDOMFactory extends AbstractNDOMParser {

	private IGraph graph;
	private Map<IMapObject, INode> srcNodeMap;
	private Map<IMapObject, INode> targetNodeMap;

	public NDOMFactory(IRootMapObject rmo) {
		super(rmo);
	}

	public IGraph makeGraph() {
		return new CytoscapeGraph(getId(getRmo()), getRmo().getParentMap().getName(), getRmo().getParentMap().getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.contextadapter.toolkit.ndom.AbstractNDOMParser#connectivity() calculate the average cardinality of nodes in the graph, the
	 *      max cardinality and the minimum
	 */
	@Override
	protected void connectivity() {
		// calculate the average cardinality of nodes in the graph, the max cardinality and the minimum
	}

	@Override
	protected void ndom() {
		graph = makeGraph();
		srcNodeMap = new HashMap<IMapObject, INode>();
		targetNodeMap = new HashMap<IMapObject, INode>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.contextadapter.toolkit.ndom.AbstractNDOMParser#rmo()
	 */
	@Override
	protected void rmo() {
		List<IMapObject> ch = getRmo().getChildren();
		// TODO NH - there is a general algorithm here for descending a graph that has no child objects - should be templated for re-use
		for (IMapObject child : ch) {
//			if (child.getObjectType().getTypeName().equals(BasicCytoscapeContextAdapterSyntaxService.ObjectTypes.Edge.name())) {
//				if (child instanceof ILink)
//					addLinkedNodesToGraph((ILink) child);
//			} else
		if (child.getObjectType().getTypeName().equals(BasicCytoscapeContextAdapterSyntaxService.ObjectTypes.Node.name())) {// only add nodes

				IShape shape = (IShape) child;
				if (shape.getSourceLinks() == null || shape.getSourceLinks().isEmpty()
						&& (shape.getTargetLinks() == null || shape.getTargetLinks().isEmpty()))
					graph.addNode(new Node(shape.getName().getString())); // shape has no links
				else {
					for (ILink link : shape.getSourceLinks())
						addLinkedNodesToGraph(link);
				}
			}
		}
	}

	/**
	 * @param aLink
	 */
	protected void addLinkedNodesToGraph(ILink aLink) {
		ILabel label = null;
		ILink link = null;
		IEdge edge = null;
		IMapObject src = null;
		IMapObject target = null;
		link = (ILink) aLink;
		if(link.getPropertyByName("interacts").isVisible()){
			edge = new Edge(link.getPropertyByName("interacts").getValue());
		}
		else
			edge = new Edge("edge");
		src = link.getSource();
		target = link.getTarget();

		Node srcNode = null;
		Node targetNode = null;
		if (!srcNodeMap.keySet().contains(src)) {
			srcNode = new Node(src.getName().getString());
			srcNodeMap.put(src, srcNode);
		} else
			srcNode = (Node) srcNodeMap.get(src);
		graph.addNode(srcNode);
		if (!targetNodeMap.keySet().contains(target)) {
			targetNode = new Node(target.getName().getString());
			targetNodeMap.put(target, targetNode);
		} else
			targetNode = (Node) targetNodeMap.get(target);
		graph.addNode(targetNode);
		graph.linkNodes(srcNode, targetNode, edge);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.contextadapter.toolkit.ndom.AbstractNDOMParser#semanticValidation()
	 */
	@Override
	protected void semanticValidation() {

	}

	public IGraph getGraph() {
		return graph;
	}

}
