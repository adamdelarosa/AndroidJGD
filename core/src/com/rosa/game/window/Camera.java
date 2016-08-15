package com.rosa.game.window;

import com.rosa.game.framework.GameObject;

public class Camera {

    private static float x;
    private static float y;

    public Camera(float x, float y) {
        Camera.x = x;
        Camera.y = y;
    }

    public void tick(GameObject player){
        x = -player.getX() + Game.WIDTH / 2;
        y = -player.getY() + Game.HEIGHT / 2;
    }

    public static float getX() {
        return x;
    }

    public static float getY() {
        return y;
    }
}
