package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom;

import org.pathwayeditor.businessobjectsAPI.IRootMapObject;
import org.pathwayeditor.contextadapter.toolkit.ndom.ModelObject;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IGraph;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.INode;

public class CytoscapeGraph  extends ModelObject  implements IGraph {

	private static String id;
	private IRootMapObject root;

	public CytoscapeGraph(IRootMapObject rmo) {
		super(id,rmo);
		this.root=rmo;
	}

	public void addNode(INode in) {
		// TODO Auto-generated method stub

	}

	public void linkNodes(INode src, INode target) {
		// TODO Auto-generated method stub

	}

}
