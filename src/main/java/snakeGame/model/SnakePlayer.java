package snakeGame.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Getter
@Setter
public class SnakePlayer implements KeyListener {

    private int x, y;
    private int width, height;
    private int dirX, dirY;
    private int speed;
    private boolean colidiu; // Variável para verificar se player colidiu com a borda da janela.
    private Color color;

    public SnakePlayer(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = 5;
        this.colidiu = false;
        this.color = color;
    }

    public void render(Graphics2D graphics){

        graphics.setColor(this.color);
        graphics.fillRect(this.x, this.y, this.width, this.height);
    }

    public void move(){
        switch (dirX){
            case 1:
                setX(getX() + getSpeed());
                break;
            case -1:
                setX(getX() - getSpeed());
                break;
        }
        switch (dirY){
            case 1:
                setY(getY() + getSpeed());
                break;
            case -1:
                setY(getY() - getSpeed());
                break;
        }
    }

    public boolean checkCollisionWithWindow(int window_width, int window_height){

        // Operador terário para verificar se o player está colidindo com os 'limites' (borda) da janela.
        return (this.x <= 0 || this.dirX + this.x + this.width >= window_width || this.y <= 0 || this.y + this.height >= window_height)? true: false;
    }

    public boolean checkCollisionWithItem(GameItem item){
        if (this.x < item.getX() + item.getWidth() && this.x + this.width > item.getX() && this.y < item.getY() + item.getHeight() && this.y + this.height > item.getY()) {
            return true;
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Tecla pressionada.");
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                this.dirX = -1;
                this.dirY = 0;
                break;
            case KeyEvent.VK_RIGHT:
                this.dirX = 1;
                this.dirY =0;
                break;
            case KeyEvent.VK_UP:
                this.dirY = -1;
                this.dirX = 0;
                break;
            case KeyEvent.VK_DOWN:
                this.dirY = 1;
                this.dirX = 0;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
