package com.space.invaders.Models.ship;

import com.badlogic.gdx.physics.box2d.*;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.services.factory.BodyFactory;

import java.util.Map;

public class BlackShip extends Ship {

    public BlackShip(float X, float Y, SpaceInvaders game) {
        super(X, Y, game);
        this.world = g.getWorld();
        createBody();
    }

    @Override
    public void loadTexture() {
        this.setShipTexture(g.getTexture("ships/blackShip.png"));
    }


}
