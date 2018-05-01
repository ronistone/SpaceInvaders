package com.space.invaders.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.space.invaders.Models.shot.Shot;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.Models.ship.WhiteShip;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Models.ship.BlackShip;
import com.space.invaders.services.movement.PlayerMovement;

public class GameScreen extends BaseScreen {

    private volatile static GameScreen instance;

    private SpriteBatch batch;
    private Texture background;
    private Ship player;
    private Camera camera;
    private Viewport viewport;
    private Array<Shot> shots;
    private Array<Ship> ships;

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
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.position.set(VIRTUAL_WIDHT/2,VIRTUAL_HEIGHT/2,0);
        viewport = new StretchViewport(VIRTUAL_WIDHT, VIRTUAL_HEIGHT, camera);

        background = game.getTexture("background.jpg");
        player = new WhiteShip(VIRTUAL_WIDHT/2, VIRTUAL_HEIGHT/2, game);
        player.setMovement(new PlayerMovement());
        ships.add(player);
        ships.add(new BlackShip(100, VIRTUAL_HEIGHT, game));
    }

    @Override
    public void render(float delta) {

        camera.update();
        game.update(player, ships, shots);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(background,0,0, VIRTUAL_WIDHT, VIRTUAL_HEIGHT);
        for(Ship s: ships){
            s.render(batch);
        }
        for(Shot s: shots){
            s.render(batch);
        }

        batch.end();

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
}
