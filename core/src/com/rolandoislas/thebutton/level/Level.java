package com.rolandoislas.thebutton.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.rolandoislas.thebutton.Assets;
import com.rolandoislas.thebutton.LevelEvent;

import java.util.ArrayList;

/**
 * Created by Rolando on 4/25/2016.
 */
class Level extends Stage {
    private ArrayList<EventListener> listeners = new ArrayList<EventListener>();
    private boolean completed = false;
    final static Color BACKGROUND_COLOR = new Color(0, 1, 1, 1);
    private float time = 0;
    private float timeLimit = 2;

    Level() {
        setBackground(BACKGROUND_COLOR);
    }

    @Override
    public boolean addListener(EventListener listener) {
        listeners.add(listener);
        return true;
    }

    @Override
    public void draw() {
        super.draw();
        // Timer
        if (time >= timeLimit)
            nextLevel(false);
        else
            time += Gdx.graphics.getDeltaTime();
    }

    void nextLevel(boolean success, int score) {
        if (completed)
            return;
        completed = true;
        for (EventListener listener : listeners) {
            LevelEvent event = new LevelEvent();
            event.setSuccess(success);
            event.setScore(score);
            listener.handle(event);
        }
    }

    void nextLevel(boolean success) {
        nextLevel(success, 0);
    }

    void setBackground(Color color) {
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
    }

    void setTimeLimit(float timeLimit) {
        this.timeLimit = timeLimit;
        this.time = 0;
    }

    void removeTimeLimit() {
        setTimeLimit(Float.MAX_VALUE);
    }

    Label createLabel(String text, int size, String font) {
        AssetManager manager = Assets.instance.getManager();
        if (!manager.isLoaded(size + font)) {
            FileHandleResolver resolver = new InternalFileHandleResolver();
            manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
            manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
            FreetypeFontLoader.FreeTypeFontLoaderParameter parameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
            parameter.fontFileName = font;
            parameter.fontParameters.size = size;
            FreeTypeFontGenerator.setMaxTextureSize(FreeTypeFontGenerator.NO_MAXIMUM);
            manager.load(size + font, BitmapFont.class, parameter);
            manager.finishLoading();
        }
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = manager.get(size + font, BitmapFont.class);
        return new Label(text, labelStyle);
    }

    void setBackgroundImage(String path) {
        Texture bgTexture = Assets.instance.getManager().get(path, Texture.class);
        TextureRegion bgRegion = new TextureRegion(bgTexture, bgTexture.getWidth(), bgTexture.getHeight());
        Image bg = new Image(bgRegion);
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bg.setZIndex(0);
        addActor(bg);
    }

    float getTimeRemaining() {
        return timeLimit - time;
    }
}
