
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/** Javadoc!!!. */
public class Sounds {
    private final String[] soundPaths = {"sounds/sound.mp3"};
    private String filePath;
    private int currentSound;

    private Clip clip;
    private AudioInputStream audioInput;

    public Sounds(int sound) {
        this.filePath = soundPaths[sound];
        this.currentSound = sound;

        playSound();
    }

    private void playSound() {
        try {
            audioInput = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

            clip = AudioSystem.getClip();
            clip.open(audioInput);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
