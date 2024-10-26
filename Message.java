
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Class for showing small messages on the screen. */
public class Message extends JPanel {
    // Variables for configuring panel bounds
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    private String message;

    /** Constructor for setting the correct bounds and content.
     * @param messageNum is used in a switch to determine content
     */
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
                message = "<html><h1>Oh no, your time ran out :(</h1></html>";
                x = 470;
                y = 300;
                width = 340;
                height = 70;
            }
            default -> {
                System.out.println("Error incorrect message type");
                x = 470;
                y = 300;
                width = 340;
                height = 70;
            }
        }

        // Setting the previously assigned variables
        setBounds(x, y, width, height);
        setBackground(Color.lightGray);
        setBorder(BorderFactory.createLineBorder(Color.black, 2));

        JLabel mesJLabel = new JLabel();
        mesJLabel.setText(message);

        add(mesJLabel);
    }
}
