package me.rtn.renderengine;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 26-Apr-17 on Apr at 2:02 AM.
 */
public class Loader {

    //used to store and removed vaos from the systems memory
    private List<Integer> vaos = new ArrayList<Integer>();
    private List<Integer> vbos = new ArrayList<Integer>();

    //loading the vao to the screen
    public RawModel loadToVAO(float[] positions){
        int vaoID = createVAO();
        storeAttributeDataList(0, positions);
        unbindVAO();
        return new RawModel(vaoID, positions.length / 3);
    }

    private int createVAO(){
        int vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);
        vaos.add(vaoID);
        return vaoID;
    }
        //storing any data we need in this list with bounded vao
    private void storeAttributeDataList(int attributeNumber, float[] data){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0); //should unbind the current vao
    }
    private void unbindVAO(){
        //unbinds the vao
        GL30.glBindVertexArray(0);
    }
    //some data needs to be stored in a float, this is what this does
    private FloatBuffer storeDataInFloatBuffer(float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
