package com.space.invaders.services.level.impl;

import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.models.level.Level;
import com.space.invaders.services.level.LevelService;

public class SingleLevelService extends LevelService {

    private SpaceInvaders g;

    public SingleLevelService(SpaceInvaders game){
        super(game);
    }

    @Override
    public void callCreateItem(Level level) throws InstantiationException, IllegalAccessException {

    }

    @Override
    protected void callCreateShips(Level level, long totalTime) throws InstantiationException, IllegalAccessException {

    }


}
