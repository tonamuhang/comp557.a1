package comp557.a1;

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
		RotaryJoint leg = new RotaryJoint("leg", new DoubleParameter("rjt", 5, -5, 5), 
				new DoubleParameter("rjr", 0, -90, 90), 'y');
		
		SphericalJoint joint = new SphericalJoint("joint", new DoubleParameter("jointx", 0, -45, 45), 
				new DoubleParameter("jointy", 0, -45, 45), new DoubleParameter("jointz", 0, -45, 45));
		
		root.add(leg);
		leg.add(joint);
		
		return root;
	}
}
