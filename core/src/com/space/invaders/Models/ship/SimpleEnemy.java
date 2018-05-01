package com.space.invaders.Models.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.space.invaders.Models.shot.Shot;
import com.space.invaders.controllers.SpaceInvaders;

public class SimpleEnemy extends Ship{

    public SimpleEnemy(float X, float Y, SpaceInvaders game) {
        super(X, Y, game);
    }

    @Override
    public void loadTexture() {
        this.setShipTexture(g.getTexture("shipEnemy.png"));
    }

    @Override
    public Shot shot() {
        return null;
    }
}
