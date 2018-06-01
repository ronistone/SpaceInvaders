package com.space.invaders.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.space.invaders.Models.health.Health;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.Models.weapon.SimpleShot;
import com.space.invaders.Models.weapon.ThreeShot;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.Models.ship.WhiteShip;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Models.ship.BlackShip;
import com.space.invaders.services.ContactListenerCustom;
import com.space.invaders.services.factory.BodyFactory;
import com.space.invaders.services.movement.FoolMovimentService;
import com.space.invaders.services.movement.PlayerMovementService;

import java.util.HashMap;
import java.util.Map;

public class GameScreen extends BaseScreen {

    private volatile static GameScreen instance;

    private SpriteBatch batch;
    private Texture background;
    private Ship player;
    private Camera camera;
    private Viewport viewport;
    private Array<Bullet> shots;
    private Array<Ship> ships;
    private World world;
    private Box2DDebugRenderer debug;


    private GameScreen(SpaceInvaders g) {
        super(g);
        shots = new Array<>();
        ships = new Array<>();
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



        /*

            Criação temporaria de naves, deve-se criar um sistema de fases que fará todo este processo

         */

        player = new BlackShip(VIRTUAL_WIDHT/2, 0, game, true);
        player.setMovement(new PlayerMovementService());
        player.setWeapon(new ThreeShot(player,world));

        ships.add(player);
        Ship a = new BlackShip(
                BaseScreen.convertToPPM(0),
                VIRTUAL_HEIGHT - BaseScreen.convertToPPM(200), game);
        a.setMovement(new FoolMovimentService());

        ships.add(a);
        a = new BlackShip(
                BaseScreen.convertToPPM(100),
                VIRTUAL_HEIGHT - BaseScreen.convertToPPM(200), game);
        a.setMovement(new FoolMovimentService());
        ships.add(a);
        a = new BlackShip(
                BaseScreen.convertToPPM(210),
                VIRTUAL_HEIGHT - BaseScreen.convertToPPM(200), game);
        a.setMovement(new FoolMovimentService());
        ships.add(a);
        a = new BlackShip(
                BaseScreen.convertToPPM(320),
                VIRTUAL_HEIGHT - BaseScreen.convertToPPM(200), game);
        a.setMovement(new FoolMovimentService());
        ships.add(a);
        a = new BlackShip(
                BaseScreen.convertToPPM(430),
                VIRTUAL_HEIGHT - BaseScreen.convertToPPM(200), game);
        a.setMovement(new FoolMovimentService());
        ships.add(a);
    }

    @Override
    public void render(float delta) {

        world.step(delta, 6, 2);
        camera.update();
        game.update(delta, player, ships, shots);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(background,0,0, VIRTUAL_WIDHT, VIRTUAL_HEIGHT);
        for(Ship s: ships){
            s.render(batch);
        }
        for(Bullet s: shots){
            s.render(batch);
        }

        batch.end();

        for(Ship s: ships){
            s.updateHealthBar(camera);
        }

        debug.render(world, camera.combined);
    }


    @Override
    public void dispose() {
        batch.dispose();
        for(Ship s: ships){
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

}
