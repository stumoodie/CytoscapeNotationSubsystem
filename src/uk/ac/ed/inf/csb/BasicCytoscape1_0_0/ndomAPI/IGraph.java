package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI;

import org.pathwayeditor.contextadapter.toolkit.ndom.IModelObject;

public interface IGraph extends IModelObject{
	
	/**
	 * @param in
	 * add a new node to this graph 
	 */
	public void addNode(INode in);
	
	/**
	 * @param src source node for new edge
	 * @param target target node for new edge
	 * creates a new edge between src and target node.
	 */
	public void linkNodes(INode src,INode target);
}
