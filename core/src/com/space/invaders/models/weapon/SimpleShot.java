package com.space.invaders.models.weapon;

import com.badlogic.gdx.math.Vector2;
import com.space.invaders.models.ship.Ship;
import com.space.invaders.models.shot.Bullet;
import com.space.invaders.models.shot.DefaultBullet;

import java.util.List;

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
