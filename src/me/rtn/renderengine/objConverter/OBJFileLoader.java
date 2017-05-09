package me.rtn.renderengine.objConverter;/*
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

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OBJFileLoader {

    private static final String RES_LOC = "res/";

    public static ModelData loadOBJ(String objFileName){
        FileReader reader = null;
        File objFile = new File(RES_LOC + objFileName + ".obj");
        try{
            reader = new FileReader(objFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bReader = new BufferedReader(reader);
        String line;
        List<Vertex> vertices = new ArrayList<Vertex>();
        List<Vector2f> textures = new ArrayList<Vector2f>();
        List<Vector3f> normals = new ArrayList<Vector3f>();
        List<Integer> indices = new ArrayList<Integer>();
        try {
            while(true){
                line = bReader.readLine();
                if(line.startsWith("v ")){
                    String[] currentLine = line.split(" ");
                    Vector3f vertex = new Vector3f((float) Float.valueOf(currentLine[1]),
                            (float) Float.valueOf(currentLine[2]),
                            (float) Float.valueOf(currentLine[3]));
                    Vertex newVertex = new Vertex(vertices.size(), vertex);
                    vertices.add(newVertex);
                } else if(line.startsWith("vt ")){
                    String[] currentLine = line.split(" ");
                    Vector3f normal = new Vector3f((float) Float.valueOf(currentLine[1]),
                            (float) Float.valueOf(currentLine[2]),
                            (float) Float.valueOf(currentLine[3]));
                    normals.add(normal);
                } else if(line.startsWith("f ")){
                    break;
                }
            }
            while(line != null && line.startsWith("f ")){
                String[] currentLine = line.split(" ");
                String[] vertex1 = currentLine[1].split("/");
                String[] vertex2 = currentLine[2].split("/");
                String[] vertex3 = currentLine[3].split("/");

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processVertex(String[] vertex, List<Vertex> vertices, List<Integer> indices){
        int index = Integer.parseInt(vertex[0]) - 1;
        Vertex currnetVertex = vertices.get(index);
        int textureIndex = Integer.parseInt(vertex[1]) - 1;
        int normalIndex = Integer.parseInt(vertex[2]) - 1;
        if(!currnetVertex.isSet()){
            currnetVertex.setTextureIndex(textureIndex);
            currnetVertex.setNormalIndex(normalIndex);
            indices.add(index);
        } else {
            
        }
    }
}
