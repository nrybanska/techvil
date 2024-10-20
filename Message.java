
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Showing small messages on the screen. */
public class Message extends JPanel {
    private final int x = 440;
    private final int y = 270;
    private final int width = 400;
    private final int height = 100;

    private String message;

    /** Constructor with a switch to decipher message type. */
    public Message(int messageNum) {
        switch (messageNum) {
            case 1:
                message = "<html><h1>Great job, that is correct!</h1></html>";
                break;
            case 2:
                message = "<html><h1>Oh no, try again :(</h1></html>";
                break;
            case 3:
                message = "<html><h1>Congratulations you won!</h1><p>(but at what cost)</p></html>";
                break;
            default:
                System.out.println("Error incorrect message type");;
        }

        //setOpaque(false);
        setBounds(x, y, width, height);

        JLabel mesJLabel = new JLabel();
        mesJLabel.setText(message);

        add(mesJLabel);
    }
}
