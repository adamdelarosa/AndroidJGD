package com.rosa.game.objects;

import com.rosa.game.framework.GameObject;
import com.rosa.game.framework.ObjectId;
import com.rosa.game.framework.Texture;
import com.rosa.game.window.Animation;
import com.rosa.game.window.Game;

import java.awt.*;
import java.util.LinkedList;

public class BlockCoin extends GameObject {

    Texture tex = Game.getInstance();
    private int type;
    private Animation coinBlocks;


    public BlockCoin(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
        coinBlocks = new Animation(8, tex.block[5], tex.block[6], tex.block[7], tex.block[8]);
    }


    public void tick(LinkedList<GameObject> object) {
        coinBlocks.runAnimation();
    }

    public void render(Graphics g) {
        if (Player.playerCreateBounds.getBounds().contains(this.x, this.y)) {
            if (type == 2) {
                coinBlocks.drawAnimation(g, (int) x, (int) y, 32, 32);
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}


