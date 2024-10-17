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
    private Background backgroundPanel;

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

        // Create and add Background panel
        backgroundPanel = new Background(currentLvl);
        contentPane.add(backgroundPanel, Integer.valueOf(0)); 

        // Add pop-up panel (overlay)
        popPanel = new Pop(this);
        contentPane.add(popPanel, Integer.valueOf(1)); 

        // Add GIF
        JPanel gifCoffee = new Gif();
        contentPane.add(gifCoffee, Integer.valueOf(2)); 

        setVisible(true);
        setResizable(false);
    }

    public int getCurrentLvl() {
        return currentLvl;
    }

    @Override
    public void removePanel(boolean delPop) {
        if (delPop) {
            int gridSize = 3 + currentLvl;
            contentPane.remove(popPanel);

            puzzle = new Puzzle(this, gridSize);
            sequence = new Sequence(gridSize, gridSize, this);

            contentPane.add(puzzle, Integer.valueOf(1)); 
            puzzle.showSequence(sequence.getSequence());

            contentPane.revalidate();
            contentPane.repaint();
        } else {
            contentPane.remove(puzzle);
            popPanel = new Pop(this);
            contentPane.add(popPanel, Integer.valueOf(1)); 
            currentLvl++;

            backgroundPanel.setLevel(currentLvl); 

            contentPane.revalidate();
            contentPane.repaint();
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

            contentPane.revalidate();
            contentPane.repaint();
        } else {
            contentPane.remove(popPanel);
            popPanel = new Pop(this);
            contentPane.add(popPanel, Integer.valueOf(1)); 

            contentPane.revalidate();
            contentPane.repaint();
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
