import javax.print.DocFlavor;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Sounds extends JPanel {

    public static AudioFormat audioFormat;
    public static AudioInputStream audioInputStream;
    public static SourceDataLine sourceDataLine;

    public static File file;

    public Sounds() {
    }

    public static void playSound(String path){
        File file = new File(path);
        try{
            if(true){
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                System.out.println();
                clip.open(sound);
                clip.setFramePosition(0);
                clip.start();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}
