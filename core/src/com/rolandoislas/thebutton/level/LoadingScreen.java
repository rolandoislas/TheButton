package com.rolandoislas.thebutton.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.rolandoislas.thebutton.Assets;

/**
 * Created by Rolando on 5/5/2016.
 */
public class LoadingScreen extends Level {
    private final ShapeRenderer shapeRenderer;
    private final Rectangle progressBar;
    private final Class[] stages;
    private float preloadPercent;
    private Runnable stageLoadRunnable;
    private boolean isStagePreloadRunning;
    private int stageLoadIndex = 0;

    public LoadingScreen(Class[] stages) {
        this.stages = stages;
        // Time
        this.removeTimeLimit();
        // Background
        this.setBackground(Color.BLACK);
        // Loading text
        I18NBundle lang = I18NBundle.createBundle(Gdx.files.internal("lang/lang"));
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(Gdx.files.internal("font/code.fnt"), Gdx.files.internal("font/code.png"), false);
        Label label = new Label(lang.get("loading"), labelStyle);
        label.setFontScale(Gdx.graphics.getHeight() * .1f / label.getHeight());
        label.setAlignment(Align.center);
        label.setPosition(Gdx.graphics.getWidth() / 2 - label.getWidth() / 2, Gdx.graphics.getHeight() / 2 + label.getHeight());
        addActor(label);
        // Progress bar
        progressBar = new Rectangle();
        progressBar.setWidth(0);
        progressBar.setHeight(Gdx.graphics.getHeight() * .05f);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
    }

    @Override
    public void draw() {
        super.draw();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.ORANGE);
        shapeRenderer.rect(progressBar.getX(), progressBar.getY(), progressBar.getWidth(), progressBar.getHeight());
        shapeRenderer.end();
        if (Assets.instance.getManager().update() && updateStagePreload())
            nextLevel(true);
        float width = Gdx.graphics.getWidth() / 2;
        progressBar.setWidth(Assets.instance.getManager().getProgress() * width + preloadPercent * width);
    }

    private boolean updateStagePreload() {
        if (preloadPercent == 1)
            return true;
        if (stageLoadRunnable == null || !isStagePreloadRunning) {
            stageLoadRunnable = new Runnable() {
                @Override
                public void run() {
                    isStagePreloadRunning = true;
                    preloadStage(stages[stageLoadIndex]);
                    isStagePreloadRunning = false;
                }
            };
            stageLoadRunnable.run();
        }
        return false;
    }

    private void setPreloadPercent(float preloadPercent) {
        this.preloadPercent = preloadPercent;
    }

    private void preloadStage(Class clazz) {
        try {
            Stage load = (Stage) clazz.newInstance();
            load.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setBackground(Color.BLACK);
        setPreloadPercent(++stageLoadIndex / (float) stages.length);
    }
}
