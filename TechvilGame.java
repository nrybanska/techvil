import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

class TechvilGame extends JFrame {

    /** game. */
    public TechvilGame() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e);
        }

        setTitle("Techvil");
        setSize(1280, 832);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize a pane for the main content
        JLayeredPane contentPane = new JLayeredPane();
        setContentPane(contentPane);

        // Add background to the pane
        JPanel backgroundPanel = new Background();
        contentPane.add(backgroundPanel, Integer.valueOf(0));

        // Add "screen", text and button
        JPanel popPanel = new Pop();
        contentPane.add(popPanel, Integer.valueOf(1));

        // Add GIF
        JPanel gifCoffee = new Gif();
        contentPane.add(gifCoffee, Integer.valueOf(2));

        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        TechvilGame game = new TechvilGame();
    }
}