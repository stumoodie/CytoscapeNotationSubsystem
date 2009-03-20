package org.pathwayeditor.notations.cytoscape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType.LinkEditableAttributes;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition.LinkTermEditableAttributes;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType.EditableShapeAttributes;
import org.pathwayeditor.notationsubsystem.toolkit.definition.FormattedTextPropertyDefinition;
import org.pathwayeditor.notationsubsystem.toolkit.definition.LinkObjectType;
import org.pathwayeditor.notationsubsystem.toolkit.definition.LinkTerminusDefinition;
import org.pathwayeditor.notationsubsystem.toolkit.definition.NumberPropertyDefinition;
import org.pathwayeditor.notationsubsystem.toolkit.definition.PlainTextPropertyDefinition;
import org.pathwayeditor.notationsubsystem.toolkit.definition.RootObjectType;
import org.pathwayeditor.notationsubsystem.toolkit.definition.ShapeObjectType;
import org.pathwayeditor.notationsubsystem.toolkit.definition.TextPropertyDefinition;

public class CytoscapeSyntaxService implements INotationSyntaxService {
    public static final int ROOT_UID = 0;
    public static final int NODE_UID = 1;
    public static final int EDGE_UID = 2;
    private static final String NODE_NAME = "node";
    private static final String NODE_DESCN = "node";
    private static final String EDGE_NAME = "edge";
    private static final String EDGE_DESCN = "edge";
	private static final int NUM_ROOT_OTS = 1;

    private static IPropertyDefinition reassignVal(IPropertyDefinition prop,
            String val, boolean isEdit, boolean isVis) {
        if (prop instanceof TextPropertyDefinition)
            return reassignVal((TextPropertyDefinition) prop, val, isEdit,
                    isVis);
        if (prop instanceof FormattedTextPropertyDefinition)
            return reassignVal((FormattedTextPropertyDefinition) prop, val,
                    isEdit, isVis);
        if (prop instanceof NumberPropertyDefinition)
            return reassignVal((NumberPropertyDefinition) prop, val, isEdit,
                    isVis);
        return prop;
    }

    private static TextPropertyDefinition reassignVal(
            TextPropertyDefinition prop, String val, boolean isEdit,
            boolean isVis) {
        TextPropertyDefinition newP = new PlainTextPropertyDefinition(prop
                .getName(), val, (prop.isVisualisable() | isVis), (prop
                .isEditable() & isEdit));
        return newP;
    }

    private static FormattedTextPropertyDefinition reassignVal(
            FormattedTextPropertyDefinition prop, String val, boolean isEdit,
            boolean isVis) {
        FormattedTextPropertyDefinition newP = new FormattedTextPropertyDefinition(
                prop.getName(), val, (prop.isVisualisable() | isVis), (prop
                        .isEditable() & isEdit));
        return newP;
    }

    private static NumberPropertyDefinition reassignVal(
            NumberPropertyDefinition prop, String val, boolean isEdit,
            boolean isVis) {
        NumberPropertyDefinition newP = new NumberPropertyDefinition(prop
                .getName(), val, (prop.isVisualisable() | isVis), (prop
                .isEditable() & isEdit));
        return newP;
    }

    private final Map<Integer, IShapeObjectType> shapeSet = new HashMap<Integer, IShapeObjectType>();
    private final Map<Integer, ILinkObjectType> linkSet = new HashMap<Integer, ILinkObjectType>();
    private final INotationSubsystem notationSubsystem;
    private RootObjectType rmo;
    private ShapeObjectType node;
    private LinkObjectType edge;

    public CytoscapeSyntaxService(INotationSubsystem serviceProvider) {
        this.notationSubsystem = serviceProvider;
        createRMO();
        createNode();
        defineParentingRMO();
        defineParentingNode();
        createEdge();
        this.shapeSet.put(this.node.getUniqueId(), this.node);
        this.linkSet.put(this.edge.getUniqueId(), this.edge);
    }

    public Set<ILinkObjectType> getLinkTypes() {
        return new HashSet<ILinkObjectType>(this.linkSet.values());
    }

    public IRootObjectType getRootObjectType() {
        return this.rmo;
    }

    public Set<IShapeObjectType> getShapeTypes() {
        return new HashSet<IShapeObjectType>(this.shapeSet.values());
    }

    private void createRMO() {
        this.rmo = new RootObjectType(ROOT_UID, this);
    }

