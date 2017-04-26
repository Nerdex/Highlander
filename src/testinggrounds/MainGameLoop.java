package testinggrounds;

import me.rtn.renderengine.DisplayManager;
import me.rtn.renderengine.Loader;
import me.rtn.renderengine.RawModel;
import me.rtn.renderengine.Renderer;
import org.lwjgl.opengl.Display;
/**
 * Created by George on 25-Apr-17 on Apr at 11:31 PM.
 */
public class MainGameLoop {

    public static void main(String[] args){

        Loader loader = new Loader();
        Renderer render = new Renderer();

        float[] v = {
                //left triangle bottom
                -0.5F, 0.5F, 0F,
                -0.5F, -0.5F, 0F,
                0.5F, -0.5F, 0f,
                //right triangle
                0.5F, -0.5F, 0f,
                0.5F, 0.5F, 0f,
                -0.5F, 0.5F, 0f
        };

        RawModel model = loader.loadToVAO(v);

        DisplayManager.createDisplay();

        while(!Display.isCloseRequested()){
            render.prepare();
            DisplayManager.updateDisplay();
        }
        loader.cleaner();
        //closing display when requested
        DisplayManager.disposeDisply();
    }
}
