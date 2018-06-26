package com.space.invaders.Models.weapon;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.Models.shot.DefaultBullet;
import com.space.invaders.Views.BaseScreen;
import com.space.invaders.controllers.SpaceInvaders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class Weapon {

    protected float SPEED = BaseScreen.convertToPPM(300);
    protected Ship ship;
    protected World world;
    protected long lastShot = 0;
    protected long shotRate = 1;

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



}
