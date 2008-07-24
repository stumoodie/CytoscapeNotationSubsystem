package plugin;

import org.eclipse.jface.preference.IPreferenceStore;
import org.pathwayeditor.application.contextadapter.uitoolkit.ValidationConfigPreferencesConfigurer;

public class CytoscapePreferencesConfigurer extends ValidationConfigPreferencesConfigurer {

	@Override
	protected IPreferenceStore getPreferenceStore() {
		return Activator.getDefault().getPreferenceStore();
	}

}
