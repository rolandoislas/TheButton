package com.rolandoislas.thebutton;

import com.badlogic.gdx.scenes.scene2d.Event;

/**
 * Created by Rolando on 4/25/2016.
 */
public class LevelEvent extends Event {
    private boolean success = false;
    private int score = Integer.MIN_VALUE;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    boolean getSuccess() {
        return success;
    }

    int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
