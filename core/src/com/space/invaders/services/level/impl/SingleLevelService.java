package com.space.invaders.services.level.impl;

import com.badlogic.gdx.utils.TimeUtils;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.models.level.Level;
import com.space.invaders.services.level.LevelService;

public class SingleLevelService extends LevelService {

    private static final long maxShip = 6;
    private static final long minGapShip = 3000;

    private long lastItem;
    private long gapItem;
    private long lastShips;
    private long gapShip;
    private Integer qtdShip;

    public SingleLevelService(SpaceInvaders game){
        super(game);
        lastItem = 0;
        lastShips = 0;
        gapItem = 15 * 1000;
        gapShip = 6 * 1000;
        qtdShip = 1;
    }

    @Override
    public void callCreateItem(Level level) throws InstantiationException, IllegalAccessException {
        if (TimeUtils.millis() - lastItem >= gapItem) {
            lastItem = TimeUtils.millis();


            int p = (int) Math.floor(random.nextDouble() * level.getItems().size);

            if (p == level.getItems().size)
                p--;

            createItem(level, level.getItems().get(p));
        }
    }

    @Override
    protected void callCreateShips(Level level, long totalTime) throws InstantiationException, IllegalAccessException {
        long now = TimeUtils.millis();
        if(now - lastShips >= gapShip){
            lastShips = TimeUtils.millis();

            int p = (int) Math.floor(random.nextDouble() * level.getEnemies().size);
            if(p == level.getItems().size)
                p--;

            createShip(level, level.getEnemiesTime().orderedKeys().get(p), qtdShip);

            if( qtdShip < maxShip && (now - g.getTimeCurrent()) / (gapShip) >= (qtdShip * 3) ){
                qtdShip++;
                if(gapShip > minGapShip){
                    gapShip -= 500;
                }
            }
        }

    }




}
