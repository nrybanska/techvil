import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Pop extends JPanel {
    /** Load image. */
    private String infoText = "Lorem ipsum dolor sit amet, "
    +"consectetur adipiscing elit, sed do eiusmod \n tempor incididunt "
    +"ut labore et dolore magna aliqua. Ut enim ad \n minim veniam, quis "
    +"nostrud exercitation ullamco laboris nisi ut aliquip \n ex ea commodo "
    +"consequat. Duis aute irure dolor in reprehenderit in voluptate velit ";

    public Pop() {
        // Creating a rectangle
        setBackground(Color.blue);
        setOpaque(true);
        setBounds(407, 200, 464, 275);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        // Adding Text
        JLabel textLabel = new JLabel(infoText);
        textLabel.setForeground(Color.WHITE);

        // Setting the grid position
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_START; 
        c.insets = new Insets(10, 0, 10, 0);

        add(textLabel, c);

        // Adding Button
        JButton okButton = new JButton("OK");

        // Setting the grid position
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weighty = 1;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10, 5, 10, 5);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;

        add(okButton, c);
    }
}