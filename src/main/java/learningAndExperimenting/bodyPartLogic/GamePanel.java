package learningAndExperimenting.bodyPartLogic;

import snakeGame.enuns.GameColorPalleteEnum;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private GamePlayer player;

    static final int DELAY = 25;

    GamePanel(){
        this.setPreferredSize(new Dimension(500,500));
        this.setBackground(GameColorPalleteEnum.DarkBackgroundTheme.getColor());
        this.setFocusable(true);
        this.setVisible(true);

        this.player = new GamePlayer(100, 100, 20, 20);
        addKeyListener(this.player);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;

        draw(graphics);
    }

    void draw(Graphics2D graphics){

        player.render(graphics);
        player.move();
        if(player.checkCollisionWithItsBody()){
            System.out.println("Colidiu com o proprio corpo");
        }

        repaint();

        // Add Delay.
        try {
            Thread.sleep(DELAY); // aguarda 'DELAY' milissegundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
