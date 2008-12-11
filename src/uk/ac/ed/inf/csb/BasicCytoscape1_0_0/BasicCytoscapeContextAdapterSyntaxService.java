package uk.ac.ed.inf.csb.BasicCytoscape1_0_0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.FormattedTextPropertyDefinition;
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.LinkObjectType;
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.LinkTerminusDefinition;
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.NumberPropertyDefinition;
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.PlainTextPropertyDefinition;
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.RootObjectType;
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.ShapeObjectType;
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.TextPropertyDefinition;

public class BasicCytoscapeContextAdapterSyntaxService implements
        INotationSyntaxService {
    public static final int ROOT_UID = 0;
    public static final int NODE_UID = 1;
    public static final int EDGE_UID = 2;
    private static final String NODE_NAME = "Node";
    private static final String NODE_DESCN = "Node";
    private static final String EDGE_NAME = "Edge";
    private static final String EDGE_DESCN = "Edge";

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
    // shapes
    private ShapeObjectType Node;
    // links
    private LinkObjectType Edge;

    public BasicCytoscapeContextAdapterSyntaxService(
            INotationSubsystem serviceProvider) {
        this.notationSubsystem = serviceProvider;
        // "Basic Cytoscape Context"
        // "Context to test code generation of a basic cytoscape"
        // 1_0_0
        createRMO();
        // shapes
        createNode();

        defineParentingRMO();
        // shapes parenting
        defineParentingNode();

        // links
        createEdge();
        // shape set
        this.shapeSet.put(this.Node.getUniqueId(), this.Node);

        // link set
        this.linkSet.put(this.Edge.getUniqueId(), this.Edge);
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
        set.addAll(Arrays.asList(new IShapeObjectType[] { this.Node }));
        for (IShapeObjectType child : set) {
            this.rmo.getParentingRules().addChild(child);
        }
    }

    private void createNode() {
        this.Node = new ShapeObjectType(this.notationSubsystem
                .getSyntaxService(), NODE_UID, NODE_NAME);
        this.Node.setDescription(NODE_DESCN);
        this.Node.getDefaultAttributes().setShapeType(
                PrimitiveShapeType.ELLIPSE);
        this.Node.getDefaultAttributes().setFillColour(new RGB(255, 255, 255));
        this.Node.getDefaultAttributes().setSize(new Size(50, 50));
        this.Node.getDefaultAttributes().setLineColour(new RGB(0, 0, 0));
        this.Node.getDefaultAttributes().setLineStyle(LineStyle.SOLID);
        this.Node.getDefaultAttributes().setLineWidth(1);
        this.Node.getDefaultAttributes().setUrl("http://");
        EnumSet<EditableShapeAttributes> editableAttributes = EnumSet
                .noneOf(EditableShapeAttributes.class);
        editableAttributes.add(EditableShapeAttributes.FILL_COLOUR);
        editableAttributes.add(EditableShapeAttributes.LINE_STYLE);
        editableAttributes.add(EditableShapeAttributes.LINE_WIDTH);
        editableAttributes.add(EditableShapeAttributes.LINE_COLOUR);
        this.Node.setEditableAttributes(editableAttributes);
    }

    private void defineParentingNode() {
        this.Node.getParentingRules().clear();
    }

    public ShapeObjectType getNode() {
        return this.Node;
    }

    private void createEdge() {
        this.Edge = new LinkObjectType(this.notationSubsystem
                .getSyntaxService(), EDGE_UID, EDGE_NAME);
        this.Edge.setDescription(EDGE_DESCN);
        this.Edge.getDefaultLinkAttributes().setLineColour(new RGB(0, 0, 0));
        this.Edge.getDefaultLinkAttributes().setLineStyle(LineStyle.SOLID);
        this.Edge.getDefaultLinkAttributes().setLineWidth(1);
        EnumSet<LinkEditableAttributes> editableAttribute = EnumSet
                .noneOf(LinkEditableAttributes.class);
        editableAttribute.add(LinkEditableAttributes.COLOUR);
        editableAttribute.add(LinkEditableAttributes.LINE_STYLE);
        editableAttribute.add(LinkEditableAttributes.LINE_WIDTH);
        IPropertyDefinition Interacts = reassignVal(getPropInteracts(), " ",
                true, false);
        Edge.getDefaultLinkAttributes().addPropertyDefinition(Interacts);

        LinkTerminusDefinition sport = this.Edge.getSourceTerminusDefinition();
        sport.getLinkTerminusDefaults().setGap((short) 0);// to set default
        // offset value
        sport.getLinkTerminusDefaults().setLinkEndDecoratorShape(
                LinkEndDecoratorShape.NONE);
        sport.getLinkTerminusDefaults().setEndSize(new Size(10, 10));
        sport.getLinkTerminusDefaults().setTermDecoratorType(
                PrimitiveShapeType.RECTANGLE);
        sport.getLinkTerminusDefaults().setTermSize(new Size(0, 0));
        sport.getLinkTerminusDefaults().setTermColour(new RGB(255, 255, 255));
        EnumSet<LinkTermEditableAttributes> srcEditableAttribute = EnumSet
                .noneOf(LinkTermEditableAttributes.class);
        srcEditableAttribute
                .add(LinkTermEditableAttributes.TERM_DECORATOR_TYPE);
        srcEditableAttribute.add(LinkTermEditableAttributes.TERM_COLOUR);
        sport.setEditableAttributes(srcEditableAttribute);

        LinkTerminusDefinition tport = this.Edge.getTargetTerminusDefinition();
        tport.getLinkTerminusDefaults().setGap((short) 0);// to set default
        // offset value
        tport.getLinkTerminusDefaults().setLinkEndDecoratorShape(
                LinkEndDecoratorShape.NONE);
        tport.getLinkTerminusDefaults().setEndSize(new Size(10, 10));
        tport.getLinkTerminusDefaults().setTermDecoratorType(
                PrimitiveShapeType.RECTANGLE);
        tport.getLinkTerminusDefaults().setTermSize(new Size(0, 0));
        tport.getLinkTerminusDefaults().setTermColour(new RGB(255, 255, 255));
        EnumSet<LinkTermEditableAttributes> tgtEditableAttribute = EnumSet
                .noneOf(LinkTermEditableAttributes.class);
        tgtEditableAttribute
                .add(LinkTermEditableAttributes.TERM_DECORATOR_TYPE);
        tgtEditableAttribute.add(LinkTermEditableAttributes.TERM_COLOUR);
        tport.setEditableAttributes(srcEditableAttribute);

        // this.Edge.setDetailedDescription(detailedDescription);
        this.Edge.getDefaultLinkAttributes().setUrl("http://");
        this.Edge.getLinkConnectionRules().addConnection(this.Node, this.Node);
    }

    public LinkObjectType getEdge() {
        return this.Edge;
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

    public INotation getNotation() {
        return this.notationSubsystem.getNotation();
    }

    public INotationSubsystem getNotationSubsystem() {
        return this.notationSubsystem;
    }

}
