package comp557.a1;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import mintools.parameters.DoubleParameter;

public class SphericalJoint extends GraphNode{
	DoubleParameter rx;
	DoubleParameter ry;
	DoubleParameter rz;
	
	public SphericalJoint(String name, DoubleParameter rx, DoubleParameter ry, DoubleParameter rz) {
		super(name);
		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
		dofs.add(rx);
		dofs.add(ry);
		dofs.add(rz);
		
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glPushMatrix();
		
		
		gl.glRotated(rx.getValue(), 1, 0, 0);
		gl.glRotated(ry.getValue(), 0, 1, 0);
		gl.glRotated(rz.getValue(), 0, 0, 1);
		gl.glTranslated(4, 0, 0);//Temp
		
		super.display(drawable);
		gl.glPopMatrix();
	}
}
