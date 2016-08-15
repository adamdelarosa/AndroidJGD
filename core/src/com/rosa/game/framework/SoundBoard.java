package com.rosa.game.framework;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SoundBoard {

    public void soundPlayer(int soundNum) {
        try {
            List<InputStream> randomFireBlastSound = new ArrayList();

            randomFireBlastSound.add(0, ClassLoader.getSystemResourceAsStream("com/rosa/game/res/Sounds/Laser_Shoot1.wav"));
            randomFireBlastSound.add(1, ClassLoader.getSystemResourceAsStream("com/rosa/game/res/Sounds/Laser_Shoot1.wav"));
            randomFireBlastSound.add(2, ClassLoader.getSystemResourceAsStream("com/rosa/game/res/Sounds/Laser_Shoot2.wav"));
            randomFireBlastSound.add(3, ClassLoader.getSystemResourceAsStream("com/rosa/game/res/Sounds/Laser_Shoot3.wav"));
            randomFireBlastSound.add(4, ClassLoader.getSystemResourceAsStream("com/rosa/game/res/Sounds/Laser_Shoot4.wav"));
            randomFireBlastSound.add(5, ClassLoader.getSystemResourceAsStream("com/rosa/game/res/Sounds/jump.wav"));
            randomFireBlastSound.add(6, ClassLoader.getSystemResourceAsStream("com/rosa/game/res/Sounds/enemyTouch.wav"));
            randomFireBlastSound.add(7, ClassLoader.getSystemResourceAsStream("com/rosa/game/res/Sounds/enemyDeath.wav"));
            randomFireBlastSound.add(8, ClassLoader.getSystemResourceAsStream("com/rosa/game/res/Sounds/enemyDead.wav"));
            randomFireBlastSound.add(9, ClassLoader.getSystemResourceAsStream("com/rosa/game/res/Sounds/pluckWall.wav"));
            randomFireBlastSound.add(10, ClassLoader.getSystemResourceAsStream("com/rosa/game/res/Sounds/pluck.wav"));
            randomFireBlastSound.add(11, ClassLoader.getSystemResourceAsStream("com/rosa/game/res/Sounds/coin.wav"));


            AudioInputStream audioIn = AudioSystem.getAudioInputStream(randomFireBlastSound.get(soundNum));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float volumeSet = -15.0f;
            gainControl.setValue(volumeSet); // Reduce volume by 10 decibels.
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
