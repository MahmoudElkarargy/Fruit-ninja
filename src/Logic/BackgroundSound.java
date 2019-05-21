package Logic;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class BackgroundSound {
    private String sound="src/View/resources/BackgroundMusic.mp3";
    boolean isSound;
    public void setSound(boolean sound) {
        isSound = sound;
    }

    Media Track = new Media(new File(sound).toURI().toString());
    MediaPlayer ON = new MediaPlayer(Track);
    public void Music_BackGround() {
        if (isSound) {
            ON.play();
        }
        else
            ON.stop();
    }
}