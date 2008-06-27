package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI;

import org.pathwayeditor.contextadapter.toolkit.ndom.IModelObject;

public interface IEdge extends IModelObject{
	public INode getSrcNode();
	public INode getTargetNode();
	public String getInteraction();
}
