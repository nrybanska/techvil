import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/** Class for handling the changes of background. */
public class Background extends JPanel {
    private int level;

    /** Adding the background graphics.
     * @param level int used to load the correct picture
     */
    public Background(int level) {
        this.level = level;
        setBounds(0, 0, 1280, 832);
    }

    /** Changing the level and background setter function.
     * @param newLevel level to which we want to change
     */
    public void setLevel(int newLevel) {
        this.level = newLevel;
        repaint(); 
    }

    // Overriding the graphics to draw the background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        String filePath = "levels/" + level + ".png";
        ImageIcon background = new ImageIcon(filePath);

        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
    }
}
