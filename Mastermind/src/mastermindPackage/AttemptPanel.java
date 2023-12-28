package mastermindPackage;
import java.awt.*;
import java.util.Arrays;

public class AttemptPanel {

	public static final int TEXT_OFFSET_WIDTH = 40;
	private static final Font font = new Font("Arial", Font.BOLD, 40);
	public static final int PANEL_WIDTH = TEXT_OFFSET_WIDTH + PuzzleCombination.PANEL_WIDTH + ResultPanel.PANEL_WIDTH;
	private PuzzleCombination puzzleCombination;
	private ResultPanel resultPanel;
	private String attemptNumber;
	private int yOffset;

	public AttemptPanel(PuzzleCombination puzzleCombination, int attemptNumber, PuzzleCombination solution, int yOffset) {
	    this.puzzleCombination = puzzleCombination;
	    puzzleCombination.setYOffset(yOffset);
	    resultPanel = new ResultPanel(puzzleCombination, solution,
	                            TEXT_OFFSET_WIDTH+PuzzleCombination.PANEL_WIDTH, yOffset);
	    this.attemptNumber = String.valueOf(attemptNumber);
	    this.yOffset = yOffset;
	}

    public AttemptPanel(PuzzleCombination solution, int yOffset) {
        this(new PuzzleCombination(new int[PuzzleCombination.COMBINATION_LENGTH], TEXT_OFFSET_WIDTH, yOffset),
                1, solution, yOffset);
    }
    
    public void cycleCombination(int index, boolean cycleUp) {
    	System.out.println("Before: " + Arrays.toString(puzzleCombination.getValues()));
        puzzleCombination.cycleCombination(index, cycleUp);
        System.out.println("After: " + Arrays.toString(puzzleCombination.getValues()));
        resultPanel.validateAttempt();
    }

    public PuzzleCombination getPuzzleCombination() {
        return puzzleCombination;
    }

    public void moveUp() {
        yOffset -= PuzzleCombination.CELL_SIZE;
        puzzleCombination.setYOffset(yOffset);
        resultPanel.setYOffset(yOffset);
    }

    public boolean getIsVictory() {
        return resultPanel.getIsVictory();
    }

    public void paint(Graphics g) {
        g.setColor(Color.PINK);
        g.setFont(font);
        g.drawString(attemptNumber,5,yOffset+35);
        puzzleCombination.paint(g);
        resultPanel.paint(g);
    }
}
