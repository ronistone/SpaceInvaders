package com.space.invaders.services.movement;

import com.badlogic.gdx.math.Vector2;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.models.ship.Ship;
import com.space.invaders.util.MathUtil;
import com.space.invaders.view.screen.BaseScreen;

import java.util.Random;

public class FoolMovementService extends MovementService {

    private Vector2 destiny, v, current;
    private Random random;
    private static float MIN = BaseScreen.convertToPPM(150);

    public FoolMovementService(){
        random = new Random();
        destiny = new Vector2();
    }

    @Override
    public void move(Ship ship) {
        v = ship.body.getLinearVelocity();
        if(Math.abs(v.x) < MathUtil.VELOCITY_EPS &&
            Math.abs(v.y) < MathUtil.VELOCITY_EPS){
            v.x = getXInScreen();
            v.y = getYInScreen();
            destiny.set(v.x,v.y);
            current = ship.body.getPosition();

            v.x = (v.x - current.x)*ship.getSPEED();
            v.y = (v.y - current.y)*ship.getSPEED();

            ship.body.applyForce(v, current, true);
        }
    }

    private float getXInScreen(){
        return (float) ((float) - BaseScreen.VIRTUAL_WIDHT/4 + (random.nextFloat() * BaseScreen.VIRTUAL_WIDHT * 1.5));
    }

    private float getYInScreen(){
        return MIN + random.nextFloat() * (BaseScreen.VIRTUAL_HEIGHT - MIN);
    }

    private double dist(Vector2 current){
        return Math.sqrt((current.x - destiny.x)*(current.x - destiny.x) +
                         (current.y - destiny.y)*(current.y - destiny.y));
    }

}
