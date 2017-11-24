import gmaths.*;
import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;

public class Light {

  private Material material;
  private Vec3 position;
  private Vec3 spotLightPosition = new Vec3(1.13f, 10.92f, -0.3f);
  private Vec3 spotLightDirection = spotLightPosition;
  private Mat4 model;
  private Shader shader;
  private Camera camera;
  private Mat4 perspective;
  private float ambientLightLevel = 0.5f;
  private float diffuseLightLevel = 0.8f;
  private float specularLightLevel = 1.0f;

  public Light(GL3 gl) {
    material = new Material();
    material.setAmbient(ambientLightLevel, ambientLightLevel, ambientLightLevel);
    material.setDiffuse(diffuseLightLevel, diffuseLightLevel, diffuseLightLevel);
    material.setSpecular(specularLightLevel, specularLightLevel, specularLightLevel);
    // material.setAmbient(0,0,0);
    // material.setDiffuse(0,0,0);
    // material.setSpecular(0,0,0);
    position = new Vec3(3f,2f,1f);
    model = new Mat4(1);
    shader = new Shader(gl, "vs_light_01.txt", "fs_light_01.txt");
    fillBuffers(gl);
  }

  public float getAmbientLightLevel() {
    return this.ambientLightLevel;
  }

  public void setAmbientLightLevel(float ambientLightLevel) {
    this.ambientLightLevel = ambientLightLevel;
    material.setAmbient(ambientLightLevel, ambientLightLevel, ambientLightLevel);
  }

  // Get position of point light
  public Vec3 getPosition(int lightNum) {
    return pointLightPositions[lightNum];
  }

  public Vec3 getSpotLightPosition() {
    return spotLightPosition;
  }

  public Vec3 getSpotLightDirection() {
    return spotLightDirection;
  }

  public void setSpotLightPosition(Vec3 position) {
    spotLightPosition = position;
  }

  public void setSpotLightDirection(Vec3 direction) {
    spotLightDirection = direction;
  }

  public void setMaterial(Material m) {
    material = m;
  }

  public Material getMaterial() {
    return material;
  }

  public void setCamera(Camera camera) {
    this.camera = camera;
  }

  public void setPerspective(Mat4 perspective) {
    this.perspective = perspective;
  }

  public void render(GL3 gl) { //, Mat4 perspective, Mat4 view) {
    Mat4 model;
    Mat4 mvpMatrix;
    gl.glBindVertexArray(vertexArrayId[0]);

    for (int i = 0; i < pointLightPositions.length; i++) {
      model = new Mat4(1);
      model = Mat4.multiply(Mat4Transform.scale(0.3f,0.3f,0.3f), model);
      model = Mat4.multiply(Mat4Transform.translate(pointLightPositions[i]), model);

      mvpMatrix = Mat4.multiply(perspective, Mat4.multiply(camera.getViewMatrix(), model));

      shader.use(gl);
      shader.setFloatArray(gl, "mvpMatrix", mvpMatrix.toFloatArrayForGLSL());

      gl.glDrawElements(GL.GL_TRIANGLES, indices.length, GL.GL_UNSIGNED_INT, 0);
    }
    //SpotLight
    model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(0.2f,0.2f,0.2f), model);
    model = Mat4.multiply(Mat4Transform.translate(spotLightPosition), model);
    mvpMatrix = Mat4.multiply(perspective, Mat4.multiply(camera.getViewMatrix(), model));
    shader.use(gl);
    shader.setFloatArray(gl, "mvpMatrix", mvpMatrix.toFloatArrayForGLSL());

    gl.glDrawElements(GL.GL_TRIANGLES, indices.length, GL.GL_UNSIGNED_INT, 0);

    gl.glBindVertexArray(0);
  }

  public void dispose(GL3 gl) {
    gl.glDeleteBuffers(1, vertexBufferId, 0);
    gl.glDeleteVertexArrays(1, vertexArrayId, 0);
    gl.glDeleteBuffers(1, elementBufferId, 0);
  }

    // ***************************************************
  /* THE DATA
   */
  // anticlockwise/counterclockwise ordering

    private float[] vertices = new float[] {  // x,y,z
      -0.5f, -0.5f, -0.5f,  // 0
      -0.5f, -0.5f,  0.5f,  // 1
      -0.5f,  0.5f, -0.5f,  // 2
      -0.5f,  0.5f,  0.5f,  // 3
       0.5f, -0.5f, -0.5f,  // 4
       0.5f, -0.5f,  0.5f,  // 5
       0.5f,  0.5f, -0.5f,  // 6
       0.5f,  0.5f,  0.5f   // 7
     };

    private int[] indices =  new int[] {
      0,1,3, // x -ve
      3,2,0, // x -ve
      4,6,7, // x +ve
      7,5,4, // x +ve
      1,5,7, // z +ve
      7,3,1, // z +ve
      6,4,0, // z -ve
      0,2,6, // z -ve
      0,4,5, // y -ve
      5,1,0, // y -ve
      2,3,7, // y +ve
      7,6,2  // y +ve
    };

    private Vec3[] pointLightPositions = new Vec3[] {
      new Vec3(0f,30f,0f),
      new Vec3(20f,10f,0f),
      new Vec3(-20f,10f,0f),
    };

  private int vertexStride = 3;
  private int vertexXYZFloats = 3;

  // ***************************************************
  /* THE LIGHT BUFFERS
   */

  private int[] vertexBufferId = new int[1];
  private int[] vertexArrayId = new int[1];
  private int[] elementBufferId = new int[1];

  private void fillBuffers(GL3 gl) {
    gl.glGenVertexArrays(1, vertexArrayId, 0);
    gl.glBindVertexArray(vertexArrayId[0]);
    gl.glGenBuffers(1, vertexBufferId, 0);
    gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vertexBufferId[0]);
    FloatBuffer fb = Buffers.newDirectFloatBuffer(vertices);

    gl.glBufferData(GL.GL_ARRAY_BUFFER, Float.BYTES * vertices.length, fb, GL.GL_STATIC_DRAW);

    int stride = vertexStride;
    int numXYZFloats = vertexXYZFloats;
    int offset = 0;
    gl.glVertexAttribPointer(0, numXYZFloats, GL.GL_FLOAT, false, stride*Float.BYTES, offset);
    gl.glEnableVertexAttribArray(0);

    gl.glGenBuffers(1, elementBufferId, 0);
    IntBuffer ib = Buffers.newDirectIntBuffer(indices);
    gl.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, elementBufferId[0]);
    gl.glBufferData(GL.GL_ELEMENT_ARRAY_BUFFER, Integer.BYTES * indices.length, ib, GL.GL_STATIC_DRAW);
    gl.glBindVertexArray(0);
  }

}