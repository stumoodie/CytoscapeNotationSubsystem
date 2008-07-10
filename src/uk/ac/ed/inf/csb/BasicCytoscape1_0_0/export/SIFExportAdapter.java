package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.export;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.pathwayeditor.contextadapter.toolkit.ndom.ExportAdapterCreationException;
import org.pathwayeditor.contextadapter.toolkit.ndom.IExportAdapter;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IEdge;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IGraph;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.INode;

public class SIFExportAdapter<N extends IGraph> implements IExportAdapter<N> {

	private boolean isTargetCreated;
	Map<String, Map<String, String>> sifFile = new HashMap<String, Map<String, String>>();

	public void createTarget(N ndom) throws ExportAdapterCreationException {
		sifFile = new HashMap<String, Map<String, String>>();
		for (INode node : ndom.getNodes()) {
			Map<String, String> nodesByType = getInteractingNodesByType(node);
			addToSifFile(node,nodesByType);
		}
		isTargetCreated = true;
	}

	private void addToSifFile(INode node,Map<String, String> nodesByType) {
		if(!sifFile.keySet().contains(node.getName())){
			if(nodesByType.isEmpty()&&!node.getTargetEdges().isEmpty())
				;//only nodes with no edges at all should be added as 'singletons' - nodes that are only targets will be added elsewhere
			else
				sifFile.put(node.getName(), nodesByType);
		}
		else{
			Map <String,String> existingNodesByType = sifFile.get(node.getName());
			for (String interactionName: nodesByType.keySet()){
				if(existingNodesByType.keySet().contains(interactionName)){
					String existingTargetNodes = existingNodesByType.get(interactionName);
					String newTargetNodes = nodesByType.get(interactionName);
					Set <String>existingSet = new HashSet<String>(Arrays.asList(existingTargetNodes.split(",")));
					Set <String>newSet = new HashSet<String>(Arrays.asList(newTargetNodes.split(",")));
					String newDataString = "";
					existingSet.addAll(newSet);
					for (String name: existingSet){
						newDataString+=name+",";
					}
					newDataString=newDataString.substring(0,newDataString.length()-1);//remove trailing comma
					existingNodesByType.put(interactionName, newDataString);
				}
				else
					existingNodesByType.put(interactionName, nodesByType.get(interactionName));
			}
		}
	}

	/**
	 * @param INode
	 *            node
	 * @return a Map of edge interaction types as key and the names of all target Nodes of that edge interaction type in a single comma delimited string
	 */
	public Map<String, String> getInteractingNodesByType(INode node) {
		Map<String, String> returnMap = new HashMap<String, String>();
		Set<IEdge> srcEdges = node.getSrcEdges();
		for (IEdge e : srcEdges) {
			if (!returnMap.containsKey(e.getInteraction()))
				returnMap.put(e.getInteraction(), e.getTargetNode().getName());
			else
				returnMap.put(e.getInteraction(), returnMap.get(e.getInteraction()) + "," + e.getTargetNode().getName());
		}
		return returnMap;
	}

	public boolean isTargetCreated() {
		return isTargetCreated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.contextadapter.toolkit.ndom.IExportAdapter#writeTarget(java.io.OutputStream) TAB delimited output; whitespace permitted in
	 *      names
	 */
	public void writeTarget(OutputStream out) throws IOException {
		Set <String> interactingNodes = new HashSet<String>();
		for (String node : sifFile.keySet()) {
			for (String interaction : sifFile.get(node).keySet()) {
				interactingNodes.add(node);
				out.write((node + "\t" + interaction + "\t" + sifFile.get(node).get(interaction).replaceAll(",", "\t") + "\n").getBytes());
				Set <String >targetNodes = new HashSet<String>(Arrays.asList(sifFile.get(node).get(interaction).split(",")));
				interactingNodes.addAll(targetNodes);
			}
			if (!interactingNodes.contains(node))
				out.write(((node) + "\n").getBytes());
		}
	}

}
