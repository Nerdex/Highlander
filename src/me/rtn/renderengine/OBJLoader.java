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

    public static RawModel loadObjModel(String fileName, Loader loader) {
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
            while (true) {
                line = reader.readLine();
                String[] currentLine = line.split(" ");
                if (line.startsWith("v")) {
                    Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
                            Float.parseFloat(currentLine[3]));
                    verticies.add(vertex);
                } else if (line.startsWith("vt ")) {
                    Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]));
                    textures.add(texture);
                } else if (line.startsWith("vn ")) {
                    Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
                            Float.parseFloat(currentLine[3]));
                    normals.add(normal);
                } else if (line.startsWith("t ")) {
                    textureArray = new float[verticies.size() * 2];
                    normalArray = new float[verticies.size() * 3];
                    break;
                }
            }
            while (line != null) {
                if (!line.startsWith("f ")) {
                   line = reader.readLine();
                    continue;
                }
                String[] currentLine = line.split(" ");
                String[] vertex1 = currentLine[1].split("/");
                String[] vertex2 = currentLine[1].split("/");
                String[] vertex3 = currentLine[1].split("/");
                processVertex(vertex1, indicies, textures, normals, textureArray, normalArray);
                processVertex(vertex2, indicies, textures, normals, textureArray, normalArray);
                processVertex(vertex3, indicies, textures, normals, textureArray, normalArray);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        verticiesArray = new float[verticies.size() * 3];
        indiciesArray = new int[indicies.size()];
        int vertexPointer = 0;
        for(Vector3f vertex : verticies){
            verticiesArray[vertexPointer++] = vertex.x;
            verticiesArray[vertexPointer++] = vertex.y;
            verticiesArray[vertexPointer++] = vertex.z;
        }

        for(int i = 0; i < indicies.size(); i++){
            indiciesArray[i] = indicies.get(i);
        }

        return loader.loadToVAO(verticiesArray, textureArray, normalArray, indiciesArray);
    }

    private static void processVertex(String[] vertexData, List<Integer> indices, List<Vector2f> textures, List<Vector3f> normals, float[] textureArray, float[] normalsArray) {
        int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
        indices.add(currentVertexPointer);
        Vector2f currentTexture = textures.get(Integer.parseInt(vertexData[1]) - 1);
        textureArray[currentVertexPointer * 2] = currentTexture.x;
        textureArray[currentVertexPointer * 2 + 1] = 1 - currentTexture.y;
        Vector3f currentNormal = normals.get(Integer.parseInt(vertexData[2]) - 1);
        normalsArray[currentVertexPointer * 3] = currentNormal.x;
        normalsArray[currentVertexPointer * 3 + 1] = currentNormal.y;

        normalsArray[currentVertexPointer * 3 + 2] = currentNormal.z;
    }
}
