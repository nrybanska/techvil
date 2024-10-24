import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Class for creating the "popup" window on the in-game screen. */
class Pop extends JPanel {
    // Variables used to define the panel bounds
    private final int width = 464;
    private final int height = 275;
    private final int offsetSide = 407;
    private final int offsetTop = 200;

    // Variables to figure out which text file to read
    private final String[] textFiles = {"textfiles/intro.txt"};
    private final int currentLvl;

    JButton okButton;

    // Interface for removing this panel
    PanelRemoveListener panelRemoveListener;

    /** Loading the pop panel.
     * @param panelRemoveListener is the interface passed from TechvilGame
     * @param currentLvl is the level which is used to load the correct text
     */
    public Pop(PanelRemoveListener panelRemoveListener, int currentLvl) {
        this.panelRemoveListener = panelRemoveListener;
        this.currentLvl = currentLvl;
        
        // Creating the screen
        setBackground(Color.blue);
        setBounds(offsetSide, offsetTop, width, height);        
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        // Adding Text
        JLabel textLabel = new JLabel();
        textLabel.setForeground(Color.WHITE);

        try {
            // !!! Add Text files currentLvl !!!
            String content = new String(Files.readAllBytes(Paths.get(textFiles[0])));
            textLabel.setText(content);
        } catch (IOException e) {
            textLabel.setText("Error: Unable to load text from file.");
        }

        // Setting the grid position for the text
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_START; 
        c.insets = new Insets(10, 10, 10, 0);

        add(textLabel, c);

        // Adding Button
        String buttonText = currentLvl == 1 ? "Start game" : "Ok";
        okButton = new JButton(buttonText);

        // Adding the event listener to the button
        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelRemoveListener.changePanel(true, 0, true);
            }
        });

        // Setting the grid position for the button
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