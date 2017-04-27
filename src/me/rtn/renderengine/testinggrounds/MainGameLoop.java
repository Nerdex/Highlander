package me.rtn.renderengine.testinggrounds;

import kotlin.Unit;
import me.rtn.renderengine.DisplayManager;
import me.rtn.renderengine.Loader;
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

        float[] verticies = {
                //left triangle bottom
                -0.5F, 0.5F, 0F,
                -0.5F, -0.5F, 0F,
                0.5F, -0.5F, 0f,
                //right triangle
                0.5F, -0.5F, 0f,
                0.5F, 0.5F, 0f,
                -0.5F, 0.5F, 0f
                //this actually renders as a quad lol
        };
        int[] indicies = {
                //V0, V1, V3
                0,1,3,
                //V3,V1,V2
                3,1,2
        };

        float[] texturedCoords = {
                0,0, //V0
                0,1, //V1
                1,1, //V2
                1,0  //V3
        };

        //Defining the fucking models
        RawModel model = loader.loadToVAO(verticies, texturedCoords, indicies); //TODO: figure out why this is throwing a compile error
        ModelTexture texture = new ModelTexture(loader.loadTexture("image"));
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
