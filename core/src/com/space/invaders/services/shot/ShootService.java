package com.space.invaders.services.shot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.space.invaders.Models.Renderable;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.Views.BaseScreen;

import java.util.Iterator;

public class ShootService {

    private Iterator<Bullet> it;
    private Bullet s;

    public void shoot(Ship player, Array<Renderable> elements, float delta){

        if(player.getLAST_SHOT() > 0){
            player.setLAST_SHOT(player.getLAST_SHOT() - delta);
        }
        if(Gdx.input.isTouched() && player.getLAST_SHOT() <= 0){
            for(Bullet b: player.shoot()){
                elements.add(b);
            }
            player.setLAST_SHOT(1/player.getSHOT_RATE());
        }
    }

}
