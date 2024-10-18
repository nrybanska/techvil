import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/** Javadoc. */
public class Background extends JPanel {
    private int level;

    public Background(int level) {
        this.level = level;
        setBounds(0, 0, 1280, 832);
    }

    public void setLevel(int newLevel) {
        this.level = newLevel;
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon background = new ImageIcon("levels/" + level + ".png");

        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
    }
}
