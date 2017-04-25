package me.rtn.renderengine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

/**
 * Created by George on 25-Apr-17 on Apr at 10:17 PM.
 */
public class DisplayManager {

    private final int WIDTH = 1280;
    private final int HEIGHT = 720;
    private final int FPS_CAP = 120;

    public static void createDisplay(){
        ContextAttribs attribs = new ContextAttribs(4 ,4);
        attribs.withForwardCompatible(true);
        attribs.withProfileCore(true);
        Display.setTitle("Random fucking game");
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create(new PixelFormat(), attribs);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        GL11.glViewport(0, 0, WIDTH,HEIGHT);
    }

    public static void updateDisplay(){
        Display.sync(FPS_CAP);
        Display.update();
    }

    public static void disposeDisply(){
        Display.destroy();
    }
}
