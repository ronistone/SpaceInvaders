package com.space.invaders.models.ship;

import com.space.invaders.controllers.SpaceInvaders;

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

