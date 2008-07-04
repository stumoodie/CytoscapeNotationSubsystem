package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.export;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.pathwayeditor.businessobjectsAPI.IMap;
import org.pathwayeditor.contextadapter.publicapi.ExportServiceException;
import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.toolkit.ndom.ExportAdapterCreationException;
import org.pathwayeditor.contextadapter.toolkit.ndom.IExportAdapter;
import org.pathwayeditor.contextadapter.toolkit.ndom.INdomModel;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndomAPI.IGraph;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.IContextAdapterExportService;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.IContextAdapterServiceProvider;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.IToolKitContextAdapterServiceProvider;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.IToolKitContextAdapterValidationService;

public class SIFExportService implements IContextAdapterExportService {
	public static final String DISPLAYNAME = "CYTOSCAPE SIF";
	public static final String SIF = "sif";
	private IExportAdapter<IGraph> generator;
	private IContext context;
	private IToolKitContextAdapterServiceProvider serviceProvider;
	final String CODE = "cytoscape_sif";
	private OutputStream fos;
	private INdomModel ndom;
	private IToolKitContextAdapterValidationService validator;
	private StreamService streamer;

	public SIFExportService(IToolKitContextAdapterServiceProvider provider, IExportAdapter<IGraph> generator) {
		this.serviceProvider = provider;
		context = provider.getContext();
		validator = serviceProvider.getToolKitValidationService();
		this.generator = generator;
		streamer = new StreamService();
	}

	public void exportMap(IMap map, File exportFile) throws ExportServiceException {
		try {
			ndom = null;
			validateGraph(map);
			if (validator.getValidationReport().isMapValid()) {
				generator.createTarget((IGraph) ndom);
				fos = streamer.makeOutStream(exportFile);
				generator.writeTarget(fos);
			}
		} catch (ExportAdapterCreationException e) {
			throw new ExportServiceException(e);
		} catch (IOException e) {
			throw new ExportServiceException(e);
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception e) {
			}
		}
	}

	void validateGraph(IMap map) {
		validator.setMapToValidate(map);
		validator.validateMap();
		ndom = validator.getNDOMValidationService().getNDOM();
	}

	public String getCode() {
		return CODE;
	}

	public IContext getContext() {
		return context;
	}

	public String getDisplayName() {
		return DISPLAYNAME;
	}

	public String getRecommendedSuffix() {
		return SIF;
	}

	public IContextAdapterServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	protected IExportAdapter<IGraph> getGenerator() {
		return generator;
	}

	protected void setFos(OutputStream out) {
		this.fos = out;
	}

	protected OutputStream getFos() {
		return fos;
	}

	void setStreamer(StreamService streamer) {
		this.streamer = streamer;
	}

}
