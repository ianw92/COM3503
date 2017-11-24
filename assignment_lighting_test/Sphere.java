import gmaths.*;
import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;

public class Sphere extends Mesh {

  private int[] textureId1;
  private int[] textureId2;

  public Sphere(GL3 gl, int[] textureId1, int[] textureId2) {
    super(gl);
    createVertices();
    super.vertices = this.vertices;
    super.indices = this.indices;
    this.textureId1 = textureId1;
    this.textureId2 = textureId2;
    material.setAmbient(1.0f, 0.5f, 0.31f);
    material.setDiffuse(1.0f, 0.5f, 0.31f);
    material.setSpecular(0.5f, 0.5f, 0.5f);
    material.setShininess(32.0f);
    shader = new Shader(gl, "vs_cube_04.txt", "fs_cube_04.txt");
    fillBuffers(gl);
  }

  public void render(GL3 gl, Mat4 model) {
    //Mat4 model = getObjectModelMatrix();
    Mat4 mvpMatrix = Mat4.multiply(perspective, Mat4.multiply(camera.getViewMatrix(), model));

    shader.use(gl);
    shader.setFloatArray(gl, "model", model.toFloatArrayForGLSL());
    shader.setFloatArray(gl, "mvpMatrix", mvpMatrix.toFloatArrayForGLSL());

    shader.setVec3(gl, "viewPos", camera.getPosition());

    shader.setVec3(gl, "pointLights[0].position", light.getPosition(0));
    shader.setFloat(gl, "pointLights[0].constant", 1.0f);
    shader.setFloat(gl, "pointLights[0].linear", 0.22f);
    shader.setFloat(gl, "pointLights[0].quadratic", 0.019f);
    shader.setVec3(gl, "pointLights[0].ambient", light.getMaterial().getAmbient());
    shader.setVec3(gl, "pointLights[0].diffuse", light.getMaterial().getDiffuse());
    shader.setVec3(gl, "pointLights[0].specular", light.getMaterial().getSpecular());

    shader.setVec3(gl, "pointLights[0].position", light.getPosition(1));
    shader.setFloat(gl, "pointLights[1].constant", 1.0f);
    shader.setFloat(gl, "pointLights[1].linear", 0.22f);
    shader.setFloat(gl, "pointLights[1].quadratic", 0.019f);
    shader.setVec3(gl, "pointLights[1].ambient", light.getMaterial().getAmbient());
    shader.setVec3(gl, "pointLights[1].diffuse", light.getMaterial().getDiffuse());
    shader.setVec3(gl, "pointLights[1].specular", light.getMaterial().getSpecular());

    shader.setVec3(gl, "pointLights[2].position", light.getPosition(2));
    shader.setFloat(gl, "pointLights[2].constant", 1.0f);
    shader.setFloat(gl, "pointLights[2].linear", 0.22f);
    shader.setFloat(gl, "pointLights[2].quadratic", 0.019f);
    shader.setVec3(gl, "pointLights[2].ambient", light.getMaterial().getAmbient());
    shader.setVec3(gl, "pointLights[2].diffuse", light.getMaterial().getDiffuse());
    shader.setVec3(gl, "pointLights[2].specular", light.getMaterial().getSpecular());

    //SpotLight
    shader.setVec3(gl, "spotLight.position", light.getSpotLightPosition());
    shader.setVec3(gl, "spotLight.direction", light.getSpotLightDirection());
    shader.setFloat(gl, "spotLight.cutOff", (float)Math.cos(0.1f));
    shader.setFloat(gl, "spotLight.outerCutOff", (float)Math.cos(0.3f));
    shader.setFloat(gl, "spotLight.constant", 1.0f);
    shader.setFloat(gl, "spotLight.linear", 0.022f);
    shader.setFloat(gl, "spotLight.quadratic", 0.0019f);
    shader.setVec3(gl, "spotLight.ambient", new Vec3(0.1f, 0.1f, 0.1f));
    shader.setVec3(gl, "spotLight.diffuse", new Vec3(0.8f, 0.8f, 0.8f));
    shader.setVec3(gl, "spotLight.specular", new Vec3(1.0f, 1.0f, 1.0f));

    shader.setVec3(gl, "material.ambient", material.getAmbient());
    shader.setVec3(gl, "material.diffuse", material.getDiffuse());
    shader.setVec3(gl, "material.specular", material.getSpecular());
    shader.setFloat(gl, "material.shininess", material.getShininess());

    //shader.setVec3(gl, "material.ambient", material.getAmbient());
    //shader.setVec3(gl, "material.diffuse", material.getDiffuse());
    //shader.setVec3(gl, "material.specular", material.getSpecular());
    shader.setFloat(gl, "material.shininess", material.getShininess());

    shader.setInt(gl, "material.diffuse", 0);  // be careful to match these with GL_TEXTURE0 and GL_TEXTURE1
    shader.setInt(gl, "material.specular", 1);

    gl.glActiveTexture(GL.GL_TEXTURE0);
    gl.glBindTexture(GL.GL_TEXTURE_2D, textureId1[0]);
    gl.glActiveTexture(GL.GL_TEXTURE1);
    gl.glBindTexture(GL.GL_TEXTURE_2D, textureId2[0]);

    gl.glBindVertexArray(vertexArrayId[0]);
    gl.glDrawElements(GL.GL_TRIANGLES, indices.length, GL.GL_UNSIGNED_INT, 0);
    gl.glBindVertexArray(0);
  }

