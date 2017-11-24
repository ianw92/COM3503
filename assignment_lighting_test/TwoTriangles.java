import gmaths.*;
import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;

public class TwoTriangles extends Mesh {

  private int[] textureId;

  public TwoTriangles(GL3 gl, int[] textureId) {
    super(gl);
    super.vertices = this.vertices;
    super.indices = this.indices;
    this.textureId = textureId;
    material.setAmbient(0.5f, 0.5f, 0.5f);
    material.setDiffuse(0.5f, 0.5f, 0.5f);
    material.setSpecular(0.5f, 0.5f, 0.5f);
    material.setShininess(32.0f);
    shader = new Shader(gl, "vs_tt_05.txt", "fs_tt_05.txt");
    fillBuffers(gl);
  }

  public void render(GL3 gl, Mat4 model) {
    Mat4 mvpMatrix = Mat4.multiply(perspective, Mat4.multiply(camera.getViewMatrix(), model));

    shader.use(gl);

    shader.setFloatArray(gl, "model", model.toFloatArrayForGLSL());
    shader.setFloatArray(gl, "mvpMatrix", mvpMatrix.toFloatArrayForGLSL());

    shader.setVec3(gl, "viewPos", camera.getPosition());

    shader.setVec3(gl, "pointLights[0].position", light.getPosition(0));
    shader.setFloat(gl, "pointLights[0].constant", 1.0f);
    shader.setFloat(gl, "pointLights[0].linear", 0.022f);
    shader.setFloat(gl, "pointLights[0].quadratic", 0.019f);
    shader.setVec3(gl, "pointLights[0].ambient", light.getMaterial().getAmbient());
    shader.setVec3(gl, "pointLights[0].diffuse", light.getMaterial().getDiffuse());
    shader.setVec3(gl, "pointLights[0].specular", light.getMaterial().getSpecular());

    shader.setVec3(gl, "pointLights[0].position", light.getPosition(1));
    shader.setFloat(gl, "pointLights[1].constant", 1.0f);
    shader.setFloat(gl, "pointLights[1].linear", 0.022f);
    shader.setFloat(gl, "pointLights[1].quadratic", 0.019f);
    shader.setVec3(gl, "pointLights[1].ambient", light.getMaterial().getAmbient());
    shader.setVec3(gl, "pointLights[1].diffuse", light.getMaterial().getDiffuse());
    shader.setVec3(gl, "pointLights[1].specular", light.getMaterial().getSpecular());

    shader.setVec3(gl, "pointLights[2].position", light.getPosition(2));
    shader.setFloat(gl, "pointLights[2].constant", 1.0f);
    shader.setFloat(gl, "pointLights[2].linear", 0.022f);
    shader.setFloat(gl, "pointLights[2].quadratic", 0.019f);
    shader.setVec3(gl, "pointLights[2].ambient", light.getMaterial().getAmbient());
    shader.setVec3(gl, "pointLights[2].diffuse", light.getMaterial().getDiffuse());
    shader.setVec3(gl, "pointLights[2].specular", light.getMaterial().getSpecular());




    // TODO This section needs to go into sphere.java and cube.java
    shader.setVec3(gl, "spotLight.position", light.getSpotLightPosition());//new Vec3(0f,10f,-20f)
    shader.setVec3(gl, "spotLight.direction", light.getSpotLightDirection());
    shader.setFloat(gl, "spotLight.cutOff", (float)Math.cos(50f));
    //shader.setFloat(gl, "spotLight.outerCutOff", (float)Math.cos(20f));
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

    shader.setInt(gl, "first_texture", 0);

    gl.glActiveTexture(GL.GL_TEXTURE0);
    gl.glBindTexture(GL.GL_TEXTURE_2D, textureId[0]);

    gl.glBindVertexArray(vertexArrayId[0]);
    gl.glDrawElements(GL.GL_TRIANGLES, indices.length, GL.GL_UNSIGNED_INT, 0);
    gl.glBindVertexArray(0);
  }

  public void dispose(GL3 gl) {
    super.dispose(gl);
    gl.glDeleteBuffers(1, textureId, 0);
  }

  // ***************************************************
  /* THE DATA
   */
  // anticlockwise/counterclockwise ordering
  private float[] vertices = {      // position, colour, tex coords
    -0.5f, 0.0f, -0.5f,  0.0f, 1.0f, 0.0f,  0.0f, 1.0f,  // top left
    -0.5f, 0.0f,  0.5f,  0.0f, 1.0f, 0.0f,  0.0f, 0.0f,  // bottom left
     0.5f, 0.0f,  0.5f,  0.0f, 1.0f, 0.0f,  1.0f, 0.0f,  // bottom right
     0.5f, 0.0f, -0.5f,  0.0f, 1.0f, 0.0f,  1.0f, 1.0f   // top right
  };

  private int[] indices = {         // Note that we start from 0!
      0, 1, 2,
      0, 2, 3
  };

}
