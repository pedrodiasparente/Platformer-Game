package Code;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {

    public static BufferedImage[] blocks;
    public static BufferedImage[] playerSprites;
    public static BufferedImage menu, flag;
    public static BufferedImage[] levels;
    public static BufferedImage[] menuOptions;

    public Images(){
        blocks = new BufferedImage[1];
        playerSprites = new BufferedImage[10];
        levels = new BufferedImage[2];
        menuOptions = new BufferedImage[3];
        try{
            blocks[0] = ImageIO.read(getClass().getResourceAsStream("/block_brick.png"));
            flag = ImageIO.read(getClass().getResourceAsStream("/flag.png"));
            playerSprites[0] = ImageIO.read(getClass().getResourceAsStream("/playerSprite0.png"));
            playerSprites[1] = ImageIO.read(getClass().getResourceAsStream("/playerSprite1.png"));
            playerSprites[2] = ImageIO.read(getClass().getResourceAsStream("/playerSprite2.png"));
            playerSprites[3] = ImageIO.read(getClass().getResourceAsStream("/playerSprite3.png"));
            playerSprites[4] = ImageIO.read(getClass().getResourceAsStream("/playerSprite4.png"));
            playerSprites[5] = ImageIO.read(getClass().getResourceAsStream("/playerSprite5.png"));
            playerSprites[6] = ImageIO.read(getClass().getResourceAsStream("/playerSprite6.png"));
            playerSprites[7] = ImageIO.read(getClass().getResourceAsStream("/playerSprite7.png"));
            playerSprites[8] = ImageIO.read(getClass().getResourceAsStream("/playerSprite8.png"));
            playerSprites[9] = ImageIO.read(getClass().getResourceAsStream("/playerSprite9.png"));
            menu = ImageIO.read(getClass().getResourceAsStream("/menuField.png"));
            menuOptions[0] = ImageIO.read(getClass().getResourceAsStream("/start.png"));
            menuOptions[1] = ImageIO.read(getClass().getResourceAsStream("/help.png"));
            menuOptions[2] = ImageIO.read(getClass().getResourceAsStream("/quit.png"));
            levels[0] = ImageIO.read(getClass().getResourceAsStream("/level4Background.png"));
            levels[1] = ImageIO.read(getClass().getResourceAsStream("/level4Background.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
