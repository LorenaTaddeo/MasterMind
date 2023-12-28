package mastermindPackage;

import java.awt.*;

public class ResultPanel {

    public static final int PANEL_WIDTH = 300;
    private PuzzleCombination puzzleAttempt;
    private PuzzleCombination solution;
    private PuzzleCombination puzzle;
    private boolean victory = false;
    private int xOffset;
    private int yOffset;
    private Font font = new Font("Arial", Font.BOLD, 20);
    private String correctPosAndColourStr = "Correct Number and Pos: ";
    private String correctColourWrongPosStr = "Correct Number Wrong Pos: ";
    private int correctPosAndColour = 0;
    private int correctColourWrongPos = 0;

    public ResultPanel(PuzzleCombination puzzleAttempt, PuzzleCombination solution, int xOffset, int yOffset) {
        this.puzzleAttempt = puzzleAttempt;
        this.solution = solution;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        validateAttempt();
    }
    
    public void initializeGame(int selectedDifficulty) {
    	puzzle = new PuzzleCombination(new int[0], 0, 0, selectedDifficulty);
        int combinationLength = puzzle.COMBINATION_LENGTH;
        int panelWidth = puzzle.PANEL_WIDTH;

    }
    
    public void setYOffset(int yOffset) {
        this.yOffset = yOffset;
    }
    
    public void validateAttempt() {
        victory = false;
        int[] excessCounter = new int[PuzzleCombination.NUMBER_OF_NUMBERS];
        int[] unusedSolutionCounter = new int[PuzzleCombination.NUMBER_OF_NUMBERS];
        correctPosAndColour = 0;
        for(int i = 0; i < puzzle.COMBINATION_LENGTH; i++) {
            if(solution.getCombination()[i] == puzzleAttempt.getCombination()[i])
            {
                correctPosAndColour++;
            } else {
                excessCounter[puzzleAttempt.getCombination()[i]]++;
                unusedSolutionCounter[solution.getCombination()[i]]++;
            }
        }
        correctColourWrongPos = 0;
        for(int i = 0; i < PuzzleCombination.NUMBER_OF_NUMBERS; i++) {
            correctColourWrongPos += Math.min(unusedSolutionCounter[i],excessCounter[i]);
        }
        if(correctPosAndColour == puzzle.COMBINATION_LENGTH) {
            victory = true;
        }
    }

    public boolean getIsVictory() {
        return victory;
    }

    public void paint(Graphics g) {
        g.setFont(font);
        g.drawString(correctPosAndColourStr + correctPosAndColour, xOffset, yOffset+17);
        g.drawString(correctColourWrongPosStr + correctColourWrongPos, xOffset, yOffset+37);
    }
}
