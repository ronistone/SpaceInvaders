package com.space.invaders.Models.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.space.invaders.Models.shot.Shot;
import com.space.invaders.controllers.SpaceInvaders;

public class BlackShip extends Ship {

    public BlackShip(float X, float Y, SpaceInvaders game) {
        super(X, Y, game);
    }

    @Override
    public void loadTexture() {
        this.setShipTexture(g.getTexture("ships/blackShip.png"));
    }

    @Override
    public void move() {
        if(movement != null){
            movement.move(this);
        }
    }

    @Override
    public Shot shot() {
        return null;
    }
}
