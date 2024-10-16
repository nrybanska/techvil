public class Sequence {
    private int[] sequence;
    private int[] playerSequence;
    private int currentLen = 0;

    public Sequence(int sequenceLen, int gridSize) {
        this.sequence = new int[sequenceLen];
        this.playerSequence = new int[sequenceLen];

        for (int i = 0; i < sequenceLen; i++) {
            this.sequence[i] = (int) Math.round(Math.random() * (Math.pow(gridSize, 2) - 1));
        }
    }

    public int[] getSequence() {
        return sequence;
    }

    public boolean addToPlayerSeq(int index) {
        // Catching error of player choosing too many boxes
        if (currentLen <= playerSequence.length) {
            playerSequence[currentLen] = index;
            currentLen++;
        } else {
            return false;
        }

        return true;
    }

    public boolean compareSeq() {
        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i] != playerSequence[i]) {
                return false;
            }
        }
        return true;
    }
}
