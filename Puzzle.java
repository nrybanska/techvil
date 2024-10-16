import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Puzzle extends JPanel implements MouseListener {
    private int gridSize = 3;
    private int WIDTH;
    private int HEIGHT;
    private final int OFFSETSIDE = 102;
    private final int OFFSETTOP = 14;
    private final int MAX = 250;

    private final int TOPLEFTX = 407;
    private final int TOPLEFTY = 200;

    private int[] sequence = {1, 2, 3};
    private int[] playerSequence;

    private JPanel[][] gridPanel = new JPanel[gridSize][gridSize];
    int currentIndex;

    public Puzzle(int gridSize) {
        this.gridSize = gridSize;
        this.WIDTH = MAX/gridSize;
        this.HEIGHT = MAX/gridSize;
        //this.gridPanel = ;

        setLayout(new GridLayout(gridSize, gridSize));

        setBounds(407, 200, 464, 275);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                JPanel square = new JPanel();

                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                square.setBackground(Color.green);

                gridPanel[i][j] = square;
                add(square);
            }
            
        }

        addMouseListener(this);
        //create sequence
    }

    private void changeColor(Color color, int delay, int xIndex, int yIndex) {
        gridPanel[xIndex][yIndex].setBackground(color);
    }

    public void showSequence(int[] sequence) {
        Timer timer = new Timer(1000, null);  // Create a timer with 1 second delay

        timer.addActionListener(e -> {
            if (currentIndex < sequence.length) {
                int seqValue = sequence[currentIndex];

                // Calculate the row and column of the square in the grid
                int xIndex = seqValue / gridSize;
                int yIndex = seqValue % gridSize;

                // Change the current square's color to red
                gridPanel[xIndex][yIndex].setBackground(Color.red);


                Timer resetTimer = new Timer(500, resetEvent -> {
                    gridPanel[xIndex][yIndex].setBackground(Color.green);
                });
                resetTimer.setRepeats(false);  // Run only once
                resetTimer.start();
                // Increment the index to point to the next square in the sequence
                currentIndex++;
            } else {
                // Stop the timer when all squares in the sequence have been updated
                timer.stop();
            }
        });

        currentIndex = 0;
        timer.start();
    }

    public int calculateIndex(int x, int y) {
        int xIndex = x % WIDTH;
        int yIndex = y % HEIGHT;

        int finalIndex = yIndex * gridSize + xIndex;     

        return finalIndex;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int[] seq = {1, 4, 6};
        showSequence(seq);

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
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}