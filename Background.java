
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/** Javadoc.*/
public class Background extends JPanel {
    private final String[] levels = {"levels/level1.png", "levels/level2.png",
        "levels/level3.png", "levels/level4.png", "levels/level5.png"};

    private final int currentLvl;

    /** Constructor with the current level to use the proper image. */
    public Background(int currentLvl) {
        this.currentLvl = currentLvl;

        setBounds(0, 0, 1280, 832);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon background = new ImageIcon(levels[currentLvl]);
        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
    }
}
