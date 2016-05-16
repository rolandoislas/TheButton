package com.rolandoislas.thebutton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.I18NBundle;

/**
 * Created by Rolando on 5/5/2016.
 */
public class Assets implements Disposable {
    public static final Assets instance = new Assets();
    private AssetManager manager;
    public I18NBundle lang;

    private Assets() {}

    @Override
    public void dispose() {
        manager.dispose();
    }

    void init(AssetManager assetManager) {
        manager = assetManager;
        // Textures
        manager.load("img/button.png", Texture.class);
        manager.load("img/keypad.png", Texture.class);
        manager.load("img/million.png", Texture.class);
        manager.load("img/switch_off.png", Texture.class);
        manager.load("img/switch_on.png", Texture.class);
        manager.load("img/duck.png", Texture.class);
        manager.load("img/ball.png", Texture.class);
        manager.load("img/glove.png", Texture.class);
        // Language Bundle
        lang = I18NBundle.createBundle(Gdx.files.internal("lang/lang"));
    }

    public AssetManager getManager() {
        return manager;
    }
}
