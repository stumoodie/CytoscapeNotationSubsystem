package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI;

import java.util.Set;

import org.pathwayeditor.contextadapter.toolkit.ndom.IModelObject;

public interface INode extends IModelObject{
	/**
	 * @return name of this node
	 * Contract:<br>
	 * Postconditions: <code>name</code> is set and is not null or empty string.<br>
	 */
	public String getName();
	
	/**
	 * @param newName a new name for this node
	 * Contract:<br>
	 * Preconditions: <code>name</code> is not null or empty string.<br>
	 */
	public void setName(String newName) ;
	
	/**
	 * @return cardinality of connections this node makes
	 *  Contract:<br>
	 *  returns a value not less than 0
	 */
	public int getNumberOfConnections();
	
	/**
	 * @return a set of all edges that touch this node and regard it as a src.
	 *  Contract:<br>
	 *  If no edges touch the node returns the empty set, not null
	 */
	public Set <IEdge> getSrcEdges();
	
	/**
	 * @return a set of all edges that touch this node and regard it as a target.
	 *  Contract:<br>
	 *  If no edges touch the node returns the empty set, not null
	 */
	public Set <IEdge> getTargetEdges();
	
	
}
