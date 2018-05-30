package com.space.invaders.services.shot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.Views.BaseScreen;

import java.util.Iterator;

public class ShootService {

    public void shoot(Ship player, Array<Bullet> shots, float delta){

        Iterator<Bullet> it = shots.iterator();
        while(it.hasNext()){
            Bullet s = it.next();
            s.update(delta);
            if(!s.isLive(BaseScreen.VIRTUAL_WIDHT, BaseScreen.VIRTUAL_HEIGHT)){
                s.destruct();
                it.remove();
            }
        }

        if(player.getLAST_SHOT() > 0){
            player.setLAST_SHOT(player.getLAST_SHOT() - delta);
        }
        if(Gdx.input.isTouched() && player.getLAST_SHOT() <= 0){
            for(Bullet b: player.shoot()){
                shots.add(b);
            }
            player.setLAST_SHOT(1/player.getSHOT_RATE());
        }
    }

}
