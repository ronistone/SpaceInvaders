package com.space.invaders.services.factory;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedMap;
import com.space.invaders.models.item.Items;
import com.space.invaders.models.level.EnemiesLevel;
import com.space.invaders.models.level.Level;
import com.space.invaders.models.ship.Ships;
import com.space.invaders.models.weapon.Weapons;
import com.space.invaders.services.movement.Movements;

public class LevelsFactory {


    public Level createFirstLevel(){
        Level level = new Level();

        Array<Ships> enemies = new Array<>();
        Array<Items> items = new Array<>();
        OrderedMap<Items,Double> pItem = new OrderedMap<>();
        OrderedMap<Long, EnemiesLevel> tEnemies = new OrderedMap<>();
        tEnemies.put(2L,new EnemiesLevel(2,Ships.WhiteShip, Movements.FollowMovement, Weapons.SimpleShot));
        tEnemies.put(10L,new EnemiesLevel(2,Ships.WhiteShip,Movements.FollowMovement,Weapons.SimpleShot));
        tEnemies.put(15L,new EnemiesLevel(2,Ships.WhiteShip,Movements.FollowMovement,Weapons.SimpleShot));
        tEnemies.put(20L,new EnemiesLevel(2,Ships.WhiteShip,Movements.FollowMovement,Weapons.SimpleShot));
        tEnemies.put(25L,new EnemiesLevel(2,Ships.WhiteShip,Movements.FollowMovement,Weapons.SimpleShot));
        tEnemies.put(30L,new EnemiesLevel(2,Ships.WhiteShip,Movements.FollowMovement,Weapons.SimpleShot));
        tEnemies.put(35L,new EnemiesLevel(2,Ships.WhiteShip,Movements.FoolMovement,Weapons.SimpleShot));
        pItem.put(Items.Heal, 100.0);
        items.add(Items.Heal);
        enemies.add(Ships.WhiteShip, Ships.BlackShip);

        level.setnItems(2);
        level.setEnemies(enemies);
        level.setItems(items);
        level.setProbabilityItems(pItem);
        level.setEnemiesTime(tEnemies);
        return level;
    }

    public Level createSecondLevel(){
        Level level = new Level();

        Array<Ships> enemies = new Array<>();
        Array<Items> items = new Array<>();
        OrderedMap<Items,Double> pItem = new OrderedMap<>();
        OrderedMap<Long, EnemiesLevel> tEnemies = new OrderedMap<>();
        tEnemies.put(2L,new EnemiesLevel(2,Ships.BlackShip, Movements.FoolMovement, Weapons.SimpleShot));
        tEnemies.put(10L,new EnemiesLevel(2,Ships.BlackShip,Movements.FoolMovement,Weapons.SimpleShot));
        tEnemies.put(15L,new EnemiesLevel(2,Ships.BlackShip,Movements.FoolMovement,Weapons.SimpleShot));
        tEnemies.put(20L,new EnemiesLevel(2,Ships.BlackShip,Movements.FoolMovement,Weapons.SimpleShot));
        tEnemies.put(25L,new EnemiesLevel(2,Ships.BlackShip,Movements.FoolMovement,Weapons.SimpleShot));
        tEnemies.put(30L,new EnemiesLevel(2,Ships.BlackShip,Movements.FoolMovement,Weapons.SimpleShot));
        tEnemies.put(35L,new EnemiesLevel(2,Ships.BlackShip,Movements.FoolMovement,Weapons.SimpleShot));
        pItem.put(Items.Heal, 100.0);
        items.add(Items.Heal);
        enemies.add(Ships.WhiteShip, Ships.BlackShip);

        level.setnItems(2);
        level.setEnemies(enemies);
        level.setItems(items);
        level.setProbabilityItems(pItem);
        level.setEnemiesTime(tEnemies);
        return level;
    }
}
