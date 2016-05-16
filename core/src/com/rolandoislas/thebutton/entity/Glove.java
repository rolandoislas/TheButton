package com.rolandoislas.thebutton.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.rolandoislas.thebutton.Assets;

/**
 * Created by Rolando on 5/13/2016.
 */
public class Glove extends Entity {
    private final Texture texture;

    public Glove() {
        texture = Assets.instance.getManager().get("img/glove.png", Texture.class);
        this.setWidth(Gdx.graphics.getWidth() * .25f);
        this.setHeight(texture.getHeight() * this.getWidth() / texture.getWidth());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(this.getColor());
        batch.draw(texture, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        batch.setColor(Color.WHITE);
    }
}
