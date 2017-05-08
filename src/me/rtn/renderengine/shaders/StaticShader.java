package me.rtn.renderengine.shaders;


import me.rtn.renderengine.entities.Camera;
import me.rtn.renderengine.entities.Light;
import me.rtn.renderengine.utils.Maths;

import javax.vecmath.Matrix4f;

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
public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/me/rtn/renderengine/shaders/vertexShader";
    private static final String FRAG_FILE = "src/me/rtn/renderengine/shaders/fragmentShader";

    private int location_transformationMatrix;
    private int location_projectMatrix;
    private int location_viewMatrix;
    private int location_lightPosition;
    private int location_lightColour;
    private int location_shineDamper;
    private int location_reflectivity;

    public StaticShader() {
        super(VERTEX_FILE, FRAG_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_lightColour = super.getUniformLocation("lightColour");
        location_lightPosition = super.getUniformLocation("lightPosition");
        location_shineDamper = super.getUniformLocation("shineDamper");
        location_reflectivity = super.getUniformLocation("reflectivity");
    }

    public void loadShine(float damper, float reflectivity){
        super.loadUiFloat(location_shineDamper, damper);
        super.loadUiFloat(location_reflectivity, reflectivity);
    }

    public void loadLight(Light light){
        super.loadUiVector(location_lightColour, light.getColour());
        super.loadUiVector(location_lightPosition, light.getPosition());
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
        super.bindAttribute(2, "normals");
    }

    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadUiMatrix(location_viewMatrix, viewMatrix);
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadUiMatrix(location_transformationMatrix, matrix);
    }

    public void loadProjectMatrix(Matrix4f projection){
        super.loadUiMatrix(location_projectMatrix, projection);
    }
}
