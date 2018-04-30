package com.space.invaders.Models.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class DefaultPlayer extends Ship {

    public DefaultPlayer(float X, float Y) {
        super(X, 0);
        this.setHEIGHT(90);
        this.setSPEED(0);
    }

    @Override
    public void loadTexture() {
        this.setShipTexture(new Texture(Gdx.files.internal("nave001.png")));
    }

}
