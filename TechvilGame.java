import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class TechvilGame extends JFrame implements ActionListener {
    JPanel north;
    JPanel center;
    JPanel south;

    /** game. */
    TechvilGame() {

        setTitle("Techvil");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLayout(new BorderLayout());

        north = new JPanel();
        add(north, BorderLayout.NORTH);

        center = new JPanel();
        add(center, BorderLayout.CENTER);

        south = new JPanel();
        add(south, BorderLayout.SOUTH);

        // Load and add the PNG image to the center panel
        JLabel pngComponent = new JLabel(new ImageIcon("levels/level1.png"));
        pngComponent.setSize(screenSize.width, screenSize.height);
        center.add(pngComponent);

        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle actions here if needed
    }

    public static void main(String[] args) {
        TechvilGame game = new TechvilGame();
        Pop p = new Pop();  // Assuming Pop is implemented elsewhere
    }
}