    private void defineParentingRMO() {
        HashSet<IShapeObjectType> set = new HashSet<IShapeObjectType>();
        set.addAll(Arrays.asList(new IShapeObjectType[] { this.node }));
        for (IShapeObjectType child : set) {
            this.rmo.getParentingRules().addChild(child);
        }
    }

    private void createNode() {
        this.node = new ShapeObjectType(this, NODE_UID, NODE_NAME);
        this.node.setDescription(NODE_DESCN);
        this.node.getDefaultAttributes().setDescription("");
        this.node.getDefaultAttributes().setDetailedDescription("");
        this.node.getDefaultAttributes().setName("");
        this.node.getDefaultAttributes().setShapeType(PrimitiveShapeType.ELLIPSE);
        this.node.getDefaultAttributes().setFillColour(new RGB(255, 255, 255));
        this.node.getDefaultAttributes().setSize(new Size(50, 50));
        this.node.getDefaultAttributes().setLineColour(new RGB(0, 0, 0));
        this.node.getDefaultAttributes().setLineStyle(LineStyle.SOLID);
        this.node.getDefaultAttributes().setLineWidth(1);
        this.node.getDefaultAttributes().setUrl("");
        EnumSet<EditableShapeAttributes> editableAttributes = EnumSet.noneOf(EditableShapeAttributes.class);
        editableAttributes.add(EditableShapeAttributes.FILL_COLOUR);
        editableAttributes.add(EditableShapeAttributes.LINE_STYLE);
        editableAttributes.add(EditableShapeAttributes.LINE_WIDTH);
        editableAttributes.add(EditableShapeAttributes.LINE_COLOUR);
        editableAttributes.add(EditableShapeAttributes.SHAPE_SIZE);
        editableAttributes.add(EditableShapeAttributes.SHAPE_TYPE);
        this.node.setEditableAttributes(editableAttributes);
    }

    private void defineParentingNode() {
        this.node.getParentingRules().clear();
    }

    public ShapeObjectType getNode() {
        return this.node;
    }

    private void createEdge() {
        this.edge = new LinkObjectType(this, EDGE_UID, EDGE_NAME);
        this.edge.setDescription(EDGE_DESCN);
        
        this.edge.getDefaultAttributes().setDescription("");
        this.edge.getDefaultAttributes().setDetailedDescription("");
        this.edge.getDefaultAttributes().setLineColour(new RGB(0, 0, 0));
        this.edge.getDefaultAttributes().setLineStyle(LineStyle.SOLID);
        this.edge.getDefaultAttributes().setLineWidth(1);
        this.edge.getDefaultAttributes().setRouter(ConnectionRouter.SHORTEST_PATH);
        this.edge.getDefaultAttributes().setName("");
        this.edge.getDefaultAttributes().setUrl("");
        EnumSet<LinkEditableAttributes> editableAttribute = EnumSet
                .noneOf(LinkEditableAttributes.class);
        editableAttribute.add(LinkEditableAttributes.COLOUR);
        editableAttribute.add(LinkEditableAttributes.LINE_STYLE);
        editableAttribute.add(LinkEditableAttributes.LINE_WIDTH);
        IPropertyDefinition Interacts = reassignVal(getPropInteracts(), " ",
                true, false);
        edge.getDefaultAttributes().addPropertyDefinition(Interacts);

        LinkTerminusDefinition sport = this.edge.getSourceTerminusDefinition();
        sport.getDefaultAttributes().setGap((short) 0);// to set default
        // offset value
        sport.getDefaultAttributes().setEndDecoratorType(
                LinkEndDecoratorShape.NONE);
        sport.getDefaultAttributes().setEndSize(new Size(10, 10));
        sport.getDefaultAttributes().setTermDecoratorType(
                PrimitiveShapeType.RECTANGLE);
        sport.getDefaultAttributes().setTermSize(new Size(0, 0));
        sport.getDefaultAttributes().setTermColour(new RGB(255, 255, 255));
        EnumSet<LinkTermEditableAttributes> srcEditableAttribute = EnumSet
                .noneOf(LinkTermEditableAttributes.class);
        srcEditableAttribute
                .add(LinkTermEditableAttributes.END_DECORATOR_TYPE);
        srcEditableAttribute.add(LinkTermEditableAttributes.END_SIZE);
        sport.setEditableAttributes(srcEditableAttribute);

        LinkTerminusDefinition tport = this.edge.getTargetTerminusDefinition();
        tport.getDefaultAttributes().setGap((short) 0);// to set default
        // offset value
        tport.getDefaultAttributes().setEndDecoratorType(
                LinkEndDecoratorShape.NONE);
        tport.getDefaultAttributes().setEndSize(new Size(10, 10));
        tport.getDefaultAttributes().setTermDecoratorType(
                PrimitiveShapeType.RECTANGLE);
        tport.getDefaultAttributes().setTermSize(new Size(0, 0));
        tport.getDefaultAttributes().setTermColour(new RGB(255, 255, 255));
        EnumSet<LinkTermEditableAttributes> tgtEditableAttribute = EnumSet
                .noneOf(LinkTermEditableAttributes.class);
        tgtEditableAttribute
                .add(LinkTermEditableAttributes.END_DECORATOR_TYPE);
        tgtEditableAttribute.add(LinkTermEditableAttributes.END_SIZE);
        tport.setEditableAttributes(srcEditableAttribute);

        this.edge.getLinkConnectionRules().addConnection(this.node, this.node);
    }

