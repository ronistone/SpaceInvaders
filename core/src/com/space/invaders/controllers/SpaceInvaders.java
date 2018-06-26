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
import com.space.invaders.services.level.LevelService;
import com.space.invaders.services.shot.ShootService;


public class SpaceInvaders extends Game {

    private ObjectMap<Class<? extends BaseScreen>, BaseScreen> screens = new ObjectMap<Class<? extends BaseScreen>, BaseScreen>();
    private AssetsService textureManager;
    private ShootService shootService;
    private LevelService levelService;
    private BodyFactory bodyFactory;
    private World world;
    private Array<Renderable> elements;
    private Array<Ship> ships;


	@Override
	public void create () {
	    elements = new Array<>();
	    ships = new Array<>();

	    textureManager = AssetsService.getInstance();
	    shootService = new ShootService();
	    bodyFactory = new BodyFactory();
        world = new World(new Vector2(0,0), true);
        world.setContactListener(new ContactListenerCustom());
        levelService = new LevelService(this);
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


    public void update(float delta, Ship player){
        shootService.shoot(player, elements, ships, delta);

        for(Renderable r: elements){
            if(!r.isAlive()){
                r.destruct();
                elements.removeValue(r,true);
                if(r instanceof Ship){
                    ships.removeValue((Ship) r, true);
                }
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

    public ObjectMap<Class<? extends BaseScreen>, BaseScreen> getScreens() {
        return screens;
    }

    public void setScreens(ObjectMap<Class<? extends BaseScreen>, BaseScreen> screens) {
        this.screens = screens;
    }

    public AssetsService getTextureManager() {
        return textureManager;
    }

    public void setTextureManager(AssetsService textureManager) {
        this.textureManager = textureManager;
    }

    public ShootService getShootService() {
        return shootService;
    }

    public void setShootService(ShootService shootService) {
        this.shootService = shootService;
    }

    public LevelService getLevelService() {
        return levelService;
    }

    public void setLevelService(LevelService levelService) {
        this.levelService = levelService;
    }

    public void setBodyFactory(BodyFactory bodyFactory) {
        this.bodyFactory = bodyFactory;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Array<Renderable> getElements() {
        return elements;
    }

    public void setElements(Array<Renderable> elements) {
        this.elements = elements;
    }

    public Array<Ship> getShips() {
        return ships;
    }

    public void setShips(Array<Ship> ships) {
        this.ships = ships;
    }
}
