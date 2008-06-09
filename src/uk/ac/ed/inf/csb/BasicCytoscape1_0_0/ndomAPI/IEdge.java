package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI;

public interface IEdge {
	public INode getSrcNode();
	public INode getTargetNode();
	public String getInteraction();
}
