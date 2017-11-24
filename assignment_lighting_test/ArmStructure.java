import gmaths.*;

import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.glsl.*;

public class ArmStructure {

  public ArmStructure(Mesh cube, Mesh cubeRing, Mesh sphere, Mesh sphereGemstone) {
    this.cube = cube;
    this.cubeRing = cubeRing;
    this.sphere = sphere;
    this.sphereGemstone = sphereGemstone;
  }

  // ***************************************************
  /* INTERACTION
   *
   *
   */

   public void resetHand() {
     thumbSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(-35));
     thumbSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(0));
     thumbSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(0));

     firstFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(0));
     firstFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(0));
     firstFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(0));

     middleFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(0));
     middleFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(0));
     middleFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(0));

     ringFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(0));
     ringFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(0));
     ringFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(0));

     littleFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(0));
     littleFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(0));
     littleFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(0));
    //  firstFingerSegment1TranslateLocal.print(4, true);

     armStructure.update();

     animatedI = false;
     animatedA = false;
     animatedN = false;
     animatedVulcan = false;
     animationTime = 0;
   }

   public void makeI() {
     thumbSegment1TranslateLocal.setTransform(Mat4.multiply(
            Mat4Transform.rotateAroundX(10), Mat4Transform.rotateAroundZ(-25)));
     thumbSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(15));
     thumbSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(45));

     firstFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     firstFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     firstFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(45));

     middleFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     middleFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     middleFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(45));

     ringFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     ringFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     ringFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(45));

     littleFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(0));
     littleFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(0));
     littleFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(0));
     armStructure.update();
    //  firstFingerSegment1TranslateLocal.print(4, true);

     animatedI = true;
     animatedA = false;
     animatedN = false;
     animatedVulcan = false;
     animationTime = 0;
   }

   public void makeA() {
     thumbSegment1TranslateLocal.setTransform(Mat4.multiply(
            Mat4Transform.rotateAroundX(0), Mat4Transform.rotateAroundZ(-25)));
     thumbSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(25));
     thumbSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(0));

     firstFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     firstFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     firstFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(45));

     middleFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     middleFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     middleFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(45));

     ringFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     ringFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     ringFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(45));

     littleFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     littleFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     littleFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(45));
     armStructure.update();
    //  firstFingerSegment1TranslateLocal.print(4, true);

     animatedI = true;
     animatedA = true;
     animatedN = false;
     animatedVulcan = false;
     animationTime = 0;
   }

   public void makeN() {
     thumbSegment1TranslateLocal.setTransform(Mat4.multiply(
             Mat4Transform.rotateAroundX(10),Mat4Transform.rotateAroundZ(-5)));
     thumbSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(35));
     thumbSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(20));

     firstFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(45));
     firstFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     firstFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(60));

     middleFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(45));
     middleFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     middleFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(60));

     ringFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     ringFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     ringFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(45));

     littleFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     littleFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(90));
     littleFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(45));
     armStructure.update();
    //  firstFingerSegment1TranslateLocal.print(4, true);

     animatedI = true;
     animatedA = true;
     animatedN = true;
     animatedVulcan = false;
     animationTime = 0;
   }

  public void makeVulcan() {
    thumbSegment1TranslateLocal.setTransform(Mat4.multiply(
            Mat4Transform.rotateAroundX(-5), Mat4Transform.rotateAroundZ(-15)));
    thumbSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(3));
    thumbSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(2));

    firstFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(-20));
    firstFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(0));
    firstFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(0));

    middleFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(-20));
    middleFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(0));
    middleFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(0));

    ringFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(20));
    ringFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(0));
    ringFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(0));

    littleFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(20));
    littleFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(0));
    littleFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(0));
    armStructure.update();
    // firstFingerSegment1TranslateLocal.print(4, true);

    animatedI = true;
    animatedA = true;
    animatedN = true;
    animatedVulcan = true;
    animationTime = 0;
  }

  private boolean animatedI = false;
  private boolean animatedA = false;
  private boolean animatedN = false;
  private boolean animatedVulcan = false;
  public double animationTime = 0;

  public void animate(double startTime) {
    double elapsedTime = (Arty_GLEventListener.getSeconds()-startTime);

    if (!animatedI) {
      float rotateTS1ZAngle = -35f+10f*(float)Math.sin(elapsedTime-animationTime);
      animateI(rotateTS1ZAngle, elapsedTime-animationTime);
      if (rotateTS1ZAngle > -25.001f) {
        animatedI = true;
        animationTime = elapsedTime;
      }
    }
    else if (!animatedA) {
      float rotateTS1XAngle = 10f-10f*(float)Math.sin(elapsedTime-animationTime);
      animateA(rotateTS1XAngle, elapsedTime-animationTime);
      if (rotateTS1XAngle < 0.002f) {
        animatedA = true;
        animationTime = elapsedTime;
      }
    }
    else if (!animatedN) {
      float rotateTS1ZAngle = -25f+20f*(float)Math.sin(elapsedTime-animationTime);
      animateN(rotateTS1ZAngle, elapsedTime-animationTime);
      if (rotateTS1ZAngle > -5.002f) {
        animatedN = true;
        animationTime = elapsedTime;
      }
    }
    else if (!animatedVulcan) {
      float rotateTS1ZAngle = -5f-10f*(float)Math.sin(elapsedTime-animationTime);
      animateVulcan(rotateTS1ZAngle, elapsedTime-animationTime);
      if (rotateTS1ZAngle < -14.998f) {
        animatedVulcan = true;
        animationTime = elapsedTime;
      }
    }
    else {
      float rotateTS1ZAngle = -15f-20f*(float)Math.sin(elapsedTime-animationTime);
      animateToStart(rotateTS1ZAngle, elapsedTime-animationTime);
      if (rotateTS1ZAngle < -34.998f) {
        animatedI = false;
        animatedA = false;
        animatedN = false;
        animatedVulcan = false;
        animationTime = elapsedTime;
      }
    }
    armStructure.update();
  }

  public void animateI(float rotateTS1ZAngle, double time) {
    float rotateTS1XAngle = 10f*(float)Math.sin(time);
    thumbSegment1TranslateLocal.setTransform(Mat4.multiply(
      Mat4Transform.rotateAroundX(rotateTS1XAngle), Mat4Transform.rotateAroundZ(rotateTS1ZAngle)));

    float rotateTS2Angle = 15f*(float)Math.sin(time);
    thumbSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(rotateTS2Angle));
    float rotateTS3Angle = 45f*(float)Math.sin(time);
    thumbSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(rotateTS3Angle));

    float rotateS1and2Angle = 0f+90f*(float)Math.sin(time);
    firstFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateS1and2Angle));
    firstFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateS1and2Angle));
    middleFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateS1and2Angle));
    middleFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateS1and2Angle));
    ringFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateS1and2Angle));
    ringFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateS1and2Angle));

    float rotateS3Angle = 0f+45f*(float)Math.sin(time);
    firstFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateS3Angle));
    middleFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateS3Angle));
    ringFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateS3Angle));
  }

  public void animateA(float rotateTS1XAngle, double time) {
    thumbSegment1TranslateLocal.setTransform(Mat4.multiply(
            Mat4Transform.rotateAroundX(rotateTS1XAngle), Mat4Transform.rotateAroundZ(-25)));
    float rotateTS2Angle = 15f+10f*(float)Math.sin(time);
    thumbSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(rotateTS2Angle));
    float rotateTS3Angle = 45f-45f*(float)Math.sin(time);
    thumbSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(rotateTS3Angle));

    float rotateFS1and2Angle = 0f+90f*(float)Math.sin(time);
    littleFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateFS1and2Angle));
    littleFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateFS1and2Angle));

    float rotateFS3Angle = 0f+45f*(float)Math.sin(time);
    littleFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateFS3Angle));
  }

  public void animateN(float rotateTS1ZAngle, double time) {
    float rotateTS1XAngle = 10f*(float)Math.sin(time);
    thumbSegment1TranslateLocal.setTransform(Mat4.multiply(
            Mat4Transform.rotateAroundX(rotateTS1XAngle), Mat4Transform.rotateAroundZ(rotateTS1ZAngle)));
    float rotateTS2Angle = 25f+10f*(float)Math.sin(time);
    thumbSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(rotateTS2Angle));
    float rotateTS3Angle = 20f*(float)Math.sin(time);
    thumbSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(rotateTS3Angle));

    float rotateFS1Angle = 90f-45f*(float)Math.sin(time);
    firstFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateFS1Angle));
    middleFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateFS1Angle));

    float rotateFS3Angle = 45f+15f*(float)Math.sin(time);
    firstFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateFS3Angle));
    middleFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateFS3Angle));
  }

  public void animateVulcan(float rotateTS1ZAngle, double time) {
    float rotateTS1XAngle = 10f-15f*(float)Math.sin(time);
    thumbSegment1TranslateLocal.setTransform(Mat4.multiply(
            Mat4Transform.rotateAroundX(rotateTS1XAngle), Mat4Transform.rotateAroundZ(rotateTS1ZAngle)));
    float rotateTS2XAngle = 3f*(float)Math.sin(time);
    float rotateTS2ZAngle = 35f-35f*(float)Math.sin(time);
    thumbSegment2TranslateLocal.setTransform(Mat4.multiply(
        Mat4Transform.rotateAroundX(rotateTS2XAngle), Mat4Transform.rotateAroundZ(rotateTS2ZAngle)));
    float rotateTS3XAngle = 2f*(float)Math.sin(time);
    float rotateTS3ZAngle = 20f-20f*(float)Math.sin(time);
    thumbSegment3TranslateLocal.setTransform(Mat4.multiply(
        Mat4Transform.rotateAroundX(rotateTS3XAngle), Mat4Transform.rotateAroundZ(rotateTS3ZAngle)));

    float rotateFirstAndMiddleFS1XAngle = 45f-45f*(float)Math.sin(time);
    float rotateFirstAndMiddleFS1ZAngle = -20f*(float)Math.sin(time);
    firstFingerSegment1TranslateLocal.setTransform(Mat4.multiply(
        Mat4Transform.rotateAroundX(rotateFirstAndMiddleFS1XAngle), Mat4Transform.rotateAroundZ(rotateFirstAndMiddleFS1ZAngle)));
    middleFingerSegment1TranslateLocal.setTransform(Mat4.multiply(
        Mat4Transform.rotateAroundX(rotateFirstAndMiddleFS1XAngle), Mat4Transform.rotateAroundZ(rotateFirstAndMiddleFS1ZAngle)));

    float rotateFirstAndMiddleFS2Angle = 90f-90f*(float)Math.sin(time);
    firstFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateFirstAndMiddleFS2Angle));
    middleFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateFirstAndMiddleFS2Angle));

    float rotateFirstAndMiddleFS3Angle = 60f-60f*(float)Math.sin(time);
    firstFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateFirstAndMiddleFS3Angle));
    middleFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateFirstAndMiddleFS3Angle));


    float rotateRingAndLittleFS1XAngle = 90f-90f*(float)Math.sin(time);
    float rotateRingAndLittleFS1ZAngle = 20f*(float)Math.sin(time);
    ringFingerSegment1TranslateLocal.setTransform(Mat4.multiply(
        Mat4Transform.rotateAroundX(rotateRingAndLittleFS1XAngle), Mat4Transform.rotateAroundZ(rotateRingAndLittleFS1ZAngle)));
    littleFingerSegment1TranslateLocal.setTransform(Mat4.multiply(
        Mat4Transform.rotateAroundX(rotateRingAndLittleFS1XAngle), Mat4Transform.rotateAroundZ(rotateRingAndLittleFS1ZAngle)));

    float rotateRingAndLittleFS2Angle = 90f-90f*(float)Math.sin(time);
    ringFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateRingAndLittleFS2Angle));
    littleFingerSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateRingAndLittleFS2Angle));

    float rotateRingAndLittleFS3Angle = 45f-45f*(float)Math.sin(time);
    ringFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateRingAndLittleFS3Angle));
    littleFingerSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateRingAndLittleFS3Angle));
  }

  public void animateToStart(float rotateTS1ZAngle, double time) {
    float rotateTS1XAngle = -5f+5f*(float)Math.sin(time);
    thumbSegment1TranslateLocal.setTransform(Mat4.multiply(
            Mat4Transform.rotateAroundX(rotateTS1XAngle), Mat4Transform.rotateAroundZ(rotateTS1ZAngle)));
    float rotateTS2XAngle = 3f-3f*(float)Math.sin(time);
    thumbSegment2TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateTS2XAngle));
    float rotateTS3XAngle = 2f-2f*(float)Math.sin(time);
    thumbSegment3TranslateLocal.setTransform(Mat4Transform.rotateAroundX(rotateTS3XAngle));

    float rotateFirstAndMiddleFS1ZAngle = -20f+20f*(float)Math.sin(time);
    firstFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(rotateFirstAndMiddleFS1ZAngle));
    middleFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(rotateFirstAndMiddleFS1ZAngle));

    float rotateRingAndLittleFS1ZAngle = 20f-20f*(float)Math.sin(time);
    ringFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(rotateRingAndLittleFS1ZAngle));
    littleFingerSegment1TranslateLocal.setTransform(Mat4Transform.rotateAroundZ(rotateRingAndLittleFS1ZAngle));
  }

  public Vec3 getRingPosition() {
    // if time = 0 then the hand is in a static position (i.e. 'rest', 'i', 'a', 'n', or 'vulcan')
    float x=0, y=0, z=0;
    if (animationTime == 0) {
      if (!animatedI) {
        // ring not moved
        x = 1.13f;
        y = 10.92f;
        z = -0.3f;
      }
      else if (!animatedA) {
        // ring at I position
        x = 1.13f;
        y = 10.8f;
        z = 0.42f;
      }
      else if (!animatedN) {
        // ring at A position
        x = 1.13f;
        y = 10.8f;
        z = 0.42f;
      }
      else if (!animatedVulcan) {
        // ring at N position
        x = 1.13f;
        y = 11.01f;
        z = 0.09f;
      }
      else {
        // ring at vulcan position
        x = 1.27f;
        y = 10.9f;
        z = -0.3f;
      }
    }
    else {
      //need to work out where in animation the ring is
      if (!animatedI) {
        // somewhere between rest and 'I'
      }
      else if (!animatedA) {
        // somewhere between 'I' and 'A'
      }
      else if (!animatedN) {
        // somewhere between 'A' and 'N'
      }
      else if (!animatedVulcan) {
        // somewhere between 'N' and 'Vulcan'
      }
      else {
        // somewhere between 'Vulcan' and 'rest'
      }
    }
    return new Vec3(x,y,z);
  }

  public Vec3 getRingDirection() {
    // if time = 0 then the hand is in a static position (i.e. 'rest', 'i', 'a', 'n', or 'vulcan')
    float x=0, y=0, z=0;
    if (animationTime == 0) {
      if (!animatedI) {
        // ring not moved
        x = 0f;
        y = 0f;
        z = -1f;
      }
      else if (!animatedA) {
        // ring at I position
        x = 1.13f;
        y = 10.8f;
        z = 0.42f;
      }
      else if (!animatedN) {
        // ring at A position
        x = 1.13f;
        y = 10.8f;
        z = 0.42f;
      }
      else if (!animatedVulcan) {
        // ring at N position
        x = 1.13f;
        y = 11.01f;
        z = 0.09f;
      }
      else {
        // ring at vulcan position
        x = 1.27f;
        y = 10.9f;
        z = -0.3f;
      }
    }
    else {
      //need to work out where in animation the ring is
      if (!animatedI) {
        // somewhere between rest and 'I'
      }
      else if (!animatedA) {
        // somewhere between 'I' and 'A'
      }
      else if (!animatedN) {
        // somewhere between 'A' and 'N'
      }
      else if (!animatedVulcan) {
        // somewhere between 'N' and 'Vulcan'
      }
      else {
        // somewhere between 'Vulcan' and 'rest'
      }
    }
    return new Vec3(x,y,z);
  }

  // ***************************************************
  /* THE SCENE
   * Now define all the methods to handle the scene.
   */
  private Mesh cube, cubeRing, sphere, sphereGemstone;
  private SGNode armStructure;

  private TransformNode armStructureMoveTranslate, thumbSegment1TranslateLocal;

  private TransformNode thumbSegment2TranslateLocal = new TransformNode("thumb segment 2 translate local", Mat4Transform.rotateAroundZ(0));
  private TransformNode thumbSegment3TranslateLocal = new TransformNode("thumb segment 3 translate local", Mat4Transform.rotateAroundZ(0));

  private TransformNode firstFingerSegment1TranslateLocal = new TransformNode("first finger segment 1 translate local", Mat4Transform.rotateAroundZ(0));
  private TransformNode middleFingerSegment1TranslateLocal = new TransformNode("middle finger segment 1 translate local", Mat4Transform.rotateAroundZ(0));
  private TransformNode ringFingerSegment1TranslateLocal = new TransformNode("ring finger segment 1 translate local", Mat4Transform.rotateAroundZ(0));
  private TransformNode littleFingerSegment1TranslateLocal = new TransformNode("little finger segment 1 translate local", Mat4Transform.rotateAroundZ(0));

  private TransformNode firstFingerSegment2TranslateLocal = new TransformNode("first finger segment 2 translate local", Mat4Transform.rotateAroundZ(0));
  private TransformNode middleFingerSegment2TranslateLocal = new TransformNode("middle finger segment 2 translate local", Mat4Transform.rotateAroundZ(0));
  private TransformNode ringFingerSegment2TranslateLocal = new TransformNode("ring finger segment 2 translate local", Mat4Transform.rotateAroundZ(0));
  private TransformNode littleFingerSegment2TranslateLocal = new TransformNode("little finger segment 2 translate local", Mat4Transform.rotateAroundZ(0));

  private TransformNode firstFingerSegment3TranslateLocal = new TransformNode("first finger segment 3 translate local", Mat4Transform.rotateAroundZ(0));
  private TransformNode middleFingerSegment3TranslateLocal = new TransformNode("middle finger segment 3 translate local", Mat4Transform.rotateAroundZ(0));
  private TransformNode ringFingerSegment3TranslateLocal = new TransformNode("ring finger segment 3 translate local", Mat4Transform.rotateAroundZ(0));
  private TransformNode littleFingerSegment3TranslateLocal = new TransformNode("little finger segment 3 translate local", Mat4Transform.rotateAroundZ(0));

  // armStructure measurements
  private float armPlinthHeight = 7.5f;
  private float armPlinthWidth = 2;
  private float armPlinthDepth = 1.5f;
  private float palmHeight = 3;
  private float palmWidth = 3;
  private float palmDepth = 0.5f;
  private float fingerWidth = palmWidth/5;
  private float thumbWidth = fingerWidth;
  private float fingerDepth = palmDepth;
  private float middleFingerHeight = palmHeight;
  private float thumbHeight = 12f/11f*middleFingerHeight;
  private float firstFingerHeight = 9.5f/12*middleFingerHeight;
  private float ringFingerHeight = 10.5f/12*middleFingerHeight;
  private float littleFingerHeight = 2f/3f*middleFingerHeight;
  private float gemWidth = fingerWidth*4f/9f;

  public void initialise(GL3 gl) {

    armStructure = new NameNode("root");
    armStructureMoveTranslate = new TransformNode("arm-structure transform", Mat4Transform.translate(0,0,0));

    // Move whole structure around from plinth
    TransformNode armStructureTranslate = new TransformNode("arm-structure transform", Mat4Transform.translate(0,0,0));

    NameNode armPlinth = new NameNode("arm plinth");
    Mat4 m = Mat4Transform.scale(armPlinthWidth,armPlinthHeight,armPlinthDepth);
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0));
    TransformNode armPlinthTransform = new TransformNode("arm transform", m);
    MeshNode armPlinthShape = new MeshNode("Sphere(armPlinth)", sphere);

    NameNode palm = new NameNode("palm");
    TransformNode palmTranslate = new TransformNode("palm translate",
                                                      Mat4Transform.translate(0,armPlinthHeight,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.scale(palmWidth,palmHeight,palmDepth)); // Size
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode palmScale = new TransformNode("palm transform", m);
    MeshNode palmShape = new MeshNode("Cube(palm)", cube);

    NameNode thumbSegment1 = new NameNode("thumb segment 1");
    TransformNode thumbSegment1TranslateOntoPalm = new TransformNode("thumb segment 1 translate",
                                                              Mat4Transform.translate(palmWidth*3/8,0,palmDepth/2));
    thumbSegment1TranslateLocal = new TransformNode("thumb segegment 1 translate local",
                                                              Mat4Transform.rotateAroundZ(-35));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.rotateAroundY(15));
    m = Mat4.multiply(m, Mat4Transform.scale(thumbWidth,thumbHeight/2,fingerDepth));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode thumbSegment1Scale = new TransformNode("thumb segment 1 transform", m);
    MeshNode thumbSegment1Shape = new MeshNode("Sphere(thumb segment 1)", sphere);

    NameNode thumbSegment2 = new NameNode("thumb segment 2");
    TransformNode thumbSegment2Translate = new TransformNode("thumb segment 2 translate",
                                                              Mat4Transform.translate(0,thumbHeight/2,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.rotateAroundY(15));
    m = Mat4.multiply(m, Mat4Transform.scale(thumbWidth,thumbHeight/3,fingerDepth));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode thumbSegment2Scale = new TransformNode("thumb segment 2 transform", m);
    MeshNode thumbSegment2Shape = new MeshNode("Sphere(thumb segment 2)", sphere);

    NameNode thumbSegment3 = new NameNode("thumb segment 3");
    TransformNode thumbSegment3Translate = new TransformNode("thumb segment 3 translate",
                                                              Mat4Transform.translate(0,thumbHeight/3,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.rotateAroundY(15));
    m = Mat4.multiply(m, Mat4Transform.scale(thumbWidth,thumbHeight/3,fingerDepth));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode thumbSegment3Scale = new TransformNode("thumb segment 3 transform", m);
    MeshNode thumbSegment3Shape = new MeshNode("Sphere(thumb segment 3)", sphere);

    NameNode firstFingerSegment1 = new NameNode("first finger segment 1");
    TransformNode firstFingerSegment1TranslateOntoPalm = new TransformNode("first finger segment 1 translate onto palm",
                                                                    Mat4Transform.translate((palmWidth*3/8),palmHeight,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.scale(fingerWidth,firstFingerHeight/3,fingerDepth));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode firstFingerSegment1Scale = new TransformNode("first finger segment 1 transform", m);
    MeshNode firstFingerSegment1Shape = new MeshNode("Sphere(first finger segment 1)", sphere);

    NameNode ringBand = new NameNode("ringBand");
    TransformNode ringBandTranslate = new TransformNode("ring band translate",
                                                          Mat4Transform.translate(0,firstFingerHeight/9,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.scale(fingerWidth+0.1f,firstFingerHeight/8,fingerDepth+0.1f));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode ringBandScale = new TransformNode("ring band transform", m);
    MeshNode ringBandShape = new MeshNode("Cube(ring band)", cubeRing);

    NameNode ringGem = new NameNode("ringGem");
    TransformNode ringGemTranslate = new TransformNode("ring gem translate",
                                                          Mat4Transform.translate(0,firstFingerHeight/100,-fingerWidth/2));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.scale(gemWidth,gemWidth,gemWidth));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode ringGemScale = new TransformNode("ring gem transform", m);
    MeshNode ringGemShape = new MeshNode("Sphere(ring gem)", sphereGemstone);

    NameNode firstFingerSegment2 = new NameNode("first finger segment 2");
    TransformNode firstFingerSegment2Translate = new TransformNode("first finger segment 2 translate",
                                                                    Mat4Transform.translate(0,firstFingerHeight/3,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.scale(fingerWidth,firstFingerHeight/3,fingerDepth));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode firstFingerSegment2Scale = new TransformNode("first finger segment 2 transform", m);
    MeshNode firstFingerSegment2Shape = new MeshNode("Sphere(first finger segment 2)", sphere);

    NameNode firstFingerSegment3 = new NameNode("first finger segment 3");
    TransformNode firstFingerSegment3Translate = new TransformNode("first finger segment 3 translate",
                                                                    Mat4Transform.translate(0,firstFingerHeight/3,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.scale(fingerWidth,firstFingerHeight/3,fingerDepth));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode firstFingerSegment3Scale = new TransformNode("first finger segment 3 transform", m);
    MeshNode firstFingerSegment3Shape = new MeshNode("Sphere(first finger segment 3)", sphere);

    NameNode middleFingerSegment1 = new NameNode("middle finger segment 1");
    TransformNode middleFingerSegment1TranslateOntoPalm = new TransformNode("middle finger segment 1 translate",
                                                                    Mat4Transform.translate((palmWidth/8),palmHeight,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.scale(fingerWidth,middleFingerHeight/3,fingerDepth));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode middleFingerSegment1Scale = new TransformNode("middle finger segment 1 transform", m);
    MeshNode middleFingerSegment1Shape = new MeshNode("Sphere(middle finger segment 1)", sphere);

    NameNode middleFingerSegment2 = new NameNode("middle finger segment 2");
    TransformNode middleFingerSegment2Translate = new TransformNode("middle finger segment 2 translate",
                                                                    Mat4Transform.translate(0,middleFingerHeight/3,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.scale(fingerWidth,middleFingerHeight/3,fingerDepth));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode middleFingerSegment2Scale = new TransformNode("middle finger segment 2 transform", m);
    MeshNode middleFingerSegment2Shape = new MeshNode("Sphere(middle finger segment 2)", sphere);

    NameNode middleFingerSegment3 = new NameNode("middle finger segment 3");
    TransformNode middleFingerSegment3Translate = new TransformNode("middle finger segment 3 translate",
                                                                    Mat4Transform.translate(0,middleFingerHeight/3,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.scale(fingerWidth,middleFingerHeight/3,fingerDepth));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode middleFingerSegment3Scale = new TransformNode("middle finger segment 3 transform", m);
    MeshNode middleFingerSegment3Shape = new MeshNode("Sphere(middle finger segment 3)", sphere);

    NameNode ringFingerSegment1 = new NameNode("ring finger segment 1");
    TransformNode ringFingerSegment1TranslateOntoPalm = new TransformNode("ring finger segment 1 translate",
                                                                    Mat4Transform.translate((-palmWidth/8),palmHeight,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.scale(fingerWidth,ringFingerHeight/3,fingerDepth));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode ringFingerSegment1Scale = new TransformNode("ring finger segment 1 transform", m);
    MeshNode ringFingerSegment1Shape = new MeshNode("Sphere(ring finger segment 1)", sphere);

    NameNode ringFingerSegment2 = new NameNode("ring finger segment 2");
    TransformNode ringFingerSegment2Translate = new TransformNode("ring finger segment 2 translate",
                                                                    Mat4Transform.translate(0,ringFingerHeight/3,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.scale(fingerWidth,ringFingerHeight/3,fingerDepth));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode ringFingerSegment2Scale = new TransformNode("ring finger segment 2 transform", m);
    MeshNode ringFingerSegment2Shape = new MeshNode("Sphere(ring finger segment 2)", sphere);

    NameNode ringFingerSegment3 = new NameNode("ring finger segment 3");
    TransformNode ringFingerSegment3Translate = new TransformNode("ring finger segment 3 translate",
                                                                    Mat4Transform.translate(0,ringFingerHeight/3,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.scale(fingerWidth,ringFingerHeight/3,fingerDepth));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode ringFingerSegment3Scale = new TransformNode("ring finger segment 3 transform", m);
    MeshNode ringFingerSegment3Shape = new MeshNode("Sphere(ring finger segment 3)", sphere);

    NameNode littleFingerSegment1 = new NameNode("little finger segment 1");
    TransformNode littleFingerSegment1TranslateOntoPalm = new TransformNode("little finger segment 1 translate",
                                                                    Mat4Transform.translate((-palmWidth*3/8),palmHeight,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.scale(fingerWidth,littleFingerHeight/3,fingerDepth));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode littleFingerSegment1Scale = new TransformNode("little finger segment 1 transform", m);
    MeshNode littleFingerSegment1Shape = new MeshNode("Sphere(little finger segment 1)", sphere);

    NameNode littleFingerSegment2 = new NameNode("little finger segment 2");
    TransformNode littleFingerSegment2Translate = new TransformNode("little finger segment 2 translate",
                                                                    Mat4Transform.translate(0,littleFingerHeight/3,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.scale(fingerWidth,littleFingerHeight/3,fingerDepth));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode littleFingerSegment2Scale = new TransformNode("little finger segment 2 transform", m);
    MeshNode littleFingerSegment2Shape = new MeshNode("Sphere(little finger segment 2)", sphere);

    NameNode littleFingerSegment3 = new NameNode("little finger segment 3");
    TransformNode littleFingerSegment3Translate = new TransformNode("little finger segment 3 translate",
                                                                    Mat4Transform.translate(0,littleFingerHeight/3,0));
    m = new Mat4(1);
    m = Mat4.multiply(m, Mat4Transform.scale(fingerWidth,littleFingerHeight/3,fingerDepth));
    m = Mat4.multiply(m, Mat4Transform.translate(0,0.5f,0)); // Position to y=0
    TransformNode littleFingerSegment3Scale = new TransformNode("little finger segment 3 transform", m);
    MeshNode littleFingerSegment3Shape = new MeshNode("Sphere(little finger segment 3)", sphere);

    armStructure.addChild(armStructureMoveTranslate);
      armStructureMoveTranslate.addChild(armStructureTranslate);
        armStructureTranslate.addChild(armPlinth);
          armPlinth.addChild(armPlinthTransform);
            armPlinthTransform.addChild(armPlinthShape);
          armPlinth.addChild(palm);
            palm.addChild(palmTranslate);
              palmTranslate.addChild(palmScale);
                palmScale.addChild(palmShape);
              palmTranslate.addChild(thumbSegment1);
                thumbSegment1.addChild(thumbSegment1TranslateOntoPalm);
                  thumbSegment1TranslateOntoPalm.addChild(thumbSegment1TranslateLocal);
                    thumbSegment1TranslateLocal.addChild(thumbSegment1Scale);
                      thumbSegment1Scale.addChild(thumbSegment1Shape);
                    thumbSegment1TranslateLocal.addChild(thumbSegment2);
                      thumbSegment2.addChild(thumbSegment2Translate);
                        thumbSegment2Translate.addChild(thumbSegment2TranslateLocal);
                          thumbSegment2TranslateLocal.addChild(thumbSegment2Scale);
                            thumbSegment2Scale.addChild(thumbSegment2Shape);
                          thumbSegment2TranslateLocal.addChild(thumbSegment3);
                            thumbSegment3.addChild(thumbSegment3Translate);
                              thumbSegment3Translate.addChild(thumbSegment3TranslateLocal);
                                thumbSegment3TranslateLocal.addChild(thumbSegment3Scale);
                                  thumbSegment3Scale.addChild(thumbSegment3Shape);
              palmTranslate.addChild(firstFingerSegment1);
                firstFingerSegment1.addChild(firstFingerSegment1TranslateOntoPalm);
                  firstFingerSegment1TranslateOntoPalm.addChild(firstFingerSegment1TranslateLocal);
                    firstFingerSegment1TranslateLocal.addChild(firstFingerSegment1Scale);
                      firstFingerSegment1Scale.addChild(firstFingerSegment1Shape);
                    firstFingerSegment1TranslateLocal.addChild(ringBand);
                      ringBand.addChild(ringBandTranslate);
                        ringBandTranslate.addChild(ringBandScale);
                          ringBandScale.addChild(ringBandShape);
                        ringBandTranslate.addChild(ringGem);
                          ringGem.addChild(ringGemTranslate);
                            ringGemTranslate.addChild(ringGemScale);
                              ringGemScale.addChild(ringGemShape);
                    firstFingerSegment1TranslateLocal.addChild(firstFingerSegment2);
                      firstFingerSegment2.addChild(firstFingerSegment2Translate);
                        firstFingerSegment2Translate.addChild(firstFingerSegment2TranslateLocal);
                          firstFingerSegment2TranslateLocal.addChild(firstFingerSegment2Scale);
                            firstFingerSegment2Scale.addChild(firstFingerSegment2Shape);
                          firstFingerSegment2TranslateLocal.addChild(firstFingerSegment3);
                            firstFingerSegment3.addChild(firstFingerSegment3Translate);
                              firstFingerSegment3Translate.addChild(firstFingerSegment3TranslateLocal);
                                firstFingerSegment3TranslateLocal.addChild(firstFingerSegment3Scale);
                                  firstFingerSegment3Scale.addChild(firstFingerSegment3Shape);
              palmTranslate.addChild(middleFingerSegment1);
                middleFingerSegment1.addChild(middleFingerSegment1TranslateOntoPalm);
                  middleFingerSegment1TranslateOntoPalm.addChild(middleFingerSegment1TranslateLocal);
                    middleFingerSegment1TranslateLocal.addChild(middleFingerSegment1Scale);
                      middleFingerSegment1Scale.addChild(middleFingerSegment1Shape);
                    middleFingerSegment1TranslateLocal.addChild(middleFingerSegment2);
                      middleFingerSegment2.addChild(middleFingerSegment2Translate);
                        middleFingerSegment2Translate.addChild(middleFingerSegment2TranslateLocal);
                          middleFingerSegment2TranslateLocal.addChild(middleFingerSegment2Scale);
                            middleFingerSegment2Scale.addChild(middleFingerSegment2Shape);
                          middleFingerSegment2TranslateLocal.addChild(middleFingerSegment3);
                            middleFingerSegment3.addChild(middleFingerSegment3Translate);
                              middleFingerSegment3Translate.addChild(middleFingerSegment3TranslateLocal);
                                middleFingerSegment3TranslateLocal.addChild(middleFingerSegment3Scale);
                                  middleFingerSegment3Scale.addChild(middleFingerSegment3Shape);
              palmTranslate.addChild(ringFingerSegment1);
                ringFingerSegment1.addChild(ringFingerSegment1TranslateOntoPalm);
                  ringFingerSegment1TranslateOntoPalm.addChild(ringFingerSegment1TranslateLocal);
                    ringFingerSegment1TranslateLocal.addChild(ringFingerSegment1Scale);
                      ringFingerSegment1Scale.addChild(ringFingerSegment1Shape);
                    ringFingerSegment1TranslateLocal.addChild(ringFingerSegment2);
                      ringFingerSegment2.addChild(ringFingerSegment2Translate);
                        ringFingerSegment2Translate.addChild(ringFingerSegment2TranslateLocal);
                          ringFingerSegment2TranslateLocal.addChild(ringFingerSegment2Scale);
                            ringFingerSegment2Scale.addChild(ringFingerSegment2Shape);
                          ringFingerSegment2TranslateLocal.addChild(ringFingerSegment3);
                            ringFingerSegment3.addChild(ringFingerSegment3Translate);
                              ringFingerSegment3Translate.addChild(ringFingerSegment3TranslateLocal);
                                ringFingerSegment3TranslateLocal.addChild(ringFingerSegment3Scale);
                                  ringFingerSegment3Scale.addChild(ringFingerSegment3Shape);
              palmTranslate.addChild(littleFingerSegment1);
                littleFingerSegment1.addChild(littleFingerSegment1TranslateOntoPalm);
                  littleFingerSegment1TranslateOntoPalm.addChild(littleFingerSegment1TranslateLocal);
                    littleFingerSegment1TranslateLocal.addChild(littleFingerSegment1Scale);
                      littleFingerSegment1Scale.addChild(littleFingerSegment1Shape);
                    littleFingerSegment1TranslateLocal.addChild(littleFingerSegment2);
                      littleFingerSegment2.addChild(littleFingerSegment2Translate);
                        littleFingerSegment2Translate.addChild(littleFingerSegment2TranslateLocal);
                          littleFingerSegment2TranslateLocal.addChild(littleFingerSegment2Scale);
                            littleFingerSegment2Scale.addChild(littleFingerSegment2Shape);
                          littleFingerSegment2TranslateLocal.addChild(littleFingerSegment3);
                            littleFingerSegment3.addChild(littleFingerSegment3Translate);
                              littleFingerSegment3Translate.addChild(littleFingerSegment3TranslateLocal);
                                littleFingerSegment3TranslateLocal.addChild(littleFingerSegment3Scale);
                                  littleFingerSegment3Scale.addChild(littleFingerSegment3Shape);

    armStructure.update();
    // armStructure.print(0, false);
    // System.exit(0);
  }

  public void render(GL3 gl) {
    armStructure.draw(gl);
  }
}