  public void dispose(GL3 gl) {
    super.dispose(gl);
    gl.glDeleteBuffers(1, textureId1, 0);
    gl.glDeleteBuffers(1, textureId2, 0);
  }

  // ***************************************************
  /* THE DATA
   */
  // anticlockwise/counterclockwise ordering


  private float[] vertices;
  private int[] indices;

  private void createVertices() {
    int XLONG = 30;
    int YLAT = 30;
    double r = 0.5;
    int step = 8;
    //float[]
    vertices = new float[XLONG*YLAT*step];
    for (int j = 0; j<YLAT; ++j) {
      double b = Math.toRadians(-90+180*(double)(j)/(YLAT-1));
      for (int i = 0; i<XLONG; ++i) {
        double a = Math.toRadians(360*(double)(i)/(XLONG-1));
        double z = Math.cos(b) * Math.cos(a);
        double x = Math.cos(b) * Math.sin(a);
        double y = Math.sin(b);
        vertices[j*XLONG*step+i*step+0] = (float)(r*x);
        vertices[j*XLONG*step+i*step+1] = (float)(r*y);
        vertices[j*XLONG*step+i*step+2] = (float)(r*z);
        vertices[j*XLONG*step+i*step+3] = (float)x;
        vertices[j*XLONG*step+i*step+4] = (float)y;
        vertices[j*XLONG*step+i*step+5] = (float)z;
        vertices[j*XLONG*step+i*step+6] = (float)(i)/(float)(XLONG-1);
        vertices[j*XLONG*step+i*step+7] = (float)(j)/(float)(YLAT-1);
      }
    }
    //for (int i=0; i<vertices.length; i+=step) {
    //  System.out.println(vertices[i]+", "+vertices[i+1]+", "+vertices[i+2]);
    //}

    indices = new int[(XLONG-1)*(YLAT-1)*6];
    for (int j = 0; j<YLAT-1; ++j) {
      for (int i = 0; i<XLONG-1; ++i) {
        indices[j*(XLONG-1)*6+i*6+0] = j*XLONG+i;
        indices[j*(XLONG-1)*6+i*6+1] = j*XLONG+i+1;
        indices[j*(XLONG-1)*6+i*6+2] = (j+1)*XLONG+i+1;
        indices[j*(XLONG-1)*6+i*6+3] = j*XLONG+i;
        indices[j*(XLONG-1)*6+i*6+4] = (j+1)*XLONG+i+1;
        indices[j*(XLONG-1)*6+i*6+5] = (j+1)*XLONG+i;
      }
    }
    //for (int i=0; i<indices.length; i+=3) {
    //  System.out.println(indices[i]+", "+indices[i+1]+", "+indices[i+2]);
    //}

  }

}
