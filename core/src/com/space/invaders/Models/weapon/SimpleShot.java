package com.space.invaders.Models.weapon;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.Models.shot.DefaultBullet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimpleShot extends Weapon {

    public SimpleShot(){}

    public SimpleShot(Ship ship){
        init(ship);
    }

    @Override
    protected void createBullet(List<Bullet> bullets) {
        bullets.add(
                new DefaultBullet(
                        ship.getX() + (ship.getWIDTH() / 2),
                        ship.getY(),// + ship.getHEIGHT(),
                        new Vector2(0, SPEED),
                        ship.getGame(),
                        world,
                        ship
                )
        );
    }

}
