package plugin;

import java.util.Set;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.pathwayeditor.application.contextadapter.uitoolkit.ValidationRulePreferences;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleConfig;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.BasicCytoscapeContextAdapterServiceProvider;

public class CytoscapeValidationRulePreferences extends ValidationRulePreferences implements IWorkbenchPreferencePage {
	
    /**
     * Default no-arg constructor needed for extension points
     */
	public CytoscapeValidationRulePreferences() {

	}
	
	
	@Override
	protected IPreferenceStore createPreferenceStore() {
		return Activator.getDefault().getPreferenceStore();
	}




	@Override
	protected Set<IValidationRuleConfig> getConfigurableRules() {
		return BasicCytoscapeContextAdapterServiceProvider
				         .getInstance()
				         .getValidationService()
				         .getRuleConfigurations();
	}


	@Override
	protected Set<IValidationRuleConfig> getDefaultConfigurableRules() {
		return    BasicCytoscapeContextAdapterServiceProvider
		         .getInstance()
		         .getValidationService()
		         .getDefaultRuleConfigurations();
	}
	
	
	
	

}
