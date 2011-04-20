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
package org.pathwayeditor.notations.cytoscape.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReportItem;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReportItem.Severity;

@RunWith(JMock.class)
public class CytoscapeValidatorTest {
	private static final String NODE1_NAME = "node";
	private static final String NODE2_NAME = "node";
	private static final String NODE3_NAME = "node3";
	private static final int EXPECTED_RULE_NUM = 2;
	private static final int EXPECTED_NUM_REPORT_ITEMS = 2;
	private static final String EDGE1_NAME = "edge";
	private static final String EDGE2_NAME = "edge";
	private static final String EDGE3_NAME = "node3";
	private static final String ALT_NODE1_NAME = "node";
	private static final String ALT_NODE2_NAME = "node2";
	private static final String ALT_NODE3_NAME = "node3";
	private static final String ALT_EDGE1_NAME = "edge";
	private static final String ALT_EDGE2_NAME = "edge2";
	private static final String ALT_EDGE3_NAME = "node3";
	private static final int EXPECTED_NO_WARNING_NUM_REPORT_ITEMS = 0;

	private final Mockery mockery = new JUnit4Mockery();

	private CytoscapeValidator testInstance;  
	private ICanvas mockCanvas;
	private IModel mockModel;
	private IShapeNode mockShapeNode1;
	private IShapeAttribute mockShapeAttribute1;
	private IShapeNode mockShapeNode2;
	private IShapeAttribute mockShapeAttribute2;
	private IShapeNode mockShapeNode3;
	private IShapeAttribute mockShapeAttribute3;
	private ILinkEdge mockLinkEdge1;
	private ILinkAttribute mockLinkAttribute1;
	private ILinkEdge mockLinkEdge2;
	private ILinkAttribute mockLinkAttribute2;
	private ILinkEdge mockLinkEdge3;
	private ILinkAttribute mockLinkAttribute3;
	private ICanvas mockAltCanvas;
	private IModel mockAltModel;
	private IShapeNode mockAltShapeNode1;
	private IShapeAttribute mockAltShapeAttribute1;
	private IShapeNode mockAltShapeNode2;
	private IShapeAttribute mockAltShapeAttribute2;
	private IShapeNode mockAltShapeNode3;
	private IShapeAttribute mockAltShapeAttribute3;
	private ILinkEdge mockAltLinkEdge1;
	private ILinkAttribute mockAltLinkAttribute1;
	private ILinkEdge mockAltLinkEdge2;
	private ILinkAttribute mockAltLinkAttribute2;
	private ILinkEdge mockAltLinkEdge3;
	private ILinkAttribute mockAltLinkAttribute3;

