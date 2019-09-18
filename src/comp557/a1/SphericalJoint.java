package comp557.a1;

import javax.vecmath.Tuple3d;

import com.jogamp.openal.sound3d.Vec3f;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import mintools.parameters.DoubleParameter;

public class SphericalJoint extends GraphNode{
	DoubleParameter rx;
	DoubleParameter ry;
	DoubleParameter rz;
	Vec3f position;
	
	public SphericalJoint(String name, DoubleParameter rx, DoubleParameter ry, DoubleParameter rz) {
		super(name);
		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
		this.position = new Vec3f(0, 0, 0);
		dofs.add(rx);
		dofs.add(ry);
		dofs.add(rz);
		
	}
	
	public void setPosition(Tuple3d position) {
		this.position = new Vec3f((float)position.x, (float)position.y, (float)position.z);
		
	}
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glPushMatrix();
		
		
		gl.glRotated(rx.getValue(), 1, 0, 0);
		gl.glRotated(ry.getValue(), 0, 1, 0);
		gl.glRotated(rz.getValue(), 0, 0, 1);
		gl.glTranslated(position.v1, position.v2, position.v3);
		
		super.display(drawable);
		gl.glPopMatrix();
	}
}
