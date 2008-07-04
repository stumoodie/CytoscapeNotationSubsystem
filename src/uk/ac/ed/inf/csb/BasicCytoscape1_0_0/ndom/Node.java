package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom;

import java.util.HashSet;
import java.util.Set;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IEdge;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.INode;

public class Node implements INode {

	private String name;
	private String id;
	private Set<IEdge> srcEdges = new  HashSet<IEdge>();
	private Set<IEdge> targetEdges= new  HashSet<IEdge>();

	public Node(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Node))
			return false;
		return this.getName().equals(((Node)obj).getName());
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	public int getNumberOfConnections() {
		return srcEdges.size()+targetEdges.size();
	}

	public Set<IEdge> getSrcEdges() {
		return srcEdges;
	}

	public Set<IEdge> getTargetEdges() {
		return targetEdges;
	}

	public void setName(String newName) {
		this.name=name;
	}

	public String getASCIIName() {
		return name;
	}

	public String getDescription() {
		return "";
	}

	public String getDetailedDescription() {
		return "";
	}

	public String getId() {
		return id;
	}

	void addSrcEdge(IEdge edge) {
		srcEdges.add(edge);
	}
	
	void addTargetEdge(IEdge edge) {
		targetEdges.add(edge);
	}

}