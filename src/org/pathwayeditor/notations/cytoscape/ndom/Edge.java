package org.pathwayeditor.notations.cytoscape.ndom;

import org.pathwayeditor.notations.cytoscape.ndomAPI.IEdge;
import org.pathwayeditor.notations.cytoscape.ndomAPI.INode;

public class Edge implements IEdge{

	private String interaction;
	private String id;
	private INode src;
	private INode target;

	public Edge(String interaction) {
		this.interaction=interaction;
	}

	public String getInteraction() {
		return interaction;
	}

	public INode getSrcNode() {
		return src;
	}

	public INode getTargetNode() {
		return target;
	}

	public String getASCIIName() {
		return "";
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

	public String getName() {
		return "";
	}

	void setSrc(INode src) {
		this.src=src;
	}

	void setTarget(INode target) {
		this.target=target;
	}
}
