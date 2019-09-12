package comp557.a1;

import javax.vecmath.Tuple3d;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import mintools.parameters.DoubleParameter;

public class SphericalJoint extends GraphNode{
	DoubleParameter rx;
	DoubleParameter ry;
	DoubleParameter rz;
	Tuple3d position;
	
	public SphericalJoint(String name, DoubleParameter rx, DoubleParameter ry, DoubleParameter rz) {
		super(name);
		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
		dofs.add(rx);
		dofs.add(ry);
		dofs.add(rz);
		
	}
	
	public void setPosition(Tuple3d position) {
		this.position = position;
	}
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glPushMatrix();
		
		
		gl.glRotated(rx.getValue(), 1, 0, 0);
		gl.glRotated(ry.getValue(), 0, 1, 0);
		gl.glRotated(rz.getValue(), 0, 0, 1);
		gl.glTranslated(position.x, position.y, position.z);//Temp
		
		super.display(drawable);
		gl.glPopMatrix();
	}
}
