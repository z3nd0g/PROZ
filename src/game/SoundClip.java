package game;

import java.io.*;
import javax.sound.sampled.*;

public class SoundClip {

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
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (LineUnavailableException e) {
           e.printStackTrace();
        }       
    }
}