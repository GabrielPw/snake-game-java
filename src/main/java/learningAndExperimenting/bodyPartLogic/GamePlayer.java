package learningAndExperimenting.bodyPartLogic;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class GamePlayer implements KeyListener {

    int headX, headY;
    int width, height;
    private int dirX, dirY;
    private int speed;
    private LinkedList<Point> body; // lista ligada de partes do corpo da cobra
    private Color color;

    public GamePlayer(int x, int y, int width, int height) {

        this.headX = x;
        this.headY = y;
        this.width = width;
        this.height = height;
        this.color = Color.RED;
        this.speed = 5;

        body = new LinkedList<Point>();
        body.add(new Point(headX, headY)); // adiciona a cabeça da cobra
    }

    void render(Graphics2D graphics){

        graphics.setColor(new Color(0x933030));
        for (Point bodyPart : body) {
            graphics.fillRect(bodyPart.x, bodyPart.y, this.width, this.height);
        }

        graphics.setColor(this.color);
        graphics.fillRect(this.headX, this.headY, this.width, this.height);

    }


    public void move(){
        switch (dirX){
            case 1:
                setHeadX(getHeadX() + getSpeed());
                break;
            case -1:
                setHeadX(getHeadX() - getSpeed());
                break;
        }
        switch (dirY){
            case 1:
                setHeadY(getHeadY() + getSpeed());
                break;
            case -1:
                setHeadY(getHeadY() - getSpeed());
                break;
        }

        // Atualiza a posição das partes do corpo da cobra
        if (body.size() > 1) {
            for (int i = body.size() - 1; i > 0; i--) {
                body.get(i).setLocation(body.get(i - 1));
            }
        }
        body.get(0).setLocation(headX, headY);
    }

    public void grow(int x, int y) {
        // adiciona uma nova parte do corpo na cauda da cobra
        body.addLast(new Point(x, y));
    }

    public boolean checkCollisionWithItsBody() {
        for (int i = 1; i < body.size(); i++) {
            if (headX == body.get(i).x && headY == body.get(i).y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("tecla pressionada.");
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                /*
                Coloquei um if abaixo para impedir que o player ande para um lado oposto ao qual ele já está andando.
                ex: se a cobrinha estiver andando para a direção direita ela não pode andar para a esquerda pois caso ela ande, ela colidirá contra seu próprio corpo.
                (isso caso ela possua corpo (body.size > 0))
                */
                //System.out.println("Body size: " + body.size());
                if (body.size() == 1){
                    this.dirX = -1;
                    this.dirY = 0;
                }else if(dirX != 1) {
                    this.dirX = -1;
                    this.dirY = 0;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (body.size() == 1){
                    this.dirX = 1;
                    this.dirY = 0;
                }else if (dirX != -1) {
                    this.dirX = 1;
                    this.dirY = 0;
                }
                break;
            case KeyEvent.VK_UP:
                if (body.size() == 1){
                    this.dirY = -1;
                    this.dirX = 0;
                }else if (dirY != 1) {
                    this.dirY = -1;
                    this.dirX = 0;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (body.size() == 1){
                    this.dirY = 1;
                    this.dirX = 0;
                }else if(dirY != -1) {
                    this.dirY = 1;
                    this.dirX = 0;
                }
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("corpo +1");
                grow(headX, headY);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
