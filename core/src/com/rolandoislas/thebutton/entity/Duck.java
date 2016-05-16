package com.rolandoislas.thebutton.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.rolandoislas.thebutton.Assets;

/**
 * Created by Rolando on 5/8/2016.
 */
public class Duck extends Entity {
    private final Texture texture;

    public Duck() {
        texture = Assets.instance.getManager().get("img/duck.png", Texture.class);
        this.setWidth(Gdx.graphics.getWidth() * .3f);
        this.setHeight(texture.getHeight() * this.getWidth() / texture.getWidth());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
