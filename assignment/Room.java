import gmaths.*;

import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.glsl.*;

public class Room {

  private Mesh floor, wall1, wall2, wall3, wall4_bottom, wall4_top, wall4_left, wall4_right, ceiling;

  // Room dimensions
  private float roomWidth, roomHeight, roomDepth;

  // Model matrices
  private Mat4 floorModel, wall1Model, wall2Model, wall3Model, wall4_bottomModel;
  private Mat4 wall4_topModel, wall4_leftModel, wall4_rightModel, ceilingModel;

  // window size as proportion of room width/height respectively
  private float windowWidthProportion = 0.5f;
  private float windowHeightProportion = 0.5f;
  private float windowWidth, windowHeight, w4bottomHeight, w4topHeight, w4leftWidth, w4rightWidth;

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
    calculateWall4Dimensions();
    calculateModelMatrices();
  }

  public void render(GL3 gl) {
    floor.setModelMatrix(floorModel);
    floor.render(gl);
    wall1.setModelMatrix(wall1Model);
    wall1.render(gl);
    wall2.setModelMatrix(wall2Model);
    wall2.render(gl);
    wall3.setModelMatrix(wall3Model);
    wall3.render(gl);
    wall4_bottom.setModelMatrix(wall4_bottomModel);
    wall4_bottom.render(gl);
    wall4_top.setModelMatrix(wall4_topModel);
    wall4_top.render(gl);
    wall4_left.setModelMatrix(wall4_leftModel);
    wall4_left.render(gl);
    wall4_right.setModelMatrix(wall4_rightModel);
    wall4_right.render(gl);
    ceiling.setModelMatrix(ceilingModel);
    ceiling.render(gl);
  }

  public void calculateWall4Dimensions() {
    windowWidth = roomWidth*windowWidthProportion;
    windowHeight = roomHeight*windowHeightProportion;
    w4bottomHeight = (roomHeight-windowHeight)*0.5f;
    w4topHeight = w4bottomHeight;
    w4leftWidth = (roomDepth-windowWidth)*0.5f;
    w4rightWidth = w4leftWidth;
  }

  private void calculateModelMatrices() {
    floorModel = getMforFloor();
    wall1Model = getMforWall1();
    wall2Model = getMforWall2();
    wall3Model = getMforWall3();
    wall4_bottomModel = getMforWall4_bottom();
    wall4_topModel = getMforWall4_top();
    wall4_leftModel = getMforWall4_left();
    wall4_rightModel = getMforWall4_right();
    ceilingModel = getMforCeiling();
  }

  private Mat4 getMforFloor() {
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomWidth,1f,roomDepth), model);
    return model;
  }

  // Back Wall
  private Mat4 getMforWall1() {
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomWidth,1f,roomHeight), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundX(90), model);
    model = Mat4.multiply(Mat4Transform.translate(0,roomHeight*0.5f,-roomDepth*0.5f), model);
    return model;
  }

  // Near Wall (from starting camera position)
  private Mat4 getMforWall2() {
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomWidth,1f,roomHeight), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundX(90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(180), model);
    model = Mat4.multiply(Mat4Transform.translate(0,roomHeight*0.5f,roomDepth*0.5f), model);
    return model;
  }

  // Right Wall (from starting camera position)
  private Mat4 getMforWall3() {
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomDepth,1f,roomHeight), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(-90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundZ(90), model);
    model = Mat4.multiply(Mat4Transform.translate(roomWidth*0.5f,roomHeight*0.5f,0), model);
    return model;
  }

  // Left Wall (from starting camera position)
  private Mat4 getMforWall4_bottom() {
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomDepth,1f,w4bottomHeight), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundZ(-90), model);
    model = Mat4.multiply(Mat4Transform.translate(-roomWidth*0.5f,roomHeight*0.125f,0), model);
    return model;
  }

  private Mat4 getMforWall4_top() {
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomDepth,1f,w4topHeight), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundZ(-90), model);
    model = Mat4.multiply(Mat4Transform.translate(-roomWidth*0.5f,roomHeight*0.875f,0), model);
    return model;
  }

  private Mat4 getMforWall4_left() {
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(w4leftWidth,1f,windowHeight), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundZ(-90), model);
    model = Mat4.multiply(Mat4Transform.translate(-roomWidth*0.5f,roomHeight*0.5f,roomDepth*0.375f), model);
    return model;
  }

  private Mat4 getMforWall4_right() {
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(w4rightWidth,1f,windowHeight), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundZ(-90), model);
    model = Mat4.multiply(Mat4Transform.translate(-roomWidth*0.5f,roomHeight*0.5f,-roomDepth*0.375f), model);
    return model;
  }

  private Mat4 getMforCeiling() {
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomWidth,1f,roomDepth), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundZ(180), model);
    model = Mat4.multiply(Mat4Transform.translate(0,roomHeight,0), model);
    return model;
  }

}
