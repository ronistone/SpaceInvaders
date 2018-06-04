package com.space.invaders.Models.level;

import com.space.invaders.Models.ship.Ships;
import com.space.invaders.Models.weapon.Weapons;
import com.space.invaders.services.movement.Movements;

public class EnemiesLevel {

    private Integer num;
    private Ships name;
    private Movements move;
    private Weapons weapon;

    public EnemiesLevel(){}

    public EnemiesLevel(Integer num, Ships name, Movements move, Weapons weapon) {
        this.num = num;
        this.name = name;
        this.move = move;
        this.weapon = weapon;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Ships getName() {
        return name;
    }

    public void setName(Ships name) {
        this.name = name;
    }

    public Movements getMove() {
        return move;
    }

    public void setMove(Movements move) {
        this.move = move;
    }

    public Weapons getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
    }
}
