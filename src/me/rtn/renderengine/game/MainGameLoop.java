package me.rtn.renderengine.game;

import me.rtn.renderengine.DisplayManager;
import me.rtn.renderengine.Loader;
import me.rtn.renderengine.MasterRenderer;
import me.rtn.renderengine.OBJLoader;
import me.rtn.renderengine.entities.Camera;
import me.rtn.renderengine.entities.Entity;
import me.rtn.renderengine.entities.Light;
import me.rtn.renderengine.models.RawModel;
import me.rtn.renderengine.models.TexturedModel;
import me.rtn.renderengine.terrain.Terrain;
import me.rtn.renderengine.textures.ModelTexture;
import org.lwjgl.opengl.Display;

import javax.vecmath.Vector3f;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/*
 * 3DGa,e
 * Copyright (C) 2017 RapidTheNerd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class MainGameLoop {

    public static void main(String[] args) throws FileNotFoundException {

        DisplayManager.createDisplay();
        Loader loader = new Loader();


        RawModel model = OBJLoader.loadObjModel("tree", loader);

        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("tree")));

        TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grass", loader), new ModelTexture(loader.loadTexture("grassTexture")));
        grass.getTexture().setTransparent(true);
        TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern", loader), new ModelTexture(loader.loadTexture("fern")));
        fern.getTexture().setTransparent(true);
        
        ArrayList<Entity> entities = new ArrayList<Entity>();
        Random random = new Random();
        for(int i=0;i<500;i++){
            entities.add(new Entity(staticModel, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,3));
            entities.add(new Entity(grass, new Vector3f(random.nextFloat() * 800 - 400, 0, random.nextFloat()  * -600), 0,0,0,1));
            entities.add(new Entity(fern, new Vector3f(random.nextFloat() * 800 - 400, 0, random.nextFloat() * - 600), 0,0,0,0.6F)));
        }


        Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));

        Terrain terrain = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("grass")));
        Terrain terrain2 = new Terrain(1,0,loader,new ModelTexture(loader.loadTexture("grass")));

        Camera camera = new Camera();
        MasterRenderer renderer = new MasterRenderer();

        while(!Display.isCloseRequested()){
            camera.move();

            renderer.processTerrains(terrain);
            renderer.processTerrains(terrain2);
            for(Entity entity:entities){
                renderer.processEntities(entity);
            }
            renderer.render(light, camera);
            DisplayManager.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleaner();
        DisplayManager.disposeDisply();

    }

}