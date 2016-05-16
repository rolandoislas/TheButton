package com.rolandoislas.thebutton.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;
import com.rolandoislas.thebutton.Assets;
import com.rolandoislas.thebutton.playservices.DummyPlayServices;
import com.rolandoislas.thebutton.playservices.PlayServices;
import com.rolandoislas.thebutton.entity.Button;

/**
 * Created by Rolando on 4/28/2016.
 */
public class StartScreen extends Level {

    public StartScreen(final PlayServices playServices) {
        // Background
        setBackground(new Color(0, 0, 0, 1));
        // Time limit
        this.removeTimeLimit();
        //Button
        Button button = new Button();
        button.positionCenter();
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                nextLevel(true);
            }
        });
        addActor(button);
        // Title
        I18NBundle lang = I18NBundle.createBundle(Gdx.files.internal("lang/lang"));
        Label title = this.createLabel(lang.get("title"), (int) (Gdx.graphics.getHeight() * .05), "font/timeburner.ttf");
        title.setX(Gdx.graphics.getWidth() / 2 - title.getWidth() / 2);
        title.setY(Gdx.graphics.getHeight() - title.getHeight() - Gdx.graphics.getHeight() * .1f);
        addActor(title);
        // Scoreboard link
        Label scoreBoard = createLabel(Assets.instance.lang.get("scoreboard"), (int) (Gdx.graphics.getHeight() * .05),
                "font/timeburner.ttf");
        scoreBoard.setPosition(Gdx.graphics.getWidth() / 2, button.getY() - Gdx.graphics.getHeight() * .1f,
                Align.center);
        scoreBoard.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playServices.showScores();
            }
        });
        addActor(scoreBoard);
        // Achievements link
        Label achievements = createLabel(Assets.instance.lang.get("achievements"), (int) (Gdx.graphics.getHeight() *
                .05), "font/timeburner.ttf");
        achievements.setPosition(Gdx.graphics.getWidth() / 2, scoreBoard.getY() - Gdx.graphics.getHeight() * .075f,
                Align.center);
        achievements.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playServices.showAchievement();
            }
        });
        addActor(achievements);
    }

    // Used by reflection
    public StartScreen() {
        new StartScreen(new DummyPlayServices());
    }
}
