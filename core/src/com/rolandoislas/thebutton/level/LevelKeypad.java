package com.rolandoislas.thebutton.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.rolandoislas.thebutton.entity.Entity;

/**
 * Created by Rolando on 5/1/2016.
 */
public class LevelKeypad extends Level {
    private Entity button;
    private float[][] buttons = {
            {.4f, .8f}, // 0
            {.08f, .51f},
            {.4f, .51f},
            {.7f, .51f},
            {.08f, .605f},
            {.4f, .605f},
            {.7f, .605f},
            {.08f, .7f},
            {.4f, .7f},
            {.7f, .7f} // 9
    };
    private float[] buttonSize = {.213f, .094f};
    private int[][] codes = {
            {0, 0, 0, 0},
            {0, 1, 2, 3},
            {1, 2, 3, 4},
            {5, 5, 5, 5}
    };

    public  LevelKeypad() {
        // Background
        this.setBackgroundImage("img/keypad.png");
        // Code
        int[] code = codes[(int) Math.floor(Math.random() * codes.length)];
        Label label = this.createLabel(String.valueOf(code[0]) + code[1] + code[2] + "_", (int) (Gdx.graphics.getHeight() * .1), "font/display.ttf");
        label.setX(Gdx.graphics.getWidth() / 2 - label.getWidth() / 2);
        label.setY(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * .2f);
        addActor(label);
        // Active button
        button = new Entity();
        button.setWidth(Gdx.graphics.getWidth() * buttonSize[0]);
        button.setHeight(Gdx.graphics.getHeight() * buttonSize[1]);
        button.setX(Gdx.graphics.getWidth() * buttons[code[3]][0]);
        button.setY(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * buttons[code[3]][1]);
        addActor(button);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (this.button.contains(screenX, screenY))
            this.nextLevel(true, 350);
        else
            this.nextLevel(false);
        return true;
    }
}
