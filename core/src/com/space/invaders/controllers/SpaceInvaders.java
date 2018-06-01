package com.space.invaders.controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.space.invaders.Models.Renderable;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.Views.BaseScreen;
import com.space.invaders.Views.GameScreen;
import com.space.invaders.Views.MainMenuScreen;
import com.space.invaders.services.AssetsService;
import com.space.invaders.services.ContactListenerCustom;
import com.space.invaders.services.factory.BodyFactory;
import com.space.invaders.services.shot.ShootService;


public class SpaceInvaders extends Game {

    private ObjectMap<Class<? extends BaseScreen>, BaseScreen> screens = new ObjectMap<Class<? extends BaseScreen>, BaseScreen>();
    private AssetsService textureManager;
    private ShootService shootService;
    private BodyFactory bodyFactory;
    private World world;


	@Override
	public void create () {
	    textureManager = AssetsService.getInstance();
	    shootService = new ShootService();
	    bodyFactory = new BodyFactory();
        world = new World(new Vector2(0,0), true);
        world.setContactListener(new ContactListenerCustom());
	    loadScreens();
        changeScreen(MainMenuScreen.class);
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
	    screens.put(MainMenuScreen.class, MainMenuScreen.getInstance(this));
    }


    public void update(float delta, Ship player, Array<Renderable> elements){
        shootService.shoot(player, elements, delta);

        for(Renderable r: elements){
            if(!r.isAlive()){
                r.destruct();
                elements.removeValue(r,true);
            }
        }
    }

    public Texture getTexture(String path){
	    return textureManager.getTexture(path);
    }

    public World getWorld() {
        return world;
    }

    public BodyFactory getBodyFactory() {
        return bodyFactory;
    }
}
