package com.space.invaders.Models.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SimpleEnemy extends Ship{

    public SimpleEnemy(float X, float Y) {
        super(X, Y);
    }

    @Override
    public void loadTexture() {
        this.setShipTexture(new Texture(Gdx.files.internal("shipEnemy.png")));
    }
}
