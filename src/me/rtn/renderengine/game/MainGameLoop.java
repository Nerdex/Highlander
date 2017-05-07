package me.rtn.renderengine.game;

import me.rtn.renderengine.DisplayManager;
import me.rtn.renderengine.Loader;
import me.rtn.renderengine.OBJLoader;
import me.rtn.renderengine.entities.Camera;
import me.rtn.renderengine.entities.Entity;
import me.rtn.renderengine.entities.Light;
import me.rtn.renderengine.MasterRenderer;
import me.rtn.renderengine.models.RawModel;
import me.rtn.renderengine.models.TexturedModel;
import me.rtn.renderengine.shaders.StaticShader;
import org.lwjgl.opengl.Display;

import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by George on 25-Apr-17 on Apr at 11:31 PM.
 */
public class MainGameLoop {

    public static void main(String[] args) {

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        StaticShader shader = new StaticShader();

        RawModel model = OBJLoader.loadObjModel("dragon", loader);
        TexturedModel cubeModel = new TexturedModel(model, new TexturedModel(loader.loadTexture("image.png")));

        Light light = new Light(new Vector3f(3000, 2000, 3000), new Vector3f(1,1,1));

        Camera camera = new Camera();

        List<Entity> allCubes = new ArrayList<Entity>();
        Random random = new Random();

        for(int i = 0; i < 200; i++){
            float x = random.nextFloat() * 100 - 50;
            float y = random.nextFloat() * 100 - 50;
            float z = random.nextFloat() * -300;
            allCubes.add(new Entity(cubeModel, new Vector3f(x, y, z), random.nextFloat() * 180F, random.nextFloat() * 180F, 0F, 1F));
        }
        MasterRenderer render = new MasterRenderer();
        while (!Display.isCloseRequested()){
            camera.move();

            for(Entity cube : allCubes){
                render.processEntities(cube);

            }
            render.render(light, camera);
            DisplayManager.updateDisplay();
        }
        render.cleanUp();
        loader.cleaner();
        DisplayManager.disposeDisply();
    }
}
