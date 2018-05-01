package com.space.invaders.services.movement;

import com.badlogic.gdx.Gdx;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Views.BaseScreen;

public class PlayerMovementService extends MovementService {


    @Override
    public void move(float delta, Ship ship) {
        float accelX = Gdx.input.getAccelerometerX();

        if(accelX < 0){
            ship.moveRight(delta, Math.abs(accelX)/10, BaseScreen.VIRTUAL_WIDHT);
        }else{
            ship.moveLeft(delta, Math.abs(accelX)/10, 0);
        }
    }
}
