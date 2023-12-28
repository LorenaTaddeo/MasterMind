package mastermindPackage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class DifficultySelectionPanel extends JPanel{

	private JComboBox<String> difficultyComboBox;
	private PuzzleCombination puzzleCombination;


    public DifficultySelectionPanel(PuzzleCombination puzzleCombination, ActionListener startButtonListener) {
    	this.puzzleCombination = puzzleCombination;
    	setLayout(new FlowLayout());
    	
        difficultyComboBox = new JComboBox<>(new String[]{"Facile (3 cifre)", "Medio (5 cifre)", "Difficile (10 cifre)"});
        add(difficultyComboBox);
     
        difficultyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCombinationLength();
            }
        });
    }
 


    public DifficultySelectionPanel(ActionListener createStartButtonActionListener) {
		// TODO Auto-generated constructor stub
	}



	public int getSelectedDifficulty() {
        String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
        if (selectedDifficulty.contains("Facile")) {
            return 3;
        } else if (selectedDifficulty.contains("Medio")) {
            return 5;
        } else if (selectedDifficulty.contains("Difficile")) {
            return 10;
        }
        return 3; 
    }
    
    private void updateCombinationLength() {
        int selectedDifficulty = getSelectedDifficulty();
        puzzleCombination.setCombinationLength(selectedDifficulty); // Imposta la nuova lunghezza della combinazione
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
    }



	public JButton getStartButton() {
		// TODO Auto-generated method stub
		return null;
	}

}
