
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Showing small messages on the screen. */
public class Message extends JPanel {
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    private String message;

    /** Constructor with a switch to decipher message type. */
    public Message(int messageNum) {
        switch (messageNum) {
            case 1 -> {
                message = "<html><h1>Great job, that is correct!</h1></html>";
                x = 480;
                y = 300;
                width = 320;
                height = 70;
            }
            case 2 -> {
                message = "<html><h1>Oh no, try again :(</h1></html>";
                x = 510;
                y = 300;
                width = 260;
                height = 70;
            }
            case 3 -> {
                message = "<html><h1>Congratulations you won!</h1><p>(but at what cost)</p></html>";
                x = 465;
                y = 295;
                width = 350;
                height = 90;
            }
            case 4 -> {
                message = "<html><h1>Oh no, your time ran out :(</h1></html>";
                x = 470;
                y = 300;
                width = 340;
                height = 70;
            }
            default -> {
                System.out.println("Error incorrect message type");
                x = 440;
                y = 270;
                width = 400;
                height = 100;
            }
        }

        setBounds(x, y, width, height);
        setBackground(Color.lightGray);
        setBorder(BorderFactory.createLineBorder(Color.black, 2));

        JLabel mesJLabel = new JLabel();
        mesJLabel.setText(message);

        add(mesJLabel);
    }
}
