package snakeGame.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Random;

@Getter
@Setter
public class GameItem {

    private Integer x, y;
    private int width, height;
    private Color color;
    private static int contadorRandom = 0;
    public GameItem(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;

    }

    public void render(Graphics2D graphics){

        graphics.setColor(this.color);
        graphics.fillOval(this.x, this.y, this.width, this.height);
    }

    // Gera uma nova posição aleatória para o item.
    public void setRandomPos(int intervalMin, int intervalMax){

        this.x = gerarNumeroAleatorio(intervalMin, intervalMax);
        this.y = gerarNumeroAleatorio(intervalMin, intervalMax);

        System.out.println("Número gerado -> X: " + x + " | Y: " + y);
        System.out.println("ContadorRandom: " + contadorRandom);

    }

    public static int gerarNumeroAleatorio(int intervaloMin, int intervaloMax){

        contadorRandom+= 1;
        Random random = new Random(System.currentTimeMillis() + contadorRandom);
        int randomNumber = intervaloMin + random.nextInt(intervaloMax - intervaloMin);

        // variável incrementadora para tornar a seed randomica mais dinâmica. (resetará ao chegar em 20)
        contadorRandom = contadorRandom >= 55? contadorRandom = 0: contadorRandom;
        return randomNumber;
    }
}
