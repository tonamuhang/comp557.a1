package comp557.a1;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import mintools.parameters.DoubleParameter;

public class RotaryJoint extends GraphNode{

	DoubleParameter translation, rotation;
	char axis;

	public RotaryJoint(String name, DoubleParameter translation, DoubleParameter rotation, double min, double max, char axis) {
		super(name);
		// TODO Auto-generated constructor stub
		this.translation = translation;
		this.rotation = rotation;
		this.axis = axis;
		
		dofs.add(new DoubleParameter(name + "r", 0, min, max));
	}
	
	
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		// TODO: implement the rest of this methods
		gl.glColor3f(1, 1, 1);
		GraphNode.glut.glutWireCube(0);
		
		gl.glPushMatrix();
		//Transformation starts here
		switch(axis) {
			case 'x':
				gl.glTranslated(translation.getValue(), 0, 0);
				break;
			case 'y':
				gl.glTranslated(0, translation.getValue(), 0);
				break;
			case 'z':
				gl.glTranslated(0, 0, translation.getValue());
				break;
				
			default:
				break;
		}
		
		switch(axis) {
		case 'x':
			gl.glRotated(rotation.getValue(), 1, 0, 0);
			break;
		case 'y':
			gl.glRotated(rotation.getValue(), 0, 1, 0);
			break;
		case 'z':
			gl.glRotated(rotation.getValue(), 0, 0, 1);
			break;
			
		default:
			break;
	}
		
		super.display(drawable);
		//transformation ends here
		gl.glPopMatrix();
		
	}
	

}
