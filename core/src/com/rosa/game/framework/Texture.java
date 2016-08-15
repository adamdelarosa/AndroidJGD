package com.rosa.game.framework;


import com.rosa.game.window.BufferdImageLoader;

import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet bs, ps, ess, bl, cin;

    public BufferedImage[] block = new BufferedImage[10];
    public BufferedImage[] player = new BufferedImage[10];
    public BufferedImage[] player_jump = new BufferedImage[2];
    public BufferedImage[] enemey_skeleton = new BufferedImage[20];
    public BufferedImage[] bull = new BufferedImage[6];
    public BufferedImage[] coin = new BufferedImage[3];


    public Texture() {

        BufferdImageLoader loader = new BufferdImageLoader();
        BufferedImage block_sheet = null;
        BufferedImage enemey_skeleton_sheet = null;
        BufferedImage player_sheet = null;
        BufferedImage coin = null;

        try {
            block_sheet = loader.loadImage("/com/rosa/game/res/spritesheet.png");
            player_sheet = loader.loadImage("/com/rosa/game/res/playersheet.png");
            enemey_skeleton_sheet = loader.loadImage("/com/rosa/game/res/skeleton.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        ess = new SpriteSheet(enemey_skeleton_sheet);
        bl = new SpriteSheet(player_sheet);
        cin = new SpriteSheet(coin);


        getTextures();
    }

    public void getTextures() {

        // Player:
        //Stand
        player[0] = ps.img(1, 33, 13, 32);
        player[5] = ps.img(163, 33, 13, 32);

        //Run Right
        player[1] = ps.img(15, 35, 17, 30);
        player[2] = ps.img(33, 35, 19, 30);
        player[3] = ps.img(53, 33, 16, 32);
        player[4] = ps.img(70, 36, 20, 29);


        //Run Left
        player[6] = ps.img(232, 36, 20, 29);
        player[7] = ps.img(215, 33, 16, 32);
        player[8] = ps.img(195, 34, 19, 30);
        player[9] = ps.img(177, 35, 17, 30);

        player_jump[0] = ps.img(115, 34, 22, 31);
        player_jump[1] = ps.img(277, 35, 22, 30);


        //Block of Grey upper:
        block[0] = bs.img(72, 49, 21, 21);
        //Block of Grey nothing:
        block[1] = bs.img(49, 72, 21, 21);
        //Block of Death:
        block[2] = bs.img(233, 26, 21, 21);
        block[3] = bs.img(279, 26, 21, 21);
        //Block of Jump:
        block[4] = bs.img(72, 141, 21, 21);
        //BLock of Coins
        block[5] = bs.img(512, 99, 15, 13);
        block[6] = bs.img(535, 99, 15, 13);
        block[7] = bs.img(558, 99, 15, 13);
        block[8] = bs.img(581, 99, 15, 13);


        //Enemey skeleton Left:
        enemey_skeleton[0] = ess.img(14, 1, 30, 46);
        enemey_skeleton[1] = ess.img(53, 1, 30, 46);
        enemey_skeleton[2] = ess.img(89, 1, 31, 45);
        enemey_skeleton[3] = ess.img(127, 1, 32, 45);
        enemey_skeleton[4] = ess.img(167, 1, 31, 45);
        enemey_skeleton[5] = ess.img(205, 1, 30, 46);
        enemey_skeleton[6] = ess.img(242, 1, 29, 46);
        enemey_skeleton[7] = ess.img(277, 1, 32, 45);
        enemey_skeleton[8] = ess.img(316, 1, 33, 45);

        //Enemey skeleton Right:
        enemey_skeleton[9] = ess.img(351, 1, 33, 45);
        enemey_skeleton[10] = ess.img(391, 1, 32, 45);
        enemey_skeleton[11] = ess.img(429, 1, 29, 46);
        enemey_skeleton[12] = ess.img(465, 1, 30, 46);
        enemey_skeleton[13] = ess.img(502, 1, 31, 45);
        enemey_skeleton[14] = ess.img(541, 1, 32, 45);
        enemey_skeleton[15] = ess.img(580, 1, 31, 45);
        enemey_skeleton[16] = ess.img(617, 1, 30, 46);
        enemey_skeleton[17] = ess.img(657, 1, 30, 46);

        //Bullet:
        bull[0] = bl.img(318, 84, 16, 16);
        bull[1] = bl.img(335, 84, 16, 16);
        bull[2] = bl.img(352, 84, 16, 16);
        bull[3] = bl.img(369, 84, 16, 16);
        bull[4] = bl.img(386, 84, 16, 16);
        bull[5] = bl.img(405, 84, 16, 16);
    }
}
