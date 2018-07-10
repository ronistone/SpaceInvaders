package com.space.invaders.services.level.impl;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.TimeUtils;
import com.space.invaders.models.Renderable;
import com.space.invaders.models.item.Heal;
import com.space.invaders.models.item.Item;
import com.space.invaders.models.item.Items;
import com.space.invaders.models.level.EnemiesLevel;
import com.space.invaders.models.level.Level;
import com.space.invaders.models.ship.BlackShip;
import com.space.invaders.models.ship.Ship;
import com.space.invaders.models.ship.Ships;
import com.space.invaders.models.ship.WhiteShip;
import com.space.invaders.models.weapon.SimpleShot;
import com.space.invaders.models.weapon.ThreeShot;
import com.space.invaders.models.weapon.Weapon;
import com.space.invaders.models.weapon.Weapons;
import com.space.invaders.services.level.LevelService;
import com.space.invaders.services.movement.FollowMovementService;
import com.space.invaders.util.MathUtil;
import com.space.invaders.view.screen.BaseScreen;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.services.movement.FoolMovementService;
import com.space.invaders.services.movement.MovementService;
import com.space.invaders.services.movement.Movements;
import com.space.invaders.services.movement.PlayerMovementService;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LevelServiceImpl extends LevelService {

    public LevelServiceImpl(SpaceInvaders game){
        super(game);
    }

    @Override
    public boolean canGetItem(Level level, double a) {
        return level.getnItems() > 0 && a > MathUtil.ITEM_PROBABILITY - (level.getnItems()/100f);
    }

    @Override
    public void callCreateItem(Level level) throws InstantiationException, IllegalAccessException {
        double item = Math.random();
        double p = 0.0;
        for (Items s : level.getProbabilityItems().orderedKeys()) {
            p += level.getProbabilityItems().get(s);
            if (item <= p) {
                createItem(level, s);
                break;
            }
        }
    }

    @Override
    public void createItem(Level level, Items s) throws IllegalAccessException, InstantiationException {
        super.createItem(level, s);
        level.setnItems(level.getnItems()-1);
    }

    @Override
    public void callCreateShips(Level level, long totalTime) throws InstantiationException, IllegalAccessException {
        for(Long t: level.getEnemiesTime().orderedKeys()){
            if(TimeUnit.SECONDS.toMillis(t) <= totalTime) {
                createShip(level, t);
            }
        }
    }

    @Override
    protected void deleteHook(Level level, long t) {
        level.getEnemiesTime().remove(t);
    }
}
