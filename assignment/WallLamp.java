/* I declare that this code is my own work */
/* Author: Ian Williams, Email: iwilliams3@sheffield.ac.uk */

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

  private float xPos, yPos, zPos;

  // Measurements
  private float baseWidth = 1.5f;
  private float baseHeight = 0.5f;
  private float baseDepth = baseWidth;
  private float columnWidth = 0.5f;
  private float columnHeight = 3f;
  private float columnDepth = columnWidth;
  private float connectorDiameter = columnWidth;

  // ***************************************************
  /* INTERACTION
   *
   *
   */

  public void setPosition(float x, float y, float z) {
   this.xPos = x;
   this.yPos = y;
   this.zPos = z;
  }

  public Vec3 getLightPosition() {
    float x;
    float distanceFromWall = baseHeight+columnHeight+connectorDiameter*0.5f;
    if (xPos < 0) {
      x = xPos+distanceFromWall;
    }
    else {
      x = xPos-distanceFromWall;
    }
    float y = yPos+3f+0.25f;
    return new Vec3(x, y, zPos);
  }

  // ***************************************************
  /* THE SCENE
  * Now define all the methods to handle the scene.
  */
  private Mesh cube, sphere;
  private SGNode wallLamp;

  public void initialise(GL3 gl) {

    wallLamp = new NameNode("wallLamp root");

    // Set transform node as if xPos < 0, change it if xPos > 0
    TransformNode wallLampTranslate = new TransformNode("wallLamp translate", Mat4.multiply(
                      Mat4Transform.translate(xPos, yPos, zPos), Mat4Transform.rotateAroundZ(-90)));
    if (xPos > 0) {
      wallLampTranslate = new TransformNode("wallLamp translate", Mat4.multiply(
                        Mat4Transform.translate(xPos, yPos, zPos), Mat4Transform.rotateAroundZ(90)));
    }

      NameNode wallLampBase = new NameNode("wallLamp base");
      Mat4 m = Mat4Transform.scale(baseWidth,baseHeight,baseDepth);
      m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0));
      TransformNode wallLampBaseTransform = new TransformNode("wallLamp base transform", m);
      MeshNode wallLampBaseShape = new MeshNode("Cube(wallLamp base)", cube);

        NameNode wallLampColumn = new NameNode("wallLamp column");
        TransformNode wallLampColumnTranslate = new TransformNode("wallLampColumn translate",
                                                         Mat4Transform.translate(0,baseHeight,0));
        m = new Mat4(1);
        m = Mat4Transform.scale(columnWidth,columnHeight,columnDepth);
        m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0));
        TransformNode wallLampColumnScale = new TransformNode("wallLamp column transform", m);
        MeshNode wallLampColumnShape = new MeshNode("Cube(wallLamp column)", cube);

          NameNode wallLampConnector = new NameNode("wallLamp connector");
          TransformNode wallLampConnectorTranslate = new TransformNode("wallLampConnector translate",
                                                            Mat4Transform.translate(0,columnHeight+connectorDiameter*0.5f,0));
          // Set transform node as if xPos < 0, change it if xPos > 0
          TransformNode wallLampConnectorTranslateLocal = new TransformNode("wallLampConnector translate local",
                                                            Mat4Transform.rotateAroundZ(90));
          if (xPos > 0) {
            wallLampConnectorTranslateLocal = new TransformNode("wallLampConnector translate local",
                                                              Mat4Transform.rotateAroundZ(-90));
          }
          m = new Mat4(1);
          m = Mat4Transform.scale(connectorDiameter,connectorDiameter,connectorDiameter);
          TransformNode wallLampConnectorScale = new TransformNode("wallLamp connector transform", m);
          MeshNode wallLampConnectorShape = new MeshNode("Sphere(wallLamp connector)", sphere);

            NameNode wallLampColumn2 = new NameNode("wallLamp column2");
            TransformNode wallLampColumn2Translate = new TransformNode("wallLampColumn2 translate",
                                                            Mat4Transform.translate(0,connectorDiameter*0.5f,0));
            m = new Mat4(1);
            m = Mat4Transform.scale(columnWidth,columnHeight,columnDepth);
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
