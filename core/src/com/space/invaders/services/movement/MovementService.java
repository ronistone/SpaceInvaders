package com.space.invaders.services.movement;

import com.space.invaders.Models.ship.Ship;

public abstract class MovementService {


    public MovementService(){}

    public abstract void move(Ship ship);

    public MovementService init(){
        return this;
    }
}
