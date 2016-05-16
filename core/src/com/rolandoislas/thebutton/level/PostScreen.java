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
 * Created by Rolando on 4/25/2016.
 */
public class PostScreen extends Level {
    private Button restart;
    private boolean active = false;
    private float activeTime = 0;

    public PostScreen(final PlayServices playServices, int scoreNumeric) {
        // "Remove" time limit
        this.removeTimeLimit();
        // Vibrate
        if (scoreNumeric >= 0)
            Gdx.input.vibrate(200);
        // Restart button
        restart = new Button();
        restart.positionCenter();
        restart.setColor(.3f, .3f, .3f, 1);
        restart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (active)
                    nextLevel(true, Integer.MIN_VALUE);
            }
        });
        addActor(restart);
        // Score
        I18NBundle lang = I18NBundle.createBundle(Gdx.files.internal("lang/lang"));
        Label score = this.createLabel(lang.get("score") + "\n" + scoreNumeric, (int) (Gdx.graphics.getHeight() * .05),
                "font/timeburner.ttf");
        score.setAlignment(Align.center);
        score.setX(restart.getX() + restart.getWidth() / 2 - score.getWidth() / 2);
        score.setY(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * .05f - score.getHeight());
        addActor(score);
        // Again
        Label again = this.createLabel(lang.get("again"), (int) (Gdx.graphics.getHeight() * .03),
                "font/timeburner.ttf");
        again.setAlignment(Align.center);
        again.setX(restart.getX() + restart.getWidth() / 2 - again.getWidth() / 2);
        again.setY(restart.getY() + restart.getHeight() + again.getHeight());
        addActor(again);
        // Background
        setBackground(new Color(0, 0, 0, 1));
        // Scoreboard link
        Label scoreBoard = createLabel(Assets.instance.lang.get("scoreboard"), (int) (Gdx.graphics.getHeight() * .05),
                "font/timeburner.ttf");
        scoreBoard.setPosition(Gdx.graphics.getWidth() / 2, restart.getY() - Gdx.graphics.getHeight() * .1f,
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
        // Submit score to leaderboard
        playServices.submitScore(scoreNumeric);
        // Check achievements
        if (scoreNumeric == 0)
            playServices.unlockAchievement("achievement_instant_finish");
        if (scoreNumeric >= 100000)
            playServices.unlockAchievement("achievement_neo");
        if (scoreNumeric >= 10000)
            playServices.unlockAchievement("achievement_focus");
        if (scoreNumeric >= 1000)
            playServices.unlockAchievement("achievement_understanding_the_game");
        if (scoreNumeric > 0)
            playServices.unlockAchievement("achievement_let_the_games_begin");
        // Show banner ad
        playServices.showBannerAd();
    }

    // Used by reflection
    public PostScreen() {
        new PostScreen(new DummyPlayServices(), -1);
    }

    @Override
    public void draw() {
        super.draw();
        // Button activation
        if (!active) {
            activeTime += Gdx.graphics.getDeltaTime();
            if (activeTime >= 2) {
                active = true;
                restart.setColor(Button.DEFAULT_COLOR);
            }
        }
    }
}
