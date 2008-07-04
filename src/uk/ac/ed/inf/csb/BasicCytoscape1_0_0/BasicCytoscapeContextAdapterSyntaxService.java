package uk.ac.ed.inf.csb.BasicCytoscape1_0_0;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.businessobjects.constants.ArrowheadStyle;
import org.pathwayeditor.businessobjects.constants.LineStyle;
import org.pathwayeditor.businessobjects.constants.ShapeType;
import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinition;
import org.pathwayeditor.contextadapter.publicapi.IRootMapObjectType;
import org.pathwayeditor.contextadapter.publicapi.IShapeObjectType;
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.FormattedTextPropertyDefinition;
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.LinkEndDefinition;
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.LinkObjectType;
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.NumberPropertyDefinition;
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.RootMapObjectType;
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.ShapeObjectType;
import org.pathwayeditor.contextadapter.toolkit.ctxdefn.TextPropertyDefinition;

import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.IContextAdapterServiceProvider;
import uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation.IContextAdapterSyntaxService;

public class BasicCytoscapeContextAdapterSyntaxService implements IContextAdapterSyntaxService {
	public static enum ObjectTypes {
		Node{public String toString(){return "10";}},
		Edge{public String toString(){return "20";}},

		ROOT_MAP_OBJECT{public String toString(){return "-10";}}
	}
	
	private static int[] getRGB(String hex) {
		hex = hex.replace("#", "");
		int r = Integer.parseInt(hex.substring(0, 2), 16);
		int g = Integer.parseInt(hex.substring(2, 4), 16);
		int b = Integer.parseInt(hex.substring(4), 16);
		return new int[] { r, g, b };
	}

	private static IPropertyDefinition reassignVal(IPropertyDefinition prop,String val,boolean isEdit,boolean isVis){
		if( prop instanceof TextPropertyDefinition) return reassignVal((TextPropertyDefinition) prop,val,isEdit,isVis);
		if( prop instanceof FormattedTextPropertyDefinition) return reassignVal((FormattedTextPropertyDefinition) prop,val,isEdit,isVis);
		if( prop instanceof NumberPropertyDefinition) return reassignVal((NumberPropertyDefinition) prop,val,isEdit,isVis);
		return prop;
	}
	
	private static TextPropertyDefinition reassignVal(TextPropertyDefinition prop,String val,boolean isEdit,boolean isVis){
		TextPropertyDefinition newP=new TextPropertyDefinition(prop.getName(),val,(prop.isVisualisable() | isVis),(prop.isEditable()&isEdit));
		return newP;
	}
	
	private static FormattedTextPropertyDefinition reassignVal(FormattedTextPropertyDefinition prop,String val,boolean isEdit,boolean isVis){
		FormattedTextPropertyDefinition newP=new FormattedTextPropertyDefinition(prop.getName(),val,(prop.isVisualisable() | isVis),(prop.isEditable()&isEdit));
		return newP;
	}
	
	private static NumberPropertyDefinition reassignVal(NumberPropertyDefinition prop,String val,boolean isEdit,boolean isVis){
		NumberPropertyDefinition newP=new NumberPropertyDefinition(prop.getName(),val,(prop.isVisualisable() | isVis),(prop.isEditable()&isEdit));
		return newP;
	}
	
	private final IContext context;
	private final Set  shapeSet = new HashSet(); 
	private final Set  linkSet = new HashSet();
	private final Set  propSet=new HashSet();
	
	private RootMapObjectType rmo;
	//shapes
	private ShapeObjectType Node;

	//links
	private LinkObjectType Edge;

	
	
	private IContextAdapterServiceProvider serviceProvider;

	public IContextAdapterServiceProvider getServiceProvider() {
		return serviceProvider;
	}
	
	public BasicCytoscapeContextAdapterSyntaxService(IContextAdapterServiceProvider serviceProvider) {
		this.serviceProvider=serviceProvider;
		this.context = serviceProvider.getContext();
		//"Basic Cytoscape Context"
		//"Context to test code generation of a basic cytoscape"
		//1_0_0
		createRMO();
	//shapes
		createNode();

		defineParentingRMO();
	//shapes parenting
		defineParentingNode();

	//links
		createEdge();
	//shape set
		this.shapeSet.add(this.Node);

	//link set
		this.linkSet.add(this.Edge);		
	}

	public IContext getContext() {
		return this.context;
	}

	public Set getLinkTypes() {
		return new HashSet(this.linkSet);
	}

	public IRootMapObjectType getRootMapObjectType() {
		return this.rmo;
	}

