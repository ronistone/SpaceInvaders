package com.space.invaders.Models.ship;

import com.badlogic.gdx.physics.box2d.*;
import com.space.invaders.Models.shot.DefaultBullet;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.Views.BaseScreen;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.services.factory.BodyFactory;

import java.util.Map;

public class WhiteShip extends Ship {

    public WhiteShip(){
        super();
    }
    public WhiteShip(float X, float Y, SpaceInvaders game) {
        this(X,Y,game,false);
    }

    public WhiteShip(float X, float Y, SpaceInvaders game, boolean isPlayer) {
        super(X, Y, game, isPlayer);
    }

    @Override
    public void loadTexture() {
        this.setShipTexture(g.getTexture("ships/whiteShip.png"));
    }


}

