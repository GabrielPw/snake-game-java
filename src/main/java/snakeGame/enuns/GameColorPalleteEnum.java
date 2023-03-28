package snakeGame.enuns;

import java.awt.*;

public enum GameColorPalleteEnum {
    VERDE(new Color(138, 168, 90)),
    AZUL(new Color(90, 168, 175)),
    VERMELHO(new Color(166, 65, 65)),
    ROXO(new Color(147, 57, 143)),
    CINZA(new Color(119, 119, 119)),
    CINZAESCURO(new Color(87, 87, 87)),
    CINZAESCURO_HALF_ALPHA(new Color(87, 87, 87, 50)),
    DarkBackgroundTheme(new Color(42, 42, 42));


    private final Color color;
    GameColorPalleteEnum(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
