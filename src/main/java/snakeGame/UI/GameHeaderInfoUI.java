package snakeGame.UI;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class GameHeaderInfoUI {

    int width, height;
    int x, y;
    Color backgroundColor;
    Color textColor;
    String text;
    public Rectangle rectangle;
    Font font;
    public GameHeaderInfoUI(int x, int y, int width, int hight, Color backgroundColor, String text) {
        this.width = width;
        this.height = hight;
        this.x = x;
        this.y = y;
        this.backgroundColor = backgroundColor;
        this.text = text;

        rectangle = new Rectangle(x, y, width, height);
    }

    public void render(Graphics2D graphics, float fontSize){

        graphics.setColor(backgroundColor);
        graphics.fillRect(x,y, width, height);
        graphics.setColor(textColor==null?Color.WHITE: textColor); // Cor default --> WHITE.
        this.font = graphics.getFont().deriveFont( fontSize );
        graphics.setFont( font );
        graphics.drawString(text, x + 10, y + (height /2) + 10);
    }
}
