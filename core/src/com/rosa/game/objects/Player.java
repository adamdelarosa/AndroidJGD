package com.rosa.game.objects;

import com.rosa.game.framework.*;
import com.rosa.game.window.Animation;
import com.rosa.game.window.Game;
import com.rosa.game.window.Handler;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {

    private float width = 32, height = 64;
    private Handler handler;
    private Texture tex = Game.getInstance();
    public static Rectangle playerCreateBounds;
    private Animation playerWalkR, playerWalkL;
    private int total_points = 0;
    SoundBoard playsound = new SoundBoard();

    public Player(float x, float y, Handler handler, ObjectId id) {
        super(x, y, id);
        this.handler = handler;
        playerWalkR = new Animation(8, tex.player[1], tex.player[2], tex.player[3], tex.player[4]);
        playerWalkL = new Animation(8, tex.player[6], tex.player[7], tex.player[8], tex.player[9]);
    }

    public void tick(LinkedList<GameObject> object) {

        //Bounds
        int rect_x = 480;
        int rect_y = 500;
        int rect_width = 1000;
        int rect_height = 1000;
        playerCreateBounds = new Rectangle((int) x - rect_x, (int) y - rect_y, rect_width, rect_height);
        x += velX;
        y += velY;

        //for walking sound: -----> // walk = (!jumping && (velX < 0)) || (!jumping && (velX > 0));

        if (velX < 0) facing = -1;
        else if (velX > 0) facing = 1;

        if (falling || jumping) {
            float gravity = 0.4f;
            velY += gravity;

            float MAX_SPEED = 10;
            if (velY > MAX_SPEED) {
                velY = MAX_SPEED;
            }
            //for double jumping:
            if (!jumping) {
                KeyInput.doubleJump = 0;
            }
        }

        collision();

        playerWalkR.runAnimation();
        playerWalkL.runAnimation();


        velX = 0;

        if (KeyInput.LEFT_KEY)
            velX = -5;

        if (KeyInput.RIGHT_KEY)
            velX = +5;
    }

    private void collision() {

        if (Player.playerCreateBounds.getBounds().contains(this.x, this.y)) {

            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getId() == ObjectId.Block) {

                    if (getBounds().intersects(tempObject.getBounds())) {

                        y = tempObject.getY() - height;
                        velY = 0;
                        falling = false;
                        jumping = false;
                    } else {
                        falling = true;
                    }

                    if (getBoundsRight().intersects(tempObject.getBounds())) {
                        x = tempObject.getX() - 35;
                    }

                    if (getBoundsLeft().intersects(tempObject.getBounds())) {
                        x = tempObject.getX() + 35;
                    }

                    if (getBoundsTop().intersects(tempObject.getBounds())) {
                        y = tempObject.getY() + 35;
                    }
                }

                if (tempObject.getId() == ObjectId.BlockDeath) {

                    if (getBounds().intersects(tempObject.getBounds())) {
                        y = tempObject.getY() - height;
                        velY = 0;
                        falling = false;
                        jumping = false;
                        total_points -= 1;
                        System.out.println("Points: " + total_points);
                    } else {
                        falling = true;
                    }

                    if (getBoundsTop().intersects(tempObject.getBounds())) {
                        y = tempObject.getY() + 35;
                    }
                }

                if (tempObject.getId() == ObjectId.BlockJump) {

                    if (getBoundsRight().intersects(tempObject.getBounds())) {
                        x = tempObject.getX() - 35;
                    }

                    if (getBoundsLeft().intersects(tempObject.getBounds())) {
                        x = tempObject.getX() + 35;
                    }

                    if (getBoundsTop().intersects(tempObject.getBounds())) {
                        y = tempObject.getY() + 35;
                    }
                }

                if (tempObject.getId() == ObjectId.BlockCoin) {

                    if (getBounds().intersects(tempObject.getBounds())) {
                        handler.removeObject(tempObject);
                        total_points += 100;
                        System.out.println("Points: " + total_points);
                        playsound.soundPlayer(11);
                    }
                }
            }
        }
    }

    public void render(Graphics g) {
        if (jumping || velY > 1) {
            if (facing == 1)
                g.drawImage(tex.player_jump[0], (int) x, (int) y, 22 * 2, 31 * 2, null);
            else if (facing == -1)
                g.drawImage(tex.player_jump[1], (int) x, (int) y, 22 * 2, 30 * 2, null);
        } else {

            if (velX != 0) {
                if (facing == 1) {
                    playerWalkR.drawAnimation(g, (int) x, (int) y, (int) width + 2, (int) height);
                } else {
                    playerWalkL.drawAnimation(g, (int) x, (int) y, (int) width + 2, (int) height);

                }
            } else {
                if (facing == 1)
                    g.drawImage(tex.player[0], (int) x, (int) y, 13 * 2, 32 * 2, null);

                else if (facing == -1)
                    g.drawImage(tex.player[5], (int) x, (int) y, 13 * 2, 32 * 2, null);
            }
        }
    }


    public Rectangle getBounds() {
        return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) ((int) y + (height / 2)), (int) width / 2, (int) height / 2);
    }


    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) y, (int) width / 2, (int) height / 2);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int) x + width - 5), (int) y + 5, 5, (int) height - 10);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x, (int) y + 5, 5, (int) height - 10);
    }
}