/* I declare that this code is my own work */
/* I based it on 'Sphere.java' provided by Dr Steve Maddock in eSheet6/Week6_3_scene_graph/
   and adapted it for my own use.
   The changes to the original file have been line 26 instead of the block of material.set...() function calls
   and line 38 instead of the block of shader.set...() function calls.
   These blocks of code have been moved into methods in the super class Mesh.java
   The other change has been the overloading of the constructor to also accept a set of texture coordinates.
      This constructor is used for the wall with a window and differs from the original constructor as it calls a new
      method I have written 'setTexCoords()' (line 66) */
/* Author: Ian Williams, Email: iwilliams3@sheffield.ac.uk */

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
    super.setupDefaultMaterialLighting();
    shader = new Shader(gl, "vs_tt.txt", "fs_tt.txt");
    fillBuffers(gl);
  }

  // Constructor used for window wall where different texture coords are required
  public TwoTriangles(GL3 gl, int[] textureId, float[] textureCoords) {
    super(gl);
    setTexCoords(textureCoords);
    super.vertices = this.vertices;
    super.indices = this.indices;
    this.textureId = textureId;
    super.setupDefaultMaterialLighting();
    shader = new Shader(gl, "vs_tt.txt", "fs_tt.txt");
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
