package com.space.invaders.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ObjectMap;
import com.space.invaders.controllers.SpaceInvaders;

public class AssetsService {

    private ObjectMap<String, Texture> textures;
    private volatile static AssetsService instance;


    private AssetsService(){
        textures = new ObjectMap<>();
    }

    public Texture getTexture(String path){
        if(textures.get(path) == null){
            textures.put(path,new Texture(path));
        }
        return textures.get(path);
    }

    public static AssetsService getInstance() {
        if(instance==null){
            synchronized (AssetsService.class){
                if(instance==null){
                    instance = new AssetsService();
                }
            }
        }
        return instance;
    }
}
