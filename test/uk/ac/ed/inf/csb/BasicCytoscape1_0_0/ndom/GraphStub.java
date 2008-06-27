package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IEdge;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IGraph;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.INode;

public class GraphStub implements IGraph {

	public List <INode> nodes = new ArrayList<INode>();
	public Set <IEdge> edges = new HashSet<IEdge>();
	
	public void addNode(INode in) {
	nodes.add(in);
	}

	public List<INode> getNodes() {
		return nodes;
	}

	public  void linkNodes(INode src, INode target, IEdge edge) {
		((Node)src).addSrcEdge(edge);
		((Node)target).addTargetEdge(edge);
		((Edge)edge).setSrc(src);
		((Edge)edge).setTarget(target);
		edges.add(edge);
	}

	public String getASCIIName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDetailedDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<IEdge> getEdges() {
		return edges;
	}

}
