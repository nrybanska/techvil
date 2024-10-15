import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;

public class Sequence extends JPanel {

    private void drawStuff(int[] sequence, int gridSize, Graphics g) {
        for (int i = 0; i < sequence.length; i++) {
            int xLoc = (sequence[i]%gridSize)*WIDTH;
            int yLoc = (int) Math.floor(sequence[i]/gridSize)*HEIGHT;

            g.setColor(Color.red);
            g.fillRect(xLoc, yLoc, WIDTH, HEIGHT);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                System.out.println("got interrupted!");
            }

            g.setColor(Color.white);
            g.fillRect(xLoc, yLoc, WIDTH, HEIGHT);
        }
    }
    public Sequence(int[] sequence, int gridSize, Graphics g) {

        setBounds(407, 200, 464, 275);
        drawStuff(sequence, gridSize, g);
    }
}
