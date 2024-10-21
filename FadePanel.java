import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/** Class to add a black fading overlay. */
public final class FadePanel extends JPanel implements ActionListener {
    Timer alphaTimer = new Timer(15, this);
    float alpha = 0;
    float direct = 1f;

    /** Constructor calling the fade-in. */
    public FadePanel() {
        setBounds(0, 0, 1280, 832);
        setOpaque(false);
        loadScreen();
    }

    /** Fade-out decreasing the alpha. */
    public void removeScreen() {
        direct = -1f;
        alphaTimer.start();
    }

    /** Fade-in increasing the alpha. */
    public void loadScreen() {
        direct = 1f;
        alphaTimer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, 1280, 832);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        alpha = alpha + direct * 0.01f;

        if (alpha > 1f) {
            alpha = 1f;
            alphaTimer.stop();
            removeScreen();
        } else if (alpha < 0) {
            alpha = 0;
            alphaTimer.stop();
        }

        repaint();
    }
}
