import gmaths.*;

import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.glsl.*;

public class Room {

  public Room(Mesh floor, Mesh wall1, Mesh wall2, Mesh wall3, Mesh wall4_bottom, Mesh wall4_top, Mesh wall4_left, Mesh wall4_right, Mesh ceiling, Vec3 roomDimensions) {
    this.floor = floor;
    this.wall1 = wall1;
    this.wall2 = wall2;
    this.wall3 = wall3;
    this.wall4_bottom = wall4_bottom;
    this.wall4_top = wall4_top;
    this.wall4_left = wall4_left;
    this.wall4_right = wall4_right;
    this.ceiling = ceiling;
    this.roomWidth = roomDimensions.x;
    this.roomHeight = roomDimensions.y;
    this.roomDepth = roomDimensions.z;
  }

  private Mesh floor, wall1, wall2, wall3, wall4_bottom, wall4_top, wall4_left, wall4_right, ceiling;

  private float roomWidth = 0f;
  private float roomHeight = 0f;
  private float roomDepth = 0f;

  public void render(GL3 gl) {
    floor.setModelMatrix(getMforFloor());       // possibly changing cube transform each frame
    floor.render(gl);
    wall1.setModelMatrix(getMforWall1());       // possibly changing cube transform each frame
    wall1.render(gl);
    wall2.setModelMatrix(getMforWall2());       // possibly changing cube transform each frame
    wall2.render(gl);
    wall3.setModelMatrix(getMforWall3());       // possibly changing cube transform each frame
    wall3.render(gl);
    wall4_bottom.setModelMatrix(getMforWall4_bottom());       // possibly changing cube transform each frame
    wall4_bottom.render(gl);
    wall4_top.setModelMatrix(getMforWall4_top());       // possibly changing cube transform each frame
    wall4_top.render(gl);
    wall4_left.setModelMatrix(getMforWall4_left());       // possibly changing cube transform each frame
    wall4_left.render(gl);
    wall4_right.setModelMatrix(getMforWall4_right());       // possibly changing cube transform each frame
    wall4_right.render(gl);
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
  private Mat4 getMforWall4_bottom() {
    float size = 40f;
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomDepth,1f,roomHeight*0.25f), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundZ(-90), model);
    model = Mat4.multiply(Mat4Transform.translate(-roomWidth*0.5f,roomHeight*0.125f,0), model);
    return model;
  }

  private Mat4 getMforWall4_top() {
    float size = 40f;
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomDepth,1f,roomHeight*0.25f), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundZ(-90), model);
    model = Mat4.multiply(Mat4Transform.translate(-roomWidth*0.5f,roomHeight*0.875f,0), model);
    return model;
  }

  private Mat4 getMforWall4_left() {
    float size = 40f;
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomDepth*0.25f,1f,roomHeight*0.5f), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundZ(-90), model);
    model = Mat4.multiply(Mat4Transform.translate(-roomWidth*0.5f,roomHeight*0.5f,roomDepth*0.375f), model);
    return model;
  }

  private Mat4 getMforWall4_right() {
    float size = 40f;
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomDepth*0.25f,1f,roomHeight*0.5f), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundZ(-90), model);
    model = Mat4.multiply(Mat4Transform.translate(-roomWidth*0.5f,roomHeight*0.5f,-roomDepth*0.375f), model);
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
  private Mat4 getMforWall2() {
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
