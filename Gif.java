import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Javadoc. */
public class Gif extends JPanel {
    /** Javadoc. */
    public Gif() {
        setOpaque(false);
        setBounds(1050, 350, 200, 200);

        ImageIcon gifIcon = new ImageIcon("levels/Coffee.gif");
        JLabel gifLabel = new JLabel(gifIcon);

        add(gifLabel);
    }
}
