package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation;

import org.eclipse.jface.preference.IPreferenceStore;
import org.pathwayeditor.application.contextadapter.uitoolkit.ValidationConfigPreferencesConfigurer;

import plugin.Activator;

public class CytoscapePreferencesConfigurer extends ValidationConfigPreferencesConfigurer {

	@Override
	protected IPreferenceStore getPreferenceStore() {
		return Activator.getDefault().getPreferenceStore();
	}

}
