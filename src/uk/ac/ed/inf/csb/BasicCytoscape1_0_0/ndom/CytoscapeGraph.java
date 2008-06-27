package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.pathwayeditor.businessobjectsAPI.IRootMapObject;
import org.pathwayeditor.contextadapter.toolkit.ndom.ModelObject;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IEdge;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IGraph;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.INode;

public class CytoscapeGraph  extends ModelObject  implements IGraph {

	private static int id;
	private IRootMapObject root;
	private List<INode> nodes; //nodes may have the same name, which makes their equals return true - so they cannot be stored safely in a set.
	private Set<IEdge> edges;

	public CytoscapeGraph(String id, String name, String asciiName) {
		super(id,name, asciiName);
		nodes=new ArrayList<INode>();
		edges = new HashSet<IEdge>();
	}

	public void addNode(INode in) {
		nodes.add(in);
	}

	public  void linkNodes(INode src, INode target, IEdge edge) {
		((Node)src).addSrcEdge(edge);
		((Node)target).addTargetEdge(edge);
		((Edge)edge).setSrc(src);
		((Edge)edge).setTarget(target);
		edges.add(edge);
	}

	public List<INode> getNodes() {
		return nodes;
	}

	public Set<IEdge> getEdges() {
		return edges;
	}

}
