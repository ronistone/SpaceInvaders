package com.space.invaders.Models.ship;

import com.space.invaders.Models.shot.DefaultBullet;
import com.space.invaders.Models.shot.Bullet;
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
    public void move(float delta) {
        if(movement != null){
            movement.move(delta, this);
        }
    }

    @Override
    public Bullet shoot() {
        return new DefaultBullet(getX()+ (getWIDTH()/2),getY()+getHEIGHT(), g);
    }

}
