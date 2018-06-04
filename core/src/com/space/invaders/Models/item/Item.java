package com.space.invaders.Models.item;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.invaders.Models.Collider;
import com.space.invaders.Models.Renderable;
import com.space.invaders.controllers.SpaceInvaders;

public interface Item extends Collider, Renderable {


    public void init(SpaceInvaders game, float speed, float x);

}
