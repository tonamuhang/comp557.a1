package comp557.a1;
import javax.vecmath.Tuple3d;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import mintools.parameters.DoubleParameter;

public class RotaryJoint extends GraphNode{

	DoubleParameter translation, rotation;
	char axis;
	float min, max;
	
	public RotaryJoint(String name, double rotation) {
		super(name);
		// TODO Auto-generated constructor stub
		translation = new DoubleParameter("t", 0, -5, 5);
		this.rotation = new DoubleParameter("r", rotation, -180, 180);
		
		dofs.add(this.rotation);
	}
	
	public void setAxis(String axis) {
		this.axis = axis.toCharArray()[0];
	}
	
	public void setMinMax(float min, float max) {
		this.rotation.setMinimum((double)min);
		this.rotation.setMaximum((double)max);
	}
	
	public void setTranslation(double translation) {
		this.translation.setValue(translation);
	}
	
	public void setRotation(double rotation) {
		this.rotation.setValue(rotation);
		
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		// TODO: implement the rest of this methods
		gl.glColor3f(1, 1, 1);
		
		
		gl.glPushMatrix();
		//Transformation starts here
		
		//Apply translation first then rotation
		
		switch(axis) {
		case 'x':
			
			gl.glRotated(this.rotation.getValue(), 1, 0, 0);
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
		
		
		super.display(drawable);
		//transformation ends here
		gl.glPopMatrix();
		
	}
	

}
