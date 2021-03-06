package com.space.invaders.models.weapon;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.space.invaders.models.ship.Ship;
import com.space.invaders.models.shot.Bullet;
import com.space.invaders.models.shot.DefaultBullet;
import com.space.invaders.view.screen.BaseScreen;

import java.util.List;

public class ThreeShot extends Weapon {

    public ThreeShot(Ship ship, World world){
        this.ship = ship;
        this.world = world;
    }

    @Override
    protected void createBullet(List<Bullet> bullets) {
        Vector2 directions[] = new Vector2[3];
        directions[0] = new Vector2(0,SPEED);
        directions[1] = new Vector2(BaseScreen.convertToPPM(100),SPEED);
        directions[2] = new Vector2(BaseScreen.convertToPPM(-100), SPEED);
        for (Vector2 v : directions) {
            bullets.add(new DefaultBullet(
                    ship.getX() + (ship.getWIDTH() / 2),
                    ship.getY(),
                    v,
                    ship.getGame(),
                    world,
                    ship
            ));
        }
    }
}
