package com.space.invaders.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.space.invaders.Models.Renderable;
import com.space.invaders.Models.item.Items;
import com.space.invaders.Models.level.EnemiesLevel;
import com.space.invaders.Models.level.Level;
import com.space.invaders.Models.ship.Ships;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.Models.weapon.ThreeShot;
import com.space.invaders.Models.weapon.Weapons;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Models.ship.BlackShip;
import com.space.invaders.services.level.LevelService;
import com.space.invaders.services.movement.Movements;
import com.space.invaders.services.movement.PlayerMovementService;

import java.util.HashMap;

public class GameScreen extends BaseScreen {

    private volatile static GameScreen instance;

    private SpriteBatch batch;
    private Texture background;
    private Ship player;
    private Camera camera;
    private Viewport viewport;
    private World world;
    private Level level;
    private Box2DDebugRenderer debug;
    private Long totalTime;
    private Long initialTime;


    private GameScreen(SpaceInvaders g) {
        super(g);
    }

    public static GameScreen getInstance(SpaceInvaders g){
        if(instance==null){
            synchronized (BaseScreen.class){
                if(instance==null){
                    instance = new GameScreen(g);
                }
            }
        }
        return instance;
    }


    @Override
    public void show() {

        instanciateElements();


        level = createFirstLevel();
        totalTime = 0L;
        initialTime = TimeUtils.millis();
        player = new BlackShip(VIRTUAL_WIDHT/2, 0, game, true);
        player.setMovement(new PlayerMovementService());
        player.setWeapon(new ThreeShot(player,world));

        game.getShips().add(player);
        game.getElements().add(player);

    }

    @Override
    public void render(float delta) {

        world.step(delta, 6, 2);
        game.getLevelService().levelManage(level,TimeUtils.millis() - initialTime);
        camera.update();
        game.update(delta, player);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(background,0,0, VIRTUAL_WIDHT, VIRTUAL_HEIGHT);
        for(Renderable r: game.getElements()){
            r.render(batch);
        }
        batch.end();

        debug.render(world, camera.combined);
    }


    @Override
    public void dispose() {
        batch.dispose();
        for(Ship s: game.getShips()){
            s.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }



    public void instanciateElements(){
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.position.set(VIRTUAL_WIDHT/2,VIRTUAL_HEIGHT/2,0);
        viewport = new StretchViewport(VIRTUAL_WIDHT, VIRTUAL_HEIGHT, camera);

        world = game.getWorld();
        debug = new Box2DDebugRenderer();

        background = game.getTexture("background.jpg");
    }


    private Level createFirstLevel(){
        Level level = new Level();

        Array<Ships> enemies = new Array<>();
        Array<Items> items = new Array<>();
        OrderedMap<Items,Double> pItem = new OrderedMap<>();
        OrderedMap<Long, EnemiesLevel> tEnemies = new OrderedMap<>();
        tEnemies.put(5L,new EnemiesLevel(10,Ships.WhiteShip,Movements.FoolMovement,Weapons.SimpleShot));
        tEnemies.put(10L,new EnemiesLevel(5,Ships.WhiteShip,Movements.FoolMovement,Weapons.SimpleShot));
        tEnemies.put(11L,new EnemiesLevel(5,Ships.WhiteShip,Movements.FoolMovement,Weapons.SimpleShot));
        tEnemies.put(12L,new EnemiesLevel(5,Ships.WhiteShip,Movements.FoolMovement,Weapons.SimpleShot));
        tEnemies.put(13L,new EnemiesLevel(5,Ships.WhiteShip,Movements.FoolMovement,Weapons.SimpleShot));
        tEnemies.put(14L,new EnemiesLevel(5,Ships.WhiteShip,Movements.FoolMovement,Weapons.SimpleShot));
        tEnemies.put(15L,new EnemiesLevel(5,Ships.WhiteShip,Movements.FoolMovement,Weapons.SimpleShot));
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
