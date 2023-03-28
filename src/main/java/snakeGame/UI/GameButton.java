package snakeGame.UI;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class GameButton {
    int width, height;
    int x, y;
    Color backgroundColor;
    Color textColor;
    String text;
    public Rectangle rectangle;
    public GameButton(int x, int y, int width, int hight, Color backgroundColor, String text) {
        this.width = width;
        this.height = hight;
        this.x = x;
        this.y = y;
        this.backgroundColor = backgroundColor;
        this.text = text;

        rectangle = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics2D g2){

        g2.setColor(backgroundColor);
        g2.fillRect(x,y, width, height);
        g2.setColor(textColor==null?Color.WHITE: textColor); // Cor default --> WHITE.
        g2.drawString(text, x + 10, y + (height /2) + 10);
    }

    public boolean contains(int x, int y) {
        return x >= this.x && x <= this.x + this.width &&
                y >= this.y && y <= this.y + this.height;
    }
}
