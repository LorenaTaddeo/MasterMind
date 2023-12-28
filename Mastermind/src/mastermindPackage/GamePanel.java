package mastermindPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel implements MouseListener{
    
    public enum GameState {Playing, GameWon, GameLost};
    private static final int MAXIMUM_ATTEMPTS = 10;
    public static final int PANEL_HEIGHT = MAXIMUM_ATTEMPTS * PuzzleCombination.CELL_SIZE;
    private List<AttemptPanel> attempts;
    private AttemptPanel currentAttempt;
    private PuzzleCombination solution;
    private Random rand;
    private List<SimpleButton> buttons;
    private SimpleTextButton submitButton;
    private GameState gameState;
    private GameEndedPanel gameEndedPanel;
    private boolean showCheat = false;
    
    private SimpleTextButton surrenderButton;

    
    public GamePanel() {
        setPreferredSize(new Dimension(AttemptPanel.PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.BLACK);
        rand = new Random();
        createButtons();
        gameEndedPanel = new GameEndedPanel();
        addMouseListener(this);
        showCheat = false;
        System.out.println("Buttons created: " + buttons);
        restart();
    }
    
    public void restart() {
        attempts = new ArrayList<>();
        currentAttempt = null;
        generateSolution();
        addNewAttempt();
        gameState = GameState.Playing;
        showCheat = false; // reimposta la variabile showCheat a false
        repaint();
    }
    
    public void addNewAttempt() {
        if(currentAttempt == null) {
            currentAttempt = new AttemptPanel(solution,PANEL_HEIGHT - PuzzleCombination.CELL_SIZE);
        } else {
            for(AttemptPanel attemptPanel : attempts) {
                attemptPanel.moveUp();
            }
            currentAttempt = new AttemptPanel(new PuzzleCombination(attempts.get(attempts.size()-1).getPuzzleCombination()),
                    attempts.size()+1, solution,PANEL_HEIGHT-PuzzleCombination.CELL_SIZE);
        }
        attempts.add(currentAttempt);
    }
    
    public void generateSolution() {
        int[] solutionValues = new int[PuzzleCombination.COMBINATION_LENGTH];
        for(int i = 0; i < solutionValues.length; i++) {
            solutionValues[i] = rand.nextInt(PuzzleCombination.NUMBER_OF_NUMBERS);
        }
        solution = new PuzzleCombination(solutionValues,
                AttemptPanel.PANEL_WIDTH / 2 - PuzzleCombination.PANEL_WIDTH / 2, PANEL_HEIGHT / 2);
        gameEndedPanel.setSolution(solution);
    }
    
    public void toggleCheat() {
        showCheat = !showCheat;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(int i = 1; i < MAXIMUM_ATTEMPTS; i++) {
            g.drawLine(0,i* PuzzleCombination.CELL_SIZE, AttemptPanel.PANEL_WIDTH, i*PuzzleCombination.CELL_SIZE);
        }
        for(AttemptPanel attemptPanel : attempts) {
            attemptPanel.paint(g);
        }
        if(gameState == GameState.Playing)
            submitButton.paint(g);
        else
            gameEndedPanel.paint(g);
        if(gameState == GameState.Playing)
        	surrenderButton.paint(g);
        else gameEndedPanel.paint(g);
        if(showCheat)
        	solution.paint(g);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(gameState != GameState.Playing) return;

        int x = e.getX();
        int y = e.getY();
        System.out.println("Mouse Clicked at: (" + x + ", " + y + ")");
        if(submitButton.isClicked(x,y)) {
            handleSubmitClicked();
        } else if (surrenderButton.isClicked(x, y)) {
            handleSurrenderClicked();
        }{
            checkForCycleButton(x,y,e.getButton() == MouseEvent.BUTTON1);
        }
        repaint();
    }
    
    private void createButtons() {
        buttons = new ArrayList<>();

        int yButtons = PANEL_HEIGHT - PuzzleCombination.CELL_SIZE;
        int xSubmit = AttemptPanel.PANEL_WIDTH - 180; 
        
        submitButton = createTextButton(xSubmit, yButtons, 180, PuzzleCombination.CELL_SIZE, "Submit", Color.DARK_GRAY, Color.WHITE, 20, -1);
        buttons.add(submitButton);

        int xSurrender = xSubmit - 120;  
        surrenderButton = createTextButton(xSurrender, yButtons, 120, PuzzleCombination.CELL_SIZE, "Surrender", Color.GRAY, Color.BLACK, 14, 2);
        buttons.add(surrenderButton);

        int x = AttemptPanel.TEXT_OFFSET_WIDTH;
        for (int i = 0; i < PuzzleCombination.COMBINATION_LENGTH; i++) {
            buttons.add(createSimpleButton(x, yButtons, PuzzleCombination.CELL_SIZE, i));
            x += PuzzleCombination.CELL_SIZE;
        }
    }

    private SimpleTextButton createTextButton(int x, int y, int width, int height, String label, Color buttonColor, Color textColor, int fontSize, int actionCode) {
        return new SimpleTextButton(x, y, width, height, buttonColor, true, actionCode, label, textColor, new Font("Arial", Font.BOLD, fontSize));
    }

    private SimpleButton createSimpleButton(int x, int y, int size, int actionCode) {
        return new SimpleButton(x, y, size, size, Color.PINK, true, actionCode);
    }
    
    private void handleSubmitClicked() {
    	System.out.println("Submit button clicked!");
        if(currentAttempt.getIsVictory()) {
            gameState = GameState.GameWon;
            gameEndedPanel.setVictory(true);
        } else if(attempts.size() < MAXIMUM_ATTEMPTS) {
            addNewAttempt();
        } else {
            gameState = GameState.GameLost;
            gameEndedPanel.setVictory(false);
        }
        repaint();
    }
    
    public void mouseClicked1(MouseEvent e) {
    	 gameState = GameState.GameLost;
    	    gameEndedPanel.setVictory(false);
    	    showCheat = true; 
    	    repaint();
    }

    private void handleMouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (submitButton.isClicked(x, y)) {
            handleSubmitClicked();
        } else if (surrenderButton.isClicked(x, y)) {
            handleSurrenderClicked();
        } else {
            checkForCycleButton(x, y, e.getButton() == MouseEvent.BUTTON1);
        }

        repaint();
    }

    private void handleSurrenderClicked() {
        gameState = GameState.GameLost;
        gameEndedPanel.setVictory(false);
        showCheat = true;
        repaint();
    }
    
    private void checkForCycleButton(int x, int y, boolean isLeft) {
    	System.out.println("Mouse Clicked at: (" + x + ", " + y + ")");
        for(SimpleButton button : buttons) {
            if(button.isClicked(x, y)) {
                currentAttempt.cycleCombination(button.getActionCode(), isLeft);
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
 
    @Override
    public void mouseEntered(MouseEvent e) {}
   
    @Override
    public void mouseExited(MouseEvent e) {}

	public void initializeGame(int selectedDifficulty) {}

}
