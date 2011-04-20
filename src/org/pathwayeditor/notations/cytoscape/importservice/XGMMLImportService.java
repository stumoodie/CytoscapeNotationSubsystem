/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/
package org.pathwayeditor.notations.cytoscape.importservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationImportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.ImportServiceException;
import org.pathwayeditor.notations.cytoscape.export.CytoscapeNdom;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.data.readers.GraphReader;
import cytoscape.data.readers.XGMMLReader;
import cytoscape.view.CyNetworkView;

public class XGMMLImportService implements INotationImportService {
	public static final int NODE_IMPORT_LIMIT = 100;

	private static final String DISPLAYNAME = "Cytoscape XGMML File";
	private static final String SUFFIX = "xgmml";
	private final INotationSubsystem notationSubsystem;
	private final String CODE = "cytoscape_xgmml";
	private CytoscapeNdom ndom = null;

	public XGMMLImportService(INotationSubsystem notationSubsystem){
		this.notationSubsystem = notationSubsystem;
	}
	
	public String getCode() {
		return CODE;
	}

	public String getDisplayName() {
		return DISPLAYNAME;
	}

	public String getRecommendedSuffix() {
		return SUFFIX;
	}

	public void importToCanvas(File importFile, ICanvas canvas)	throws ImportServiceException {
		InputStream reader = null;
		try {
			Cytoscape.createNewSession();
			reader = new FileInputStream(importFile);
			GraphReader parser = new XGMMLReader(reader);
			CyNetwork network = Cytoscape.createNetwork(parser, true, null);
			if(network.getNodeCount() <= NODE_IMPORT_LIMIT){
				CyNetworkView view = Cytoscape.getNetworkView(network.getIdentifier());
				ndom = new CytoscapeNdom(network, view);
				BusinessObjectBuilder builder = new BusinessObjectBuilder(canvas.getNotationSubsystem().getSyntaxService(), ndom);
				builder.setCanvas(canvas);
				builder.build();
			}
			else{
				throw new ImportServiceException("The number of nodes is too large to be imported: node count=" + network.getNodeCount());
			}
		} catch (IOException e) {
			throw new ImportServiceException(e);
		}
		finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					// suppressing because in finally
				}
			}
		}
	}

	public INotation getNotation() {
		return this.notationSubsystem.getNotation();
	}

	public INotationSubsystem getNotationSubsystem() {
		return this.notationSubsystem;
	}

}
