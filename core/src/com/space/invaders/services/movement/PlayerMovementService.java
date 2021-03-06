package com.space.invaders.services.movement;

import com.badlogic.gdx.Gdx;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.models.ship.Ship;
import com.space.invaders.view.screen.BaseScreen;

public class PlayerMovementService extends MovementService {

    private float move;

    @Override
    public void move(Ship ship) {
        move = Gdx.input.getAccelerometerX();
        if((ship.body.getPosition().x +ship.getWIDTH()/2 < BaseScreen.VIRTUAL_WIDHT || move > 0) &&
                (ship.body.getPosition().x - ship.getWIDTH()/2 > 0 || move < 0)) {
            ship.body.setLinearVelocity(-move * ship.getLATERAL_SPEED(), 0f);
        }else{
            ship.body.setLinearVelocity(0f, 0f);
        }
    }
}
