package me.rtn.renderengine.shaders;


import me.rtn.renderengine.entities.Camera;
import me.rtn.renderengine.entities.Light;
import me.rtn.renderengine.utils.Maths;

import javax.vecmath.Matrix4f;

/**
 * Created by George on 26-Apr-17 on Apr at 10:38 PM.
 */
public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/me/rtn/renderengine/shaders/vertexShader";
    private static final String FRAG_FILE = "src/me/rtn/renderengine/shaders/vertexShader";

    private int location_transformationMatrix;
    private int location_projectMatrix;
    private int location_viewMatrix;
    private int location_lightPosition;
    private int location_lightColour;
    private int location_shineDamper;
    private int location_reflectivity;

    public StaticShader() {
        super(VERTEX_FILE, FRAG_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_lightColour = super.getUniformLocation("lightColour");
        location_lightPosition = super.getUniformLocation("lightPosition");
        location_shineDamper = super.getUniformLocation("shineDamper");
        location_reflectivity = super.getUniformLocation("reflectivity");
    }

    public void loadShine(float damper, float reflectivity){
        super.loadUiFloat(location_shineDamper, damper);
        super.loadUiFloat(location_reflectivity, reflectivity);
    }

    public void loadLight(Light light){
        super.loadUiVector(location_lightColour, light.getColour());
        super.loadUiVector(location_lightPosition, light.getPosition());
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
        super.bindAttribute(2, "normals");
    }

    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadUiMatrix(location_viewMatrix, viewMatrix);
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadUiMatrix(location_transformationMatrix, matrix);
    }

    public void loadProjectMatrix(Matrix4f projection){
        super.loadUiMatrix(location_projectMatrix, projection);
    }
}
