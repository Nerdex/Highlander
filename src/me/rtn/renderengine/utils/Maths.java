package me.rtn.renderengine.utils;

import me.rtn.renderengine.entities.Camera;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

/**
 * Created by George on 01-May-17 on May at 3:51 PM.
 */
public class Maths {

    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale){
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        matrix.setTranslation(translation);
        matrix.rotX(ry);
        matrix.rotX(rx);
        matrix.rotZ(rz);
        matrix.setScale(scale);
        return matrix;
    }

    public Matrix4f createViewMatrix(Camera camera){
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.setIdentity();
        viewMatrix.rotX(1);
        viewMatrix.rotY(-1);
        viewMatrix.rotZ(0);
        Vector3f cameraPos = camera.getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        viewMatrix.setTranslation(negativeCameraPos);
        return viewMatrix;
    }
}
