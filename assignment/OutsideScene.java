import gmaths.*;

import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.glsl.*;

public class OutsideScene {

  public OutsideScene(Mesh sceneBillboard, Mesh grassBillboard, Mesh skyBillboard, Vec3 roomDimensions) {
    this.sceneBillboard = sceneBillboard;
    this.grassBillboard = grassBillboard;
    this.skyBillboard = skyBillboard;
    this.roomWidth = roomDimensions.x;
    this.roomHeight = roomDimensions.y;
    this.roomDepth = roomDimensions.z;
  }

  private Mesh sceneBillboard, grassBillboard, skyBillboard;

  private float roomWidth = 0f;
  private float roomHeight = 0f;
  private float roomDepth = 0f;

  public void render(GL3 gl) {
    sceneBillboard.setModelMatrix(getMforSceneBillboard());       // possibly changing cube transform each frame
    sceneBillboard.render(gl);
    grassBillboard.setModelMatrix(getMforGrassBillboard());       // possibly changing cube transform each frame
    grassBillboard.render(gl);
    skyBillboard.setModelMatrix(getMforSkyBillboard());
    skyBillboard.render(gl);
  }

  // Left Wall (from starting camera position)
  private Mat4 getMforSceneBillboard() {
    Mat4 model = new Mat4(1);
    model = Mat4.multiply(Mat4Transform.scale(roomDepth*4,1f,roomHeight), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundY(90), model);
    model = Mat4.multiply(Mat4Transform.rotateAroundZ(-90), model);
    model = Mat4.multiply(Mat4Transform.translate(-roomWidth,roomHeight*0.5f,0), model);
    // model = Mat4.multiply(Mat4Transform.translate(0,0,0), model);
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
