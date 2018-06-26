package com.space.invaders.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import java.util.concurrent.TimeUnit;

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
    private Long endTime;


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
        endTime = null;

    }

    @Override
    public void render(float delta) {

//        if(endGame()){
//
//            showEndGame();
//
//        }else {

            world.step(delta, 6, 2);
            game.getLevelService().levelManage(level, TimeUtils.millis() - initialTime);
            camera.update();
            game.update(delta, player);

            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.setProjectionMatrix(camera.combined);

            batch.begin();

            batch.draw(background, 0, 0, VIRTUAL_WIDHT, VIRTUAL_HEIGHT);
            for (Renderable r : game.getElements()) {
                r.render(batch);
            }
            if(endGame()){
                if(endTime == null){
                    endTime = TimeUtils.millis();
                }
                showEndGame();
            }

            batch.end();

            debug.render(world, camera.combined);
//        }
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
        tEnemies.put(2L,new EnemiesLevel(2,Ships.WhiteShip,Movements.FoolMovement,Weapons.SimpleShot));
        tEnemies.put(10L,new EnemiesLevel(2,Ships.WhiteShip,Movements.FoolMovement,Weapons.SimpleShot));
        tEnemies.put(15L,new EnemiesLevel(2,Ships.WhiteShip,Movements.FoolMovement,Weapons.SimpleShot));
        tEnemies.put(20L,new EnemiesLevel(2,Ships.WhiteShip,Movements.FoolMovement,Weapons.SimpleShot));
        tEnemies.put(25L,new EnemiesLevel(2,Ships.WhiteShip,Movements.FoolMovement,Weapons.SimpleShot));
        tEnemies.put(30L,new EnemiesLevel(2,Ships.WhiteShip,Movements.FoolMovement,Weapons.SimpleShot));
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

    private boolean endGame(){
        if(!player.isAlive()) return true;
        if(level==null || (game.getShips().size <= 1 && level.getEnemiesTime().size == 0)) return  true;
        System.out.println(game.getShips().size);
        return false;
    }

    private void showEndGame(){
       Texture end = game.getTexture("endGame.png");
       player.setWeapon(null);
       player.setMovement(null);
       batch.draw(end, 0,BaseScreen.VIRTUAL_HEIGHT/2, BaseScreen.VIRTUAL_WIDHT, BaseScreen.VIRTUAL_WIDHT/2);
       if(Gdx.input.isTouched() && (TimeUtils.millis() - endTime) > TimeUnit.SECONDS.toMillis(2)){
           destroy();
           game.changeScreen(MainMenuScreen.class);
       }
    }

    private void destroy(){
        for(Renderable r: game.getElements()){
            r.destruct();
        }
        game.getShips().clear();
        game.getElements().clear();
        level = null;
    }
}
