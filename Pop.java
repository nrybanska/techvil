import java.awt.*;
import javax.swing.*;

class Pop extends JPanel {
    private ImageIcon imageIcon;
    private ImageIcon gifIcon;
	
	/** Load image. */
	Pop() {
        imageIcon = new ImageIcon("levels/level1.png");
        gifIcon = new ImageIcon("levels/Coffee.gif");

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the image
        if (imageIcon != null) {
            g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
        }

        // Draw the rectangle on top of the image
        g.setColor(Color.pink);
        g.fillRect((1280 - 466) / 2, (832) / 2 - 228, 464, 260);
    }
}