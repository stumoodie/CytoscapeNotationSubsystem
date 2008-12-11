package org.pathwayeditor.notations.cytoscape.ndom.stubs;

import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pathwayeditor.businessobjects.constants.EditableGraphicalAttribute;
import org.pathwayeditor.businessobjects.constants.PortPlacement;
import org.pathwayeditor.businessobjects.constants.ShapeType;
import org.pathwayeditor.businessobjectsAPI.BlockStyle;
import org.pathwayeditor.businessobjectsAPI.Bounds;
import org.pathwayeditor.businessobjectsAPI.IContextProperty;
import org.pathwayeditor.businessobjectsAPI.IFillProperty;
import org.pathwayeditor.businessobjectsAPI.ILabel;
import org.pathwayeditor.businessobjectsAPI.ILabelLocationPolicy;
import org.pathwayeditor.businessobjectsAPI.ILineProperty;
import org.pathwayeditor.businessobjectsAPI.ILink;
import org.pathwayeditor.businessobjectsAPI.IMap;
import org.pathwayeditor.businessobjectsAPI.IMapObject;
import org.pathwayeditor.businessobjectsAPI.IRootMapObject;
import org.pathwayeditor.businessobjectsAPI.IShape;
import org.pathwayeditor.businessobjectsAPI.ITextProperty;
import org.pathwayeditor.businessobjectsAPI.Location;
import org.pathwayeditor.businessobjectsAPI.Size;
import org.pathwayeditor.businessobjectsAPI.URLResource;
import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.IObjectType;

import org.pathwayeditor.notations.cytoscape.BasicCytoscapeContextAdapterSyntaxService;

public class ShapeStub implements IShape {
public class ObjectTypeStub implements IObjectType {

		private String type;

		public ObjectTypeStub(String type) {
			this.type=type;
	}

		public IContext getContext() {
			// TODO Auto-generated method stub
			return null;
		}

		public int getTypeCode() {
			// TODO Auto-generated method stub
			return 0;
		}

		public String getTypeName() {
			return type;
		}

	}

public class TextPropertyStub implements ITextProperty {

		private String name;

		public TextPropertyStub(String name) {
			this.name=name;
	}
		

		public void add(List<BlockStyle> arg0) {
			// TODO Auto-generated method stub

		}

		public String getHTML() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getHTMLExt(boolean arg0, String arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		public List<String> getListofLineStrings() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getRTF() {
			// TODO Auto-generated method stub
			return null;
		}

		public Size getSize() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getString() {
			return name;
		}

		public List<URLResource> getURLs() {
			// TODO Auto-generated method stub
			return null;
		}

		public List<BlockStyle> getValue() {
			// TODO Auto-generated method stub
			return null;
		}

		public void putHTML(String arg0) throws Exception {
			// TODO Auto-generated method stub

		}

		public void putHTMLDOM(String arg0) throws Exception {
			// TODO Auto-generated method stub

		}

		public void setValue(List<BlockStyle> arg0) {
			// TODO Auto-generated method stub

		}

	}

public String name;
public Set<ILink> srcLinks = new HashSet<ILink>();
public Set<ILink>targetLinks= new HashSet<ILink>();
	public ShapeStub(String name) {
		this.name=name;
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
		return new ObjectTypeStub(BasicCytoscapeContextAdapterSyntaxService.ObjectTypes.Node.name());
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
		return  new TextPropertyStub(name);
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

	public boolean canAddChild(IMapObject arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Location getCentre() {
		// TODO Auto-generated method stub
		return null;
	}

	public Bounds getInnerBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	public PortPlacement getPortPlacement() {
		// TODO Auto-generated method stub
		return null;
	}

	public ShapeType getShapeType() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<ILink> getSourceLinks() {
		return srcLinks;
	}

	public Set<ILink> getTargetLinks() {
		return targetLinks;
	}

	public boolean isSimilar(IShape arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setBounds(Bounds arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setPortPlacement(PortPlacement arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setShapeType(ShapeType arg0) {
		// TODO Auto-generated method stub
		
	}

	public void alterPadding(int arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setPadding(int arg0) {
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
