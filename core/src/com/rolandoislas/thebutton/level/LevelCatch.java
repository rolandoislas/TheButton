package com.rolandoislas.thebutton.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.rolandoislas.thebutton.Assets;
import com.rolandoislas.thebutton.entity.Ball;
import com.rolandoislas.thebutton.entity.Glove;

/**
 * Created by Rolando on 5/8/2016.
 */
public class LevelCatch extends Level {
    private final Glove glove;
    private final Ball ball;
    private final Label label;
    private int leftToCatch = 5;

    public LevelCatch() {
        // Init
        this.setBackground(Color.BLACK);
        this.removeTimeLimit();
        // Glove
        glove = new Glove();
        glove.setPosition(Gdx.graphics.getWidth() / 2 - glove.getHeight(), glove.getHeight());
        glove.setColor(Color.LIGHT_GRAY);
        addActor(glove);
        // Ball
        ball = new Ball();
        ball.addCollidingEntity(glove);
        ball.setFallSpeed(40);
        resetBall();
        addActor(ball);
        // Label
        label = createLabel(Assets.instance.lang.format("catch", leftToCatch), (int) (Gdx.graphics.getHeight() * .05), "font/timeburner.ttf");
        label.setPosition(Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() - label.getHeight(), Align.center);
        addActor(label);
    }

    @Override
    public void draw() {
        super.draw();
        // Check if ball fell off screen
        if (ball.getY() < 0)
            nextLevel(false);
        if (ball.isColliding()) {
            leftToCatch--;
            label.setText(Assets.instance.lang.format("catch", leftToCatch));
            resetBall();
            if (leftToCatch == 0)
                nextLevel(true, 150);
        }
    }

    private void resetBall() {
        float x = (float) (Math.floor(Math.random() * (Gdx.graphics.getWidth() - ball.getWidth())) + ball.getWidth() / 2);
        float y = (float) (Math.floor(Math.random() * (Gdx.graphics.getHeight() * .7 - ball.getHeight())) - ball.getHeight() / 2);
        y = Gdx.graphics.getHeight() - y;
        ball.setPosition(x, y);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        glove.setX(screenX - glove.getWidth() / 2);
        return true;
    }
}
