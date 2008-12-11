package org.pathwayeditor.notations.cytoscape.ndom.stubs;

import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pathwayeditor.businessobjects.constants.EditableGraphicalAttribute;
import org.pathwayeditor.businessobjectsAPI.Bounds;
import org.pathwayeditor.businessobjectsAPI.IContextProperty;
import org.pathwayeditor.businessobjectsAPI.IFillProperty;
import org.pathwayeditor.businessobjectsAPI.ILabel;
import org.pathwayeditor.businessobjectsAPI.ILabelLocationPolicy;
import org.pathwayeditor.businessobjectsAPI.ILineProperty;
import org.pathwayeditor.businessobjectsAPI.IMap;
import org.pathwayeditor.businessobjectsAPI.IMapObject;
import org.pathwayeditor.businessobjectsAPI.IRootMapObject;
import org.pathwayeditor.businessobjectsAPI.ITextProperty;
import org.pathwayeditor.businessobjectsAPI.Location;
import org.pathwayeditor.businessobjectsAPI.Size;
import org.pathwayeditor.contextadapter.publicapi.IObjectType;

public class LabelStub implements ILabel {

	private IContextProperty interacts;
	private String stringValue;

	public LabelStub(String displayValue) {
		stringValue=displayValue;
	}

	public IMapObject getLeader() {
		// TODO Auto-generated method stub
		return null;
	}

	public ITextProperty getLeaderTextProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	public Location getOffset() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPropertyToDisplay() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasFill() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasLine() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setPropertyToDisplay(String propertyName) {
		// TODO Auto-generated method stub

	}

	public boolean addChild(IMapObject child) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addChildAtIndex(IMapObject child, int index) {
		// TODO Auto-generated method stub
		return false;
	}

	public void assignRootMapObjectToChildren() {
		// TODO Auto-generated method stub

	}

	public boolean copyAddChild(IMapObject child) {
		// TODO Auto-generated method stub
		return false;
	}

	public Bounds getAbsoluteBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	public Bounds getAbsoluteInnerBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	public Location getAbsoluteLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public Bounds getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IMapObject> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<EditableGraphicalAttribute> getEditableAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IContextProperty> getEditableProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public IFillProperty getFillProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getHovertext() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, IContextProperty> getImmutableFormattedTextProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, IContextProperty> getImmutableNumberProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, IContextProperty> getImmutableProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, IContextProperty> getImmutableTextProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ILabel> getLabels() {
		// TODO Auto-generated method stub
		return null;
	}

	public ILineProperty getLineProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public IMapObject getNewObject() {
		// TODO Auto-generated method stub
		return null;
	}

	public IObjectType getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPadding() {
		// TODO Auto-generated method stub
		return 0;
	}

	public IMapObject getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	public IMap getParentMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public IContextProperty getPropertyByName(String name) {
		if(name.equals("interacts"))
			return new ContextPropertyStub(stringValue);
		else
			return null;
	}

	public IRootMapObject getRootMapObject() {
		// TODO Auto-generated method stub
		return null;
	}

	public Size getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	public ITextProperty getTextProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IContextProperty> getVisualizableProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEditableAttribute(EditableGraphicalAttribute sga) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isPropertyVisible(String propertyName) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isValidParent(IMapObject child) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean propertyCanBeDisplayed(String propertyName) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeChild(IMapObject child) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setChildIndex(IMapObject child, int NewIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setFillProperty(IFillProperty fp) {
		// TODO Auto-generated method stub

	}

	public void setHovertext(String text) {
		// TODO Auto-generated method stub

	}

	public void setIcon(String icon) {
		// TODO Auto-generated method stub

	}

	public void setLineProperty(ILineProperty lp) {
		// TODO Auto-generated method stub

	}

	public void setLocation(Location l) {
		// TODO Auto-generated method stub

	}

	public void setParent(IMapObject abstractMapObject) {
		// TODO Auto-generated method stub

	}

	public void setProperty(String name, String value) {
		// TODO Auto-generated method stub

	}

	public void setProperty(String name, String value, boolean isVisible) {
		// TODO Auto-generated method stub

	}

	public void setPropertyIdsToNull() {
		// TODO Auto-generated method stub

	}

	public void setPropertyVisibility(String propertyName, boolean isVisible) {
		// TODO Auto-generated method stub

	}

	public void setRootMapObject(IRootMapObject rootMapObject) {
		// TODO Auto-generated method stub

	}

	public void setSize(Size size) {
		// TODO Auto-generated method stub

	}

	public void setText(String text) {
		// TODO Auto-generated method stub

	}

	public void setTextProperty(ITextProperty textProperty) {
		// TODO Auto-generated method stub

	}

	public boolean isRoot() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	public void firePropertyChange(String property, Object oldValue, Object newValue) {
		// TODO Auto-generated method stub

	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	public boolean addLabel(ILabel label) {
		// TODO Auto-generated method stub
		return false;
	}

	public Location calculateLabelLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public ILabelLocationPolicy getLabelLocationPolicy() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean removeLabel(ILabel label) {
		// TODO Auto-generated method stub
		return false;
	}

	public ITextProperty getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public ITextProperty getDetailedDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public ITextProperty getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getURL() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDescription(String description) {
		// TODO Auto-generated method stub

	}

	public void setDescription(ITextProperty description) {
		// TODO Auto-generated method stub

	}

	public void setDetailedDescription(String text) {
		// TODO Auto-generated method stub

	}

	public void setDetailedDescription(ITextProperty text) {
		// TODO Auto-generated method stub

	}

	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	public void setName(ITextProperty name) {
		// TODO Auto-generated method stub

	}

	public void setURL(String urlString) throws MalformedURLException {
		// TODO Auto-generated method stub

	}

	public String getAsciiName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAsciiName(String name) {
		// TODO Auto-generated method stub
		
	}

}
