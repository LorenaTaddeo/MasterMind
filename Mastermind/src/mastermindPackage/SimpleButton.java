package mastermindPackage;

import java.awt.*;

public class SimpleButton {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Color backgroundColour;
    protected boolean draw;
    protected int actionCode;

    public SimpleButton(int x, int y, int width, int height, Color backgroundColour, boolean draw, int actionCode) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.backgroundColour = backgroundColour;
        this.draw = draw;
        this.actionCode = actionCode;
    }

    public boolean isClicked(int mouseX, int mouseY) {
    	return !(mouseX < x || mouseY < y || mouseX > x+width || mouseY > y + height);

    }

    public int getActionCode() {
        return actionCode;
    }
    
    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public void paint(Graphics g) {
        if(!draw) return;
        g.setColor(backgroundColour);
        g.fillRect(x, y, width, height);
        
    }
}
