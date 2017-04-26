package testinggrounds;

import me.rtn.renderengine.DisplayManager;
import me.rtn.renderengine.Loader;
import me.rtn.renderengine.RawModel;
import me.rtn.renderengine.Renderer;
import me.rtn.renderengine.shaders.StaticShader;
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

        float[] v = {
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


        RawModel model = loader.loadToVAO(v, indicies);

        while(!Display.isCloseRequested()){
            render.prepare();
            shader.start();
            render.render(model);
            shader.stop();
            DisplayManager.updateDisplay();
        }
        shader.clean();
        loader.cleaner();
        DisplayManager.disposeDisply();
    }
}
