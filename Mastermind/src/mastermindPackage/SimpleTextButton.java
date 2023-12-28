package mastermindPackage;

import java.awt.*;

public class SimpleTextButton extends SimpleButton{

    private Font font;
    private String text;
    private Color textColour;

    public SimpleTextButton(int x, int y, int width, int height, Color backgroundColour, boolean draw, int actionCode,
                            String text, Color textColour, Font font) {
        super(x, y, width, height, backgroundColour, draw, actionCode);
        this.text = text;
        this.textColour = textColour;
        this.font = font;
    }
    public boolean isClicked(int mouseX, int mouseY) {
    	 System.out.println("Button Coordinates: (" + x + ", " + y + ")");
    	    System.out.println("Button Dimensions: (" + width + ", " + height + ")");
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
    
    @Override
    public void paint(Graphics g) {
        if(!draw) return;
        super.paint(g);
        g.setColor(Color.WHITE);
        g.setFont(font);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int fontWidth = fontMetrics.stringWidth(text);
        int fontHeight = fontMetrics.getHeight();
        
        g.drawString(text, x + width / 2 - fontWidth / 2, y + height / 2 + fontHeight / 4);
        
    }

    
}
