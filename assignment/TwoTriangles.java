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
    shader = new Shader(gl, "vs_tt_05.txt", "fs_tt_05.txt");
    fillBuffers(gl);
  }

  public TwoTriangles(GL3 gl, int[] textureId, float[] textureCoords) {
    super(gl);
    setTexCoords(textureCoords);
    System.out.println();
    for (int i=0; i<32; i++) {
      System.out.println(vertices[i]);
    }
    super.vertices = this.vertices;
    super.indices = this.indices;
    this.textureId = textureId;
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
    shader = new Shader(gl, "vs_tt_05.txt", "fs_tt_05.txt");
    fillBuffers(gl);
  }

  public void render(GL3 gl, Mat4 model) {
    Mat4 mvpMatrix = Mat4.multiply(perspective, Mat4.multiply(camera.getViewMatrix(), model));

    shader.use(gl);
    shader.setFloatArray(gl, "model", model.toFloatArrayForGLSL());
    shader.setFloatArray(gl, "mvpMatrix", mvpMatrix.toFloatArrayForGLSL());
    shader.setVec3(gl, "viewPos", camera.getPosition());

    super.setupShaderLights(shader, gl, light, material);

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

  public void setTexCoords(float[] texCoords) {
    for (int i=0;i<4;i++) {
      for (int j=0;j<2;j++) {
        vertices[(i*8)+6+j] = texCoords[(i*2)+j];
      }
    }
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
