package snakeGame;

import javax.swing.*;

public class SnakeFrame extends JFrame {

    // Classe responsável pela criação da janela.

    public SnakeFrame(){
        this.setTitle("Snake Game | Gabriel Xavier");
        this.add(new SnakePanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
