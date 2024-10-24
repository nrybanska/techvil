import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.Timer;
import javax.swing.UIManager;

/** Main game class for orchestrating the whole process. */
class TechvilGame extends JFrame implements PanelRemoveListener, PlayerSequence {
    // Declaring custom classes so they are global
    private final JLayeredPane contentPane;
    private final Background backgroundPanel;
    private Pop popPanel;
    private final Gif gifPanel;
    private Puzzle puzzle;
    private Sequence sequence;
    private FadePanel fadePanel;
    private Gif timerGif;

    // Variables for level progression
    private final int maxLvl = 5;
    private int currentLvl = 1;
    private boolean fadePresent = false;

    /** Constructor setting the initial game screen. */
    public TechvilGame() {
        // Trying to set a crossplatform ui to limit the deviations from positions of elements
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e);
        }

        setTitle("Techvil");
        setSize(1280, 832);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initializing the pane for the main content
        contentPane = new JLayeredPane();
        setContentPane(contentPane);

        // Adding background to the pane
        backgroundPanel = new Background(currentLvl);
        contentPane.add(backgroundPanel, Integer.valueOf(0));

        // Add pop-up panel (overlay)
        popPanel = new Pop(this, currentLvl);
        contentPane.add(popPanel, Integer.valueOf(1));

        // Add GIF
        gifPanel = new Gif(0);
        contentPane.add(gifPanel, Integer.valueOf(2));

        setVisible(true);
        setResizable(false);
    }

    /** Fuction for displaying miscellaneous messages.
     * @param delPop used to determine what to delete/reset
     * @param messageNum int for determining the type of message to be displayed
     * @param removePanel detremining if panel should be removed or reset
     */
    private void showMessage(boolean delPop, int messageNum, boolean removePanel) {
        switch (messageNum) {
            case 0 -> {
                if (removePanel) {
                    removePanel(delPop);
                } else {
                    resetPanel(delPop);
                }
            }
            
            default -> {
                if (messageNum == 1) {
                    handleFadePanel();
                }            
    
                Message mes = new Message(messageNum); 
                
                contentPane.add(mes, Integer.valueOf(3)); 
                
                int delay = messageNum == 3 ? 40000 : 2000;
    
                Timer resetTimer = new Timer(delay, resetEvent -> {
                    contentPane.remove(mes);
                    revalidate();
                    repaint();

                    // This is the "deeply nested statement"!!!
                    if (currentLvl == maxLvl) {
                        System.out.println("You won!");
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
    }

    private void handleFadePanel() {
        if (fadePresent) {
            contentPane.remove(fadePanel);
        }

        fadePanel = new FadePanel();
        contentPane.add(fadePanel, Integer.valueOf(4)); 
        fadePresent = true;
    }

    private void removePanel(boolean delPop) {
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

            // Removing old puzzle and Timer
            contentPane.remove(puzzle);
            contentPane.remove(timerGif);

            // Stop current timer
            puzzle.terminateGameTimer();

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
            //recalculate the gridSize
            int gridSize = 3 + currentLvl;
            // Remove old components
            contentPane.remove(puzzle);
            contentPane.remove(timerGif);

            // Stop current timer
            puzzle.terminateGameTimer();

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

    @Override
    public void changePanel(boolean delPop, int messageNum, boolean removePanel) {
        if (currentLvl == maxLvl && !delPop && removePanel) {
            messageNum = 3;
        }
        showMessage(delPop, messageNum, removePanel);
    }

    @Override
    public boolean addToPlayerSeq(int index) {
        if (index == -1) {
            // Puzzle reset after timer runs out
            contentPane.remove(timerGif);
            repaint();
            changePanel(false, 4, false);
            return false;
        } else {
            return sequence.addToPlayerSeq(index);
        }
    }

    @Override
    public void addTimerGif() {
        int gifIndex = currentLvl < 3 ? 1 : 2;
        timerGif = new Gif(gifIndex);
        contentPane.add(timerGif, Integer.valueOf(3));
    }

    public static void main(String[] args) {
        TechvilGame game = new TechvilGame();
    }
}
