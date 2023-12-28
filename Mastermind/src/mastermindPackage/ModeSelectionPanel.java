package mastermindPackage;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ModeSelectionPanel extends JPanel{

	private JButton singlePlayerButton;
    private JButton multiplayerButton;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private ModeSelectionListener modeSelectionListener;

    public ModeSelectionPanel(CardLayout cardLayout, JPanel cardPanel) {
    	this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        
        singlePlayerButton = new JButton("Gioca da solo");
        multiplayerButton = new JButton("Gioca con un altro giocatore");

        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyModeSelected(Mode.SINGLE_PLAYER);
            }
        
        });

        multiplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyModeSelected(Mode.MULTIPLAYER);
            }
        });

        add(singlePlayerButton);
        add(multiplayerButton);
    }

	public ModeSelectionPanel() {
		// TODO Auto-generated constructor stub
	}

	public Mode getSelectedMode() {
        if (singlePlayerButton.getModel().isArmed()) {
            return Mode.SINGLE_PLAYER;
        } else if (multiplayerButton.getModel().isArmed()) {
            return Mode.MULTIPLAYER;
        } else {
            return Mode.SINGLE_PLAYER;
        }
    }

    public JButton getSinglePlayerButton() {
        return singlePlayerButton;
    }

    public JButton getMultiplayerButton() {
        return multiplayerButton;
    }

    public void setModeSelectionListener(ModeSelectionListener listener) {
        this.modeSelectionListener = listener;
    }

    private void notifyModeSelected(Mode mode) {
        if (modeSelectionListener != null) {
            modeSelectionListener.onModeSelected(mode);
            cardLayout.next(cardPanel);
        }
    }

    public enum Mode {
        SINGLE_PLAYER,
        MULTIPLAYER
    }

    public interface ModeSelectionListener {
        void onModeSelected(Mode mode);
    }

}
