/* I declare that this code is my own work */
/* I based it on 'Light.java' provided by Dr Steve Maddock in eSheet6/Week6_3_scene_graph/
   and adapted it for my own use.
   I have updated it to include properties for the different light types (main, point and spot)
   I have therefore also changed the get and set methods for ambient, diffuse and specular */
/* Author: Ian Williams, Email: iwilliams3@sheffield.ac.uk */

import gmaths.*;

 /**
 * This class stores the Material properties for a Mesh
 *
 * @author    Dr Steve Maddock
 * @version   1.0 (15/10/2017)
 */

public class Material {

  public static final Vec3 DEFAULT_AMBIENT = new Vec3(0.2f, 0.2f, 0.2f);
  public static final Vec3 DEFAULT_DIFFUSE = new Vec3(0.8f, 0.8f, 0.8f);
  public static final Vec3 DEFAULT_SPECULAR = new Vec3(0.5f, 0.5f, 0.5f);
  public static final Vec3 DEFAULT_EMISSION = new Vec3(0.0f, 0.0f, 0.0f);
  public static final float DEFAULT_SHININESS = 32;

  private Vec3 ambientMain;
  private Vec3 diffuseMain;
  private Vec3 specularMain;

  private Vec3 ambientPoint;
  private Vec3 diffusePoint;
  private Vec3 specularPoint;

  private Vec3 ambientSpot;
  private Vec3 diffuseSpot;
  private Vec3 specularSpot;

  private Vec3 emission;
  private float shininess;

  /**
   * Constructor. Sets attributes to default initial values.
   */
  public Material() {
    ambientMain = new Vec3(DEFAULT_AMBIENT);
    ambientPoint = new Vec3(DEFAULT_AMBIENT);
    ambientSpot = new Vec3(DEFAULT_AMBIENT);
    diffuseMain = new Vec3(DEFAULT_DIFFUSE);
    diffusePoint = new Vec3(DEFAULT_DIFFUSE);
    diffuseSpot = new Vec3(DEFAULT_DIFFUSE);
    specularMain = new Vec3(DEFAULT_SPECULAR);
    specularPoint = new Vec3(DEFAULT_SPECULAR);
    specularSpot = new Vec3(DEFAULT_SPECULAR);
    emission = new Vec3(DEFAULT_EMISSION);
    shininess = DEFAULT_SHININESS;
  }

  /**
   * Sets the ambient value (as used in Phong local reflection model)
   *
   * @param  red    the red value in the range 0.0..1.0
   * @param  green  the green value in the range 0.0..1.0
   * @param  blue   the blue value in the range 0.0..1.0
   */
  public void setAmbient(float red, float green, float blue, String lightType) {
    switch (lightType) {
      case "main": ambientMain.x = red;
                   ambientMain.y = green;
                   ambientMain.z = blue;
                   break;
      case "point": ambientPoint.x = red;
                    ambientPoint.y = green;
                    ambientPoint.z = blue;
                    break;
      case "spot": ambientSpot.x = red;
                   ambientSpot.y = green;
                   ambientSpot.z = blue;
                   break;
    }
  }

  /**
   * Sets the ambient value (as used in Phong local reflection model)
   *
   * @param  rgb  vector of 3 values, where the  3 values are the values for red, green and blue,
                   in the range 0.0..1.0.
   */
  public void setAmbient(Vec3 rgb, String lightType) {
    setAmbient(rgb.x, rgb.y, rgb.z, lightType);
  }

  /**
   * Gets the ambient value (as a clone)
   *
   * @return  vector of 3 values, where the  3 values are the values for red, green and blue.
   */
  public Vec3 getAmbient(String lightType) {
    switch (lightType) {
      case "main": return new Vec3(ambientMain);
      case "point": return new Vec3(ambientPoint);
      case "spot": return new Vec3(ambientSpot);
    }
    return new Vec3(0,0,0);
  }

  /**
   * Sets the diffuse value (as used in Phong local reflection model)
   *
   * @param  red    the red value in the range 0.0..1.0
   * @param  green  the green value in the range 0.0..1.0
   * @param  blue   the blue value in the range 0.0..1.0
  */
  public void setDiffuse(float red, float green, float blue, String lightType) {
    switch (lightType) {
      case "main": diffuseMain.x = red;
                   diffuseMain.y = green;
                   diffuseMain.z = blue;
                   break;
      case "point": diffusePoint.x = red;
                    diffusePoint.y = green;
                    diffusePoint.z = blue;
                    break;
      case "spot": diffuseSpot.x = red;
                   diffuseSpot.y = green;
                   diffuseSpot.z = blue;
                   break;
    }
  }

