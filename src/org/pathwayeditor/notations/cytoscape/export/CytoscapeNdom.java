package org.pathwayeditor.notations.cytoscape.export;

import cytoscape.CyNetwork;
import cytoscape.ding.DingNetworkView;

public class CytoscapeNdom {
	private final CyNetwork network;
	private final DingNetworkView view;
	
	public CytoscapeNdom(CyNetwork network, DingNetworkView view){
		this.network = network;
		this.view = view;
	}

	/**
	 * @return the network
	 */
	public CyNetwork getNetwork() {
		return network;
	}

	/**
	 * @return the view
	 */
	public DingNetworkView getView() {
		return view;
	}
	
}
