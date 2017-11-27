import gmaths.*;

import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.glsl.*;

public class Lamp {

  public Lamp(Mesh cube, Mesh sphere) {
    this.cube = cube;
    this.sphere = sphere;
  }

  private float xPos, yPos, zPos, width, height, depth;
  private float baseHeight = 1f;

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

  public Vec3 getLightPosition() {
    float y = height+baseHeight;
    return new Vec3(xPos, y, zPos);
  }

  // ***************************************************
  /* THE SCENE
  * Now define all the methods to handle the scene.
  */
  private Mesh cube, sphere;
  private SGNode lamp;

  public void initialise(GL3 gl) {

    lamp = new NameNode("lamp root");

    TransformNode lampTranslate = new TransformNode("lamp translate",
                                            Mat4Transform.translate(xPos, yPos, zPos));

    NameNode lampBase = new NameNode("lamp base");
    Mat4 m = Mat4Transform.scale(width,baseHeight,depth);
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0));
    TransformNode lampBaseTransform = new TransformNode("lamp base transform", m);
    MeshNode lampBaseShape = new MeshNode("Cube(lamp base)", cube);

    NameNode lampColumn = new NameNode("lamp column");
    TransformNode lampColumnTranslate = new TransformNode("lampColumn translate",
                                                     Mat4Transform.translate(0,baseHeight,0));
    m = new Mat4(1);
    m = Mat4Transform.scale(width/5,height,depth/5);
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0));
    TransformNode lampColumnScale = new TransformNode("lamp column transform", m);
    MeshNode lampColumnShape = new MeshNode("Cube(lamp column)", cube);

    lamp.addChild(lampTranslate);
      lampTranslate.addChild(lampBase);
        lampBase.addChild(lampBaseTransform);
          lampBaseTransform.addChild(lampBaseShape);
        lampBase.addChild(lampColumn);
          lampColumn.addChild(lampColumnTranslate);
            lampColumnTranslate.addChild(lampColumnScale);
              lampColumnScale.addChild(lampColumnShape);

    lamp.update();
    // lamp.print(0, false);

  }

  public void render(GL3 gl) {
    lamp.draw(gl);
  }
}
