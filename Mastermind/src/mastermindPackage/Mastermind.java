package mastermindPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Mastermind implements KeyListener{

	public static void main(String[] args) {
	       Mastermind mastermind = new Mastermind();
	}
	 
	private GamePanel gamePanel;
	private DifficultySelectionPanel difficultyPanel;
	
    public Mastermind() {
        JFrame frame = new JFrame("Master Mind");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        difficultyPanel = new DifficultySelectionPanel(createStartButtonActionListener());

        gamePanel = new GamePanel();
       
        
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().add(difficultyPanel);
        frame.addKeyListener(this);
        frame.getContentPane().add(gamePanel);
        

        frame.pack();
        frame.setVisible(true);
    }

	protected void startGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	    if(e.getKeyCode() == KeyEvent.VK_R) {
	    	if (gamePanel != null) {
	            gamePanel.restart();
	            gamePanel.repaint();
	        }
	    } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	        System.exit(0);
	    } else if(e.getKeyCode() == KeyEvent.VK_C) {
	    	if (gamePanel != null) {
	            gamePanel.toggleCheat();
	            gamePanel.repaint();
	        }
	    }
	}
	    
    private ActionListener createStartButtonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedDifficulty = difficultyPanel.getSelectedDifficulty();
                gamePanel.initializeGame(selectedDifficulty);
                gamePanel.repaint();
            }

			
        };
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {}
}