	public Set getShapeTypes() {
		return new HashSet(this.shapeSet);
	}
		private void createRMO(){
			this.rmo = new RootMapObjectType(this.context, ObjectTypes.ROOT_MAP_OBJECT);
		}
		private void defineParentingRMO(){
			HashSet<IShapeObjectType> set=new HashSet<IShapeObjectType>();
			set.addAll(Arrays.asList(new IShapeObjectType[]{this.Node}));
			for (IShapeObjectType child : set) {
			  this.rmo.getParentingRules().addChild(child);
			}

		}

	private void createNode(){
	this.Node = new ShapeObjectType(this.context, ObjectTypes.Node);
	this.Node.setName("Node");
	//this.Node.setDescription("node");
	this.Node.setDescription("node");//ment to be TypeDescription rather
	this.Node.setShapeType(ShapeType.RECTANGLE);
	this.Node.setFillProperty(255,255,255);
	this.Node.setSize(20,20);
	int[] lc=new int[]{0,0,0};
	this.Node.setLineProperty(1, LineStyle.SOLID,lc[0],lc[1],lc[2]);
	this.Node.setShapeType(ShapeType.ELLIPSE);		int[] s=new int[]{50,50};
			this.Node.setSize(s[0],s[1]);
	this.Node.setFillEditable(true);
	//this.Node.setShapeTypeEditable(true);
	//this.Node.setSizeEditable(true);
	this.Node.setLineStyleEditable(true);
	this.Node.setLineWidthEditable(true);
	this.Node.setLineColourEditable(true);
	this.Node.setURL("http://");
	}

		private void defineParentingNode(){
			this.Node.getParentingRules().clear();
		}

		public ShapeObjectType getNode(){
			return this.Node;
		}

	
	private void createEdge(){
	HashSet<IShapeObjectType> set=null;
	this.Edge= new LinkObjectType(this.context, ObjectTypes.Edge);
	int[] lc=new int[]{0,0,0};
	this.Edge.setLineProperty(1, LineStyle.SOLID,lc[0],lc[1],lc[2]);
	this.Edge.setName("Edge");
	this.Edge.setLineColourEditable(true);
	this.Edge.setLineStyleEditable(true);
	this.Edge.setLineWidthEditable(true);
	 	IPropertyDefinition Interacts=reassignVal(getPropInteracts()," ",true,false);
	 	Edge.addProperty(Interacts);
	 
	LinkEndDefinition sport=this.Edge.getLinkSource();
	LinkEndDefinition tport=this.Edge.getLinkTarget();
	sport.setOffset(0);//to set default offset value
	sport.getLinkEndDecorator().setDecorator(ArrowheadStyle.NONE, 10,10);
	sport.getTerminusDecorator().setDecoratorType(ShapeType.RECTANGLE);
	sport.getTerminusDecorator().setSize(0,0);
	int[] csport=new int[]{255,255,255};
	sport.getTerminusDecorator().setColourProperties(csport[0],csport[1],csport[2]);
	sport.getTerminusDecorator().setLineProperties(0, LineStyle.SOLID);
	sport.getTerminusDecorator().setShapeTypeEditable(true);
	sport.getTerminusDecorator().setColourEditable(true);
	tport.setOffset(0);
	tport.getLinkEndDecorator().setDecorator(ArrowheadStyle.NONE, 10,10);
	tport.getTerminusDecorator().setDecoratorType(ShapeType.RECTANGLE);
	tport.getTerminusDecorator().setSize(0,0);
	int[] ctport=new int[]{255,255,255};
	tport.getTerminusDecorator().setColourProperties(ctport[0],ctport[1],ctport[2]);
	tport.getTerminusDecorator().setLineProperties(0, LineStyle.SOLID);
	tport.getTerminusDecorator().setShapeTypeEditable(true);
	tport.getTerminusDecorator().setColourEditable(true);

	//this.Edge.setDetailedDescription(detailedDescription);
	this.Edge.setUrl("http://");
	set=new HashSet<IShapeObjectType>();
	set.addAll(Arrays.asList(new IShapeObjectType[]{this.Node}));
	for (IShapeObjectType tgt : set) {
	  this.Edge.getLinkConnectionRules().addConnection(this.Node, tgt);
	}

	}

	public LinkObjectType getEdge(){
		return this.Edge;
	}
	

	private IPropertyDefinition getPropInteracts(){
		IPropertyDefinition Interacts=new TextPropertyDefinition("interacts"," ",true,true);
		return Interacts;
	}


}
