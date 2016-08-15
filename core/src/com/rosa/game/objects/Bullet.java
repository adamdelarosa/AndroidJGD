package com.rosa.game.objects;

import com.rosa.game.framework.GameObject;
import com.rosa.game.framework.ObjectId;
import com.rosa.game.framework.SoundBoard;
import com.rosa.game.framework.Texture;
import com.rosa.game.window.Animation;
import com.rosa.game.window.Game;
import com.rosa.game.window.Handler;
import java.awt.*;
import java.util.LinkedList;

public class Bullet extends GameObject {

    SoundBoard playsound = new SoundBoard();
    private Handler handler;
    private static int timeHit = 0;
    private Animation bulletShotAnimation;
    private Texture tex = Game.getInstance();

    public Bullet(float x, float y, ObjectId id, int velX, Handler handler) {
        super(x, y, id);
        this.velX = velX;
        this.handler = handler;

        playsound.soundPlayer(2);
        bulletShotAnimation = new Animation(3, tex.bull[0], tex.bull[1], tex.bull[2], tex.bull[3], tex.bull[4]);
    }

    private void Collision() {
        if (Player.playerCreateBounds.getBounds().contains(this.x, this.y)) {




            //Block the Bullet in the walls
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getId() == ObjectId.Block || tempObject.getId() == ObjectId.BlockJump || tempObject.getId() == ObjectId.BlockDeath) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        handler.removeObject(this);
                        playsound.soundPlayer(9);
                    }
                }
            }

            // kill the enemy
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getId() == ObjectId.Enemy) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        handler.removeObject(this);
                        timeHit++;
                        if (timeHit == 5) {
                            handler.removeObject(tempObject);
                            timeHit = 0;
                            playsound.soundPlayer(8);
                        } else {
                            playsound.soundPlayer(7);
                        }
                    }
                }
            }
        }
    }

    private float bulletFirstPosition = x;

    public void tick(LinkedList<GameObject> object) {
        x += velX;
        Collision();

        float bulletVal = bulletFirstPosition - x;
        if (bulletVal > 800 || bulletVal < -800) {
            handler.removeObject(this);
        }
        bulletShotAnimation.runAnimation();
    }


    public void render(Graphics g) {
        if (Player.playerCreateBounds.getBounds().contains(this.x, this.y)) {
            bulletShotAnimation.drawAnimation(g, (int) x, (int) y, 32, 32);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
