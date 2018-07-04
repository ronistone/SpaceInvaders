package com.space.invaders.services.movement;

import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.models.ship.Ship;

public abstract class MovementService {

    protected SpaceInvaders g;

    public MovementService(){}

    public abstract void move(Ship ship);

    public MovementService init(SpaceInvaders game){
        return this;
    }

}
