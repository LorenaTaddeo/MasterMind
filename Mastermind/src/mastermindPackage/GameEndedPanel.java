package mastermindPackage;

import java.awt.*;

public class GameEndedPanel {

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean victory;
    private PuzzleCombination solution;
    private Font gameEndFont = new Font("Arial", Font.BOLD, 20);
    private String endLineSuccess = "You won! :D";
    private String endLineLoss = "You lost! :(";
    private String endLine1 = "Press R to Restart!";
    private String endLine2 = "Solution was:";
    
    public GameEndedPanel() {
        this.x = AttemptPanel.PANEL_WIDTH/2;
        this.y = GamePanel.PANEL_HEIGHT/2;
        this.width = 200;
        this.height = 100;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    public void setSolution(PuzzleCombination solution) {
        this.solution = solution;
    }

    public void paint(Graphics g) {
        g.setColor(new Color(213, 180, 112, 230));
        g.fillRect(x-width/2, y-height/2-30,width,height+30);
        g.setFont(gameEndFont);
        int strWidth;
        if(victory) {
            g.setColor(new Color(36, 76, 19));
            strWidth = g.getFontMetrics().stringWidth(endLineSuccess);
            g.drawString(endLineSuccess, x-strWidth/2, y-55);
        } else {
            g.setColor(new Color(104, 15, 15));
            strWidth = g.getFontMetrics().stringWidth(endLineLoss);
            g.drawString(endLineLoss, x-strWidth/2, y-55);
        }
        g.setColor(Color.WHITE);
        strWidth = g.getFontMetrics().stringWidth(endLine1);
        g.drawString(endLine1, x-strWidth/2, y-30);
        strWidth = g.getFontMetrics().stringWidth(endLine2);
        g.drawString(endLine2, x-strWidth/2, y-5);
        solution.paint(g);
    }
}
