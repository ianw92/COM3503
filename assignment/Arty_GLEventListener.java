import gmaths.*;

import java.nio.*;
import java.util.*;
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
   light.setLightLevel(0.25f,0.5f,0.7f, "main");
  }

  public void lampsOff() {
   light.setLightLevel(0,0,0, "point");
  }

  public void lampsOn() {
   light.setLightLevel(0.1f,0.4f,0.8f, "point");
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
  private Mesh floor, wall1, wall2, wall3, ceiling, sphere, cube, cubeRing, sphereGemstone, cubeWindow, cubeLampFittings, sphereLampFittings;
  private TwoTriangles wall4_bottom, wall4_top, wall4_left, wall4_right, outsideSceneBillboard, grassBillboard, skyBillboard;
  private List<Mesh> meshList = new ArrayList<Mesh>();
  private List<TwoTriangles> twoTrianglesList = new ArrayList<TwoTriangles>();
  private Light light;

  private ArmStructure armStructure;
  private Lamp lamp1, lamp2;
  private WallLamp wallLamp1, wallLamp2;
  private Room room;
  private WindowFrame windowFrame;
  private OutsideScene outsideScene;

  private Vec3 roomDimensions = new Vec3(40f,30f,40f);

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
    int[] textureId18 = TextureLibrary.loadTexture(gl, "textures/outdoorScene1.jpg");
    int[] textureId19 = TextureLibrary.loadTexture(gl, "textures/grass.jpg");
    int[] textureId20 = TextureLibrary.loadTexture(gl, "textures/sky.jpg");
    int[] textureId21 = TextureLibrary.loadTexture(gl, "textures/wood.jpg");
    int[] textureId22 = TextureLibrary.loadTexture(gl, "textures/lampMetal.jpg");

    // Room components
    floor = new TwoTriangles(gl, textureId2);
    wall1 = new TwoTriangles(gl, textureId7);
    wall2 = new TwoTriangles(gl, textureId10);
    wall3 = new TwoTriangles(gl, textureId9);
    float[] textureCoordsBottom = {0,0.25f,   0,0,   1,0,   1,0.25f};
    float[] textureCoordsTop = {0,1,   0,0.75f,   1,0.75f,   1,1};
    float[] textureCoordsLeft = {0,0.75f,   0,0.25f,    0.25f,0.25f,   0.25f,0.75f};
    float[] textureCoordsRight = {0.75f, 0.75f,   0.75f, 0.25f,   1, 0.25f,   1, 0.75f};
    wall4_bottom = new TwoTriangles(gl, textureId16, textureCoordsBottom);
    wall4_top = new TwoTriangles(gl, textureId16, textureCoordsTop);
    wall4_left = new TwoTriangles(gl, textureId16, textureCoordsLeft);
    wall4_right = new TwoTriangles(gl, textureId16, textureCoordsRight);
    ceiling = new TwoTriangles(gl, textureId11);

    // OutsideScene components
    outsideSceneBillboard = new TwoTriangles(gl, textureId18);
    grassBillboard = new TwoTriangles(gl, textureId19);
    skyBillboard = new TwoTriangles(gl, textureId20);

    // ArmStructure components
    sphere = new Sphere(gl, textureId15, textureId15);
    cube = new Cube(gl, textureId15, textureId4);
    cubeRing = new Cube(gl, textureId13, textureId13);
    sphereGemstone = new Sphere(gl, textureId14, textureId14);

    // WindowFrame component
    cubeWindow = new Cube(gl, textureId21, textureId21);

    // Lamp and WallLamp components
    cubeLampFittings = new Cube(gl, textureId22, textureId22);
    sphereLampFittings = new Sphere(gl, textureId22, textureId22);

    // Add all meshes to list
    meshList.add(floor);
    meshList.add(wall1);
    meshList.add(wall2);
    meshList.add(wall3);
    meshList.add(ceiling);
    meshList.add(sphere);
    meshList.add(cube);
    meshList.add(cubeRing);
    meshList.add(sphereGemstone);
    meshList.add(cubeWindow);
    meshList.add(cubeLampFittings);
    meshList.add(sphereLampFittings);
    meshList.add(wall4_bottom);
    meshList.add(wall4_top);
    meshList.add(wall4_left);
    meshList.add(wall4_right);
    meshList.add(outsideSceneBillboard);
    meshList.add(grassBillboard);
    meshList.add(skyBillboard);

    light = new Light(gl);
    light.setCamera(camera);

    for (Mesh mesh : meshList) {
      mesh.setLight(light);
      mesh.setCamera(camera);
    }

    armStructure = new ArmStructure(cube, cubeRing, sphere, sphereGemstone);
    armStructure.initialise(gl);

    windowFrame = new WindowFrame(cubeWindow, roomDimensions);
    windowFrame.initialise(gl);

    lamp1 = new Lamp(cubeLampFittings, sphereLampFittings);
    lamp1.setSize(3,9,3);
    lamp1.setPosition(-18,0,-18);
    lamp1.initialise(gl);
    light.setPointLightPosition(new Vec3(-18,10,-18), 2);

    lamp2 = new Lamp(cubeLampFittings, sphereLampFittings);
    lamp2.setSize(5,11,5);
    lamp2.setPosition(15,0,15);
    lamp2.initialise(gl);
    light.setPointLightPosition(new Vec3(15,12,15), 3);

    wallLamp1 = new WallLamp(cubeLampFittings, sphereLampFittings);
    wallLamp1.setSize(3,9,3);
    wallLamp1.setPosition(-20,20,15);
    wallLamp1.initialise(gl);
    light.setPointLightPosition(new Vec3((-20f+3f+0.5f+0.25f),(20f+3f+0.25f),15), 4);

    wallLamp2 = new WallLamp(cubeLampFittings, sphereLampFittings);
    wallLamp2.setSize(3,9,3);
    wallLamp2.setPosition(20,18,-3);
    wallLamp2.initialise(gl);
    light.setPointLightPosition(new Vec3((20f-3f-0.5f-0.25f),(18f+3f+0.25f),-3), 5);

    room = new Room(floor, wall1, wall2, wall3, wall4_bottom, wall4_top, wall4_left, wall4_right, ceiling, roomDimensions);
    outsideScene = new OutsideScene(outsideSceneBillboard, grassBillboard, skyBillboard, roomDimensions);

  }

  private void render(GL3 gl) {
    gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
    updatePerspectiveMatrices();
    updateSpotLightPosition();
    updateSpotLightDirection();

    if (animation) animate(startTime);

    light.render(gl);
    room.render(gl);
    lamp1.render(gl);
    lamp2.render(gl);
    wallLamp1.render(gl);
    wallLamp2.render(gl);
    armStructure.render(gl);
    windowFrame.render(gl);
    outsideScene.render(gl);
  }

  private void updatePerspectiveMatrices() {
    // needs to be changed if user resizes the window
    perspective = Mat4Transform.perspective(45, aspect);
    light.setPerspective(perspective);

    for (Mesh mesh : meshList) {
      mesh.setPerspective(perspective);
    }
  }

  private void disposeMeshes(GL3 gl) {
    light.dispose(gl);
    for (Mesh mesh : meshList) {
      mesh.dispose(gl);
    }
  }

  private void updateSpotLightPosition() {
    Vec3 pos = armStructure.getRingPosition();
    light.setSpotLightPosition(pos);
  }

  private void updateSpotLightDirection() {
    Vec3 direction = armStructure.getRingDirection();
    light.setSpotLightDirection(direction);
  }

}
