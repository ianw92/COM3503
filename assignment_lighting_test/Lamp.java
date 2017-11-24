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

  private float xPos = 0;
  private float yPos = 0;
  private float zPos = 0;
  private float width = 1;
  private float height = 1;
  private float depth = 1;
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

  public void lightOn() {

  }

  public void lightOff() {

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

    NameNode lampOrb = new NameNode("lamp orb");
    TransformNode lampOrbTranslate = new TransformNode("lamp orb translate",
                                                     Mat4Transform.translate(0,height,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.scale(width*2f/3f,width*2f/3f,depth*2f/3f));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0));
    TransformNode lampOrbScale = new TransformNode("lamp orb transform", m);
    MeshNode lampOrbShape = new MeshNode("Sphere(lamp orb)", sphere);

    lamp.addChild(lampTranslate);
      lampTranslate.addChild(lampBase);
        lampBase.addChild(lampBaseTransform);
          lampBaseTransform.addChild(lampBaseShape);
        lampBase.addChild(lampColumn);
          lampColumn.addChild(lampColumnTranslate);
            lampColumnTranslate.addChild(lampColumnScale);
              lampColumnScale.addChild(lampColumnShape);
            lampColumnTranslate.addChild(lampOrb);
              lampOrb.addChild(lampOrbTranslate);
                lampOrbTranslate.addChild(lampOrbScale);
                  lampOrbScale.addChild(lampOrbShape);

    lamp.update();
    lamp.print(0, false);

  }

  public void render(GL3 gl) {
    lamp.draw(gl);
  }
}
