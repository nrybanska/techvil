import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Javadoc. */
public class Gif extends JPanel {
    private final String[] gifPaths = {"gifs/Coffee.gif", "gifs/timer.gif", 
        "gifs/timer2.gif"};
    private final String gifPath;
    
    /** Javadoc. */
    public Gif(int gifIndex) {
        this.gifPath = gifPaths[gifIndex];
        setOpaque(false);

        switch (gifIndex) {
            case 0 -> {
                setBounds(1050, 450, 200, 200);
            }
            case 1 -> {
                setBounds(1000, 5, 300, 300);
            }
            case 2 -> {
                setBounds(950, 300, 300, 200);   
            }
            default -> {
                System.out.println("Error: Incorrect Gif index");
            }
        }

        ImageIcon gifIcon = new ImageIcon(gifPath);
        JLabel gifLabel = new JLabel(gifIcon);

        add(gifLabel);
    }
}
