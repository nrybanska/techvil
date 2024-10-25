import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/** Class for accessing and playing different sounds. */
public class Sounds {
    // Variables to access available sounds
    private final String countDownSound = "sounds/countdown.aifc";
    private final String[] memeSounds = {"sounds/myinstants1.aifc", "sounds/myinstants2.aifc", 
        "sounds/myinstants3.aifc"};
    private final String windowsSound = "sounds/Windowsxp.aifc";
    private final String failSound = "sounds/Fail.aifc";

    private final String filePath;

    private Clip clip;
    private AudioInputStream audioInput;

    /** Constructor which starts the sound.
     * @param sound type of sound to be played
     */
    public Sounds(int sound) {
        switch (sound) {
            case 0 -> {
                int index = (int) Math.round(Math.random() * (memeSounds.length - 1));
                this.filePath = memeSounds[index];
            }
            case 1 -> {
                this.filePath = windowsSound;
            }
            case 2 -> {
                this.filePath = failSound;
            }
            case 3 -> {
                this.filePath = countDownSound;
            } 
            default -> {
                System.out.println("Error: Incorrect sound type!");
                filePath = failSound;
            }
        }

        playSound();
    }

    /** Function to read file and play sound. */
    private void playSound() {
        try {
            audioInput = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /** Function for stoping sound from outside of this class. */
    public void stop() {
        clip.stop();
    }
}
