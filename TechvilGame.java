import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/** Main game class for orchestrating the whole process. */
class TechvilGame extends JFrame implements PanelRemoveListener, PlayerSequence {
    private JLayeredPane contentPane;
    private Puzzle puzzle;
    private Sequence sequence;

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
        JPanel popPanel = new Pop(this);
        contentPane.add(popPanel, Integer.valueOf(1));

        // Add GIF
        JPanel gifCoffee = new Gif();
        contentPane.add(gifCoffee, Integer.valueOf(2));

        setVisible(true);
        setResizable(false);
    }

    @Override
    public void removePanel(JPanel jPanel) {
        contentPane.remove(jPanel);
        puzzle = new Puzzle(this, 3);

        contentPane.add(puzzle, Integer.valueOf(1));
        sequence = new Sequence(3, 3);

        puzzle.showSequence(sequence.getSequence());
    }

    @Override
    public boolean addToPlayerSeq(int index) {
        return sequence.addToPlayerSeq(index);
    }

    public static void main(String[] args) {
        TechvilGame game = new TechvilGame();
    }
}