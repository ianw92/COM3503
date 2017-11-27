import gmaths.*;
import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;

public class Cube extends Mesh {

  private int[] textureId1;
  private int[] textureId2;

  public Cube(GL3 gl, int[] textureId1, int[] textureId2) {
    super(gl);
    super.vertices = this.vertices;
    super.indices = this.indices;
    this.textureId1 = textureId1;
    this.textureId2 = textureId2;
    material.setAmbient(1.0f, 0.5f, 0.31f, "main");
    material.setDiffuse(1.0f, 0.5f, 0.31f, "main");
    material.setSpecular(0.5f, 0.5f, 0.5f, "main");
    material.setAmbient(1.0f, 0.5f, 0.31f, "point");
    material.setDiffuse(1.0f, 0.5f, 0.31f, "point");
    material.setSpecular(0.5f, 0.5f, 0.5f, "point");
    material.setAmbient(1.0f, 0.5f, 0.31f, "spot");
    material.setDiffuse(1.0f, 0.5f, 0.31f, "spot");
    material.setSpecular(0.5f, 0.5f, 0.5f, "spot");
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
    shader.setFloat(gl, "pointLights[0].linear", 0.027f);
    shader.setFloat(gl, "pointLights[0].quadratic", 0.0028f);
    shader.setVec3(gl, "pointLights[0].ambient", light.getMaterial().getAmbient("main"));
    shader.setVec3(gl, "pointLights[0].diffuse", light.getMaterial().getDiffuse("main"));
    shader.setVec3(gl, "pointLights[0].specular", light.getMaterial().getSpecular("main"));

    shader.setVec3(gl, "pointLights[1].position", light.getPosition(1));
    shader.setFloat(gl, "pointLights[1].constant", 1.0f);
    shader.setFloat(gl, "pointLights[1].linear", 0.007f);
    shader.setFloat(gl, "pointLights[1].quadratic", 0.0002f);
    shader.setVec3(gl, "pointLights[1].ambient", light.getMaterial().getAmbient("main"));
    shader.setVec3(gl, "pointLights[1].diffuse", light.getMaterial().getDiffuse("main"));
    shader.setVec3(gl, "pointLights[1].specular", light.getMaterial().getSpecular("main"));

    shader.setVec3(gl, "pointLights[2].position", light.getPosition(2));
    shader.setFloat(gl, "pointLights[2].constant", 1.0f);
    shader.setFloat(gl, "pointLights[2].linear", 0.027f);
    shader.setFloat(gl, "pointLights[2].quadratic", 0.0028f);
    shader.setVec3(gl, "pointLights[2].ambient", light.getMaterial().getAmbient("point"));
    shader.setVec3(gl, "pointLights[2].diffuse", light.getMaterial().getDiffuse("point"));
    shader.setVec3(gl, "pointLights[2].specular", light.getMaterial().getSpecular("point"));

    shader.setVec3(gl, "pointLights[3].position", light.getPosition(3));
    shader.setFloat(gl, "pointLights[3].constant", 1.0f);
    shader.setFloat(gl, "pointLights[3].linear", 0.027f);
    shader.setFloat(gl, "pointLights[3].quadratic", 0.0028f);
    shader.setVec3(gl, "pointLights[3].ambient", light.getMaterial().getAmbient("point"));
    shader.setVec3(gl, "pointLights[3].diffuse", light.getMaterial().getDiffuse("point"));
    shader.setVec3(gl, "pointLights[3].specular", light.getMaterial().getSpecular("point"));

    shader.setVec3(gl, "pointLights[4].position", light.getPosition(4));
    shader.setFloat(gl, "pointLights[4].constant", 1.0f);
    shader.setFloat(gl, "pointLights[4].linear", 0.027f);
    shader.setFloat(gl, "pointLights[4].quadratic", 0.0028f);
    shader.setVec3(gl, "pointLights[4].ambient", light.getMaterial().getAmbient("point"));
    shader.setVec3(gl, "pointLights[4].diffuse", light.getMaterial().getDiffuse("point"));
    shader.setVec3(gl, "pointLights[4].specular", light.getMaterial().getSpecular("point"));

    shader.setVec3(gl, "pointLights[5].position", light.getPosition(5));
    shader.setFloat(gl, "pointLights[5].constant", 1.0f);
    shader.setFloat(gl, "pointLights[5].linear", 0.027f);
    shader.setFloat(gl, "pointLights[5].quadratic", 0.0028f);
    shader.setVec3(gl, "pointLights[5].ambient", light.getMaterial().getAmbient("point"));
    shader.setVec3(gl, "pointLights[5].diffuse", light.getMaterial().getDiffuse("point"));
    shader.setVec3(gl, "pointLights[5].specular", light.getMaterial().getSpecular("point"));

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

    shader.setVec3(gl, "material.ambient", material.getAmbient("main"));
    shader.setVec3(gl, "material.diffuse", material.getDiffuse("main"));
    shader.setVec3(gl, "material.specular", material.getSpecular("main"));
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

   private float[] vertices = new float[] {  // x,y,z, nx,ny,nz, s,t
      -0.5f, -0.5f, -0.5f,  -1, 0, 0,  0.0f, 0.0f,  // 0
      -0.5f, -0.5f,  0.5f,  -1, 0, 0,  1.0f, 0.0f,  // 1
      -0.5f,  0.5f, -0.5f,  -1, 0, 0,  0.0f, 1.0f,  // 2
      -0.5f,  0.5f,  0.5f,  -1, 0, 0,  1.0f, 1.0f,  // 3
       0.5f, -0.5f, -0.5f,   1, 0, 0,  1.0f, 0.0f,  // 4
       0.5f, -0.5f,  0.5f,   1, 0, 0,  0.0f, 0.0f,  // 5
       0.5f,  0.5f, -0.5f,   1, 0, 0,  1.0f, 1.0f,  // 6
       0.5f,  0.5f,  0.5f,   1, 0, 0,  0.0f, 1.0f,  // 7

      -0.5f, -0.5f, -0.5f,  0,0,-1,  1.0f, 0.0f,  // 8
      -0.5f, -0.5f,  0.5f,  0,0,1,   0.0f, 0.0f,  // 9
      -0.5f,  0.5f, -0.5f,  0,0,-1,  1.0f, 1.0f,  // 10
      -0.5f,  0.5f,  0.5f,  0,0,1,   0.0f, 1.0f,  // 11
       0.5f, -0.5f, -0.5f,  0,0,-1,  0.0f, 0.0f,  // 12
       0.5f, -0.5f,  0.5f,  0,0,1,   1.0f, 0.0f,  // 13
       0.5f,  0.5f, -0.5f,  0,0,-1,  0.0f, 1.0f,  // 14
       0.5f,  0.5f,  0.5f,  0,0,1,   1.0f, 1.0f,  // 15

      -0.5f, -0.5f, -0.5f,  0,-1,0,  0.0f, 0.0f,  // 16
      -0.5f, -0.5f,  0.5f,  0,-1,0,  0.0f, 1.0f,  // 17
      -0.5f,  0.5f, -0.5f,  0,1,0,   0.0f, 1.0f,  // 18
      -0.5f,  0.5f,  0.5f,  0,1,0,   0.0f, 0.0f,  // 19
       0.5f, -0.5f, -0.5f,  0,-1,0,  1.0f, 0.0f,  // 20
       0.5f, -0.5f,  0.5f,  0,-1,0,  1.0f, 1.0f,  // 21
       0.5f,  0.5f, -0.5f,  0,1,0,   1.0f, 1.0f,  // 22
       0.5f,  0.5f,  0.5f,  0,1,0,   1.0f, 0.0f   // 23
   };

   private int[] indices =  new int[] {
      0,1,3, // x -ve
      3,2,0, // x -ve
      4,6,7, // x +ve
      7,5,4, // x +ve
      9,13,15, // z +ve
      15,11,9, // z +ve
      8,10,14, // z -ve
      14,12,8, // z -ve
      16,20,21, // y -ve
      21,17,16, // y -ve
      23,22,18, // y +ve
      18,19,23  // y +ve
  };

}
