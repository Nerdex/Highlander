package me.rtn.renderengine.shaders;

/**
 * Created by George on 26-Apr-17 on Apr at 10:38 PM.
 */
public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/me/rtn/renderengine/shaders/vertexShader";
    private static final String FRAG_FILE = "src/me/rtn/renderengine/shaders/vertexShader";

    public StaticShader() {
        super(VERTEX_FILE, FRAG_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }
}
