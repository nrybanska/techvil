
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/** Javadoc.*/
public class Background extends JPanel {

    public Background() {
        setBounds(0, 0, 1280, 832);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon background = new ImageIcon("levels/level1.png");
        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
    }
}