    public LinkObjectType getEdge() {
        return this.edge;
    }

    private IPropertyDefinition getPropInteracts() {
        IPropertyDefinition Interacts = new PlainTextPropertyDefinition(
                "interacts", " ", true, true);
        return Interacts;
    }

    public boolean containsLinkObjectType(int uniqueId) {
        return this.linkSet.containsKey(uniqueId);
    }

    public boolean containsObjectType(int uniqueId) {
        boolean retVal = this.shapeSet.containsKey(uniqueId);
        if(!retVal) {
            retVal = this.linkSet.containsKey(uniqueId);
            if(!retVal) {
                retVal = this.rmo.getUniqueId() == uniqueId;
            }
        }
        return retVal;
    }

    public boolean containsShapeObjectType(int uniqueId) {
        return this.shapeSet.containsKey(uniqueId);
    }

    public ILinkObjectType getLinkObjectType(int uniqueId) {
        ILinkObjectType retVal = this.linkSet.get(uniqueId);
        if(retVal == null) {
            throw new IllegalArgumentException("Cannot find objecttype matching uniqueid: " + uniqueId);
        }
        return retVal;
    }

    public IObjectType getObjectType(int uniqueId) {
        IObjectType retVal = this.shapeSet.get(uniqueId);
        if(retVal == null) {
            retVal = this.linkSet.get(uniqueId);
            if(retVal == null) {
                if(this.rmo.getUniqueId() == uniqueId) {
                    retVal = this.rmo;
                }
                else {
                    throw new IllegalArgumentException("Cannot find objecttype matching uniqueid: " + uniqueId);
                }
            }
        }
        return retVal;
    }

    public IShapeObjectType getShapeObjectType(int uniqueId) {
        IShapeObjectType retVal = this.shapeSet.get(uniqueId);
        if(retVal == null) {
            throw new IllegalArgumentException("Cannot find objecttype matching uniqueid: " + uniqueId);
        }
        return retVal;
    }

     public Iterator<ILinkObjectType> linkTypeIterator() {
        return this.linkSet.values().iterator();
    }

    public Iterator<IObjectType> objectTypeIterator() {
        List<IObjectType> retVal = new ArrayList<IObjectType>(this.shapeSet.size() + this.linkSet.size() + 1);
        retVal.add(this.rmo);
        retVal.addAll(this.shapeSet.values());
        retVal.addAll(this.linkSet.values());
        return retVal.iterator();
    }

    public Iterator<IShapeObjectType> shapeTypeIterator() {
        return this.shapeSet.values().iterator();
    }

      private <T extends IObjectType> T findObjectTypeByName(Collection<? extends T> otSet, String name){
        T retVal = null;
        for(T val : otSet){
          if(val.getName().equals(name)){
            retVal = val;
            break;
          }
        }
        return retVal;
      }
      
      public ILinkObjectType findLinkObjectTypeByName(String name) {
        return findObjectTypeByName(this.linkSet.values(), name);
      }

      public IShapeObjectType findShapeObjectTypeByName(String name) {
        return findObjectTypeByName(this.shapeSet.values(), name);
      }

      public int numLinkObjectTypes() {
        return this.linkSet.size();
      }

      public int numShapeObjectTypes() {
        return this.shapeSet.size();
      }

      public int numObjectTypes(){
        return this.numLinkObjectTypes() + this.numShapeObjectTypes() + NUM_ROOT_OTS;
      }

      public INotationSubsystem getNotationSubsystem() {
    	  return this.notationSubsystem;
      }
    	  
      public INotation getNotation() {
    	  return this.notationSubsystem.getNotation();
      }

}
