import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.Timer;
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
    private int currentLvl = 4;

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

        // Add pop-up panel (overlay)
        popPanel = new Pop(this, currentLvl);
        contentPane.add(popPanel, Integer.valueOf(1));

        // Add GIF
        gifPanel = new Gif();
        contentPane.add(gifPanel, Integer.valueOf(2));

        setVisible(true);
        setResizable(false);
    }

    public int getCurrentLvl() {
        return currentLvl;
    }

    private void showMessage(boolean delPop, int messageNum, boolean removePanel) {
        if (messageNum == 0) {
            if (removePanel) {
                removePanel(delPop);
            } else {
                resetPanel(delPop);
            }
        } else {
            Message mes = new Message(messageNum); 
            contentPane.add(mes, Integer.valueOf(3)); 
            
            int delay;
            switch (messageNum) {
                case 3:
                    delay = 4000;
                    break;
                default:
                    delay = 2000;
            }


            Timer resetTimer = new Timer(delay, resetEvent -> {
                contentPane.remove(mes);
                revalidate();
                repaint();
                if (currentLvl == maxLvl) {
                    quitGame();
                } else if (removePanel){
                    removePanel(delPop);
                } else {
                    resetPanel(delPop);
                }
            });
            resetTimer.setRepeats(false);  // Run only once
            resetTimer.start();
        }
    }

    private void removePanel(boolean delPop) {
        if (delPop) {
            // Calculating the new grid size (difficulty)
            int gridSize = 1;//3 + currentLvl;

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

            // Removing old puzzle
            contentPane.remove(puzzle);

            // Creating new panel
            popPanel = new Pop(this, currentLvl);
            contentPane.add(popPanel, Integer.valueOf(1)); 

            // Changing the background picture
            backgroundPanel.setLevel(currentLvl);
        }

        revalidate();
        repaint();
    }

    private void resetPanel(boolean delPop) {
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
      
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void quitGame() {
        dispose();
    }

    @Override
    public void changePanel(boolean delPop, int messageNum, boolean removePanel) {
        if (currentLvl == maxLvl && !delPop && removePanel) {
            messageNum = 3;
        }
        showMessage(delPop, messageNum, removePanel);
    }

    @Override
    public boolean addToPlayerSeq(int index) {
        return sequence.addToPlayerSeq(index);
    }

    public static void main(String[] args) {
        TechvilGame game = new TechvilGame();
    }
}
