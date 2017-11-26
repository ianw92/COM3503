import gmaths.*;

import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.glsl.*;

public class Arty_GLEventListener implements GLEventListener {

  private static final boolean DISPLAY_SHADERS = false;
  private float aspect;

  public Arty_GLEventListener(Camera camera) {
    this.camera = camera;
  }

  // ***************************************************
  /*
   * METHODS DEFINED BY GLEventListener
   */

  /* Initialisation */
  public void init(GLAutoDrawable drawable) {
    GL3 gl = drawable.getGL().getGL3();
    System.err.println("Chosen GLCapabilities: " + drawable.getChosenGLCapabilities());
    gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    gl.glClearDepth(1.0f);
    gl.glEnable(GL.GL_DEPTH_TEST);
    gl.glDepthFunc(GL.GL_LESS);
    gl.glFrontFace(GL.GL_CCW);    // default is 'CCW'
    gl.glEnable(GL.GL_CULL_FACE); // default is 'not enabled'
    gl.glCullFace(GL.GL_BACK);   // default is 'back', assuming CCW
    initialise(gl);
    startTime = getSeconds();
  }

  /* Called to indicate the drawing surface has been moved and/or resized  */
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    GL3 gl = drawable.getGL().getGL3();
    gl.glViewport(x, y, width, height);
    aspect = (float)width/(float)height;
  }

  /* Draw */
  public void display(GLAutoDrawable drawable) {
    GL3 gl = drawable.getGL().getGL3();
    render(gl);
  }

  /* Clean up memory, if necessary */
  public void dispose(GLAutoDrawable drawable) {
    GL3 gl = drawable.getGL().getGL3();
    disposeMeshes(gl);
  }

  // ***************************************************
  /* TIME
   */

  private double startTime;

  public static double getSeconds() {
    return System.currentTimeMillis()/1000.0;
  }

  // ***************************************************
  /* INTERACTION
   *
   *
   */
  // Lighting Interaction
  public void mainLightOff() {
   light.setLightLevel(0,0,0, "main");
  }

  public void mainLightOn() {
   light.setLightLevel(0.15f,0.5f,0.7f, "main");
  }

  public void lampsOff() {
   light.setLightLevel(0,0,0, "point");
  }

  public void lampsOn() {
   light.setLightLevel(0.05f,0.4f,0.8f, "point");
  }


  // Hand Interaction
  private boolean animation = false;
  private double savedTime = 0;

  public void startAnimation() {
    if (!animation) {
      animation = true;
      if (savedTime == -1) {
        startTime = getSeconds()-0.00001f;
      }
      else {
        startTime = getSeconds()-savedTime;
      }
    }
  }

  public void stopAnimation() {
    animation = false;
    double elapsedTime = getSeconds()-startTime;
    savedTime = elapsedTime;
    armStructure.stopAnimation(false);
  }

  public void resetHand() {
    armStructure.resetHand();
    stopAnimation();
    armStructure.stopAnimation(true);
    savedTime = -1;
    System.out.println(light.getSpotLightPosition());
  }

  public void makeI() {
    if (!animation) {
      armStructure.makeI();
      armStructure.stopAnimation(true);
      savedTime = -1;
    }
    System.out.println(light.getSpotLightPosition());
  }

  public void makeA() {
    if (!animation) {
      armStructure.makeA();
      armStructure.stopAnimation(true);
      savedTime = -1;
    }
    System.out.println(light.getSpotLightPosition());
  }

  public void makeN() {
    if (!animation) {
      armStructure.makeN();
      armStructure.stopAnimation(true);
      savedTime = -1;
    }
    System.out.println(light.getSpotLightPosition());
  }

  public void makeVulcan() {
    if (!animation) {
      armStructure.makeVulcan();
      armStructure.stopAnimation(true);
      savedTime = -1;
    }
    System.out.println(light.getSpotLightPosition());
  }

  public void animate(double startTime) {
    armStructure.animate(startTime);
  }

  // ***************************************************
  /* THE SCENE
   * Now define all the methods to handle the scene.
   * This will be added to in later examples.
   */

  private Camera camera;
  private Mat4 perspective;
  private Mesh floor, wall1, wall2, wall3, ceiling, window, sphere, cube, cubeRing, sphereGemstone;
  private TwoTriangles wall4_bottom, wall4_top, wall4_left, wall4_right;
  private Light light;

  private ArmStructure armStructure;
  private Lamp lamp1, lamp2;
  private WallLamp wallLamp1, wallLamp2;
  private Room room;

  private void initialise(GL3 gl) {
    int[] textureId0 = TextureLibrary.loadTexture(gl, "textures/chequerboard.jpg");
    int[] textureId1 = TextureLibrary.loadTexture(gl, "textures/jade.jpg");
    int[] textureId2 = TextureLibrary.loadTexture(gl, "textures/jade_specular.jpg");
    int[] textureId3 = TextureLibrary.loadTexture(gl, "textures/container2.jpg");
    int[] textureId4 = TextureLibrary.loadTexture(gl, "textures/container2_specular.jpg");
    int[] textureId5 = TextureLibrary.loadTexture(gl, "textures/wattBook.jpg");
    int[] textureId6 = TextureLibrary.loadTexture(gl, "textures/wattBook_specular.jpg");
    int[] textureId7 = TextureLibrary.loadTexture(gl, "textures/wall1.jpg");
    int[] textureId8 = TextureLibrary.loadTexture(gl, "textures/wall2.jpg");
    int[] textureId9 = TextureLibrary.loadTexture(gl, "textures/wall3.jpg");
    int[] textureId10 = TextureLibrary.loadTexture(gl, "textures/wall4.jpg");
    int[] textureId11 = TextureLibrary.loadTexture(gl, "textures/ceiling.jpg");
    int[] textureId12 = TextureLibrary.loadTexture(gl, "textures/floor.jpg");
    int[] textureId13 = TextureLibrary.loadTexture(gl, "textures/ring.jpg");
    int[] textureId14 = TextureLibrary.loadTexture(gl, "textures/gemstone.jpg");
    int[] textureId15 = TextureLibrary.loadTexture(gl, "textures/hand2.jpg");
    int[] textureId16 = TextureLibrary.loadTexture(gl, "textures/wallAlphabet.jpg");
    int[] textureId17 = TextureLibrary.loadTexture(gl, "textures/outsideWithWindow.jpg");

    floor = new TwoTriangles(gl, textureId2);
    wall1 = new TwoTriangles(gl, textureId7);
    wall2 = new TwoTriangles(gl, textureId10);
    wall3 = new TwoTriangles(gl, textureId9);

    float[] textureCoordsBottom = {0,0.25f,   0,0,   1,0,   1,0.25f};
    wall4_bottom = new TwoTriangles(gl, textureId16, textureCoordsBottom);
    float[] textureCoordsTop = {0,1,   0,0.75f,   1,0.75f,   1,1};
    wall4_top = new TwoTriangles(gl, textureId16, textureCoordsTop);
    float[] textureCoordsLeft = {0,0.75f,   0,0.25f,    0.25f,0.25f,   0.25f,0.75f};
    wall4_left = new TwoTriangles(gl, textureId16, textureCoordsLeft);
    float[] textureCoordsRight = {0.75f, 0.75f,   0.75f, 0.25f,   1, 0.25f,   1, 0.75f};
    wall4_right = new TwoTriangles(gl, textureId16, textureCoordsRight);

    ceiling = new TwoTriangles(gl, textureId11);
    window = new TwoTriangles(gl, textureId17);
    sphere = new Sphere(gl, textureId15, textureId15);
    cube = new Cube(gl, textureId15, textureId4);
    cubeRing = new Cube(gl, textureId13, textureId13);
    sphereGemstone = new Sphere(gl, textureId14, textureId14);

    light = new Light(gl);
    light.setCamera(camera);

    floor.setLight(light);
    floor.setCamera(camera);
    wall1.setLight(light);
    wall1.setCamera(camera);
    wall2.setLight(light);
    wall2.setCamera(camera);
    wall3.setLight(light);
    wall3.setCamera(camera);

    wall4_bottom.setLight(light);
    wall4_bottom.setCamera(camera);
    wall4_top.setLight(light);
    wall4_top.setCamera(camera);
    wall4_left.setLight(light);
    wall4_left.setCamera(camera);
    wall4_right.setLight(light);
    wall4_right.setCamera(camera);

    ceiling.setLight(light);
    ceiling.setCamera(camera);
    window.setLight(light);
    window.setCamera(camera);
    sphere.setLight(light);
    sphere.setCamera(camera);
    cube.setLight(light);
    cube.setCamera(camera);
    cubeRing.setLight(light);
    cubeRing.setCamera(camera);
    sphereGemstone.setLight(light);
    sphereGemstone.setCamera(camera);

    armStructure = new ArmStructure(cube, cubeRing, sphere, sphereGemstone);
    armStructure.initialise(gl);

    lamp1 = new Lamp(cube, sphere);
    lamp1.setSize(3,9,3);
    lamp1.setPosition(-18,0,-18);
    lamp1.initialise(gl);
    light.setPointLightPosition(new Vec3(-18,10+(1f/3f*3f),-18), 1);

    lamp2 = new Lamp(cube, sphere);
    lamp2.setSize(5,11,5);
    lamp2.setPosition(15,0,15);
    lamp2.initialise(gl);
    light.setPointLightPosition(new Vec3(15,12f+(1f/3f*5f),15), 2);

    wallLamp1 = new WallLamp(cube, sphere);
    wallLamp1.setSize(3,9,3);
    wallLamp1.setPosition(-20,20,15);
    wallLamp1.initialise(gl);
    light.setPointLightPosition(new Vec3((-20f+3f+0.5f+0.25f),(20f+3f+0.75f),15), 3);

    wallLamp2 = new WallLamp(cube, sphere);
    wallLamp2.setSize(3,9,3);
    wallLamp2.setPosition(20,18,-3);
    wallLamp2.initialise(gl);
    light.setPointLightPosition(new Vec3((20f-3f-0.5f-0.25f),(18f+3f+0.75f),-3), 4);

    room = new Room(floor, wall1, wall2, wall3, wall4_bottom, wall4_top, wall4_left, wall4_right, ceiling);

  }

  private void render(GL3 gl) {
    gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
    updatePerspectiveMatrices();
    updateSpotLightPosition();
    updateSpotLightDirection();

    light.render(gl);
    room.render(gl);
    lamp1.render(gl);
    lamp2.render(gl);
    wallLamp1.render(gl);
    wallLamp2.render(gl);
    if (animation) animate(startTime);
    armStructure.render(gl);
  }

  private void updatePerspectiveMatrices() {
    // needs to be changed if user resizes the window
    perspective = Mat4Transform.perspective(45, aspect);
    light.setPerspective(perspective);
    floor.setPerspective(perspective);
    wall1.setPerspective(perspective);
    wall2.setPerspective(perspective);
    wall3.setPerspective(perspective);
    wall4_bottom.setPerspective(perspective);
    wall4_top.setPerspective(perspective);
    wall4_left.setPerspective(perspective);
    wall4_right.setPerspective(perspective);
    ceiling.setPerspective(perspective);
    window.setPerspective(perspective);
    sphere.setPerspective(perspective);
    cube.setPerspective(perspective);
    cubeRing.setPerspective(perspective);
    sphereGemstone.setPerspective(perspective);
  }

  private void disposeMeshes(GL3 gl) {
    light.dispose(gl);
    floor.dispose(gl);
    wall1.dispose(gl);
    wall2.dispose(gl);
    wall3.dispose(gl);
    wall4_bottom.dispose(gl);
    wall4_top.dispose(gl);
    wall4_left.dispose(gl);
    wall4_right.dispose(gl);
    ceiling.dispose(gl);
    window.dispose(gl);
    sphere.dispose(gl);
    cube.dispose(gl);
    cubeRing.dispose(gl);
    sphereGemstone.dispose(gl);
  }

  private void updateSpotLightPosition() {
    Vec3 pos = armStructure.getRingPosition();
    light.setSpotLightPosition(pos);
    // System.out.println("Pos = " + light.getSpotLightPosition());
  }

  private void updateSpotLightDirection() {
    Vec3 direction = armStructure.getRingDirection();
    light.setSpotLightDirection(direction);
    // System.out.println("Dir = " + light.getSpotLightDirection());
  }

}
