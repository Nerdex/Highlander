package me.rtn.renderengine;

import org.lwjgl.opengl.GL30;

/**
 * Created by George on 26-Apr-17 on Apr at 2:02 AM.
 */
public class Loader {

    public RawModel loadToVAO(float[] positions){
        int vaoID = createVAO();
        storeAttributeDataList(0, positions);
        unbindVAO();
        return new RawModel(vaoID, positions.length / 3);
    }

    private int createVAO(){
        int vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    private void storeAttributeDataList(int attributeNumber, float[] data){

    }
    private void unbindVAO(){

    }
}
