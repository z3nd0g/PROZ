package game;

import java.io.*;
import javax.sound.sampled.*;

/**
 * Responsible for playing all sounds in the game.
 * 
 */

public class SoundClip {

    /**
     * Used for looping sounds.
     */
    
    public static void loopBackgroundMusic() {
        try {
            // Open an audio input stream.           
           File backgroundMusic = new File("LoungeGame2.wav"); 
           AudioInputStream audioIn = AudioSystem.getAudioInputStream(backgroundMusic);              

           DataLine.Info info = new DataLine.Info(Clip.class, audioIn.getFormat());
           Clip clip = (Clip)AudioSystem.getLine(info);

           // Open audio clip and load samples from the audio input stream.
           clip.open(audioIn);
           clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        }
    }
    
    /**
     * Used for short sounds.
     * 
     * @param fileName 
     */
    public static void play(String fileName) {
        try {
            // Open an audio input stream.
            File sound = new File(fileName); 
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
            
            DataLine.Info info = new DataLine.Info(Clip.class, audioIn.getFormat());
            
            // Get a sound clip resource.
            Clip clip = (Clip)AudioSystem.getLine(info);
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }       
    }
}