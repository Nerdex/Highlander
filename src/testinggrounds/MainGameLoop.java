package testinggrounds;

import me.rtn.renderengine.DisplayManager;
import org.lwjgl.opengl.Display;
/**
 * Created by George on 25-Apr-17 on Apr at 11:31 PM.
 */
public class MainGameLoop {

    public static void main(String[] args){


        DisplayManager.createDisplay();

        while(!Display.isCloseRequested()){

            DisplayManager.updateDisplay();
        }
        //closing display when requested
        DisplayManager.disposeDisply();
    }
}
