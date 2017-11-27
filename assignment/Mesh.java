import gmaths.*;
import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;

public abstract class Mesh {

  protected float[] vertices;
  protected int[] indices;
  private int vertexStride = 8;
  private int vertexXYZFloats = 3;
  private int vertexNormalFloats = 3;
  private int vertexTexFloats = 2;
  private int[] vertexBufferId = new int[1];
  protected int[] vertexArrayId = new int[1];
  private int[] elementBufferId = new int[1];

  protected Material material;
  protected Shader shader;
  protected Mat4 model;

  protected Camera camera;
  protected Mat4 perspective;
  protected Light light;

  public Mesh(GL3 gl) {
    material = new Material();
    model = new Mat4(1);
  }

  public void setModelMatrix(Mat4 m) {
    model = m;
  }

  public void setCamera(Camera camera) {
    this.camera = camera;
  }

  public void setPerspective(Mat4 perspective) {
    this.perspective = perspective;
  }

  public void setLight(Light light) {
    this.light = light;
  }

  public void dispose(GL3 gl) {
    gl.glDeleteBuffers(1, vertexBufferId, 0);
    gl.glDeleteVertexArrays(1, vertexArrayId, 0);
    gl.glDeleteBuffers(1, elementBufferId, 0);
  }

  public void setupDefaultMaterialLighting() {
    material.setAmbient(1.0f, 0.5f, 0.31f, "main");
    material.setDiffuse(1.0f, 0.5f, 0.31f, "main");
    material.setSpecular(0.5f, 0.5f, 0.5f, "main");
    material.setAmbient(1.0f, 0.5f, 0.31f, "point");
    material.setDiffuse(1.0f, 0.5f, 0.31f, "point");
    material.setSpecular(0.5f, 0.5f, 0.5f, "point");
    material.setAmbient(1.0f, 0.5f, 0.31f, "spot");
    material.setDiffuse(1.0f, 0.5f, 0.31f, "spot");
    material.setSpecular(0.5f, 0.5f, 0.5f, "spot");
    material.setShininess(32.0f);
  }

  public void setupShaderLights(Shader shader, GL3 gl, Light light, Material material) {
    shader.setVec3(gl, "pointLights[0].position", light.getPosition(0));
    shader.setFloat(gl, "pointLights[0].constant", 1.0f);
    shader.setFloat(gl, "pointLights[0].linear", 0.027f);
    shader.setFloat(gl, "pointLights[0].quadratic", 0.0028f);
    shader.setVec3(gl, "pointLights[0].ambient", light.getMaterial().getAmbient("main"));
    shader.setVec3(gl, "pointLights[0].diffuse", light.getMaterial().getDiffuse("main"));
    shader.setVec3(gl, "pointLights[0].specular", light.getMaterial().getSpecular("main"));

    shader.setVec3(gl, "pointLights[1].position", light.getPosition(1));
    shader.setFloat(gl, "pointLights[1].constant", 1.0f);
    shader.setFloat(gl, "pointLights[1].linear", 0.007f);
    shader.setFloat(gl, "pointLights[1].quadratic", 0.0002f);
    shader.setVec3(gl, "pointLights[1].ambient", light.getMaterial().getAmbient("main"));
    shader.setVec3(gl, "pointLights[1].diffuse", light.getMaterial().getDiffuse("main"));
    shader.setVec3(gl, "pointLights[1].specular", light.getMaterial().getSpecular("main"));

    shader.setVec3(gl, "pointLights[2].position", light.getPosition(2));
    shader.setFloat(gl, "pointLights[2].constant", 1.0f);
    shader.setFloat(gl, "pointLights[2].linear", 0.027f);
    shader.setFloat(gl, "pointLights[2].quadratic", 0.0028f);
    shader.setVec3(gl, "pointLights[2].ambient", light.getMaterial().getAmbient("point"));
    shader.setVec3(gl, "pointLights[2].diffuse", light.getMaterial().getDiffuse("point"));
    shader.setVec3(gl, "pointLights[2].specular", light.getMaterial().getSpecular("point"));

    shader.setVec3(gl, "pointLights[3].position", light.getPosition(3));
    shader.setFloat(gl, "pointLights[3].constant", 1.0f);
    shader.setFloat(gl, "pointLights[3].linear", 0.027f);
    shader.setFloat(gl, "pointLights[3].quadratic", 0.0028f);
    shader.setVec3(gl, "pointLights[3].ambient", light.getMaterial().getAmbient("point"));
    shader.setVec3(gl, "pointLights[3].diffuse", light.getMaterial().getDiffuse("point"));
    shader.setVec3(gl, "pointLights[3].specular", light.getMaterial().getSpecular("point"));

    shader.setVec3(gl, "pointLights[4].position", light.getPosition(4));
    shader.setFloat(gl, "pointLights[4].constant", 1.0f);
    shader.setFloat(gl, "pointLights[4].linear", 0.027f);
    shader.setFloat(gl, "pointLights[4].quadratic", 0.0028f);
    shader.setVec3(gl, "pointLights[4].ambient", light.getMaterial().getAmbient("point"));
    shader.setVec3(gl, "pointLights[4].diffuse", light.getMaterial().getDiffuse("point"));
    shader.setVec3(gl, "pointLights[4].specular", light.getMaterial().getSpecular("point"));

    shader.setVec3(gl, "pointLights[5].position", light.getPosition(5));
    shader.setFloat(gl, "pointLights[5].constant", 1.0f);
    shader.setFloat(gl, "pointLights[5].linear", 0.027f);
    shader.setFloat(gl, "pointLights[5].quadratic", 0.0028f);
    shader.setVec3(gl, "pointLights[5].ambient", light.getMaterial().getAmbient("point"));
    shader.setVec3(gl, "pointLights[5].diffuse", light.getMaterial().getDiffuse("point"));
    shader.setVec3(gl, "pointLights[5].specular", light.getMaterial().getSpecular("point"));

    //SpotLight
    shader.setVec3(gl, "spotLight.position", light.getSpotLightPosition());
    shader.setVec3(gl, "spotLight.direction", light.getSpotLightDirection());
    shader.setFloat(gl, "spotLight.cutOff", (float)Math.cos(0.1f));
    shader.setFloat(gl, "spotLight.outerCutOff", (float)Math.cos(0.3f));
    shader.setFloat(gl, "spotLight.constant", 1.0f);
    shader.setFloat(gl, "spotLight.linear", 0.022f);
    shader.setFloat(gl, "spotLight.quadratic", 0.0019f);
    shader.setVec3(gl, "spotLight.ambient", new Vec3(0.1f, 0.1f, 0.1f));
    shader.setVec3(gl, "spotLight.diffuse", new Vec3(0.8f, 0.8f, 0.8f));
    shader.setVec3(gl, "spotLight.specular", new Vec3(1.0f, 1.0f, 1.0f));

    shader.setVec3(gl, "material.ambient", material.getAmbient("main"));
    shader.setVec3(gl, "material.diffuse", material.getDiffuse("main"));
    shader.setVec3(gl, "material.specular", material.getSpecular("main"));
    shader.setFloat(gl, "material.shininess", material.getShininess());

    shader.setInt(gl, "material.diffuse", 0);  // be careful to match these with GL_TEXTURE0 and GL_TEXTURE1
    shader.setInt(gl, "material.specular", 1);
  }

