package com.space.invaders.Models.weapon;

import com.badlogic.gdx.physics.box2d.World;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Models.shot.Bullet;

import java.util.List;

public abstract class Weapon {

    protected float SPEED = 3000;
    protected Ship ship;
    protected World world;

    public abstract List<Bullet> shoot();

}
