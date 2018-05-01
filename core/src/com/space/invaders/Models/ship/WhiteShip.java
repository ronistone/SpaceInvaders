package com.space.invaders.Models.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.space.invaders.Models.shot.DefaultShot;
import com.space.invaders.Models.shot.Shot;
import com.space.invaders.controllers.SpaceInvaders;

public class WhiteShip extends Ship {

    public WhiteShip(float X, float Y, SpaceInvaders game) {
        super(X, 0, game);
        this.setHEIGHT(90);
        this.setSPEED(0);
    }

    @Override
    public void loadTexture() {
        this.setShipTexture(g.getTexture("ships/whiteShip.png"));
    }

    @Override
    public void move() {
        if(movement != null){
            movement.move(this);
        }
    }

    @Override
    public Shot shot() {
        return new DefaultShot(getX()+ (getWIDTH()/2),getY()+getHEIGHT(), g);
    }

}
