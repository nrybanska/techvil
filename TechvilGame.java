import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/** Main game class for orchestrating the whole process. */
class TechvilGame extends JFrame implements PanelRemoveListener, PlayerSequence {
    private JLayeredPane contentPane;
    private JPanel popPanel;
    private Puzzle puzzle;
    private Sequence sequence;

    private final int maxLvl = 5;
    private int currentLvl = 1;

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
        JPanel backgroundPanel = new Background();
        contentPane.add(backgroundPanel, Integer.valueOf(0));

        // Add "screen", text and button
        popPanel = new Pop(this);
        contentPane.add(popPanel, Integer.valueOf(1));

        // Add GIF
        JPanel gifCoffee = new Gif();
        contentPane.add(gifCoffee, Integer.valueOf(2));

        setVisible(true);
        setResizable(false);
    }

    @Override
    public void removePanel(boolean delPop) {
        if (delPop) {
            int gridSize = 3 + currentLvl;
            contentPane.remove(popPanel);
            puzzle = new Puzzle(this, gridSize);

            contentPane.add(puzzle, Integer.valueOf(1));
            sequence = new Sequence(gridSize, gridSize, this);

            puzzle.showSequence(sequence.getSequence());
        } else {
            contentPane.remove(puzzle);
            popPanel = new Pop(this);
            contentPane.add(popPanel, Integer.valueOf(1));
            currentLvl++;
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
            popPanel = new Pop(this);
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