	@Before
	public void setUp() throws Exception {
		mockCanvas = mockery.mock(ICanvas.class, "mockCanvas");
		this.mockModel = mockery.mock(IModel.class, "mockModel");
		this.mockShapeNode1 = mockery.mock(IShapeNode.class, "mockShapeNode1");
		this.mockShapeNode2 = mockery.mock(IShapeNode.class, "mockShapeNode2");
		this.mockShapeNode3 = mockery.mock(IShapeNode.class, "mockShapeNode3");
		this.mockShapeAttribute1 = mockery.mock(IShapeAttribute.class, "mockShapeAttribute1");
		this.mockShapeAttribute2 = mockery.mock(IShapeAttribute.class, "mockShapeAttribute2");
		this.mockShapeAttribute3 = mockery.mock(IShapeAttribute.class, "mockShapeAttribute3");
		this.mockLinkEdge1 = mockery.mock(ILinkEdge.class, "mockLinkEdge1");
		this.mockLinkEdge2 = mockery.mock(ILinkEdge.class, "mockLinkEdge2");
		this.mockLinkEdge3 = mockery.mock(ILinkEdge.class, "mockLinkEdge3");
		this.mockLinkAttribute1 = mockery.mock(ILinkAttribute.class, "mockLinkAttribute1");
		this.mockLinkAttribute2 = mockery.mock(ILinkAttribute.class, "mockLinkAttribute2");
		this.mockLinkAttribute3 = mockery.mock(ILinkAttribute.class, "mockLinkAttribute3");
		mockAltCanvas = mockery.mock(ICanvas.class, "mockAltCanvas");
		this.mockAltModel = mockery.mock(IModel.class, "mockAltModel");
		this.mockAltShapeNode1 = mockery.mock(IShapeNode.class, "mockAltShapeNode1");
		this.mockAltShapeNode2 = mockery.mock(IShapeNode.class, "mockAltShapeNode2");
		this.mockAltShapeNode3 = mockery.mock(IShapeNode.class, "mockAltShapeNode3");
		this.mockAltShapeAttribute1 = mockery.mock(IShapeAttribute.class, "mockAltShapeAttribute1");
		this.mockAltShapeAttribute2 = mockery.mock(IShapeAttribute.class, "mockAltShapeAttribute2");
		this.mockAltShapeAttribute3 = mockery.mock(IShapeAttribute.class, "mockAltShapeAttribute3");
		this.mockAltLinkEdge1 = mockery.mock(ILinkEdge.class, "mockAltLinkEdge1");
		this.mockAltLinkEdge2 = mockery.mock(ILinkEdge.class, "mockAltLinkEdge2");
		this.mockAltLinkEdge3 = mockery.mock(ILinkEdge.class, "mockAltLinkEdge3");
		this.mockAltLinkAttribute1 = mockery.mock(ILinkAttribute.class, "mockAltLinkAttribute1");
		this.mockAltLinkAttribute2 = mockery.mock(ILinkAttribute.class, "mockAltLinkAttribute2");
		this.mockAltLinkAttribute3 = mockery.mock(ILinkAttribute.class, "mockAltLinkAttribute3");
		mockery.checking(new Expectations() {{
			allowing(mockCanvas).getModel(); will(returnValue(mockModel));

			allowing(mockModel).shapeNodeIterator(); will(returnIterator(mockShapeNode1, mockShapeNode2, mockShapeNode3));
			
			allowing(mockModel).linkEdgeIterator(); will(returnIterator(mockLinkEdge1, mockLinkEdge2, mockLinkEdge3));
			
			allowing(mockShapeNode1).getAttribute(); will(returnValue(mockShapeAttribute1));
			
			allowing(mockShapeNode2).getAttribute(); will(returnValue(mockShapeAttribute2));
			
			allowing(mockShapeNode3).getAttribute(); will(returnValue(mockShapeAttribute3));
			
			allowing(mockShapeAttribute1).getName(); will(returnValue(NODE1_NAME));
			
			allowing(mockShapeAttribute2).getName(); will(returnValue(NODE2_NAME));
			
			allowing(mockShapeAttribute3).getName(); will(returnValue(NODE3_NAME));
			
			allowing(mockLinkEdge1).getAttribute(); will(returnValue(mockLinkAttribute1));
			
			allowing(mockLinkEdge2).getAttribute(); will(returnValue(mockLinkAttribute2));
			
			allowing(mockLinkEdge3).getAttribute(); will(returnValue(mockLinkAttribute3));
			
			allowing(mockLinkAttribute1).getName(); will(returnValue(EDGE1_NAME));
			
			allowing(mockLinkAttribute2).getName(); will(returnValue(EDGE2_NAME));
			
			allowing(mockLinkAttribute3).getName(); will(returnValue(EDGE3_NAME));

			allowing(mockAltCanvas).getModel(); will(returnValue(mockAltModel));

			allowing(mockAltModel).shapeNodeIterator(); will(returnIterator(mockAltShapeNode1, mockAltShapeNode2, mockAltShapeNode3));
			
			allowing(mockAltModel).linkEdgeIterator(); will(returnIterator(mockAltLinkEdge1, mockAltLinkEdge2, mockAltLinkEdge3));
			
			allowing(mockAltShapeNode1).getAttribute(); will(returnValue(mockAltShapeAttribute1));
			
			allowing(mockAltShapeNode2).getAttribute(); will(returnValue(mockAltShapeAttribute2));
			
			allowing(mockAltShapeNode3).getAttribute(); will(returnValue(mockAltShapeAttribute3));
			
			allowing(mockAltShapeAttribute1).getName(); will(returnValue(ALT_NODE1_NAME));
			
			allowing(mockAltShapeAttribute2).getName(); will(returnValue(ALT_NODE2_NAME));
			
			allowing(mockAltShapeAttribute3).getName(); will(returnValue(ALT_NODE3_NAME));
			
			allowing(mockAltLinkEdge1).getAttribute(); will(returnValue(mockAltLinkAttribute1));
			
			allowing(mockAltLinkEdge2).getAttribute(); will(returnValue(mockAltLinkAttribute2));
			
			allowing(mockAltLinkEdge3).getAttribute(); will(returnValue(mockAltLinkAttribute3));
			
			allowing(mockAltLinkAttribute1).getName(); will(returnValue(ALT_EDGE1_NAME));
			
			allowing(mockAltLinkAttribute2).getName(); will(returnValue(ALT_EDGE2_NAME));
			
			allowing(mockAltLinkAttribute3).getName(); will(returnValue(ALT_EDGE3_NAME));
		}});
		this.testInstance = CytoscapeValidator.getInstance();
		this.testInstance.setCanvas(mockCanvas);
	}

	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
	}

	@Test
	public final void testValidateCanvasWithErrors() {
		this.testInstance.validateCanvas();
		IValidationReport report = this.testInstance.getValidationReport();
		assertFalse("invalid report", report.isValid());
		assertFalse("expect no warnings", report.hasWarnings());
		List<IValidationReportItem> actualReportItems = report.getValidationReportItems();
		assertEquals("expected num report items", EXPECTED_NUM_REPORT_ITEMS, actualReportItems.size());
		// check that they are warnings
		for(IValidationReportItem item : actualReportItems){
			assertEquals("warning item", Severity.ERROR, item.getSeverity());
		}
	}

	@Test
	public final void testValidateCanvasWithoutWarnings() {
		this.testInstance.setCanvas(mockAltCanvas);
		this.testInstance.validateCanvas();
		IValidationReport report = this.testInstance.getValidationReport();
		assertTrue("valid report", report.isValid());
		assertFalse("no warnings", report.hasWarnings());
		List<IValidationReportItem> actualReportItems = report.getValidationReportItems();
		assertEquals("expected num report items", EXPECTED_NO_WARNING_NUM_REPORT_ITEMS, actualReportItems.size());
	}

	@Test
	public final void testHasBeenValidatedWhenNoCanvas() {
		this.testInstance.setCanvas(mockAltCanvas);
		assertFalse("alt canvas not validated", this.testInstance.hasBeenValidated());
	}

	@Test(expected=IllegalStateException.class)
	public final void testGetValidationReport() {
		this.testInstance.setCanvas(mockAltCanvas);
		this.testInstance.getValidationReport();
	}

	@Test
	public final void testGetRuleStore() {
		assertNotNull("rule store defined", this.testInstance.getRuleStore());
		assertEquals("expected num rules", EXPECTED_RULE_NUM, this.testInstance.getRuleStore().getAllRuleDefinitions().size());
	}

}
