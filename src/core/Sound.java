package core;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/res/sound/Play_Song.wav");
        soundURL[1] = getClass().getResource("/res/sound/Intro_Song.wav");
        soundURL[2] = getClass().getResource("/res/sound/click.mp3");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);

            if (clip != null) {
                clip.stop();
                clip.close();
            }

            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void play(){
        if(clip != null) {
            clip.start();
        }
    }

    public void loop(){
        if(clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    
}
