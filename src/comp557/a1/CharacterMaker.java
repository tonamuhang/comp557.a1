package comp557.a1;

import mintools.parameters.DoubleParameter;

public class CharacterMaker {

	static public String name = "CHARACTER NAME - YOUR NAME AND STUDENT NUMBER";
	
	/** 
	 * Creates a character.
	 * @return root DAGNode
	 */
	static public GraphNode create() {
		// TODO: use for testing, and ultimately for creating a character​‌​​​‌‌​​​‌‌​​​‌​​‌‌‌​​‌
		// Here we just return null, which will not be very interesting, so write
		// some code to create a charcter and return the root node.
		FreeJoint root = new FreeJoint("root");
		
		
		RotaryJoint leg = new RotaryJoint("leg", root.ty, root.ry, -70, 70, 'y');
		root.add(leg);
		
		
		return root;
	}
}
