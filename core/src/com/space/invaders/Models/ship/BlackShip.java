package com.space.invaders.Models.ship;

import com.space.invaders.Models.shot.Bullet;
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
    public void move(float delta) {
        if(movement != null){
            movement.move(delta, this);
        }
    }

    @Override
    public Bullet shoot() {
        return null;
    }
}
