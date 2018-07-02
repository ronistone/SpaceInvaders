package com.space.invaders.services.shot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.space.invaders.Models.Renderable;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Models.shot.Bullet;

import java.util.Iterator;

public class ShootService {

    private Iterator<Bullet> it;
    private Bullet s;

    public void shoot(Ship player, Array<Renderable> elements, Array<Ship> ships, float delta){

        if(Gdx.input.isTouched()){
            for(Bullet b: player.shoot()){
                elements.add(b);
            }
        }

        for(Ship s: ships){
            if(s!=player){
                for(Bullet b: s.shoot()){
                    elements.add(b);
                }
            }
        }
    }

}
