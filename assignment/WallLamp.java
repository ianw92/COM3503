import gmaths.*;

import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.glsl.*;

public class WallLamp {

  public WallLamp(Mesh cube, Mesh sphere) {
    this.cube = cube;
    this.sphere = sphere;
  }

  private float xPos = 0;
  private float yPos = 0;
  private float zPos = 0;
  private float width = 1;
  private float height = 1;
  private float depth = 1;
  private float baseHeight = 0.5f;

  // ***************************************************
  /* INTERACTION
   *
   *
   */

  public void setSize(float width, float height, float depth) {
   this.width = width;
   this.height = height;
   this.depth = depth;
  }

  public void setPosition(float x, float y, float z) {
   this.xPos = x;
   this.yPos = y;
   this.zPos = z;
  }

  // ***************************************************
  /* THE SCENE
  * Now define all the methods to handle the scene.
  */
  private Mesh cube, sphere;
  private SGNode wallLamp;

  public void initialise(GL3 gl) {

    wallLamp = new NameNode("wallLamp root");

    TransformNode wallLampTranslate = new TransformNode("wallLamp translate", Mat4.multiply(
                      Mat4Transform.translate(xPos, yPos, zPos), Mat4Transform.rotateAroundZ(-90)));
    if (xPos > 0) {
      wallLampTranslate = new TransformNode("wallLamp translate", Mat4.multiply(
                        Mat4Transform.translate(xPos, yPos, zPos), Mat4Transform.rotateAroundZ(90)));
    }

    NameNode wallLampBase = new NameNode("wallLamp base");
    Mat4 m = Mat4Transform.scale(1.5f,baseHeight,1.5f);
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0));
    TransformNode wallLampBaseTransform = new TransformNode("wallLamp base transform", m);
    MeshNode wallLampBaseShape = new MeshNode("Cube(wallLamp base)", cube);

    NameNode wallLampColumn = new NameNode("wallLamp column");
    TransformNode wallLampColumnTranslate = new TransformNode("wallLampColumn translate",
                                                     Mat4Transform.translate(0,baseHeight,0));
    m = new Mat4(1);
    m = Mat4Transform.scale(0.5f,3f,0.5f);
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0));
    TransformNode wallLampColumnScale = new TransformNode("wallLamp column transform", m);
    MeshNode wallLampColumnShape = new MeshNode("Cube(wallLamp column)", cube);

    NameNode wallLampConnector = new NameNode("wallLamp connector");
    TransformNode wallLampConnectorTranslate = new TransformNode("wallLampConnector translate",
                                                      Mat4Transform.translate(0,3.25f,0));
    TransformNode wallLampConnectorTranslateLocal = new TransformNode("wallLampConnector translate local",
                                                      Mat4Transform.rotateAroundZ(90));
    if (xPos > 0) {
      wallLampConnectorTranslateLocal = new TransformNode("wallLampConnector translate local",
                                                        Mat4Transform.rotateAroundZ(-90));
    }
    m = new Mat4(1);
    m = Mat4Transform.scale(0.5f,0.5f,0.5f);
    m = Mat4.multiply(m, Mat4Transform.translate(0,0,0));
    TransformNode wallLampConnectorScale = new TransformNode("wallLamp connector transform", m);
    MeshNode wallLampConnectorShape = new MeshNode("Sphere(wallLamp connector)", sphere);

    NameNode wallLampColumn2 = new NameNode("wallLamp column2");
    TransformNode wallLampColumn2Translate = new TransformNode("wallLampColumn2 translate",
                                                    Mat4Transform.translate(0,0.25f,0));
    m = new Mat4(1);
    m = Mat4Transform.scale(0.5f,3f,0.5f);
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0));
    TransformNode wallLampColumn2Scale = new TransformNode("wallLamp column2 transform", m);
    MeshNode wallLampColumn2Shape = new MeshNode("Cube(wallLamp column2)", cube);

    wallLamp.addChild(wallLampTranslate);
      wallLampTranslate.addChild(wallLampBase);
        wallLampBase.addChild(wallLampBaseTransform);
          wallLampBaseTransform.addChild(wallLampBaseShape);
        wallLampBase.addChild(wallLampColumn);
          wallLampColumn.addChild(wallLampColumnTranslate);
            wallLampColumnTranslate.addChild(wallLampColumnScale);
              wallLampColumnScale.addChild(wallLampColumnShape);
            wallLampColumnTranslate.addChild(wallLampConnector);
              wallLampConnector.addChild(wallLampConnectorTranslate);
                wallLampConnectorTranslate.addChild(wallLampConnectorTranslateLocal);
                  wallLampConnectorTranslateLocal.addChild(wallLampConnectorScale);
                    wallLampConnectorScale.addChild(wallLampConnectorShape);
                  wallLampConnectorTranslateLocal.addChild(wallLampColumn2);
                    wallLampColumn2.addChild(wallLampColumn2Translate);
                        wallLampColumn2Translate.addChild(wallLampColumn2Scale);
                          wallLampColumn2Scale.addChild(wallLampColumn2Shape);

    wallLamp.update();
    // wallLamp.print(0, false);

  }

  public void render(GL3 gl) {
    wallLamp.draw(gl);
  }
}
