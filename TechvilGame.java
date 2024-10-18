import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;

/** Main game class for orchestrating the whole process. */
class TechvilGame extends JFrame implements PanelRemoveListener, PlayerSequence {
    private final JLayeredPane contentPane;
    private Background backgroundPanel;
    private Pop popPanel;
    private Gif gifPanel;
    private Puzzle puzzle;
    private Sequence sequence;

    private final int maxLvl = 5;
    private int currentLvl = 0;

    /** game. */
    public TechvilGame() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e);
        }

        setTitle("Techvil");
        setSize(1280, 832);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize a pane for the main content
        contentPane = new JLayeredPane();
        setContentPane(contentPane);

        // Add background to the pane
        backgroundPanel = new Background(currentLvl);
        contentPane.add(backgroundPanel, Integer.valueOf(0));

        // Add "screen", text and button
        popPanel = new Pop(this, currentLvl);
        contentPane.add(popPanel, Integer.valueOf(1));

        // Add GIF
        gifPanel = new Gif();
        contentPane.add(gifPanel, Integer.valueOf(2));

        setVisible(true);
        setResizable(false);
    }

    @Override
    public void removePanel(boolean delPop) {
        if (delPop) {
            // Calculating the new grid size (difficulty)
            int gridSize = 3 + currentLvl;

            // Removing panel
            contentPane.remove(popPanel);

            // Creating new panel and sequence
            puzzle = new Puzzle(this, gridSize);
            sequence = new Sequence(gridSize, gridSize, this);

            // Adding the panel and visualizing the sequence
            contentPane.add(puzzle, Integer.valueOf(1));

            puzzle.showSequence(sequence.getSequence());
        } else {
            // Incrementing the level
            currentLvl++;

            // Removing old panels
            contentPane.remove(backgroundPanel);
            contentPane.remove(puzzle);

            // Creating new panels
            backgroundPanel = new Background(currentLvl);
            popPanel = new Pop(this, currentLvl);

            // Adding the panels to the content pane
            contentPane.add(backgroundPanel, Integer.valueOf(0));
            contentPane.add(popPanel, Integer.valueOf(1));
        }

        if (currentLvl == maxLvl) {

        }
    }
    
    @Override
    public void resetPanel(boolean delPop) {
        if (!delPop) {
            int gridSize = 3 + currentLvl;
            contentPane.remove(puzzle);
            puzzle = new Puzzle(this, gridSize);

            contentPane.add(puzzle, Integer.valueOf(1));
            sequence = new Sequence(gridSize, gridSize, this);

            puzzle.showSequence(sequence.getSequence());
        } else {
            contentPane.remove(popPanel);
            popPanel = new Pop(this, currentLvl);
            contentPane.add(popPanel, Integer.valueOf(1));
        }
    }

    @Override
    public boolean addToPlayerSeq(int index) {
        return sequence.addToPlayerSeq(index);
    }

    public static void main(String[] args) {
        TechvilGame game = new TechvilGame();
    }
}