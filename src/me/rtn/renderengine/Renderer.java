package me.rtn.renderengine;

import me.rtn.renderengine.entities.Entity;
import me.rtn.renderengine.models.RawModel;
import me.rtn.renderengine.models.TexturedModel;
import me.rtn.renderengine.shaders.StaticShader;
import me.rtn.renderengine.textures.ModelTexture;
import me.rtn.renderengine.utils.Maths;
import org.lwjgl.opengl.*;

import javax.vecmath.Matrix4f;
import java.util.List;
import java.util.Map;

/**
 * Created by George on 26-Apr-17 on Apr at 2:14 AM.
 */
public class Renderer {

    private final float FOV = 100;
    private final float NEAR_PLANE = 0.01F;
    private final float FAR_PLANE = 100;
    private Matrix4f projectionMatrix;
    private StaticShader staticShader;

    public Renderer(StaticShader shader){
        this.staticShader = shader;
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        createProjectionMatrix();
        shader.start();
        shader.loadProjectMatrix(projectionMatrix);
        shader.stop();
    }

    //clearing the previous frames of any colours left behind
    public void prepare(){
        GL11.glClearColor(1,0,0,1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    //rendering the models
    public void render(Map<TexturedModel, List<Entity>> entities){
        for(TexturedModel model : entities.keySet()){
            prepareTexturedModels(model);
            List<Entity> batch = entities.get(model);
            for(Entity entity : batch){
                prepareInstance(entity);
                GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            }
            unbindTextureModel();
        }

    }

    public void prepareTexturedModels(TexturedModel model){
        RawModel rawModel = model.getRawModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        ModelTexture texture = model.getTexture();
        staticShader.loadShine(texture.getShineDamper(), texture.getRelfectivity());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
    }

    public void unbindTextureModel(){
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    public void prepareInstance(Entity entity){
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(),
                entity.getRotZ(), entity.getScale());
        staticShader.loadTransformationMatrix(transformationMatrix);
    }

    private void createProjectionMatrix(){
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float yScale = (float) (1f / Math.tan(Math.toRadians(FOV / 2F)) * aspectRatio);
        float xScale = yScale / aspectRatio;
        float frustum_length = FAR_PLANE / NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = xScale;
        projectionMatrix.m11 = yScale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE / FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
    }
}
