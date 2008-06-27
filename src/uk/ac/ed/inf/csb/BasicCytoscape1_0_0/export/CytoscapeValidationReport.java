package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.export;

import java.util.Date;
import java.util.List;

import org.pathwayeditor.businessobjectsAPI.IMap;
import org.pathwayeditor.contextadapter.publicapi.IValidationReport;
import org.pathwayeditor.contextadapter.publicapi.IValidationReportItem;

public class CytoscapeValidationReport implements IValidationReport {

	private boolean valid;

	public CytoscapeValidationReport(boolean mapValid) {
		valid=mapValid;
	}

	public IMap getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IValidationReportItem> getValidationReportItems() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getValidationTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasWarnings() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isMapValid() {
		return valid;
	}

	public boolean isReportCurrent() {
		// TODO Auto-generated method stub
		return false;
	}

}
