package com.rosa.game.window;

import com.rosa.game.framework.*;
import com.rosa.game.objects.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.time.temporal.Temporal;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = -1124669278749788281L;
    private boolean running = false;
    public static int WIDTH, HEIGHT;

    Handler handler;
    Camera cam;

    static Texture tex;

    private void init() {

        WIDTH = getWidth();
        HEIGHT = getWidth();


        tex = new Texture();
        BufferdImageLoader loader = new BufferdImageLoader();
        BufferedImage level = loader.loadImage("/com/rosa/game/res/GamePlateOne.png");

        //Camera Background:
        cam = new Camera(0, 0);
        //Handler:
        handler = new Handler();
        //Level Image to screen by RGB:
        loadImageLevel(level);
        //KeyBoard:
        this.addKeyListener(new KeyInput(handler));
    }

    public synchronized void start() {

        if (running) {
            return;
        }
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
//                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void tick() {
        handler.tick();

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ObjectId.Player) {
                cam.tick(handler.object.get(i));
            }
        }
    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        //////////  DRAW START //////////
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g2d.translate(Camera.getX(), 150); //Cam start
        g2d.setColor(Color.GRAY);
        handler.render(g);
        g2d.translate(-Camera.getY(), -Camera.getX()); //Cam end
        //////////  DRAW END   //////////
        g.dispose();
        bs.show();

    }

    private void loadImageLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        for (int xx = 0; xx < h; xx++) {
            for (int yy = 0; yy < w; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                //floor Grey upper:
                if (red == 255 && green == 255 && blue == 255)
                    handler.addObject(new Block(xx * 32, yy * 32, 0, ObjectId.Block,handler));
                //floor Grey nothing:
                if (red == 112 && green == 112 && blue == 112)
                    handler.addObject(new Block(xx * 32, yy * 32, 1, ObjectId.Block,handler));
                //floor Of Death:
                if (red == 255 && green == 255 && blue == 0)
                    handler.addObject(new BlockDeath(xx * 32, yy * 32, 2, ObjectId.BlockDeath));
                //floor Of Jump:
                if (red == 0 && green == 255 && blue == 0)
                    handler.addObject(new BlockJump(xx * 32, yy * 32, 3, handler, ObjectId.BlockJump));
                //floor Of Coins:
                if (red == 86 && green == 194 && blue == 177)
                    handler.addObject(new BlockCoin(xx * 32, yy * 32, 2, ObjectId.BlockCoin));

                //// ^ ^ ^ ^ BLOCKS ^ ^ ^ ^ ////

                //Player:
                if (red == 0 && green == 0 && blue == 255)
                    handler.addObject(new Player(xx * 32, yy * 32, handler, ObjectId.Player));

                //Enemey:
                if (red == 255 && green == 0 && blue == 0)
                    handler.addObject(new Enemey(100, 100, xx * 32, yy * 32, handler, ObjectId.Enemy));
            }
        }
    }

    public static Texture getInstance() {
        return tex;
    }

    public static void main(String args[]) {
        new Window(800, 800, "New Game - 2d", new Game());
    }
}