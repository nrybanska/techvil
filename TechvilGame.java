import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

class TechvilGame extends JFrame {
    JPanel north;
    static Pop center;
    JPanel south;

    /** game. */
    TechvilGame() {
        setTitle("Techvil");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1280, 832);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        north = new JPanel();
        add(north, BorderLayout.NORTH);

        // Use custom panel for drawing
        center = new Pop();
        add(center, BorderLayout.CENTER);

        south = new JPanel();
        add(south, BorderLayout.SOUTH);

        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        TechvilGame game = new TechvilGame();
    }
}