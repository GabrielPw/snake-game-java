package snakeGame;

import snakeGame.UI.GameButton;
import snakeGame.UI.GameHeaderInfoUI;
import snakeGame.enuns.GameColorPalleteEnum;
import snakeGame.model.GameItem;
import snakeGame.model.SnakePlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class SnakePanel extends JPanel implements ActionListener{

    // Classe responsável por renderizar e atualizar a tela do jogo, bem como também verificar movimentos do Player.
    private static final int window_width = 500;
    private static final int window_height = 500;
    private static final int DELAY = 25;
    Timer timer;
    private SnakePlayer player;
    private GameItem fruit;
    private GameButton btnPlay = new GameButton(120, 210, 110, 40, GameColorPalleteEnum.AZUL.getColor(), "Play Again");
    private GameButton btnExit = new GameButton(250, 210, 110, 40, GameColorPalleteEnum.VERMELHO.getColor(), "Exit");
    private GameHeaderInfoUI gameScoreHeader = new GameHeaderInfoUI(50, 0, 100, 30, GameColorPalleteEnum.CINZAESCURO_HALF_ALPHA.getColor(), "Score: 0");;
    private int gameScore, scoreFinal = 0; // o scoreFinal será responsável por armazenar o score feito ao final do jogo (no GameOver)
    private GameSoundEffect gameSoundEffect;
    private boolean tocouMusicaGameOver = false;
    SnakePanel(){
        this.setPreferredSize(new Dimension(window_width,window_height));
        this.setBackground(GameColorPalleteEnum.DarkBackgroundTheme.getColor());
        this.setFocusable(true);

        // inicializando som do jogo.
        try {
            this.gameSoundEffect = new GameSoundEffect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        this.player = new SnakePlayer(100, 100, 20, 20, GameColorPalleteEnum.VERDE.getColor());
        this.fruit = new GameItem(200, 200, 20, 20, GameColorPalleteEnum.VERMELHO.getColor());

        this.addKeyListener(player); // Adiciona Listener de teclado para movimentação do player.
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (btnPlay != null && btnPlay.contains(x, y)) {
                    // Clicou para jogar novamente.

                    tocouMusicaGameOver = false;
                    player.setColidiu(false);
                    player.setColor(GameColorPalleteEnum.VERDE.getColor());
                    fruit.setColor(GameColorPalleteEnum.VERMELHO.getColor());

                    fruit.setRandomPos(0, window_width - fruit.getWidth());
                    fruit.setRandomPos(0, window_height - fruit.getHeight());

                } else if (btnExit != null && btnExit.contains(x, y)) {
                    System.out.println("Exit.");
                    System.exit(0);
                }
            }
        });
    }

    // O método PaintComponente vai ser chamado a cada frame do jogo e chamará funções que desenham tudo na tela.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        draw(g2);
    }


    // método que efetivamente vai desenhar as formas.
    public void draw(Graphics2D graphics){

        this.fruit.render(graphics);
        this.player.render(graphics);
        this.gameScoreHeader.render(graphics, 20.f); // renderiza o score.
        this.gameScoreHeader.setText("Score: " + gameScore);

        this.player.move();

        // Colidiu com a janela ( GameOver ).
        if (player.checkCollisionWithWindow(window_width, window_height) == true || player.isColidiu()){

            scoreFinal = gameScore > 0? gameScore: scoreFinal;
            gameOver(graphics);
        }

        if (player.checkCollisionWithItem(fruit)){

            fruit.setRandomPos(0, window_width - fruit.getWidth());
            fruit.setRandomPos(0, window_height - fruit.getHeight());

            gameScore+=1;

            // Ativando som de mordida.
            try {
                gameSoundEffect.setSound("file:C:\\Users\\XGabriel\\Documents\\Projetos Java\\SnakeGame2\\src\\main\\resources\\sounds\\bite_effect.wav");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
            gameSoundEffect.playMusic();
        }

        repaint();

        // Delay para que o jogo não fique com um FPS muito alto (caso contrário o jogo vai ficar em velocidade super rápida).
        try {
            Thread.sleep(DELAY); // aguarda 'DELAY' milissegundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void gameOver(Graphics2D graphics){

        // Ativando som de GameOver.

        System.out.println("Tocou musica: " + tocouMusicaGameOver);
        if (tocouMusicaGameOver == false) {
            try {
                gameSoundEffect.setSound("file:C:\\Users\\XGabriel\\Documents\\Projetos Java\\SnakeGame2\\src\\main\\resources\\sounds\\bonus_effect.wav");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
            gameSoundEffect.playMusic();
            tocouMusicaGameOver = true;
        }

       // System.out.println("Game Over...");

        this.player.setColidiu(true);
        this.gameScore = 0;
        resetarPlayer();

        // Pintar elementos na tela de cinza, para evidenciar o gameOver.
        player.setColor(GameColorPalleteEnum.CINZAESCURO.getColor());
        fruit.setColor(GameColorPalleteEnum.CINZAESCURO.getColor());

        // Renderizar mensagem de gameOver e botões (Play/Exit).
        graphics.setColor(Color.WHITE);
        Font font = graphics.getFont().deriveFont( 30.0f );
        graphics.setFont( font );
        graphics.drawString("GameOver", 160, 160);

        font = graphics.getFont().deriveFont( 20.0f );
        graphics.setFont( font );
        graphics.drawString("pontuação: " + scoreFinal, 160, 185);

        font = graphics.getFont().deriveFont( 20.0f );
        graphics.setFont(font);
        btnPlay.draw(graphics);
        btnExit.draw(graphics);

    }

    // Quando der gameOver os atributos do player resetarão para seus valores iniciais.
    void resetarPlayer(){
        this.player.setX(100);
        this.player.setY(100);
        this.player.setSpeed(5);
        this.player.setDirX(0);
        this.player.setDirY(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
