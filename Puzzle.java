import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Puzzle extends JPanel {
    private final int WIDTH = 464;
    private final int HEIGHT = 275;
    private final int OFFSET_SIDE = 407;
    private final int OFFSET_TOP = 200;

    private int gridSize;

    private JPanel[][] gridPanel;
    private int currentIndex;

    PlayerSequence playerSequence;
    private boolean setup = false;

    public Puzzle(PlayerSequence playerSequence, int gridSize) {
        this.playerSequence = playerSequence;
        this.gridSize = gridSize;
        this.gridPanel = new JPanel[gridSize][gridSize];

        setLayout(new GridLayout(gridSize, gridSize));

        setBounds(OFFSET_SIDE, OFFSET_TOP, WIDTH, HEIGHT);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                JPanel square = new JPanel();
                int index = i * gridSize + j;

                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                square.setBackground(Color.green);

                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        clickSquare(index);
                    }
                });

                gridPanel[i][j] = square;
                add(square);
            }
            
        }

        //create sequence
    }

    private void clickSquare(int index) {
        if (setup) {
            boolean added = playerSequence.addToPlayerSeq(index);

            if (added) {
                int xIndex = index / gridSize;
                int yIndex = index % gridSize;

                // Change the current square's color to red
                gridPanel[xIndex][yIndex].setBackground(Color.orange);

                Timer resetTimer = new Timer(350, resetEvent -> {
                    gridPanel[xIndex][yIndex].setBackground(Color.green);
                });
                resetTimer.setRepeats(false);  // Run only once
                resetTimer.start();
            }
        }
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
                setup = true;
            }
        });

        currentIndex = 0;
        timer.start();
    }
}