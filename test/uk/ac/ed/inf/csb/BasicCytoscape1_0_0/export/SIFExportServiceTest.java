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

	@Before
	public void setUp() {
		contextService = mockery.mock(IContextAdapterServiceProvider.class);
		exportFile = new File("");
		mockery.checking(new Expectations() {
			{
				ignoring(contextService).getContext();
			}
		});
		validator = new CytoscapeValidatorStub(contextService);
		mockery.checking(new Expectations() {
			{
				atLeast(1).of(contextService).getValidationService();
				will(returnValue(validator));
			}
		});
		map = mockery.mock(IMap.class);
		service = new SIFExportService(contextService, new SIFExportAdapter<IGraph>());
		service.setStreamer(new StreamServiceStub());
	}

	@Test
	public void testExportMapValidatesMap() throws ExportServiceException {
		service.exportMap(map, exportFile);
		assertTrue(validator.mapValidated);
	}

	public void testExportCreatesDataStructureForWriting() throws IOException, ExportAdapterCreationException, ExportServiceException{
		final IExportAdapter< IGraph> generator = mockery.mock(IExportAdapter.class);
		mockery.checking(new Expectations() {
			{
				ignoring(generator).writeTarget(with(any(OutputStream.class)));
				atLeast(1).of(generator).createTarget(with(any(IGraph.class)));
			}
		});
		service = new SIFExportService(contextService,generator);
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
		service = new SIFExportService(contextService,generator);
		service.setStreamer(new StreamServiceStub());
		service.exportMap(map, exportFile);
	}

}
