package mastermindPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Multiplayers {

	private JFrame frame;
    private GamePanel gamePanelPlayer1;
    private GamePanel gamePanelPlayer2;

    public Multiplayers() {
        frame = new JFrame("Master Mind - Multiplayer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        gamePanelPlayer1 = new GamePanel();
        gamePanelPlayer2 = new GamePanel();

        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().add(gamePanelPlayer1);

        JButton startButton = new JButton("Inizia Partita");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startMultiplayerGame();
            }
        });

        frame.getContentPane().add(startButton);

        frame.pack();
        frame.setVisible(true);
    }

    private void startMultiplayerGame() {
        int selectedDifficulty = getSelectedDifficulty();

        gamePanelPlayer1.initializeGame(selectedDifficulty);
        gamePanelPlayer2.initializeGame(selectedDifficulty);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(gamePanelPlayer1);
        frame.getContentPane().add(gamePanelPlayer2);

        frame.repaint();
    }

    private int getSelectedDifficulty() {
        // Implementa la logica per ottenere la difficoltà selezionata (puoi prendere spunto dalla tua implementazione attuale)
        return 0;
    }
}
