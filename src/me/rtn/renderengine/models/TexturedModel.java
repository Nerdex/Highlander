package me.rtn.renderengine.models;

import me.rtn.renderengine.textures.ModelTexture;

/**
 * Created by George on 28-Apr-17 on Apr at 12:09 AM.
 */
public class TexturedModel {

    private RawModel model;
    private ModelTexture texture;

    public TexturedModel(RawModel model, ModelTexture texture){
        this.model = model;
        this.texture = texture;
    }

    public RawModel getRawModel(){
        return model;
    }

    public ModelTexture getTexture() {
        return texture;
    }

    public RawModel getModel() {
        return model;
    }

    public void setModel(RawModel model) {
        this.model = model;
    }

    public void setTexture(ModelTexture texture) {
        this.texture = texture;
    }

}
