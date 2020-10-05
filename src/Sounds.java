import javax.print.DocFlavor;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Sounds extends JPanel {

    public Sounds(String path) {


        /*try {
            if (file.exists()) {
                System.out.println("exist");
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                this.clip = AudioSystem.getClip()
                System.out.println(clip.getFrameLength());
                clip.open(sound);
                System.out.println(clip.getFrameLength());

            }
            else {
                System.out.println("not exist");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }*/
    }

    public static void playSound(String path){
        File file = new File(path);
        Media media = new Media(file.toURI().toString());
        System.out.println(media.getDuration());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();


    }
}
