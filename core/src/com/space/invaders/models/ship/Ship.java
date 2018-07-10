package com.space.invaders.models.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.space.invaders.models.Collider;
import com.space.invaders.models.ObserverMovement;
import com.space.invaders.models.Renderable;
import com.space.invaders.models.SubjectMovement;
import com.space.invaders.models.health.Health;
import com.space.invaders.models.shot.Bullet;
import com.space.invaders.models.weapon.Weapon;
import com.space.invaders.util.MathUtil;
import com.space.invaders.view.screen.BaseScreen;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.services.movement.MovementService;

import java.util.ArrayList;
import java.util.List;

public abstract class Ship implements Collider, Renderable, SubjectMovement {

    private boolean isPlayer;
    private Texture shipTexture;
    private Sprite sprite;
    private float WIDTH = BaseScreen.convertToPPM(100);
    private float HEIGHT = BaseScreen.convertToPPM(90);
    private float LATERAL_SPEED = BaseScreen.convertToPPM(400);
    private float SPEED = 100;
    private float LIFE = 100;
    private long points = 0;
    private final long POINT = 10;
    private long createTime;
    private List<ObserverMovement> observers;
    public Body body;
    protected World world;

    protected boolean isAlive;
    protected float x;
    protected float y;
    protected float currentLife;

    private Vector2 healthBarPosition;

    protected SpaceInvaders g;

    // Elements
    protected MovementService movement;
    protected Weapon weapon;
    protected Health health;


    public Ship(){}
    public Ship(float x, float y, SpaceInvaders game, boolean isPlayer){
        init(game, isPlayer, x, y);
    }

    public void init(SpaceInvaders game, boolean isPlayer, float x, float y){
        g = game;
        isAlive = true;
        this.isPlayer = isPlayer;
        loadTexture();
        sprite = new Sprite(shipTexture);
        sprite.setSize(WIDTH,HEIGHT);
        health = new Health(BaseScreen.convertToPPM(100), BaseScreen.convertToPPM(15), LIFE, g);
        healthBarPosition = new Vector2();
        currentLife = LIFE;

        setX(x);
        setY(y);
        this.world = g.getWorld();
        createBody();

        if(!isPlayer){
            sprite.flip(false,true);
        }
        observers = new ArrayList<>();
        createTime = TimeUtils.millis() - g.getTimeCurrent();
    }

    public void render(SpriteBatch sb){
        move(Gdx.graphics.getDeltaTime());
        setX(body.getPosition().x-getWIDTH()/2);
        setY(body.getPosition().y-getHEIGHT()/2);
        sprite.draw(sb);
        updateHealthBar(sb);
    }

    public void updateHealthBar(SpriteBatch batch){
        health.updateLife(currentLife);
        healthBarPosition.set(body.getPosition().x,body.getPosition().y);
        healthBarPosition.x -= (getWIDTH()/2);
        healthBarPosition.y -= 2*(getHEIGHT()/3);
        health.draw(healthBarPosition, batch);
    }

    public abstract void loadTexture();

    public List<Bullet> shoot(){
        if(weapon!=null) {
            return weapon.shoot();
        }
        return new ArrayList<>();
    }

    @Override
    public void collide(Object a) {}

    public void hit(float damage){
        currentLife -= damage;
        if(currentLife <= MathUtil.LIFE_EPS){
            setAlive(false);
        }
    }

    @Override
    public void register(ObserverMovement observer) {
        observers.add(observer);
    }

    @Override
    public void exit(ObserverMovement observer) {
        observers.remove(observer);
    }

    public void createBody() {
        this.body = g.getBodyFactory().createSimpleShipBody(this, world);
    }

    public void destruct() {
        //health.destruct();
        world.destroyBody(body);
    }

    public void move(float delta) {
        if(movement != null){
            movement.move(this);
        }
        for( ObserverMovement o: observers ){
            o.update(body.getPosition());
        }
    }

    public Vector2 getPosition(){
        return body.getPosition();
    }

    @Override
    public float getWidht() {
        return sprite.getWidth();
    }

    @Override
    public float getHeight() {
        return sprite.getHeight();
    }

    @Override
    public void setSize(float widht, float height) {
        sprite.setSize(widht,height);
    }

    public void dispose(){
        shipTexture.dispose();
    }

    public void setMovement(MovementService movement) {
        this.movement = movement;
    }

    public float getX() {
        return sprite.getX();
    }

    public void setX(float x) {
        sprite.setX(x);
    }

    public float getY() {
        return sprite.getY();
    }

    public void setY(float y) {
        sprite.setY(y);
    }

    public boolean isAlive() {
        if(currentLife <= MathUtil.LIFE_EPS){
            setAlive(false);
        }
        return isAlive;
    }

    public void setCurrentLife(float currentLife) {
        this.currentLife = currentLife;
        if(this.currentLife > this.LIFE){
            this.currentLife = LIFE;
        }
    }

    public void addPoints(long time, long points){
        this.points += ((Math.floor(time/15000)+1) * points);
    }

    public Texture getShipTexture() {
        return shipTexture;
    }

    public void setShipTexture(Texture shipTexture) {
        this.shipTexture = shipTexture;
    }

    public float getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(float WIDTH) {
        this.WIDTH = WIDTH;
    }

    public float getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(float HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public float getLATERAL_SPEED() {
        return LATERAL_SPEED;
    }

    public void setLATERAL_SPEED(float LATERAL_SPEED) {
        this.LATERAL_SPEED = LATERAL_SPEED;
    }

    public float getSPEED() {
        return SPEED;
    }

    public void setSPEED(float SPEED) {
        this.SPEED = SPEED;
    }


    public MovementService getMovement() {
        return movement;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        if( weapon != null && isPlayer ){
            weapon.changeShotRate(weapon.getShotRate()*1.8f);
            weapon.setSPEED(weapon.getSPEED()*1.5f);
        }
    }

    public SpaceInvaders getGame() {
        return g;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public float getLIFE() {
        return LIFE;
    }

    public void setLIFE(float LIFE) {
        this.LIFE = LIFE;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public float getCurrentLife() {
        return currentLife;
    }

    public Vector2 getHealthBarPosition() {
        return healthBarPosition;
    }

    public void setHealthBarPosition(Vector2 healthBarPosition) {
        this.healthBarPosition = healthBarPosition;
    }

    public SpaceInvaders getG() {
        return g;
    }

    public void setG(SpaceInvaders g) {
        this.g = g;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public long getPoints() {
        return points;
    }

    public long getPOINT() {
        return POINT;
    }

    public long getCreateTime() {
        return createTime;
    }
}


