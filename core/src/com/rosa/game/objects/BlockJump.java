package com.rosa.game.objects;

import com.rosa.game.framework.*;
import com.rosa.game.window.Animation;
import com.rosa.game.window.Game;
import com.rosa.game.window.Handler;

import java.awt.*;
import java.util.LinkedList;

public class BlockJump extends GameObject {

    Texture tex = Game.getInstance();
    private int type;
    private Animation jumpBlocks;
    private Handler handler;

    public BlockJump(float x, float y, int type, Handler handler, ObjectId id) {
        super(x, y, id);
        this.type = type;
        this.handler = handler;
        jumpBlocks = new Animation(8, tex.block[2], tex.block[3]);
    }

    private void Collision() {
        if (Player.playerCreateBounds.getBounds().contains(this.x, this.y)) {
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getId() == ObjectId.Player) {
                    if (getBoundsTopJumpArea().intersects(tempObject.getBounds())) {
                        tempObject.setVelY(-12);
                        jumping = false;
                        KeyInput.doubleJump = 1;
                    }
                }
            }
        }
    }

    public void tick(LinkedList<GameObject> object) {
        jumpBlocks.runAnimation();
        Collision();
    }

    public void render(Graphics g) {
        if (Player.playerCreateBounds.getBounds().contains(this.x, this.y)) {
            if (type == 3) {
                g.drawImage(tex.block[4], (int) x, (int) y, 32, 32, null);
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public Rectangle getBoundsTopJumpArea() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}


