/** Class for creating and altering both player and base sequence. */
public class Sequence {
    private final int[] sequence;
    private final int[] playerSequence;
    private int currentLen = 0;

    private final PanelRemoveListener panelRemoveListener;

    /** Constructor for initializing the sequence.
     * @param sequenceLen length of the sequence
     * @param gridSize size of grid used in calculating random number
     * @param prl interface used to change panels on events
     */
    public Sequence(int sequenceLen, int gridSize, PanelRemoveListener prl) {
        this.sequence = new int[sequenceLen];
        this.playerSequence = new int[sequenceLen];
        this.panelRemoveListener = prl;

        // Creating the random sequence
        for (int i = 0; i < sequenceLen; i++) {
            /* The random number ranges from 0 to gridsize^2 - 1 
            *  because we wanted to use only one number to determine 
            *  the position thus [1][0] is [gridSize + 0] etc.
            *
            *  x * (gridsize^2 - 1) */
            this.sequence[i] = (int) Math.round(Math.random() * (Math.pow(gridSize, 2) - 1));
        }
    }

    /** Simple getter for retreiving the sequence.
     * @return an int array of the sequence
     */
    public int[] getSequence() {
        return sequence;
    }

    /** Function to validate and execute addition to the player sequence.
     * @param index is the place where the player clicked
     * @return signals if player's move is valid
     */
    public boolean addToPlayerSeq(int index) {
        // Catching error of player choosing too many boxes
        if (currentLen < playerSequence.length) {
            playerSequence[currentLen] = index;
            currentLen++;

            // Compare the sequences once they are the same size
            if (currentLen == playerSequence.length) {
                compareSeq();
            }
        } else {
            return false;
        }

        return true;
    }

    /** Comparing both sequences and returning if they are same. */
    public void compareSeq() {
        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i] != playerSequence[i]) {
                // Stop the comparison if there is a discrepancy
                panelRemoveListener.changePanel(false, 2, false);
                return;
            }
        }

        // If all indexes are same progress to next level
        panelRemoveListener.changePanel(false, 1, true);
    }
}
