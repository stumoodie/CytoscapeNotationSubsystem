package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.ndom.stubs;

import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pathwayeditor.businessobjects.constants.EditableGraphicalAttribute;
import org.pathwayeditor.businessobjectsAPI.Bounds;
import org.pathwayeditor.businessobjectsAPI.IChildValidationService;
import org.pathwayeditor.businessobjectsAPI.IContextProperty;
import org.pathwayeditor.businessobjectsAPI.IFillProperty;
import org.pathwayeditor.businessobjectsAPI.ILabel;
import org.pathwayeditor.businessobjectsAPI.ILabelLocationPolicy;
import org.pathwayeditor.businessobjectsAPI.ILineProperty;
import org.pathwayeditor.businessobjectsAPI.ILink;
import org.pathwayeditor.businessobjectsAPI.IMap;
import org.pathwayeditor.businessobjectsAPI.IMapObject;
import org.pathwayeditor.businessobjectsAPI.IRootMapObject;
import org.pathwayeditor.businessobjectsAPI.ITextProperty;
import org.pathwayeditor.businessobjectsAPI.Location;
import org.pathwayeditor.businessobjectsAPI.Size;
import org.pathwayeditor.contextadapter.publicapi.IObjectType;

public class RootStub implements IRootMapObject {

	public List<IMapObject> children;

	public String getContextUUID() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ILink> getLinks() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMapUUID() {
		// TODO Auto-generated method stub
		return null;
	}

	public IMap getParentMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeParentMap() {
		// TODO Auto-generated method stub

	}

	public void setChildValidationService(IChildValidationService arg0) {
		// TODO Auto-generated method stub

	}

	public void setContextUUID(String arg0) {
		// TODO Auto-generated method stub

	}

	public void setParentMap(IMap arg0) {
		// TODO Auto-generated method stub

	}

	public boolean addChild(IMapObject arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addChildAtIndex(IMapObject arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public void assignRootMapObjectToChildren() {
		// TODO Auto-generated method stub

	}

	public boolean copyAddChild(IMapObject arg0) {
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

	public List<IMapObject> getChildren() {return children;
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
		return "root";
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

	public IContextProperty getPropertyByName(String arg0) {
		// TODO Auto-generated method stub
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

	public boolean isEditableAttribute(EditableGraphicalAttribute arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isPropertyVisible(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isValidParent(IMapObject arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean propertyCanBeDisplayed(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeChild(IMapObject arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setChildIndex(IMapObject arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setFillProperty(IFillProperty arg0) {
		// TODO Auto-generated method stub

	}

	public void setHovertext(String arg0) {
		// TODO Auto-generated method stub

	}

	public void setIcon(String arg0) {
		// TODO Auto-generated method stub

	}

	public void setLineProperty(ILineProperty arg0) {
		// TODO Auto-generated method stub

	}

	public void setLocation(Location arg0) {
		// TODO Auto-generated method stub

	}

	public void setParent(IMapObject arg0) {
		// TODO Auto-generated method stub

	}

	public void setProperty(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	public void setProperty(String arg0, String arg1, boolean arg2) {
		// TODO Auto-generated method stub

	}

	public void setPropertyIdsToNull() {
		// TODO Auto-generated method stub

	}

	public void setPropertyVisibility(String arg0, boolean arg1) {
		// TODO Auto-generated method stub

	}

	public void setRootMapObject(IRootMapObject arg0) {
		// TODO Auto-generated method stub

	}

	public void setSize(Size arg0) {
		// TODO Auto-generated method stub

	}

	public void setText(String arg0) {
		// TODO Auto-generated method stub

	}

	public void setTextProperty(ITextProperty arg0) {
		// TODO Auto-generated method stub

	}

	public boolean isRoot() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub

	}

	public void firePropertyChange(String arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub

	}

	public boolean addLabel(ILabel arg0) {
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

	public boolean removeLabel(ILabel arg0) {
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

	public void setDescription(String arg0) {
		// TODO Auto-generated method stub

	}

	public void setDescription(ITextProperty arg0) {
		// TODO Auto-generated method stub

	}

	public void setDetailedDescription(String arg0) {
		// TODO Auto-generated method stub

	}

	public void setDetailedDescription(ITextProperty arg0) {
		// TODO Auto-generated method stub

	}

	public void setName(String arg0) {
		// TODO Auto-generated method stub

	}

	public void setName(ITextProperty arg0) {
		// TODO Auto-generated method stub

	}

	public void setURL(String arg0) throws MalformedURLException {
		// TODO Auto-generated method stub

	}

	public String getAsciiName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAsciiName(String name) {
		// TODO Auto-generated method stub
		
	}

	public Set<IMapObject> getMapObjectsOfType(IObjectType type) {
		// TODO Auto-generated method stub
		return null;
	}

}
