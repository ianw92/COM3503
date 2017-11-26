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
  private float ambientLightLevelMain = 0.15f;
  private float diffuseLightLevelMain = 0.5f;
  private float specularLightLevelMain = 0.7f;

  private float ambientLightLevelPoint = 0.05f;
  private float diffuseLightLevelPoint = 0.4f;
  private float specularLightLevelPoint = 0.8f;

  private float ambientLightLevelSpot = 0f;
  private float diffuseLightLevelSpot = 0.8f;
  private float specularLightLevelSpot = 1.0f;

  public Light(GL3 gl) {
    material = new Material();
    material.setAmbient(ambientLightLevelMain, ambientLightLevelMain, ambientLightLevelMain, "main");
    material.setDiffuse(diffuseLightLevelMain, diffuseLightLevelMain, diffuseLightLevelMain, "main");
    material.setSpecular(specularLightLevelMain, specularLightLevelMain, specularLightLevelMain, "main");
    material.setAmbient(ambientLightLevelPoint, ambientLightLevelPoint, ambientLightLevelPoint, "point");
    material.setDiffuse(diffuseLightLevelPoint, diffuseLightLevelPoint, diffuseLightLevelPoint, "point");
    material.setSpecular(specularLightLevelPoint, specularLightLevelPoint, specularLightLevelPoint, "point");
    material.setAmbient(ambientLightLevelSpot, ambientLightLevelSpot, ambientLightLevelSpot, "spot");
    material.setDiffuse(diffuseLightLevelSpot, diffuseLightLevelSpot, diffuseLightLevelSpot, "spot");
    material.setSpecular(specularLightLevelSpot, specularLightLevelSpot, specularLightLevelSpot, "spot");
    position = new Vec3(3f,2f,1f);
    model = new Mat4(1);
    shader = new Shader(gl, "vs_light_01.txt", "fs_light_01.txt");
    fillBuffers(gl);
  }

  public float getAmbientLightLevel() {
    return this.ambientLightLevelMain;
  }

  public void setLightLevel(float ambientLightLevel, float diffuseLightLevel, float specularLightLevel, String lightType) {
    this.ambientLightLevelMain = ambientLightLevel;
    this.diffuseLightLevelMain = diffuseLightLevel;
    this.specularLightLevelMain = specularLightLevel;
    material.setAmbient(ambientLightLevel, ambientLightLevel, ambientLightLevel, lightType);
    material.setDiffuse(diffuseLightLevel, diffuseLightLevel, diffuseLightLevel, lightType);
    material.setSpecular(specularLightLevel, specularLightLevel, specularLightLevel, lightType);
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

  public void setPointLightPosition(Vec3 position, int lamp) {
    pointLightPositions[lamp] = position;
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
      new Vec3(0f,30f,0f), //Main light on ceiling
      new Vec3(0f,0f,0f), //Lamp1
      new Vec3(0f,0f,0f), //Lamp2
      new Vec3(0f,0f,0f), //WallLamp1
      new Vec3(0f,0f,0f), //WallLamp2
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
