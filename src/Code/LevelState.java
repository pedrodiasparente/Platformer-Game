package Code;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LevelState extends GameState {

    private Player player;

    private Map map;

    public LevelState(GameStateManager gsm, String path, int currentLevel, int x){
        super(gsm,path,currentLevel);
    }

    public void init() {
        player = new Player(40,56, currentLevel);
        map = new Map(path, player);
    }

    public void tick() {
        player.tick(map.getBlocks(), map.getMovingBlocks(), map.getFlag(), gsm);
        map.tick();
    }

    public void draw(Graphics g) {
        g.drawImage(Images.levels[currentLevel - 1],-GameState.backgroundX - ((GameState.spawnPoint-1)*Blocks.blockSize)/GameState.offsetBackgroundRatio,0,GamePanel.WIDTH + GameState.endPoint * Blocks.blockSize/offsetBackgroundRatio,GamePanel.HEIGHT,null);
        player.draw(g);
        map.draw(g);
    }

    public void keyPressed(int k) {
        //escape to menu
        if(k == KeyEvent.VK_ESCAPE){
            gsm.states.pop();
            Sounds.stopSound();
            Sounds.playSound("./sounds/on-melancholy-hill.mp3");
        }
        else{
            player.keyPressed(k);
        }
    }

    public void keyReleased(int k) {
        player.keyReleased(k);
    }
}
