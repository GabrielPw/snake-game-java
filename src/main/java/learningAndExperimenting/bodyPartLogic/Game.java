package learningAndExperimenting.bodyPartLogic;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    public Game() {
        add(new GamePanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Game game = new Game();
    }
}
