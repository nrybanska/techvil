import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Pop extends JPanel implements ActionListener {
    private final int width = 464;
    private final int height = 275;
    private final int offsetSide = 407;
    private final int offsetTop = 200;

    private final String[] textFiles = {"textfiles/intro.txt"};

    JButton okButton;
    PanelRemoveListener panelRemoveListener;

    /** Load the popup panel. */
    public Pop(PanelRemoveListener panelRemoveListener) {
        this.panelRemoveListener = panelRemoveListener;
        
        // Creating the screen
        setBackground(Color.blue);
        setBounds(offsetSide, offsetTop, width, height);        
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        // Adding Text
        JLabel textLabel = new JLabel();
        textLabel.setForeground(Color.WHITE);

        try {
            String content = new String(Files.readAllBytes(Paths.get(textFiles[0])));
            textLabel.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
            textLabel.setText("Error: Unable to load text from file.");
        }

        // Setting the grid position
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
        okButton = new JButton("OK");
        okButton.addActionListener(this);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            panelRemoveListener.removePanel(true);
        }
    }
}