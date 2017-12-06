/* I declare that this code is my own work */
/* I based it on 'Light.java' provided by Dr Steve Maddock in eSheet6/Week6_3_scene_graph/
   and adapted it for my own use.
   Line 191 to te end of the file are unchanged.
   Most of the rest of the file has been changed to some extent. */
/* Author: Ian Williams, Email: iwilliams3@sheffield.ac.uk */

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
  private float ambientLightLevelMain = 0.25f;
  private float diffuseLightLevelMain = 0.5f;
  private float specularLightLevelMain = 0.7f;

  private float ambientLightLevelPoint = 0.1f;
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
    shader = new Shader(gl, "vs_light.txt", "fs_light.txt");
    fillBuffers(gl);
  }

  public float getAmbientLightLevel() {
    return this.ambientLightLevelMain;
  }

  public void setLightLevel(float ambientLightLevel, float diffuseLightLevel, float specularLightLevel, String lightType) {
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

  // sets the color of the light 'bulb' to either grey (off) or white (on)
  public void setLightColor(GL3 gl, int i) {
    float mainAmbient = material.getAmbient("main").x;
    float lampAmbient = material.getAmbient("point").x;
    if (i==0 || i==1) {
      if (mainAmbient==0) {
        if (lampAmbient==0) {
          shader.setFloat(gl, "lightColor", 0f);
        }
        else {
          shader.setFloat(gl, "lightColor", 0.2f);
        }
      }
      else {
        shader.setFloat(gl, "lightColor", 1.0f);
      }
    }
    else{
      if (lampAmbient==0) {
        if (mainAmbient==0) {
          shader.setFloat(gl, "lightColor", 0f);
        }
        else {
          shader.setFloat(gl, "lightColor", 0.2f);
        }
      }
      else {
        shader.setFloat(gl, "lightColor", 1.0f);
      }
    }
  }

  // draw the light 'bulb'
  public void drawLight(GL3 gl, int i) {
    Mat4 model;
    Mat4 mvpMatrix;
    model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(1f,1f,1f), model);
    if (i==2 || i==3) {
      model = Mat4.multiply(Mat4Transform.scale(2f,2f,2f), model);
    }
    model = Mat4.multiply(Mat4Transform.translate(pointLightPositions[i]), model);
    model = Mat4.multiply(Mat4Transform.translate(0,0.5f,0), model);

    mvpMatrix = Mat4.multiply(perspective, Mat4.multiply(camera.getViewMatrix(), model));

    shader.use(gl);
    shader.setFloatArray(gl, "mvpMatrix", mvpMatrix.toFloatArrayForGLSL());

    gl.glDrawElements(GL.GL_TRIANGLES, indices.length, GL.GL_UNSIGNED_INT, 0);
  }

  public void render(GL3 gl) {
    gl.glBindVertexArray(vertexArrayId[0]);

    shader.use(gl);
    for (int i = 0; i < pointLightPositions.length; i++) {
      setLightColor(gl, i);
      drawLight(gl, i);
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

    private Vec3[] pointLightPositions = new Vec3[] {
      new Vec3(0f,30f,0f), //Main light on ceiling
      new Vec3(-40f,40f,0f), //Main light to illuminate outside scene
      new Vec3(0f,0f,0f), //Lamp1
      new Vec3(0f,0f,0f), //Lamp2
      new Vec3(0f,0f,0f), //WallLamp1
      new Vec3(0f,0f,0f), //WallLamp2
    };

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
