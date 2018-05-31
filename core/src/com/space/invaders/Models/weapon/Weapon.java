package com.space.invaders.Models.weapon;

import com.badlogic.gdx.physics.box2d.World;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.Views.BaseScreen;

import java.util.List;

public abstract class Weapon {

    protected float SPEED = BaseScreen.convertToPPM(300);
    protected Ship ship;
    protected World world;

    public abstract List<Bullet> shoot();

}
