package me.rtn.renderengine.entities;

import org.lwjgl.input.Keyboard;

import javax.vecmath.Vector3f;

/**
 * Created by George on 02-May-17 on May at 1:12 AM.
 */
public class Camera {

    private Vector3f position = new Vector3f(0,0,0);
    private float pitch;
    private float yaw;
    private float roll;

    public Camera(Vector3f position, float pitch, float yaw, float roll) {
        this.position = position;
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public void move(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            position.x -= 0.02F;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            position.z += 0.02F;
        }
         if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            position.x -= 0.02F;
         }
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }
}
