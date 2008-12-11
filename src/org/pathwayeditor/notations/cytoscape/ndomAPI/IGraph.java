package org.pathwayeditor.notations.cytoscape.ndomAPI;

import java.util.List;
import java.util.Set;

import org.pathwayeditor.contextadapter.toolkit.ndom.INdomModel;

public interface IGraph extends INdomModel{
	
	/**
	 * @param in
	 * add a new node to this graph 
	 */
	public void addNode(INode in);
	
	/**
	 * @param src source node for new edge
	 * @param target target node for new edge
	 * creates a new edge between src and target node.
	 * @param edge the link between the two nodes
	 */
	public void linkNodes(INode src,INode target, IEdge edge);

	/**
	 * @return a flat list of all nodes in this graph
	 */
	public List<INode> getNodes();

	public Set<IEdge> getEdges();
}
