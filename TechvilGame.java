import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.Timer;
import javax.swing.UIManager;

/** Main game class for orchestrating the whole process. */
class TechvilGame extends JFrame implements PanelRemoveListener, PlayerSequence {
    // Declaring custom classes so they are global
    private JLayeredPane contentPane;
    private Background backgroundPanel;
    private Pop popPanel;
    private Gif gifPanel;
    private Puzzle puzzle;
    private Sequence sequence;
    private FadePanel fadePanel;
    private Gif timerGif;
    private Sounds sound;

    // Variables for level progression
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
        
        // Add loading windows sound
        sound = new Sounds(1);

        setVisible(true);
        setResizable(false);
    }

    /** Fuction for displaying miscellaneous messages.
     * @param delPop used to determine what to delete/reset
     * @param messageNum int for determining the type of message to be displayed
     * @param removePanel detremining if panel should be removed or reset
     */
    private void showMessage(boolean delPop, int messageNum, boolean removePanel) {
        if (messageNum == 0) {
            removePanel(delPop);
        } else {
            if (messageNum == 1) {
                handleFadePanel();
            }            

            Message mes = new Message(messageNum); 
            
            contentPane.add(mes, Integer.valueOf(3)); 
            
            int delay = 2000;

            Timer resetTimer = new Timer(delay, resetEvent -> {
                contentPane.remove(mes);
                revalidate();
                repaint();
                
                if (removePanel) {
                    sound.stop();
                    // Play random success sound
                    sound = new Sounds(0);
                    removePanel(delPop);
                } else {
                    sound.stop();
                    // Play fail sound
                    sound = new Sounds(2);
                    resetPanel();
                }
            });
            resetTimer.setRepeats(false);  // Run only once
            resetTimer.start();
        }
    }

    /** Fuction for adding and removing the overlay fading panel. */
    private void handleFadePanel() {
        if (fadePresent) {
            contentPane.remove(fadePanel);
        }

        fadePanel = new FadePanel(currentLvl);
        contentPane.add(fadePanel, Integer.valueOf(4)); 
        fadePresent = true;
    }

    /** Fuction to remove the current panel and create a new one.
     * @param delPop is used to determine which exact panel to remove
     */
    private void removePanel(boolean delPop) {
        if (delPop) {
            // Calculating the new grid size (difficulty)
            int gridSize = 2 + currentLvl;

            // Removing panel
            contentPane.remove(popPanel);

            // Creating new panel and sequence
            puzzle = new Puzzle(this, gridSize);
            sequence = new Sequence(gridSize - 1, gridSize, this);

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

    /** Fuction to reset the puzzle after a failed attempt. */
    private void resetPanel() {
        //recalculate the gridSize
        int gridSize = 2 + currentLvl;

        // Remove old components
        contentPane.remove(puzzle);
        contentPane.remove(timerGif);

        // Stop current timer
        puzzle.terminateGameTimer();

        puzzle = new Puzzle(this, gridSize);

        contentPane.add(puzzle, Integer.valueOf(1)); 
        sequence = new Sequence(gridSize - 1, gridSize, this);

        puzzle.showSequence(sequence.getSequence());
      
        contentPane.revalidate();
        contentPane.repaint();
    }

    /** Overriding the inteface so that it can be passed to custom classes. 
     * @param delPop is a boolean for determining if Pop should be deleted or Puzzle
     * @param messageNum is the int for determining the type of message to be displayed
     * @param removePanel boolean for detremining if panel should be removed or reset
    */
    @Override
    public void changePanel(boolean delPop, int messageNum, boolean removePanel) {
        showMessage(delPop, messageNum, removePanel);
    }

    /** Overriding the inteface so that it can be passed to custom classes.
     * @param index of the clicked cell
     * @return if addition was successful or not
     */
    @Override
    public boolean addToPlayerSeq(int index) {
        if (index == -1) {
            // Puzzle reset after timer runs out
            contentPane.remove(timerGif);
            repaint();
            changePanel(false, 3, false);
            return false;
        } else {
            return sequence.addToPlayerSeq(index);
        }
    }

    /** Overriding the inteface so that it can be passed to custom classes. */
    @Override
    public void addTimerGif() {
        // Different timer gifs for higher levels
        int gifIndex = currentLvl < 3 ? 0 : 1;
        timerGif = new Gif(gifIndex);
        // Playing intense music for maximum immersion
        sound = new Sounds(3);
        contentPane.add(timerGif, Integer.valueOf(3));
    }

    public static void main(String[] args) {
        TechvilGame game = new TechvilGame();
    }
}
