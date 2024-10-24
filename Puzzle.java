import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

/** Class for creating and manipulating with the puzzle grid. */
public class Puzzle extends JPanel {
    // Variables for setting the bounds of the panel
    private final int width = 464;
    private final int height = 275;
    private final int offsetSide = 407;
    private final int offsetTop = 200;
    private final int maxTime;

    private final int gridSize;

    // Main array used to keep track of the state of the puzzle
    private final JPanel[][] gridPanel;

    private int currentIndex; // Variable for showing sequence

    // Interface and timer variables
    private final PlayerSequence playerSequence;
    private boolean setup = false;
    private Timer gameTimer;

    /** Constructor initializing the puzzle grid.
     * @param playerSequence interface to handle Sequence
     * @param gridSize height and width of puzzle grid
     */
    public Puzzle(PlayerSequence playerSequence, int gridSize) {
        this.playerSequence = playerSequence;
        this.gridSize = gridSize;
        this.gridPanel = new JPanel[gridSize][gridSize];
        this.maxTime = gridSize < 6 ? 16500 : 21000;

        setLayout(new GridLayout(gridSize, gridSize));

        setBounds(offsetSide, offsetTop, width, height);

        setUpGrid();        
    }

    /** Function for creating the actual grid. */
    private void setUpGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                // Creating a new JPanle for each cell
                JPanel square = new JPanel();

                // Calculating the one 1D index of current cell
                int index = i * gridSize + j;

                // Setting the borders and background of grid
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                square.setBackground(Color.green);

                // Adding an event listener for each cell with current index
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
    }

    /** Function for handling user input.
     * @param index of the clicked cell
     */
    private void clickSquare(int index) {
        // Checking if the sequence has finished showing
        if (setup) {
            // Calling the interface to access sequence data
            boolean added = playerSequence.addToPlayerSeq(index);

            // In case of a successful addition change background-color for a short time
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

    /** Visualizing the created sequence on the grid.
     * @param sequence the correct sequence to be shown
     */
    public void showSequence(int[] sequence) {
        // Create a timer with 1 second delay
        Timer timer = new Timer(1000, null);  

        // Adding the event listener which handles the color changes
        timer.addActionListener(e -> {
            // Interval runs until all index in sequence are shown
            if (currentIndex < sequence.length) {
                int seqValue = sequence[currentIndex];

                // Calculate the row and column of the square in the grid
                int xIndex = seqValue / gridSize;
                int yIndex = seqValue % gridSize;

                // Change the current square's color to red
                gridPanel[xIndex][yIndex].setBackground(Color.red);

                // Timer to repaint the cell back to "normal" color
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

                // Starting the ingame timer
                startGameTimer();
            }
        });

        currentIndex = 0;
        timer.start();
    }

    /** Function to start the countdown game timer. */
    private void startGameTimer() {
        gameTimer = new Timer(maxTime, null);
        gameTimer.addActionListener(event -> {
            // Once the time runs out the puzzle is reset
            playerSequence.addToPlayerSeq(-1);
            gameTimer.stop();
        });

        playerSequence.addTimerGif();
        gameTimer.start();
    }

    /** Function to make terminating the timer available outside of class. */
    public void terminateGameTimer() {
        gameTimer.stop();
    }
}