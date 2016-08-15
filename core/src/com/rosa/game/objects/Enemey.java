package com.rosa.game.objects;

import com.rosa.game.framework.GameObject;
import com.rosa.game.framework.ObjectId;
import com.rosa.game.framework.Texture;
import com.rosa.game.window.Animation;
import com.rosa.game.window.Game;
import com.rosa.game.window.Handler;

import java.awt.*;
import java.util.LinkedList;


public class Enemey extends GameObject {

    private float width = 32, height = 64;
    boolean movingRight = true;
    private Handler handler;
    private Texture tex = Game.getInstance();
    private Animation enemeySkeletonWalkR, enemeySkeletonWalkL;


    public Enemey(float x, float y, int enemeyLocaionX, int enemeyLocaionY, Handler handler, ObjectId id) {
        super(x, y, id);
        velX = enemeyLocaionX;
        velY = enemeyLocaionY;
        enemeySkeletonWalkL = new Animation(3, tex.enemey_skeleton[0], tex.enemey_skeleton[1], tex.enemey_skeleton[2], tex.enemey_skeleton[3], tex.enemey_skeleton[4], tex.enemey_skeleton[5], tex.enemey_skeleton[6], tex.enemey_skeleton[7], tex.enemey_skeleton[8]);
        enemeySkeletonWalkR = new Animation(3, tex.enemey_skeleton[9], tex.enemey_skeleton[10], tex.enemey_skeleton[11], tex.enemey_skeleton[12], tex.enemey_skeleton[13], tex.enemey_skeleton[14], tex.enemey_skeleton[15], tex.enemey_skeleton[16], tex.enemey_skeleton[17]);

        this.handler = handler;
    }

    public void tick(LinkedList<GameObject> object) {
       // if (Player.playerCreateBounds.getBounds().contains(this.x, this.y)) {
            if (movingRight) {
                velX += 2;

            } else {
                velX -= 2;
            }
            Collision();
            enemeySkeletonWalkR.runAnimation();
            enemeySkeletonWalkL.runAnimation();
        //}
    }

    public void render(Graphics g) {
//        if (Player.playerCreateBounds.getBounds().contains(this.x, this.y)) {
            if (movingRight) {
                enemeySkeletonWalkR.drawAnimation(g, (int) velX - 10, (int) velY + 20, (int) width + 10, (int) height + 10);
            } else {
                enemeySkeletonWalkL.drawAnimation(g, (int) velX - 10, (int) velY + 20, (int) width + 10, (int) height + 10);
            }
        }
//    }


    private void Collision() {


//        if (Player.playerCreateBounds.getBounds().contains(this.x, this.y)) {

            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getId() == ObjectId.Player) {

                    if (getBoundsRight().intersects(tempObject.getBounds())) {
                        tempObject.setVelY(-1);
                        movingRight = true;
                        velX += 2;
                    }

                    if (getBoundsLeft().intersects(tempObject.getBounds())) {
                        tempObject.setVelY(-1);
                        movingRight = false;
                        velX -= 2;
                    }

                    if (getBoundsTop().intersects(tempObject.getBounds())) {
                        tempObject.setVelY(-1);
                    }
                }


                if (tempObject.getId() == ObjectId.Block) {

                    if (getBoundsRight().intersects(tempObject.getBounds())) {
                        movingRight = true;
                        velX += 2;
                    }
                    if (getBoundsLeft().intersects(tempObject.getBounds())) {
                        movingRight = false;
                        velX -= 2;
                    }
                }
            }
        }
//    }


    public Rectangle getBounds() {
        return new Rectangle((int) velX, (int) velY, 20, 80);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int) velX + (width / 2) - ((width / 2) / 2)), (int) velY, (int) width / 2, (int) height / 2);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) velX - 10, (int) velY + 20, 5, 70);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) velX + 10, (int) velY + 20, 5, 70);
    }
}
