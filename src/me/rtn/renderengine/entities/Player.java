package me.rtn.renderengine.entities;/*
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

import me.rtn.renderengine.DisplayManager;
import me.rtn.renderengine.models.TexturedModel;
import org.lwjgl.input.Keyboard;

import javax.vecmath.Vector3f;

public class Player extends Entity {

    private final float RUN_SPEED = 10;
    private final float TURN_SPEED = 180;

    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;

    public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }

    public void move(){
        checkInputs();
        super.increateRotation(0, currentSpeed * DisplayManager.getFrameTimeSeconds(), 0);
        float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
        float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
        float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotZ())));
    }

    private void checkInputs(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            this.currentSpeed = RUN_SPEED;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)){
            this.currentSpeed = - RUN_SPEED;
        } else {
            this.currentSpeed = 0;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            this.currentSpeed = - TURN_SPEED;
        } else if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            this.currentSpeed = TURN_SPEED;
        } else {
            this.currentSpeed = 0;
        }
    }
}
