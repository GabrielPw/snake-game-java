package snakeGame;

import lombok.Getter;
import lombok.Setter;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Getter
@Setter
public class GameSoundEffect {

    private Clip clip;
    private AudioInputStream audioInputStream;
    private URL file;
    GameSoundEffect() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

    }
    void setSound(String filePath) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        this.file = new URL(filePath);
        audioInputStream = AudioSystem.getAudioInputStream(file);

        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }

    void playMusic(){
        this.clip.setFramePosition(0);
        this.clip.start();
    }
}
