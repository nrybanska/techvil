import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Javadoc. */
public class Gif extends JPanel {
    private final String[] gifPaths = {"gifs/Coffee.gif", "gifs/timer3.gif"};
    private final String gifPath;
    
    /** Javadoc. */
    public Gif(int gifIndex) {
        this.gifPath = gifPaths[gifIndex];

        setOpaque(false);

        switch (gifIndex) {
            case 0 -> {
                setBounds(1050, 350, 200, 200);
            }
            case 1 -> {
                setBounds(1000, 0, 300, 300);
            }
            default -> {
                System.out.println("Error: Incorrect Gif index");
            }
        }

        ImageIcon gifIcon = new ImageIcon(this.gifPath);
        JLabel gifLabel = new JLabel(gifIcon);

        add(gifLabel);
    }
}
