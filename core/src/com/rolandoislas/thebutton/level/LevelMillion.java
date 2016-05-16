package com.rolandoislas.thebutton.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

/**
 * Created by Rolando on 5/4/2016.
 */
public class LevelMillion extends Level {
    private final Rectangle answerOne;
    private final Rectangle answerTwo;
    private final Rectangle answerThree;
    private final Rectangle answerFour;
    private final Label time;
    private int correctAnswer;

    public LevelMillion() {
        // Initially remove limit for font generator
        this.removeTimeLimit();
        // Background
        this.setBackgroundImage("img/million.png");
        this.setBackground(Color.BLACK);
        // Question
        I18NBundle lang = I18NBundle.createBundle(Gdx.files.internal("lang/lang"));
        int questionNumber = (int) Math.floor(Math.random() * 4) + 1;
        Label question = this.createLabel(lang.get("question" + questionNumber), (int) (Gdx.graphics.getHeight() * .025), "font/timeburner.ttf");
        float x = Gdx.graphics.getWidth() / 2;
        question.setAlignment(Align.center);
        question.setPosition(x - question.getWidth() / 2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * .125f - question.getHeight());
        addActor(question);
        // Answer rect
        answerOne = new Rectangle();
        answerOne.setWidth(Gdx.graphics.getWidth() * .7f);
        answerOne.setHeight(Gdx.graphics.getHeight() * .08f);
        answerOne.setPosition(x - answerOne.getWidth() / 2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * .569f);
        answerTwo = new Rectangle();
        answerTwo.setSize(answerOne.getWidth(), answerOne.getHeight());
        answerTwo.setPosition(answerOne.getX(), answerOne.getY() - answerOne.getHeight());
        answerThree = new Rectangle();
        answerThree.setSize(answerOne.getWidth(), answerOne.getHeight());
        answerThree.setPosition(answerOne.getX(), answerTwo.getY() - answerOne.getHeight());
        answerFour = new Rectangle();
        answerFour.setSize(answerOne.getWidth(), answerOne.getHeight());
        answerFour.setPosition(answerOne.getX(), answerThree.getY() - answerOne.getHeight());
        // Answers
        int answerSize = (int) (Gdx.graphics.getHeight() * .025f);
        int[] answerOrder = new int[] {1, 2, 3 ,4};
        for (int i = 0; i < answerOrder.length; i++) {
            int rand = (int) Math.floor(Math.random() *  answerOrder.length);
            int temp = answerOrder[i];
            answerOrder[i] = answerOrder[rand];
            answerOrder[rand] = temp;
        }
        for (int i = 0; i < answerOrder.length; i++)
            if (answerOrder[i] == 1)
                correctAnswer = i + 1;
        Label[] answerLabels = new Label[answerOrder.length];
        for (int i = 0; i < answerOrder.length; i++)
            answerLabels[i] = createLabel(lang.get("answer" + questionNumber + "." + answerOrder[i]), answerSize, "font/timeburner.ttf");
        float height = answerOne.getHeight() / 3;
        answerLabels[0].setPosition(x - answerLabels[0].getWidth() / 2, answerOne.getY() + height);
        answerLabels[1].setPosition(x - answerLabels[1].getWidth() / 2, answerTwo.getY() + height);
        answerLabels[2].setPosition(x - answerLabels[2].getWidth() / 2, answerThree.getY() + height);
        answerLabels[3].setPosition(x - answerLabels[3].getWidth() / 2, answerFour.getY() + height);
        for (Label label : answerLabels) {
            label.setAlignment(Align.center);
            addActor(label);
        }
        // Time indicator
        time = createLabel("0", (int) (Gdx.graphics.getHeight() * .05f), "font/timeburner.ttf");
        time.setAlignment(Align.center);
        time.setPosition(x - time.getWidth() / 2, Gdx.graphics.getHeight() * .6f);
        addActor(time);
        // Time
        this.setTimeLimit(10);
    }

    @Override
    public void draw() {
        super.draw();
        time.setText(String.valueOf(Math.round(this.getTimeRemaining())));
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int y = Gdx.graphics.getHeight() - screenY;
        if ((correctAnswer == 1 && answerOne.contains(screenX, y)) ||
                (correctAnswer == 2 && answerTwo.contains(screenX, y)) ||
                (correctAnswer == 3 && answerThree.contains(screenX, y)) ||
                (correctAnswer == 4 && answerFour.contains(screenX, y)))
            nextLevel(true, 200);
        else
            nextLevel(false);
        return true;
    }
}
