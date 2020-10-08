package Code;

import java.awt.*;

public abstract class GameState {

    public GameStateManager gsm;
    public String path;
    public int currentLevel;
    public static double xOffset, yOffset;
    public static int spawnPoint, endPoint, backgroundX, offsetBackgroundRatio;

    public GameState(GameStateManager gsm, String path, int currentLevel){
        this.gsm = gsm;
        this.path = path;
        this.currentLevel = currentLevel;

        offsetBackgroundRatio = 3;
        backgroundX = 0;
        spawnPoint = 0;
        endPoint = 0;
        xOffset = 0;
        yOffset = 0;

        init();
    }

    public abstract void init();
    public abstract void tick();
    public abstract void draw(Graphics g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);
}
