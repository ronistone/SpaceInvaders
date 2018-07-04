package com.space.invaders.services.movement;

import com.badlogic.gdx.math.Vector2;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.models.ObserverMovement;
import com.space.invaders.models.ship.Ship;
import com.space.invaders.util.MathUtil;
import com.space.invaders.view.screen.BaseScreen;

public class FollowMovementService extends MovementService implements ObserverMovement{

    private Vector2 destiny;
    private Vector2 current;
    private Vector2 v;

    public FollowMovementService(){}


    @Override
    public void move(Ship ship) {
        v = ship.body.getLinearVelocity();
        if(Math.abs(v.x) < MathUtil.VELOCITY_EPS*10 &&
                Math.abs(v.y) < MathUtil.VELOCITY_EPS*10 ){
            current = ship.getPosition();
            v.x = (destiny.x - current.x) * ship.getSPEED();
            v.y = (BaseScreen.VIRTUAL_HEIGHT/2 - current.y)*ship.getSPEED();
            ship.body.applyForce(v, current, true);
        }
    }

    @Override
    public void update(Vector2 position) {
        destiny = position;
        destiny.x = BaseScreen.convertToPixel(destiny.x);
        destiny.y = BaseScreen.convertToPixel(destiny.y);
    }

    @Override
    public MovementService init(SpaceInvaders game) {
        v = new Vector2();
        current = new Vector2();
        destiny = new Vector2();
        g = game;
        game.getPlayer().register(this);
        return this;
    }
}
