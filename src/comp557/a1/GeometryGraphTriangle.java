package comp557.a1;

import com.jogamp.openal.sound3d.Vec3f;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import mintools.parameters.DoubleParameter;

public class GeometryGraphTriangle extends GraphNode{
	

	Vec3f color, location, scale;
	public GeometryGraphTriangle(String name, Vec3f location, Vec3f scale, Vec3f color) {
		super(name);
		this.location = location;
		this.scale = scale;
		this.color = color;
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glColor3f(color.v1, color.v2, color.v3);
		
		gl.glPushMatrix();
		gl.glTranslated(location.v1, location.v2, location.v3);//Temp
		gl.glScalef(scale.v1, scale.v2, scale.v3);
		
		
		GraphNode.glut.glutSolidCone(1, 2, 10, 10);
		super.display(drawable);
		gl.glPopMatrix();
	}
}
