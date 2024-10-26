import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Class for displaying different GIFs. */
public class Gif extends JPanel {
    // Array of all available GIFs
    private final String[] gifPaths = {"gifs/timer.gif", 
        "gifs/timer2.gif"};
    private final String gifPath;
    
    /** Constructor for creating GIF.
     * @param gifIndex used to choose the GIF
     */
    public Gif(int gifIndex) {
        this.gifPath = gifPaths[gifIndex];
        setOpaque(false);

        // Setting the position of each GIF
        switch (gifIndex) {
            case 0 -> {
                setBounds(1000, 5, 300, 300);
            }
            case 1 -> {
                setBounds(950, 300, 300, 200);   
            }
            default -> {
                System.out.println("Error: Incorrect Gif index");
            }
        }

        // Adding the GIF itself
        ImageIcon gifIcon = new ImageIcon(gifPath);
        JLabel gifLabel = new JLabel(gifIcon);

        add(gifLabel);
    }
}
