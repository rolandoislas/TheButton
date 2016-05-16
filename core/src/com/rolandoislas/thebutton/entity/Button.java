package com.rolandoislas.thebutton.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.rolandoislas.thebutton.Assets;

/**
 * Created by Rolando on 4/24/2016.
 */
public class Button extends Entity {
    public static final Color DEFAULT_COLOR = new Color(0.80392156862f, 0.09411764705f, 0.09411764705f, 1);
    private Texture texture;

    public Button() {
        texture = Assets.instance.getManager().get("img/button.png", Texture.class);
        this.setWidth(Gdx.graphics.getWidth() * .25f);
        this.setHeight(this.getWidth());
        this.setColor(DEFAULT_COLOR);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(this.getColor());
        batch.draw(texture, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        batch.setColor(1, 1, 1, 1);
    }
}
