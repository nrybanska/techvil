import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

/** Class for creating and manipulating with the puzzle grid. */
public class Puzzle extends JPanel {
    private final int width = 464;
    private final int height = 275;
    private final int offsetSide = 407;
    private final int offsetTop = 200;

    private int gridSize;

    private JPanel[][] gridPanel;
    private int currentIndex;

    PlayerSequence playerSequence;
    private boolean setup = false;

    /** Constructor needing the grid size and also the playerSequence interface. */
    public Puzzle(PlayerSequence playerSequence, int gridSize) {
        this.playerSequence = playerSequence;
        this.gridSize = gridSize;
        this.gridPanel = new JPanel[gridSize][gridSize];

        setLayout(new GridLayout(gridSize, gridSize));

        setBounds(offsetSide, offsetTop, width, height);

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

    /** Visualizing the created sequence on the grid. */
    public void showSequence(int[] sequence) {
        Timer timer = new Timer(1000, null);  // Create a timer with 1 second delay

        timer.addActionListener(e -> {
            if (currentIndex < sequence.length) {
                int seqValue = sequence[currentIndex];
                System.out.println("curr value: " + seqValue);

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