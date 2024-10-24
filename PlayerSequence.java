/** Inteface for making changes in TechvilGame from Puzzle. */
public interface PlayerSequence {
    /** Tries to add the clicked box at index to the sequence of player.
     * @param index of the clicked cell
     * @return if addition was successful or not
     */
    public boolean addToPlayerSeq(int index);
    
    /** Adds the gif of the timer when the time for solving the puzzle starts. */
    public void addTimerGif();
}
