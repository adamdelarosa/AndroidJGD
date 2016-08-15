package com.rosa.game.framework;

import com.rosa.game.objects.Bullet;
import com.rosa.game.window.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Handler handler;
    SoundBoard playSound = new SoundBoard();
    GameObject tempObject;

    public static boolean RIGHT_KEY = false;
    public static boolean LEFT_KEY = false;
    public static int doubleJump = 0;
    private int doubleJumpMax = 0;


    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            RIGHT_KEY = true;
        }

        if (key == KeyEvent.VK_LEFT) {
            LEFT_KEY = true;
        }

        for (int i = 0; i < handler.object.size(); i++) {
            tempObject = handler.object.get(i);

            if (tempObject.getId() == ObjectId.Player) {

//                if (key == KeyEvent.VK_SPACE && !tempObject.isJumping()) {
                if (key == KeyEvent.VK_SPACE && (doubleJump != 2)) {
                    tempObject.setVelY(-10);

                    tempObject.setJumping(true);
                    playSound.soundPlayer(5);
                    doubleJump += 1;

                    if (doubleJump == 3) {
                        doubleJump = 0;
                    }

                    System.out.println(doubleJump + " " + doubleJumpMax);


                }

                if (key == KeyEvent.VK_CONTROL) {
                    shotBullet();
                }

                if (key == KeyEvent.VK_ESCAPE) {
                    System.exit(1);
                }
            }

        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            RIGHT_KEY = false;
        }

        if (key == KeyEvent.VK_LEFT) {
            LEFT_KEY = false;
        }
    }

    public void shotBullet() {
        if (tempObject.getFacing() == 1) {
            handler.addObject(new Bullet(tempObject.getX() + 10, tempObject.getY(), ObjectId.bullet, tempObject.getFacing() * 7, handler));

        } else if (tempObject.getFacing() == -1)
            handler.addObject(new Bullet(tempObject.getX() - 10, tempObject.getY(), ObjectId.bullet, tempObject.getFacing() * 7, handler));
    }
}