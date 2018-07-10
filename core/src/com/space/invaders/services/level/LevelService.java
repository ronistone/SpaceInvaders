package com.space.invaders.services.level;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.TimeUtils;
import com.space.invaders.controllers.SpaceInvaders;
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
import com.space.invaders.services.movement.FollowMovementService;
import com.space.invaders.services.movement.FoolMovementService;
import com.space.invaders.services.movement.MovementService;
import com.space.invaders.services.movement.Movements;
import com.space.invaders.services.movement.PlayerMovementService;
import com.space.invaders.util.MathUtil;
import com.space.invaders.view.screen.BaseScreen;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class LevelService {

    protected SpaceInvaders g;
    protected Array<Renderable> e;
    protected Array<Ship> s;

    protected OrderedMap<Ships,Class> shipsAvailable;
    protected OrderedMap<Items,Class> itemsAvailable;
    protected OrderedMap<Weapons,Class> weaponsAvailable;
    protected OrderedMap<Movements,Class> movementationAvailable;

    protected Random random;

    public LevelService(SpaceInvaders game){
        this.g = game;
        this.e = game.getElements();
        this.s = game.getShips();
        this.shipsAvailable = new OrderedMap<>();
        this.itemsAvailable = new OrderedMap<>();
        this.weaponsAvailable =  new OrderedMap<>();
        this.movementationAvailable = new OrderedMap<>();
        this.random = new Random();
        init();
    }


    public void levelManage(Level level, Long totalTime){
        try {
            generateShips(level, totalTime);
            random.setSeed(TimeUtils.nanoTime());
            double a = random.nextDouble();
            if(canGetItem(level, a)) {
                generateItem(level);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void init(){
        shipsAvailable.put(Ships.WhiteShip, WhiteShip.class);
        shipsAvailable.put(Ships.BlackShip, BlackShip.class);

        itemsAvailable.put(Items.Heal, Heal.class);

        weaponsAvailable.put(Weapons.SimpleShot, SimpleShot.class);
        weaponsAvailable.put(Weapons.ThreeShot, ThreeShot.class);

        movementationAvailable.put(Movements.FoolMovement, FoolMovementService.class);
        movementationAvailable.put(Movements.PlayerMovement, PlayerMovementService.class);
        movementationAvailable.put(Movements.FollowMovement, FollowMovementService.class);

    }

    private void generateItem(Level level) throws IllegalAccessException, InstantiationException {
        callCreateItem(level);
    }

    private void generateShips(Level level, Long totalTime) throws IllegalAccessException, InstantiationException {
        callCreateShips(level, totalTime);
    }


    protected boolean canGetItem(Level level, double a){
        return true;
    }

    protected abstract void callCreateItem(Level level) throws InstantiationException, IllegalAccessException;

    protected void createItem(Level level, Items s) throws IllegalAccessException, InstantiationException {
        Item i = (Item) itemsAvailable.get(s).newInstance();
        i.init(g,
                (float) (BaseScreen.convertToPPM(50) + Math.random()* (BaseScreen.convertToPPM(100)/2)),
                (float) Math.random() * BaseScreen.VIRTUAL_WIDHT
        );
        e.add(i);
    }


    /*
     *     Methods to create Ships
     */

    protected abstract void callCreateShips(Level level, long totalTime) throws InstantiationException, IllegalAccessException;

    protected void createShip(Level level, Long t) throws IllegalAccessException, InstantiationException {
        Ship s;
        EnemiesLevel e = level.getEnemiesTime().get(t);
        for(int i=0;i<e.getNum();i++){
            s = (Ship) shipsAvailable.get(e.getName()).newInstance();
            s.init(g,false,
                    (float) (Math.random()*BaseScreen.VIRTUAL_WIDHT),
                    BaseScreen.VIRTUAL_HEIGHT
            );
            if(movementationAvailable.get(e.getMove()) != null) {
                s.setMovement(((MovementService) movementationAvailable.get(e.getMove()).newInstance()).init(g));
            }
            if(weaponsAvailable.get(e.getWeapon()) != null) {
                s.setWeapon(((Weapon) weaponsAvailable.get(e.getWeapon()).newInstance()).init(s));
            }
            this.e.add(s);
            this.s.add(s);
        }
        deleteHook(level, t);
    }

    protected void deleteHook(Level level, long t){}

}
