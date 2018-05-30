package com.space.invaders.Models.ship;

import com.badlogic.gdx.physics.box2d.*;
import com.space.invaders.Models.shot.DefaultBullet;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.services.factory.BodyFactory;

import java.util.Map;

public class WhiteShip extends Ship {

    public WhiteShip(float X, float Y, SpaceInvaders game) {
        super(X, 0, game);
        this.setHEIGHT(90);
        this.world = g.getWorld();
        createBody();
    }

    @Override
    public void loadTexture() {
        this.setShipTexture(g.getTexture("ships/whiteShip.png"));
    }


}

