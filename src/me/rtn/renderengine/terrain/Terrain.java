package me.rtn.renderengine.terrain;/*
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

import me.rtn.renderengine.Loader;
import me.rtn.renderengine.models.RawModel;
import me.rtn.renderengine.textures.ModelTexture;

public class Terrain {

    private static final float SIZE = 1000;
    private static final int VERTEX_COUNT = 128;

    private float x;
    private float z;
    private RawModel model;
    private ModelTexture texture;

    public Terrain(int gridX, int gridZ, RawModel model, ModelTexture texture) {
        this.texture = texture;
        this.x = gridX * SIZE;
        this.z = gridZ * SIZE;
    }

    private RawModel generateTerrain(Loader loader){
        int count = VERTEX_COUNT * VERTEX_COUNT;
        float[] verticies = new float[count * 3];
        float[] normals = new float[count * 3];
        float[] textureCoords = new float[count * 2];
        int[] indicies = new int[6*(VERTEX_COUNT)*(VERTEX_COUNT - 1)];
        int vertexPointer = 0;

        for(int i = 0; i < VERTEX_COUNT; i++){
            for(int j = 0; j < VERTEX_COUNT; j++){
                
            }
        }
    }
}
