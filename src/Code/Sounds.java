package Code;

import javax.swing.*;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Sounds extends JPanel {

    private static MediaPlayer mp;

    public static void playSound(String path){
        File file = new File(path);
        Media media = new Media(file.toURI().toString());
        mp = new MediaPlayer(media);
        mp.setVolume(0.01000000025);
        mp.setCycleCount(MediaPlayer.INDEFINITE);
        mp.play();
    }

    public static void stopSound(){
        mp.stop();
    }
}
