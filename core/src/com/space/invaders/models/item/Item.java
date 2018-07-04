package com.space.invaders.models.item;

import com.space.invaders.models.Collider;
import com.space.invaders.models.Renderable;
import com.space.invaders.controllers.SpaceInvaders;

public interface Item extends Collider, Renderable {


    public void init(SpaceInvaders game, float speed, float x);

}
