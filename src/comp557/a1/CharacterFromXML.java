package comp557.a1;
 		  	  				   
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jogamp.openal.sound3d.Vec3f;

import mintools.parameters.DoubleParameter;

/**
 * Loads an articulated character hierarchy from an XML file. 
 */
public class CharacterFromXML {

	public static GraphNode load( String filename ) {
		try {
			InputStream inputStream = new FileInputStream(new File(filename));
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputStream);
			return createScene( null, document.getDocumentElement() ); // we don't check the name of the document elemnet
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load simulation input file.", e);
		}
	}
	
	/**
	 * Load a subtree from a XML node.
	 * Returns the root on the call where the parent is null, but otherwise
	 * all children are added as they are created and all other deeper recursive
	 * calls will return null.
	 */
	public static GraphNode createScene( GraphNode parent, Node dataNode ) {
        NodeList nodeList = dataNode.getChildNodes();
        for ( int i = 0; i < nodeList.getLength(); i++ ) {
            Node n = nodeList.item(i);
            // skip all text, just process the ELEMENT_NODEs
            if ( n.getNodeType() != Node.ELEMENT_NODE ) continue;
            String nodeName = n.getNodeName();
            GraphNode node = null;
            if ( nodeName.equalsIgnoreCase( "node" ) ) {
            	node = CharacterFromXML.createJoint( n );
            } else if ( nodeName.equalsIgnoreCase( "geom" ) ) {        		
        		node = CharacterFromXML.createGeom( n ) ;            
            }
            // recurse to load any children of this node
            createScene( node, n );
            if ( parent == null ) {
            	// if no parent, we can only have one root... ignore other nodes at root level
            	return node;
            } else {
            	parent.add( node );
            }
        }
        return null;
	}
	
	/**​‌​​​‌‌​​​‌‌​​​‌​​‌‌‌​​‌
	 * Create a joint
	 * 
	 * TODO: Objective 5: Adapt commented code in createJoint() to create your joint nodes when loading from xml
	 */
	public static GraphNode createJoint( Node dataNode ) {
		String type = dataNode.getAttributes().getNamedItem("type").getNodeValue();
		String name = dataNode.getAttributes().getNamedItem("name").getNodeValue();
		Tuple3d t;
		if ( type.equals("free") ) {
			FreeJoint joint = new FreeJoint( name );
			
			return joint;
		} else if ( type.equals("spherical") ) {
			// position is optional (ignored if missing) but should probably be a required attribute!​‌​​​‌‌​​​‌‌​​​‌​​‌‌‌​​‌
			// Could add optional attributes for limits (to all joints)
			
			Tuple3d orientation = getTuple3dAttr(dataNode, "orientation");
			float min = Float.valueOf(dataNode.getAttributes().getNamedItem("min").getNodeValue());
			float max = Float.valueOf(dataNode.getAttributes().getNamedItem("max").getNodeValue());

			
			SphericalJoint joint = new SphericalJoint(name, new DoubleParameter(name + "rx", orientation.x, min, max),
					new DoubleParameter(name + "ry", orientation.y, min, max), 
					new DoubleParameter(name + "rz", orientation.z, min, max));
			if ( (t=getTuple3dAttr(dataNode,"translation")) != null ) joint.setPosition( t );			
			return joint;
			
		} else if ( type.equals("rotary") ) {
			// position and axis are required... passing null to set methods
			// likely to cause an execption (perhaps OK)
			
			float translation = Float.valueOf(dataNode.getAttributes().getNamedItem("translation").getNodeValue());
			float rotation = Float.valueOf(dataNode.getAttributes().getNamedItem("rotation").getNodeValue());
			float min = Float.valueOf(dataNode.getAttributes().getNamedItem("min").getNodeValue());
			float max = Float.valueOf(dataNode.getAttributes().getNamedItem("max").getNodeValue());
			String axis = dataNode.getAttributes().getNamedItem("axis").getNodeValue();
			RotaryJoint joint = new RotaryJoint(name, rotation);
			joint.setMinMax(min, max);
			joint.setAxis(axis);
			joint.setTranslation(translation);
//			joint.setRotation(rotation);
			return joint;
		}
		return null;
	}

	/**
	 * Creates a geometry DAG node 
	 * 
	 * TODO: Objective 5: Adapt commented code in greatGeom to create your geometry nodes when loading from xml
	 */
	public static GraphNode createGeom( Node dataNode ) {
		String type = dataNode.getAttributes().getNamedItem("type").getNodeValue();
		
		String name = dataNode.getAttributes().getNamedItem("name").getNodeValue();
		Tuple3d value = getTuple3dAttr(dataNode, "color");
		Vec3f color = new Vec3f((float)value.x, (float)value.y, (float)value.z);
		value = getTuple3dAttr(dataNode, "location");
		Vec3f location = new Vec3f((float)value.x, (float)value.y, (float)value.z);
		value = getTuple3dAttr(dataNode, "scale");
		Vec3f scale = new Vec3f((float)value.x, (float)value.y, (float)value.z);
		if ( type.equals("triangle" ) ) {
			
			GeometryGraphTriangle geom = new GeometryGraphTriangle(name, location, scale, color);
//			if ( (t=getTuple3dAttr(dataNode,"center")) != null ) geom.setCentre( t );
//			if ( (t=getTuple3dAttr(dataNode,"scale")) != null ) geom.setScale( t );
//			if ( (t=getTuple3dAttr(dataNode,"color")) != null ) geom.setColor( t );
			return geom;
		} else if ( type.equals( "sphere" )) {
			GeometryGraphCircle geom = new GeometryGraphCircle(name, location, scale, color);			
//			if ( (t=getTuple3dAttr(dataNode,"center")) != null ) geom.setCentre( t );
//			if ( (t=getTuple3dAttr(dataNode,"scale")) != null ) geom.setScale( t );
//			if ( (t=getTuple3dAttr(dataNode,"color")) != null ) geom.setColor( t );
			return geom;	
		}
		return null;		
	}
	
	/**
	 * Loads tuple3d attributes of the given name from the given node.
	 * @param dataNode
	 * @param attrName
	 * @return null if attribute not present
	 */
	public static Tuple3d getTuple3dAttr( Node dataNode, String attrName ) {
		Node attr = dataNode.getAttributes().getNamedItem( attrName);
		Vector3d tuple = null;
		if ( attr != null ) {
			Scanner s = new Scanner( attr.getNodeValue() );
			tuple = new Vector3d( s.nextDouble(), s.nextDouble(), s.nextDouble() );			
			s.close();
		}
		return tuple;
	}

}