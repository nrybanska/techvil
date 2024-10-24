/** Interface to remove or reset panels. */
public interface PanelRemoveListener {
    /** Function passed to change the panels.
     * @param delPop is a boolean for determining if Pop should be deleted or Puzzle
     * @param messageNum is the int for determining the type of message to be displayed
     * @param removePanel boolean for detremining if panel should be removed or reset
     */
    public void changePanel(boolean delPop, int messageNum, boolean removePanel);
}
