package me.rtn.renderengine.testinggrounds;

import kotlin.Unit;
import me.rtn.renderengine.DisplayManager;
import me.rtn.renderengine.Loader;
import me.rtn.renderengine.OBJLoader;
import me.rtn.renderengine.Renderer;
import me.rtn.renderengine.entities.Entity;
import me.rtn.renderengine.models.RawModel;
import me.rtn.renderengine.models.TexturedModel;
import me.rtn.renderengine.shaders.StaticShader;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.Display;

import javax.vecmath.Vector3f;

/**
 * Created by George on 25-Apr-17 on Apr at 11:31 PM.
 */
public class MainGameLoop {

    public static void main(String[] args){

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer render = new Renderer(shader);

        RawModel model = OBJLoader.loadObjModel("stall", loader);
        TexturedModel tModel = new TexturedModel(model, new TexturedModel(loader.loadTexture("stallTexture")));
        Entity entity = new Entity(tModel, new Vector3f(0,0,-50),0,0,0,1);
        while(!Display.isCloseRequested()){
            entity.increasePosition(0.002F, 0,0);
            entity.increateRotation(0.002F, 0, 0);
            render.prepare();
            shader.start();
            render.render(entity, shader);
            shader.stop();
            DisplayManager.updateDisplay();
        }
        shader.clean();
        loader.cleaner();
        DisplayManager.disposeDisply();
    }
    //ignore this shit for now
    public static void main(@NotNull Unit load) {

    }
}
