package com.rosa.game.objects;

import com.rosa.game.framework.GameObject;
import com.rosa.game.framework.ObjectId;
import com.rosa.game.framework.Texture;
import com.rosa.game.window.Animation;
import com.rosa.game.window.Game;

import java.awt.*;
import java.util.LinkedList;

public class BlockDeath extends GameObject {

    Texture tex = Game.getInstance();
    private int type;
    private Animation waterDeathBlocks;

    public BlockDeath(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
        waterDeathBlocks = new Animation(8, tex.block[2], tex.block[3]);
    }


    public void tick(LinkedList<GameObject> object) {
        waterDeathBlocks.runAnimation();
    }

    public void render(Graphics g) {
        if (Player.playerCreateBounds.getBounds().contains(this.x, this.y)) {

            //Death water block:
            if (type == 2) {
                waterDeathBlocks.drawAnimation(g, (int) x, (int) y, 32, 32);
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}


