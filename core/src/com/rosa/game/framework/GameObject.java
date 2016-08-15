package com.rosa.game.framework;

import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {

    protected float x, y;
    protected ObjectId id;
    protected float velX, velY;
    protected boolean falling = true;
    protected boolean jumping = false;
    protected int facing = 1;

    public GameObject(float x, float y, ObjectId id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick(LinkedList<GameObject> object);

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public Object getId() {
        return id;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public int getFacing(){
        return facing;
    }
}
