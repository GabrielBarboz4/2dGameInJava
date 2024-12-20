package game.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundUrl[] = new URL[30];

    public Sound() {

        soundUrl[0] = getClass().getClassLoader().getResource("sound/BlueBoyAdventure.wav");
        soundUrl[1] = getClass().getClassLoader().getResource("sound/coin.wav");
        soundUrl[2] = getClass().getClassLoader().getResource("sound/fanfare.wav");
        soundUrl[3] = getClass().getClassLoader().getResource("sound/powerup.wav");
        soundUrl[4] = getClass().getClassLoader().getResource("sound/unlock.wav");
        soundUrl[5] = getClass().getClassLoader().getResource("sound/dooropen.wav");

    }

    public void setFile (int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception _) {
        }
    }

    public void play () {

        clip.start();
    }

    public void loop () {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop () {
        clip.stop();
    }
}
