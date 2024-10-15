import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class Puzzle extends JPanel implements MouseListener {
    private int gridSize;
    private int WIDTH;
    private int HEIGHT;
    private final int OFFSETSIDE = 102;
    private final int OFFSETTOP = 14;
    private final int MAX = 250;

    private final int TOPLEFTX = 407;
    private final int TOPLEFTY = 200;


    public Puzzle(int gridSize) {
        this.gridSize = gridSize;
        this.WIDTH = MAX/gridSize;
        this.HEIGHT = MAX/gridSize;
        setBounds(407, 200, 464, 275);
    }

    public int calculateIndex(int x, int y) {
        int xIndex = x % WIDTH;
        int yIndex = y % HEIGHT;

        int finalIndex = yIndex * gridSize + xIndex;     

        return finalIndex;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.green);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                g.drawRect(j*WIDTH+OFFSETSIDE, i*HEIGHT+OFFSETTOP, WIDTH, HEIGHT);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() - (TOPLEFTX+OFFSETSIDE);
        int y = e.getY() - (TOPLEFTY+OFFSETTOP);

        boolean outOfBoundsX = x < 0 || x > MAX;
        boolean outOfBoundsY = y < 0 || y > MAX;
        if (!outOfBoundsX && !outOfBoundsY) {
            int pressedRect = calculateIndex(x, y);
            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
