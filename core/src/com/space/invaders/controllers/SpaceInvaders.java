package com.space.invaders.controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.Views.BaseScreen;
import com.space.invaders.Views.GameScreen;
import com.space.invaders.Views.MainMenu;
import com.space.invaders.services.AssetsService;
import com.space.invaders.services.shot.ShootService;


public class SpaceInvaders extends Game {

    private ObjectMap<Class<? extends BaseScreen>, BaseScreen> screens = new ObjectMap<Class<? extends BaseScreen>, BaseScreen>();
    private AssetsService textureManager;
    private ShootService shootService;


	@Override
	public void create () {
	    textureManager = AssetsService.getInstance();
	    shootService = new ShootService();
	    loadScreens();
        changeScreen(MainMenu.class);
	}

	@Override
	public void render () {
	    super.render();
	}

    @Override
	public void dispose () {
        for(ObjectMap.Entry<Class<? extends BaseScreen>, BaseScreen> e: screens){
            e.value.dispose();
        }

        setScreen(null);
        screens.clear();
	}

	public void changeScreen(Class<? extends BaseScreen> screen){
	    this.setScreen(screens.get(screen));
    }

	private void loadScreens(){
	    screens.put(GameScreen.class, GameScreen.getInstance(this));
	    screens.put(MainMenu.class, MainMenu.getInstance(this));
    }


    public void update(float delta, Ship player, Array<Ship> ships, Array<Bullet> shots){
        System.out.println(delta);
        shootService.shoot(player, shots, delta);

        for(Ship s: ships){
	        s.move(delta);
        }
    }

    public Texture getTexture(String path){
	    return textureManager.getTexture(path);
    }
}
