
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
    JButton okButton;
    PanelRemoveListener panelRemoveListener;

    /** Load the popup panel. */
    public Pop(PanelRemoveListener panelRemoveListener) {
        this.panelRemoveListener = panelRemoveListener;
        
        // Creating a rectangle
        setBackground(Color.blue);
        setOpaque(true);
        setBounds(407, 200, 464, 275);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        // Adding Text
        JLabel textLabel = new JLabel();
        textLabel.setForeground(Color.WHITE);
        //textLabel.setLineWrap(true);
        try {
            String content = new String(Files.readAllBytes(Paths.get("levels/intro.txt")));
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
        if(e.getSource() == okButton) {
            panelRemoveListener.removePanel(Pop.this);
        }

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Pop extends JFrame implements ActionListener {
    // popup
    Popup p;
    int width = 400;
    int height = 400;

    // constructor
    Pop() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame f = new JFrame("pop");

        JLabel l = new JLabel("This is a popup");
        f.setSize(width, height);
        f.setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        f.setResizable(false);
        f.setUndecorated(true);

        PopupFactory pf = new PopupFactory();

        JPanel p2 = new JPanel();

        p2.setBackground(Color.red);
        p2.add(l);

        p = pf.getPopup(f, p2, 180, 100);

        JButton b = new JButton("click");

        b.addActionListener(this);

        JPanel p1 = new JPanel();

        p1.add(b);
        f.add(p1);
        // f.show();
    }

    public void actionPerformed(ActionEvent e) {
        p.show();
    }

    // main class
    public static void main(String args[]) {
        Pop p = new Pop();
    }
}