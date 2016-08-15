package com.rosa.game.objects;

import com.rosa.game.framework.GameObject;
import com.rosa.game.framework.ObjectId;
import com.rosa.game.framework.Texture;
import com.rosa.game.window.Animation;
import com.rosa.game.window.Game;
import com.rosa.game.window.Handler;
import java.awt.*;
import java.util.LinkedList;

public class Block extends GameObject {

    Texture tex = Game.getInstance();
    private int type;
    private Animation waterDeathBlocks;
    Handler handler;

    public Block(float x, float y, int type, ObjectId id, Handler handler) {
        super(x, y, id);
        this.type = type;
        this.handler = handler;
        waterDeathBlocks = new Animation(8, tex.block[2], tex.block[3]);

    }


    public void tick(LinkedList<GameObject> object) {
        waterDeathBlocks.runAnimation();
        collision();
    }

    private void collision() {
    }


    public void render(Graphics g) {

        if (Player.playerCreateBounds.getBounds().contains(this.x, this.y)) {
            //BLock grey1
            if (type == 0) {
                g.drawImage(tex.block[0], (int) x, (int) y, 32, 32, null);
            }
            //BLock grey2
            if (type == 1) {
                g.drawImage(tex.block[1], (int) x, (int) y, 32, 32, null);
            }
        }
    }


    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}


