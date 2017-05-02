package me.rtn.renderengine.shaders;


import me.rtn.renderengine.entities.Camera;
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

    public StaticShader() {
        super(VERTEX_FILE, FRAG_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
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
