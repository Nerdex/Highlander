package me.rtn.renderengine.textures;

/**
 * Created by George on 28-Apr-17 on Apr at 12:08 AM.
 */
public class ModelTexture {

    private int textureID;

    private float shineDamper = 1;
    private float relfectivity = 0;

    public float getShineDamper() {
        return shineDamper;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    public float getRelfectivity() {
        return relfectivity;
    }

    public void setRelfectivity(float relfectivity) {
        this.relfectivity = relfectivity;
    }

    public ModelTexture(int id){
        this.textureID = id;
    }

    public int getTextureID() {
        return textureID;
    }
}
