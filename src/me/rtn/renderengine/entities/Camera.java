package me.rtn.renderengine.entities;

import org.lwjgl.input.Keyboard;

import javax.vecmath.Vector3f;

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
public class Camera {

    private Vector3f position = new Vector3f(0,0,0);
    private float pitch;
    private float yaw;

    public Camera() {
        this.position = position;
        this.pitch = pitch;
        this.yaw = yaw;
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

}
