import gmaths.*;

import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.glsl.*;

public class WindowFrame {

  public WindowFrame(Mesh cube, Vec3 roomDimensions) {
    this.cube = cube;
    this.roomWidth = roomDimensions.x;
    this.roomHeight = roomDimensions.y;
    this.roomDepth = roomDimensions.z;
    frameWidth = 0.5f*roomDepth;
    frameHeight = 0.5f*roomHeight;
    frameDepth = 0.5f;
    outerHBarHeight = 0.05f*roomHeight;
    outerVBarWidth = outerHBarHeight;
    innerHBarHeight = 0.5f*outerHBarHeight;
    innerVBarWidth = innerHBarHeight;
    innerHBarWidth = frameWidth - 2*outerVBarWidth;
    vBarHeight = frameHeight - 2*outerHBarHeight;
  }

  private float roomWidth = 0f;
  private float roomHeight = 0f;
  private float roomDepth = 0f;
  private float width = 1;
  private float height = 1;
  private float depth = 1;

  private float frameWidth;
  private float frameHeight;
  private float frameDepth;
  private float outerHBarHeight;
  private float outerVBarWidth;
  private float innerHBarHeight;
  private float innerVBarWidth;
  private float innerHBarWidth;
  private float vBarHeight;

  // ***************************************************
  /* THE SCENE
  * Now define all the methods to handle the scene.
  */
  private Mesh cube;
  private SGNode frame;

  public void initialise(GL3 gl) {

    frame = new NameNode("frame root");

    TransformNode frameTranslate = new TransformNode("frame translate",
                                            Mat4.multiply(Mat4Transform.translate(-0.5f*roomWidth, 0.25f*roomHeight, 0),Mat4Transform.rotateAroundY(90)));

    NameNode frameH1 = new NameNode("frame H1");
    Mat4 m = Mat4Transform.scale(frameWidth,outerHBarHeight,frameDepth);
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0));
    TransformNode frameH1Transform = new TransformNode("frame H1 transform", m);
    MeshNode frameH1Shape = new MeshNode("Cube(frame H1)", cube);

      NameNode frameV1 = new NameNode("frame V1");
      TransformNode frameV1Translate = new TransformNode("frame V1 translate",
                                                       Mat4Transform.translate(-0.5f*frameWidth+0.5f*outerVBarWidth,outerHBarHeight,0));
      m = new Mat4(1);
      m = Mat4Transform.scale(outerVBarWidth,vBarHeight,frameDepth);
      m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0));
      TransformNode frameV1Scale = new TransformNode("frame V1 transform", m);
      MeshNode frameV1Shape = new MeshNode("Cube(frame V1)", cube);

        NameNode frameH2 = new NameNode("frame H2");
        TransformNode frameH2Translate = new TransformNode("frame H2 translate",
                                                         Mat4Transform.translate(0.5f*frameWidth-0.5f*outerVBarWidth,0.5f*vBarHeight-0.5f*innerHBarHeight,0));
        m = new Mat4(1);
        m = Mat4Transform.scale(innerHBarWidth,innerHBarHeight,frameDepth);
        m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0));
        TransformNode frameH2Scale = new TransformNode("frame H2 transform", m);
        MeshNode frameH2Shape = new MeshNode("Cube(frame H2)", cube);

        NameNode frameH3 = new NameNode("frame H3");
        TransformNode frameH3Translate = new TransformNode("frame H3 translate",
                                                         Mat4Transform.translate(0.5f*frameWidth-0.5f*outerVBarWidth,vBarHeight,0));
        m = new Mat4(1);
        m = Mat4Transform.scale(frameWidth,outerHBarHeight,frameDepth);
        m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0));
        TransformNode frameH3Scale = new TransformNode("frame H3 transform", m);
        MeshNode frameH3Shape = new MeshNode("Cube(frame H3)", cube);

      NameNode frameV2 = new NameNode("frame V2");
      TransformNode frameV2Translate = new TransformNode("frame V2 translate",
                                                       Mat4Transform.translate(0,outerHBarHeight,0));
      m = new Mat4(1);
      m = Mat4Transform.scale(innerVBarWidth,vBarHeight,frameDepth);
      m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0));
      TransformNode frameV2Scale = new TransformNode("frame V2 transform", m);
      MeshNode frameV2Shape = new MeshNode("Cube(frame V2)", cube);

      NameNode frameV3 = new NameNode("frame V3");
      TransformNode frameV3Translate = new TransformNode("frame V3 translate",
                                                       Mat4Transform.translate(0.5f*frameWidth-0.5f*outerVBarWidth,outerHBarHeight,0));
      m = new Mat4(1);
      m = Mat4Transform.scale(outerVBarWidth,vBarHeight,frameDepth);
      m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0));
      TransformNode frameV3Scale = new TransformNode("frame V3 transform", m);
      MeshNode frameV3Shape = new MeshNode("Cube(frame V3)", cube);



    frame.addChild(frameTranslate);
      frameTranslate.addChild(frameH1);
        frameH1.addChild(frameH1Transform);
          frameH1Transform.addChild(frameH1Shape);
        frameH1.addChild(frameV1);
          frameV1.addChild(frameV1Translate);
            frameV1Translate.addChild(frameV1Scale);
              frameV1Scale.addChild(frameV1Shape);
            frameV1Translate.addChild(frameH2);
              frameH2.addChild(frameH2Translate);
                frameH2Translate.addChild(frameH2Scale);
                  frameH2Scale.addChild(frameH2Shape);
            frameV1Translate.addChild(frameH3);
              frameH3.addChild(frameH3Translate);
                frameH3Translate.addChild(frameH3Scale);
                  frameH3Scale.addChild(frameH3Shape);
        frameH1.addChild(frameV2);
          frameV2.addChild(frameV2Translate);
            frameV2Translate.addChild(frameV2Scale);
              frameV2Scale.addChild(frameV2Shape);
        frameH1.addChild(frameV3);
          frameV3.addChild(frameV3Translate);
            frameV3Translate.addChild(frameV3Scale);
              frameV3Scale.addChild(frameV3Shape);

    frame.update();
    // frame.print(0, false);

  }

  public void render(GL3 gl) {
    frame.draw(gl);
  }
}
