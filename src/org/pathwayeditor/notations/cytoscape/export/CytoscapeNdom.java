package org.pathwayeditor.notations.cytoscape.export;

import cytoscape.CyNetwork;
import cytoscape.view.CyNetworkView;

public class CytoscapeNdom {
	private final CyNetwork network;
	private final CyNetworkView view;
	
	public CytoscapeNdom(CyNetwork network, CyNetworkView view){
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
	public CyNetworkView getView() {
		return view;
	}
	
}
