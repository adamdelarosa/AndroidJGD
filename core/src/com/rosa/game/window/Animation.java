package com.rosa.game.window;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private int speed;
    private int frames;

    private int index = 0;
    private int count = 0;

    private BufferedImage[] images;
    private BufferedImage currentImg;

    public Animation(int speed, BufferedImage... args) {
        this.speed = speed;
        images = new BufferedImage[args.length];
        System.arraycopy(args, 0, images, 0, args.length);

        frames = args.length;
    }

    public void runAnimation() {
        index++;
        if (index > speed) {
            index = 0;
            nextFrame();
        }
    }

    public void nextFrame() {
        if (count < frames) {
            currentImg = images[count];
            count++;
        } else {
            count = 0;

        }
    }

    public void drawAnimation(Graphics g, int x, int y, int xx, int yy) {
        g.drawImage(currentImg, x, y, xx + 1, yy, null);
    }
}