  /**
   * Sets the diffuse value (as used in Phong local reflection model)
   *
   * @param  rgb  vector of 3 values, where the  3 values are the values for red, green and blue,
                   in the range 0.0..1.0.
   */
  public void setDiffuse(Vec3 rgb, String lightType) {
    setDiffuse(rgb.x, rgb.y, rgb.z, lightType);
  }

  /**
   * Gets the diffuse value (clone) (as used in Phong local reflection model)
   *
   * @return  vector of 3 values, where the  3 values are the values for red, green and blue
   */
  public Vec3 getDiffuse(String lightType) {
    switch (lightType) {
      case "main": return new Vec3(diffuseMain);
      case "point": return new Vec3(diffusePoint);
      case "spot": return new Vec3(diffuseSpot);
    }
    return new Vec3(0,0,0);
  }

  /**
   * Sets the specular value (as used in Phong local reflection model)
   *
   * @param  red    the red value in the range 0.0..1.0
   * @param  green  the green value in the range 0.0..1.0
   * @param  blue   the blue value in the range 0.0..1.0
  */
  public void setSpecular(float red, float green, float blue, String lightType) {
    switch (lightType) {
      case "main": specularMain.x = red;
                   specularMain.y = green;
                   specularMain.z = blue;
                   break;
      case "point": specularPoint.x = red;
                    specularPoint.y = green;
                    specularPoint.z = blue;
                    break;
      case "spot": specularSpot.x = red;
                   specularSpot.y = green;
                   specularSpot.z = blue;
                   break;
    }
  }

  /**
   * Sets the specular value (as used in Phong local reflection model)
   *
   * @param  rgb  vector of 3 values, where the first 3 values are the values for red, green and blue,
                   in the range 0.0..1.0, and the last value is an alpha term, which is always 1.
   */
  public void setSpecular(Vec3 rgb, String lightType) {
    setSpecular(rgb.x, rgb.y, rgb.z, lightType);
  }

  /**
   * Gets the specular value (clone) (as used in Phong local reflection model)
   *
   * @return  vector of 3 values, where the  3 values are the values for red, green and blue.
   */
  public Vec3 getSpecular(String lightType) {
    switch (lightType) {
      case "main": return new Vec3(specularMain);
      case "point": return new Vec3(specularPoint);
      case "spot": return new Vec3(specularSpot);
    }
    return new Vec3(0,0,0);
  }

  /**
   * Sets the emission value (as used in OpenGL lighting model)
   *
   * @param  red    the red value in the range 0.0..1.0
   * @param  green  the green value in the range 0.0..1.0
   * @param  blue   the blue value in the range 0.0..1.0
   */
  public void setEmission(float red, float green, float blue) {
    emission.x = red;
    emission.y = green;
    emission.z = blue;
  }

  /**
   * Sets the emission value (as used in OpenGL lighting model)
   *
   * @param  rgb  vector of 3 values, where the 3 values are the values for red, green and blue,
                   in the range 0.0..1.0.
   */
  public void setEmission(Vec3 rgb) {
    setEmission(rgb.x, rgb.y, rgb.z);
  }

  /**
   * Gets the emission value (clone) (as used in OpenGL lighting model)
   *
   * @return  vector of 3 values, where the  3 values are the values for red, green and blue.
   */
  public Vec3 getEmission() {
    return new Vec3(emission);
  }

  /**
   * Sets the shininess value (as used in Phong local reflection model)
   *
   * @param  shininess  the shininess value.
   */
  public void setShininess(float shininess) {
    this.shininess = shininess;
  }

  /**
   * Gets the shininess value (as used in Phong local reflection model)
   *
   * @return  the shininess value.
   */
  public float getShininess() {
    return shininess;
  }

  public String toString() {
    return "aMain:"+ambientMain+", dMain:"+diffuseMain+", sMain:"+specularMain+
           "aPoint:"+ambientPoint+", dPoint:"+diffusePoint+", sPoint:"+specularPoint+
           "aSpot:"+ambientSpot+", dSpot:"+diffuseSpot+", sSpot:"+specularSpot+
           ", e:"+emission+", shininess:"+shininess;
  }

}
