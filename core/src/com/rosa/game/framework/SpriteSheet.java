package com.rosa.game.framework;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage img(int col, int raw, int width, int height) {
        return image.getSubimage(col, raw, width, height);
    }
}
