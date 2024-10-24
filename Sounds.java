import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/** Class for accessing and playing different sounds. */
public class Sounds {
    // Variables to access available sounds
    private final String[] soundPaths = {"sounds/sound.aifc", "sounds/countdown.aifc"};
    private final String filePath;

    private Clip clip;
    private AudioInputStream audioInput;

    /** Constructor which starts the sound.
     * @param sound type of sound to be played
     */
    public Sounds(int sound) {
        this.filePath = soundPaths[sound];

        playSound();
    }

    /** Function to read file and play sound. */
    private void playSound() {
        try {
            audioInput = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            System.out.println("Clip started");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /** Function for stoping sound from outside of this class. */
    public void stop() {
        clip.stop();
    }
}
