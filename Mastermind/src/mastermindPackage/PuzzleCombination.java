package mastermindPackage;

import java.awt.*;
import java.util.Arrays;



public class PuzzleCombination {
	
    public static final int CELL_SIZE = 40;
    private static final Integer[] ID_TO_NUMBER = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static final int NUMBER_OF_NUMBERS = ID_TO_NUMBER.length;
    public static int COMBINATION_LENGTH = 3;
    public static int PANEL_WIDTH = COMBINATION_LENGTH * CELL_SIZE;
    private int[] combination;
    private int xOffset;
    private int yOffset;
    private int difficulty;

    public PuzzleCombination(int[] combination, int xOffset, int yOffset, int difficulty) {
        this.combination = combination;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.difficulty = difficulty;
        
//        setCombinationLength();
    }
    
    public PuzzleCombination(int[] combination, int xOffset, int yOffset) {
        this.combination = combination;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    public PuzzleCombination() {
}

//    private void setCombinationLength() {
//        switch (difficulty) {
//            case 3:
//                COMBINATION_LENGTH = 3;
//                break;
//            case 5:
//                COMBINATION_LENGTH = 5;
//                break;
//            case 10:
//                COMBINATION_LENGTH = 10;
//                break;
//            default:
//                COMBINATION_LENGTH = 3; 
//                break;
//        }
//        PANEL_WIDTH = COMBINATION_LENGTH * CELL_SIZE;
//    }

    public PuzzleCombination(PuzzleCombination puzzleCombination) {
        this.combination = new int[puzzleCombination.combination.length];
        for(int i = 0; i < combination.length; i++) {
            combination[i] = puzzleCombination.combination[i];
        }
        this.xOffset = puzzleCombination.xOffset;
        this.yOffset = puzzleCombination.yOffset;
    }

    public void cycleCombination(int position, boolean cycleUp) {
    	System.out.println("Before - combination: " + Arrays.toString(combination) + ", position: " + position);
        if(cycleUp) {
            combination[position]++;
            combination[position] = combination[position] % NUMBER_OF_NUMBERS;
        } else {
        	combination[position] = (combination[position] - 1 + NUMBER_OF_NUMBERS) % NUMBER_OF_NUMBERS;
            if(combination[position] < 0)
                combination[position] = NUMBER_OF_NUMBERS - 1;
        }
        System.out.println("After - combination: " + Arrays.toString(combination) + ", position: " + position);

    }
    public void setYOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public int[] getCombination() {
        return combination;
    }
    
    public int getCombinationLength() {
        return COMBINATION_LENGTH;
    }

    public void paint(Graphics g) {
    	 for (int i = 0; i < combination.length; i++) {
    	        String number = Integer.toString(ID_TO_NUMBER[combination[i]]);
    	        int xPosition = xOffset + i * CELL_SIZE; // Calcola la posizione x in base all'indice
    	        int yPosition = yOffset + 35; // Altezza fissa per tutti i numeri

    	        g.drawString(number, xPosition, yPosition);
    	    }
    }

	public void setCombinationLength(int selectedDifficulty) {
		// TODO Auto-generated method stub
		
	}

	public long[] getValues() {
		// TODO Auto-generated method stub
		return null;
	}

}
