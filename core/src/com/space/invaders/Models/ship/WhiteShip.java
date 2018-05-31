package com.space.invaders.Models.ship;

import com.badlogic.gdx.physics.box2d.*;
import com.space.invaders.Models.shot.DefaultBullet;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.Views.BaseScreen;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.services.factory.BodyFactory;

import java.util.Map;

public class WhiteShip extends Ship {

    public WhiteShip(float X, float Y, SpaceInvaders game) {
        super(X, Y, game);
        this.setHEIGHT(BaseScreen.convertToPPM(90));
        this.world = g.getWorld();
        createBody();
    }

    @Override
    public void loadTexture() {
        this.setShipTexture(g.getTexture("ships/whiteShip.png"));
    }


}

