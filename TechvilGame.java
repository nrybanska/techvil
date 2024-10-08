// java Program to create a simple JWindow 
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*; 
import javax.swing.*; 


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

        setVisible(true);
        setResizable(false);
    }
    


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    } 

    public static void main(String[] args) {
        TechvilGame game = new TechvilGame();
        Pop p = new Pop();
    }   
} 