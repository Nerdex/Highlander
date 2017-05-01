package me.rtn.renderengine.testinggrounds;

import kotlin.Unit;
import me.rtn.renderengine.DisplayManager;
import me.rtn.renderengine.Loader;
import me.rtn.renderengine.OBJLoader;
import me.rtn.renderengine.models.RawModel;
import me.rtn.renderengine.Renderer;
import me.rtn.renderengine.models.TexturedModel;
import me.rtn.renderengine.shaders.StaticShader;
import me.rtn.renderengine.textures.ModelTexture;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.Display;
/**
 * Created by George on 25-Apr-17 on Apr at 11:31 PM.
 */
public class MainGameLoop {

    public static void main(String[] args){

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        Renderer render = new Renderer();
        StaticShader shader = new StaticShader();



        //Defining the fucking models
        RawModel model = OBJLoader.loadObjModel("stall", loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));
        TexturedModel tModel = new TexturedModel(model, texture);
        

        while(!Display.isCloseRequested()){
            render.prepare();
            shader.start();
            render.render(tModel);
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
