package uk.ac.ed.inf.csb.BasicCytoscape1_0_0.validation;

import java.util.Set;

import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.ILinkObjectType;
import org.pathwayeditor.contextadapter.publicapi.IRootMapObjectType;
import org.pathwayeditor.contextadapter.publicapi.IShapeObjectType;

/**
 * Defines the context adapter syntax service. Implementations of this interfaces should define the visual
 * look of the glyphs in the notation and its syntactic rules. This is done by providing implementations 
 * of IShapeObjectType and ILinkObjectType and an IRootMapObject for the map.
 * <p>
 * The API provides methods for accessing the object types so created, which are then used to define new
 * map objects and the notation's syntactic rules.
 * <p>
 * The object type also controls which properties of its associated map object are editable. It also
 * defines additional annotation properties, which again may be editable and also displayed as a label 
 * associated with the map object (visualisable).
 * 
 * Typically this interface will be implemented behind an eclipse extension point and should have minimal
 * dependencies on the business object API of EPE.
 * 
 * @author smoodie
 *
 */
public interface IContextAdapterSyntaxService {

	/**
	 * The context (or notation) that this service defines the syntax of.
	 * @return A context instance which will not be null.
	 */
	IContext getContext();
	
	/**
	 * Get Service provider which was be used to instantiate this service. 
	 * This method could be used to get access to other services, which are registered for
	 * that context. 
	 * @return An non-null service provider which this service is registered with
	 */
	IContextAdapterServiceProvider getServiceProvider();

	/**
	 * Returns a set of shape object types defined by this syntax service. The set should be a copy and can be
	 * freely modified and manipulated.
	 * @return A set of shape types or an empty set if there are none.
	 */
	Set<IShapeObjectType> getShapeTypes();

	/**
	 * Returns a set of link object types defined by this syntax service. The set should be a copy and can be
	 * freely modified and manipulated.
	 * @return A set of link object types or an empty set if there are none.
	 */
	Set<ILinkObjectType> getLinkTypes();

	/**
	 * Returns an object type defining the map attribute defaults and syntax rules for shapes that can be
	 * placed on it.
	 * @return An instance of the root map object. This cannot be null and the same object will always be
	 * returned for a given instance of this interface.
	 */
	IRootMapObjectType getRootMapObjectType();

}
