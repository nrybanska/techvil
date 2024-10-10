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