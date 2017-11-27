import gmaths.*;

import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.glsl.*;

public class OutsideScene {

  private Mesh sceneBillboard, grassBillboard, skyBillboard;

  //Room dimensions
  private float roomWidth, roomHeight, roomDepth;

  // Model matrices
  private Mat4 sceneModel, grassModel, skyModel;

  public OutsideScene(Mesh sceneBillboard, Mesh grassBillboard, Mesh skyBillboard, Vec3 roomDimensions) {
    this.sceneBillboard = sceneBillboard;
    this.grassBillboard = grassBillboard;
    this.skyBillboard = skyBillboard;
    this.roomWidth = roomDimensions.x;
    this.roomHeight = roomDimensions.y;
    this.roomDepth = roomDimensions.z;
    calculateModelMatrices();
  }

  public void render(GL3 gl) {
    sceneBillboard.setModelMatrix(sceneModel);
    sceneBillboard.render(gl);
    grassBillboard.setModelMatrix(grassModel);
    grassBillboard.render(gl);
    skyBillboard.setModelMatrix(skyModel);
    skyBillboard.render(gl);
  }

  private void calculateModelMatrices() {
    sceneModel = getMforSceneBillboard();
    grassModel = getMforGrassBillboard();
    skyModel = getMforSkyBillboard();
  }

  private Mat4 getMforSceneBillboard() {
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomDepth*4,1f,roomHeight), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundZ(-90), model);
    model = Mat4.multiply(Mat4Transform.translate(-roomWidth,roomHeight*0.5f,0), model);
    return model;
  }

  private Mat4 getMforGrassBillboard() {
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomDepth*4,1f,roomWidth), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(90), model);
    model = Mat4.multiply(Mat4Transform.translate(-roomWidth,0,0), model);
    return model;
  }

  private Mat4 getMforSkyBillboard() {
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomDepth*4,1f,roomWidth), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundX(180), model);
    model = Mat4.multiply(Mat4Transform.translate(-roomWidth,roomHeight,0), model);
    return model;
  }

}
