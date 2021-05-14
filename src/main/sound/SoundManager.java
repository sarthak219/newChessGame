package main.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * represents the class which plays the file at the given location
 */
public class SoundManager {

    //EFFECTS: plays the audio present at the given source
    public void play(String source) {
        try {
            File file = new File(source);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
