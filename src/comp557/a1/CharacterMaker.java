package comp557.a1;

import com.jogamp.openal.sound3d.Vec3f;

import mintools.parameters.DoubleParameter;

public class CharacterMaker {

	static public String name = "LadyBugy - Muhang Li 260736135";
	
	/** 
	 * Creates a character.
	 * @return root DAGNode
	 */
	static public GraphNode create() {
		// TODO: use for testing, and ultimately for creating a character​‌​​​‌‌​​​‌‌​​​‌​​‌‌‌​​‌
		// Here we just return null, which will not be very interesting, so write
		// some code to create a charcter and return the root node.
		FreeJoint root = new FreeJoint("root");
		
		
		
		//Create a rotary joint node that moves/rotates in a given axis
		RotaryJoint leg = new RotaryJoint("leg", new DoubleParameter("rjt", 2, -5, 5), 
				new DoubleParameter("rjr", 0, -90, 90));
		
		//Create a speherical joint node that only rotates to the set orientation
		SphericalJoint joint = new SphericalJoint("joint", new DoubleParameter("jointx", 0, -45, 45), 
				new DoubleParameter("jointy", 0, -45, 45), new DoubleParameter("jointz", 0, -45, 45));
		
		
		//Create a geomtry node 
	
		GeometryGraphTriangle mouth = new GeometryGraphTriangle("mouth", new Vec3f(0, -3, 0),
				new Vec3f(2, 0.5f, 0.5f), new Vec3f(0.7f, 1, 1));
		GeometryGraphCircle leye = new GeometryGraphCircle("leye", new Vec3f(-5, 5, 0),
				new Vec3f(1, 1, 1), new Vec3f(1, 1, 1));
		GeometryGraphCircle reye = new GeometryGraphCircle("reye", new Vec3f(5, 5, 0),
				new Vec3f(1, 1, 1), new Vec3f(1, 1, 1));
		
		root.add(leg);
		root.add(mouth);
		root.add(leye);
		root.add(reye);
		leg.add(joint);
		
		return root;
	}
}
