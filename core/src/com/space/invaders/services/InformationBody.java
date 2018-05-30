package com.space.invaders.services;

import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Models.shot.Bullet;

public class InformationBody {

    private Ship owner;
    private Bullet bullet;


    private InformationBody(){
    }

    public static InformationBody createBulletInformation(Bullet bullet, Ship ship){
        InformationBody i = new InformationBody();
        i.setBullet(bullet);
        i.setOwner(ship);
        return i;
    }

    public static InformationBody createShipInformation(Ship ship){
        InformationBody i = new InformationBody();
        i.setOwner(ship);
        return i;
    }


    public Ship getOwner() {
        return owner;
    }

    public void setOwner(Ship owner) {
        this.owner = owner;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }
}
