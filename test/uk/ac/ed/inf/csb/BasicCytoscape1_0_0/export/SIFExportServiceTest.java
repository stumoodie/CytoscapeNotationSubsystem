package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.export;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjectsAPI.IMap;
import org.pathwayeditor.contextadapter.publicapi.ExportServiceException;
import org.pathwayeditor.contextadapter.publicapi.IContextAdapterServiceProvider;
import org.pathwayeditor.contextadapter.toolkit.ndom.ExportAdapterCreationException;
import org.pathwayeditor.contextadapter.toolkit.ndom.IExportAdapter;
import org.pathwayeditor.contextadapter.toolkit.ndom.INDOMValidationService;
import org.pathwayeditor.contextadapter.toolkit.validation.ContextValidationService;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.stubs.StreamServiceStub;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IGraph;

@RunWith(JMock.class)
public class SIFExportServiceTest {
	Mockery mockery = new JUnit4Mockery();
	private IContextAdapterServiceProvider contextService;
	private CytoscapeValidatorStub validator;
	private IMap map;
	private SIFExportService service;
	private File exportFile;
	private ContextValidationService validationService;
	private INDOMValidationService ndomValidationService;
	private IGraph graph;

	@Before
	public void setUp() {
		contextService = mockery.mock(IContextAdapterServiceProvider.class);
		ndomValidationService=mockery.mock(INDOMValidationService.class);
		graph=mockery.mock(IGraph.class);
		exportFile = new File("");
		mockery.checking(new Expectations() {
			{ignoring(contextService).getContext();}
			{atLeast(1).of(ndomValidationService).getNDOM();will(returnValue(graph));}
			{ignoring(graph).getNodes();}
		});
		validator = new CytoscapeValidatorStub(contextService);
		validationService = new ContextValidationService(contextService,validator);
		map = mockery.mock(IMap.class);
		mockery.checking(new Expectations() {
			{
				atLeast(1).of(contextService).getValidationService();
				will(returnValue(validationService));
			}
		});
		service = new SIFExportService(contextService, new SIFExportAdapter<IGraph>(),ndomValidationService);
		service.setStreamer(new StreamServiceStub());
	}

	@Test
	public void testExportMapValidatesMap() throws ExportServiceException {
		service.exportMap(map, exportFile);
		assertTrue(validator.hasMapBeenValidated());
	}

	public void testExportCreatesDataStructureForWriting() throws IOException, ExportAdapterCreationException, ExportServiceException{
		final IExportAdapter< IGraph> generator = mockery.mock(IExportAdapter.class);
		mockery.checking(new Expectations() {
			{
				ignoring(generator).writeTarget(with(any(OutputStream.class)));
				atLeast(1).of(generator).createTarget(with(any(IGraph.class)));
			}
		});
		service = new SIFExportService(contextService,generator, ndomValidationService);
		service.setStreamer(new StreamServiceStub());
		service.exportMap(map, exportFile);
	}

	@Test
	public void testExportWritesToOutPutStream() throws ExportServiceException, IOException, ExportAdapterCreationException {
		final IExportAdapter< IGraph> generator = mockery.mock(IExportAdapter.class);
		mockery.checking(new Expectations() {
			{
				atLeast(1).of(generator).writeTarget(with(any(OutputStream.class)));
				ignoring(generator).createTarget(with(any(IGraph.class)));
			}
		});
		service = new SIFExportService(contextService,generator, ndomValidationService);
		service.setStreamer(new StreamServiceStub());
		service.exportMap(map, exportFile);
	}

}
