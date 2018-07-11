package com.space.invaders.models.level;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedMap;
import com.space.invaders.models.item.Items;
import com.space.invaders.models.ship.Ships;

public class Level {

    private Integer nItems;
    private Array<Items> items;
    private OrderedMap<Items, Double> probabilityItems;
    private Array<Ships> enemies;
    private OrderedMap<Long, EnemiesLevel> enemiesTime;


    public Level() {}

    public Level(Integer nItems, Array<Items> items, OrderedMap<Items, Double> probabilityItems, Array<Ships> enemies, OrderedMap<Long, EnemiesLevel> enemiesTime) {
        this.nItems = nItems;
        this.items = items;
        this.probabilityItems = probabilityItems;
        this.enemies = enemies;
        this.enemiesTime = enemiesTime;
    }

    public Integer getnItems() {
        return nItems;
    }

    public void setnItems(Integer nItems) {
        this.nItems = nItems;
    }

    public Array<Items> getItems() {
        return items;
    }

    public void setItems(Array<Items> items) {
        this.items = items;
    }

    public OrderedMap<Items, Double> getProbabilityItems() {
        return probabilityItems;
    }

    public void setProbabilityItems(OrderedMap<Items, Double> probabilityItems) {
        this.probabilityItems = probabilityItems;
    }

    public Array<Ships> getEnemies() {
        return enemies;
    }

    public void setEnemies(Array<Ships> enemies) {
        this.enemies = enemies;
    }

    public OrderedMap<Long, EnemiesLevel> getEnemiesTime() {
        return enemiesTime;
    }

    public void setEnemiesTime(OrderedMap<Long, EnemiesLevel> enemiesTime) {
        this.enemiesTime = enemiesTime;
    }
}
