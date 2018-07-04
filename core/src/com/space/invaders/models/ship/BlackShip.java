package com.space.invaders.models.ship;

import com.space.invaders.controllers.SpaceInvaders;

public class BlackShip extends Ship {

    public BlackShip(){super();}
    public BlackShip(float X, float Y, SpaceInvaders game) {
        this(X,Y,game,false);
    }

    public BlackShip(float X, float Y, SpaceInvaders game, boolean isPlayer) {
        super(X, Y, game, isPlayer);
    }

    @Override
    public void loadTexture() {
        this.setShipTexture(g.getTexture("ships/blackShip.png"));
    }


}
