package com.rolandoislas.thebutton.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

/**
 * Created by Rolando on 5/3/2016.
 */
public class LevelSimon extends Level {
    private final Rectangle green;
    private final Rectangle red;
    private final Rectangle yellow;
    private final Rectangle blue;
    private final ShapeRenderer shapeRenderer;
    private boolean showPattern = true;
    private ArrayList<Integer> pattern = new ArrayList<Integer>();
    private int patternIndex = 0;
    private float[] alpha = new float[] {
            1, 1, 1, 1
    };
    private float patternTime = 0;
    private boolean showing = false;

    public LevelSimon() {
        // Green
        float size = Gdx.graphics.getWidth() * .4f;
        float margin = Gdx.graphics.getWidth() * .033f;
        float[] middle = new float[] {
                Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2
        };
        green = new Rectangle();
        green.setSize(size, size);
        green.setPosition(middle[0] - green.getWidth() - margin, middle[1] + margin);
        // Red
        red = new Rectangle();
        red.setSize(size, size);
        red.setPosition(green.getX() + green.getWidth() + margin * 2, green.getY());
        // Yellow
        yellow = new Rectangle();
        yellow.setSize(size, size);
        yellow.setPosition(middle[0] - yellow.getWidth() - margin, middle[1] - yellow.getHeight() - margin);
        // Blue
        blue = new Rectangle();
        blue.setSize(size, size);
        blue.setPosition(yellow.getX() + yellow.getWidth() + margin * 2, yellow.getY());
        // Renderer
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        // Background
        this.setBackground(new Color(.95f, .95f, .95f, 1));
        // Time
        this.removeTimeLimit();
        // Pattern
        for (int i = 0; i < 4; i++)
            pattern.add((int) Math.floor(Math.random() * 4));
    }

    @Override
    public void draw() {
        super.draw();
        // Draw shapes
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(.57f, .77f, 0, alpha[0]);
        shapeRenderer.rect(green.getX(), green.getY(), green.getWidth(), green.getHeight());
        shapeRenderer.setColor(.97f, .41f, .17f, alpha[1]);
        shapeRenderer.rect(red.getX(), red.getY(), red.getWidth(), red.getHeight());
        shapeRenderer.setColor(1, .76f, 0, alpha[2]);
        shapeRenderer.rect(yellow.getX(), yellow.getY(), yellow.getWidth(), yellow.getHeight());
        shapeRenderer.setColor(0, .74f, .95f, alpha[3]);
        shapeRenderer.rect(blue.getX(), blue.getY(), blue.getWidth(), blue.getHeight());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        // Update pattern
        if (showPattern) {
            if (patternTime >= .5) {
                if (!showing) {
                    alpha[pattern.get(patternIndex)] = .1f;
                    showing = true;
                } else {
                    alpha[pattern.get(patternIndex)] = 1;
                    patternIndex++;
                    showing = false;
                }
                patternTime = 0;
                if (patternIndex == pattern.size()) {
                    patternIndex = 0;
                    showPattern = false;
                    this.setBackground(Color.BLACK);
                    this.setTimeLimit(2);
                }
            }
            else
                patternTime += Gdx.graphics.getDeltaTime();
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (showPattern)
            nextLevel(false);
        Rectangle[] buttons = new Rectangle[] {
                green, red, yellow, blue
        };
        if (buttons[pattern.get(patternIndex)].contains(screenX, Gdx.graphics.getHeight() - screenY))
            patternIndex++;
        else
            nextLevel(false);
        if (patternIndex == pattern.size())
            nextLevel(true, 800);
        return true;
    }
}