  protected void fillBuffers(GL3 gl) {
    gl.glGenVertexArrays(1, vertexArrayId, 0);
    gl.glBindVertexArray(vertexArrayId[0]);
    gl.glGenBuffers(1, vertexBufferId, 0);
    gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vertexBufferId[0]);
    FloatBuffer fb = Buffers.newDirectFloatBuffer(vertices);

    gl.glBufferData(GL.GL_ARRAY_BUFFER, Float.BYTES * vertices.length, fb, GL.GL_STATIC_DRAW);

    int stride = vertexStride;
    int numXYZFloats = vertexXYZFloats;
    int offset = 0;
    gl.glVertexAttribPointer(0, numXYZFloats, GL.GL_FLOAT, false, stride*Float.BYTES, offset);
    gl.glEnableVertexAttribArray(0);

    int numNormalFloats = vertexNormalFloats; // x,y,z for each vertex
    offset = numXYZFloats*Float.BYTES;  // the normal values are three floats after the three x,y,z values
                                    // so change the offset value
    gl.glVertexAttribPointer(1, numNormalFloats, GL.GL_FLOAT, false, stride*Float.BYTES, offset);
                                    // the vertex shader uses location 1 (sometimes called index 1)
                                    // for the normal information
                                    // location, size, type, normalize, stride, offset
                                    // offset is relative to the start of the array of data
    gl.glEnableVertexAttribArray(1);// Enable the vertex attribute array at location 1

    // now do the texture coordinates  in vertex attribute 2
    int numTexFloats = vertexTexFloats;
    offset = (numXYZFloats+numNormalFloats)*Float.BYTES;
    gl.glVertexAttribPointer(2, numTexFloats, GL.GL_FLOAT, false, stride*Float.BYTES, offset);
    gl.glEnableVertexAttribArray(2);

    gl.glGenBuffers(1, elementBufferId, 0);
    IntBuffer ib = Buffers.newDirectIntBuffer(indices);
    gl.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, elementBufferId[0]);
    gl.glBufferData(GL.GL_ELEMENT_ARRAY_BUFFER, Integer.BYTES * indices.length, ib, GL.GL_STATIC_DRAW);
    gl.glBindVertexArray(0);
  }

  // public abstract void display(int indent);

  public abstract void render(GL3 gl, Mat4 model);

  public void render(GL3 gl) {
    render(gl, model);
  }

  //public abstract void render(GL3 gl, Light light, Vec3 viewPosition, Mat4 perspective, Mat4 view);
  /*public void render(GL3 gl, Light light, Vec3 viewPosition, Mat4 perspective, Mat4 view) {
    setViewPosition(viewPosition);
    setView(view);
    setPerspective(perspective);
    setLight(light);
    render(gl, this.model);
  }
  */


}
