import gmaths.*;

import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.glsl.*;

public class Room {

  public Room(Mesh floor, Mesh wall1, Mesh wall2, Mesh wall3, Mesh wall4, Mesh ceiling, Mesh window) {
    this.floor = floor;
    this.wall1 = wall1;
    this.wall2 = wall2;
    this.wall3 = wall3;
    this.wall4 = wall4;
    this.ceiling = ceiling;
    this.window = window;
  }

  private Mesh floor, wall1, wall2, wall3, wall4, ceiling, window;

  private float roomHeight = 30f;
  private float roomWidth = 40f;
  private float roomDepth = 40f;

  public void render(GL3 gl) {
    floor.setModelMatrix(getMforFloor());       // possibly changing cube transform each frame
    floor.render(gl);
    wall1.setModelMatrix(getMforWall1());       // possibly changing cube transform each frame
    wall1.render(gl);
    window.setModelMatrix(getMforWindow());
    window.render(gl);
    wall2.setModelMatrix(getMforWall2());       // possibly changing cube transform each frame
    wall2.render(gl);
    wall3.setModelMatrix(getMforWall3());       // possibly changing cube transform each frame
    wall3.render(gl);
    wall4.setModelMatrix(getMforWall4());       // possibly changing cube transform each frame
    wall4.render(gl);
    ceiling.setModelMatrix(getMforCeiling());       // possibly changing cube transform each frame
    ceiling.render(gl);

  }

  // As the transforms do not change over time for this object, they could be stored once rather than continually being calculated
  private Mat4 getMforFloor() {
    float size = 40f;
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomWidth,1f,roomDepth), model);
    return model;
  }

  // As the transforms do not change over time for this object, they could be stored once rather than continually being calculated
  // Back Wall
  private Mat4 getMforWall1() {
    float size = 40f;
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomWidth,1f,roomHeight), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundX(90), model);
    model = Mat4.multiply(Mat4Transform.translate(0,roomHeight*0.5f,-roomDepth*0.5f), model);
    return model;
  }

  // Left Wall (from starting camera position)
  private Mat4 getMforWall2() {
    float size = 40f;
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomDepth,1f,roomHeight), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundZ(-90), model);
    model = Mat4.multiply(Mat4Transform.translate(-roomWidth*0.5f,roomHeight*0.5f,0), model);
    return model;
  }

  private Mat4 getMforWindow() {
    float size = 40f;//25f
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomDepth*1f/2f,1f,roomHeight*1f/2f), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundZ(-90), model);
    model = Mat4.multiply(Mat4Transform.translate(-roomWidth*0.5f,roomHeight*0.5f,0), model);
    return model;
  }

  // Right Wall (from starting camera position)
  private Mat4 getMforWall3() {
    float size = 40f;
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomDepth,1f,roomHeight), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(-90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundZ(90), model);
    model = Mat4.multiply(Mat4Transform.translate(roomWidth*0.5f,roomHeight*0.5f,0), model);
    return model;
  }

  // Near Wall (from starting camera position)
  private Mat4 getMforWall4() {
    float size = 40f;
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomWidth,1f,roomHeight), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundX(90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(180), model);
    model = Mat4.multiply(Mat4Transform.translate(0,roomHeight*0.5f,roomDepth*0.5f), model);
    return model;
  }

  private Mat4 getMforCeiling() {
    float size = 40f;
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomWidth,1f,roomDepth), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundZ(180), model);
    model = Mat4.multiply(Mat4Transform.translate(0,roomHeight,0), model);
    return model;
  }

}
