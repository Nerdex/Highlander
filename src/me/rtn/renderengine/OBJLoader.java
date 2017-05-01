package me.rtn.renderengine;

import me.rtn.renderengine.models.RawModel;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 01-May-17 on May at 11:48 PM.
 */
public class OBJLoader {

    public static RawModel loadObjModel(String fileName, Loader loader){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File("me/rtn/models/3d" + fileName + ".obj"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fileReader);
        String line;
        List<Vector3f> verticies = new ArrayList<Vector3f>();
        List<Vector2f> textures = new ArrayList<Vector2f>();
        List<Vector3f> normals = new ArrayList<Vector3f>();
        List<Integer> indicies = new ArrayList<Integer>();
        float[] verticiesArray = null;
        float[] normalArray = null;
        float[] textureArray = null;
        int[] indiciesArray = null;
        try {
            while(true){
                line = reader.readLine();
                String[] currentLine = line.split(" ");
                if(line.startsWith("v")){
                    Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
                            Float.parseFloat(currentLine[3]));
                    verticies.add(vertex);
                } else if(line.startsWith("vt ")){
                    Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]));
                    textures.add(texture);
                } else if(line.startsWith("vn ")){
                    Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
                            Float.parseFloat(currentLine[3]));
                    normals.add(normal);
                } else if(line.startsWith("t ")){
                    textureArray = new float[verticies.size() * 2];
                    normalArray = new float[verticies.size() * 3];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
