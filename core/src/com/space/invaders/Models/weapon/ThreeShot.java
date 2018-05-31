package com.space.invaders.Models.weapon;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.Models.shot.DefaultBullet;
import com.space.invaders.Views.BaseScreen;

import java.util.ArrayList;
import java.util.List;

public class ThreeShot extends Weapon {

    public ThreeShot(Ship ship, World world){
        this.ship = ship;
        this.world = world;
    }


    @Override
    public List<Bullet> shoot() {
        Vector2 directions[] = new Vector2[3];
        directions[0] = new Vector2(0,SPEED);
        directions[1] = new Vector2(BaseScreen.convertToPPM(100),SPEED);
        directions[2] = new Vector2(BaseScreen.convertToPPM(-100), SPEED);
        List<Bullet> bullets = new ArrayList<>();

        for(Vector2 v: directions){
            bullets.add(new DefaultBullet(
                    ship.getX()+ (ship.getWIDTH()/2),
                    ship.getY(),
                    v,
                    ship.getGame(),
                    world,
                    ship
            ));
        }
        return bullets;
    }
}
