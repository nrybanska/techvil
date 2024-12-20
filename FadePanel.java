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
    // Variables used to set the timers
    private final int offsetDelay = 500;
    private final int mainInterval = 10;
    private float alpha = 0;
    private float direct = 1f; // variable to adjust for fade-in/fade-out
    private final int level;

    Timer alphaTimer = new Timer(mainInterval, this);

    /** Constructor calling the fade-in. */
    public FadePanel(int currentLvl) {
        this.level = currentLvl;

        setBounds(0, 0, 1280, 832);
        setOpaque(false);
        loadScreen();
    }

    /** Fade-out decreasing the alpha. */
    public void removeScreen() {
        direct = -1f;

        // Adding an offset to mitigate environment switch glitches
        Timer offsetTimer = new Timer(offsetDelay, null);
        offsetTimer.addActionListener(e -> {
            alphaTimer.start();
            offsetTimer.stop();
        });
        offsetTimer.start();
    }

    /** Fade-in increasing the alpha. */
    public void loadScreen() {
        direct = 1f;

        // Adding an offset timer to let the player view the message displayed
        Timer offsetTimer = new Timer(offsetDelay, null);
        offsetTimer.addActionListener(e -> {
            alphaTimer.start();
            offsetTimer.stop();
        });
        offsetTimer.start();
    }

    /** Override for the displaying of different alpha. */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        // Changing the fade panel colour for the last "level"
        Color color = level == 5 ? Color.red : Color.black;
        g2d.setColor(color);
        g2d.fillRect(0, 0, 1280, 832);
    }

    /** Override to act as the event listener in the timer. */
    @Override
    public void actionPerformed(ActionEvent e) {
        alpha = alpha + direct * 0.01f;

        if (alpha > 1f) {
            alpha = 1f;
            alphaTimer.stop();

            // Once the fade-in is done the fade-out is called
            removeScreen();
        } else if (alpha < 0) {
            alpha = 0;
            alphaTimer.stop();
        }

        repaint();
    }
}
