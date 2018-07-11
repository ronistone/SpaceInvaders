package com.space.invaders.models.weapon;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.space.invaders.models.ship.Ship;
import com.space.invaders.models.shot.Bullet;
import com.space.invaders.view.screen.BaseScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class Weapon {

    protected float SPEED = BaseScreen.convertToPPM(400);
    protected Ship ship;
    protected World world;
    protected long lastShot = 0;
    protected float shotRate = 1;

    public Weapon(){}

    public List<Bullet> shoot(){
        List<Bullet> bullets = new ArrayList<>();
        if((TimeUtils.millis() - lastShot) > TimeUnit.SECONDS.toMillis(1)/shotRate) {
            createBullet(bullets);
            lastShot = TimeUtils.millis();
        }
        return bullets;

    }

    protected abstract void createBullet(List<Bullet> bullets);

    public Weapon init(Ship ship){
        this.ship = ship;
        this.world = ship.getWorld();
        if(!ship.isPlayer()){
            SPEED = -SPEED;
        }
        return this;
    }

    public Weapon changeShotRate(float shotRate){
        this.shotRate = shotRate;
        return this;
    }

    public float getSPEED() {
        return SPEED;
    }

    public void setSPEED(float SPEED) {
        this.SPEED = SPEED;
    }

    public float getShotRate() {
        return shotRate;
    }

    public void setShotRate(float shotRate) {
        this.shotRate = shotRate;
    }
}
