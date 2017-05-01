package me.rtn.renderengine.shaders;


import javax.vecmath.Matrix4f;

/**
 * Created by George on 26-Apr-17 on Apr at 10:38 PM.
 */
public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/me/rtn/renderengine/shaders/vertexShader";
    private static final String FRAG_FILE = "src/me/rtn/renderengine/shaders/vertexShader";

    private int location_transformationMatrix;

    public StaticShader() {
        super(VERTEX_FILE, FRAG_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadUiMatrix(location_transformationMatrix, matrix);
    }
}
