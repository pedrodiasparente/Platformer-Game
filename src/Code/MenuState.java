package Code;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

    private String[] options = {"Start", "Help", "Quit"};
    private int currentSelection = 0;
    private int optionWidth = 200;
    private int optionWidthSelect = 300;
    private int optionHeight = 80;
    private int optionHeightSelect = 120;
    private boolean music = false;

    private int firstLevel = 1; //maybe if i play with this i cant get save points

    public MenuState(GameStateManager gsm){
        super(gsm,"",0);
    }

    public void init() {
    }

    public void tick() {
        //maybe i peek here at last gsm.states to see what my current level is and always save it, then when i play again i pick up where i left off
    }

    public void draw(Graphics g) {

        if(!music) {
            Sounds.playSound("./sounds/on-melancholy-hill.mp3");
            music = true;
        }

        //g.setColor(Color.CYAN);
        //g.fillRect(0,0,Code.GamePanel.WIDTH,Code.GamePanel.HEIGHT);
        g.drawImage(Images.menu,0,0,GamePanel.WIDTH,GamePanel.HEIGHT,null);
        for(int i = 0; i < options.length; i++){
            if(i == currentSelection) {
                g.drawImage(Images.menuOptions[i],GamePanel.WIDTH / 2  - optionWidthSelect /2,GamePanel.HEIGHT / 2 + i * 170 - options.length * 85,optionWidthSelect,optionHeightSelect,null);
            }
            else {
                //g.setColor(Color.BLACK);
                //g.setFont(new Font(null, Font.BOLD,62));
                //g.drawString(options[i], Code.GamePanel.WIDTH / 2 - 75, Code.GamePanel.HEIGHT / 2 + i * 150 - options.length * 40);
                g.drawImage(Images.menuOptions[i],GamePanel.WIDTH / 2 - optionWidth/2,GamePanel.HEIGHT / 2 + i * 170 - options.length * 75,optionWidth,optionHeight,null);
            }
        }
    }

    public void keyPressed(int k) {
        if(k == KeyEvent.VK_DOWN){
            currentSelection++;
            if(currentSelection >= options.length) currentSelection = 0;
        }
        else if(k == KeyEvent.VK_UP){
            currentSelection--;
            if(currentSelection < 0) currentSelection = options.length - 1;
        }

        if (k == KeyEvent.VK_ENTER){
            if(currentSelection == 0){
                gsm.states.push(new LevelState(gsm, "/map" + firstLevel + ".map",firstLevel, 0));
                Sounds.stopSound();
                Sounds.playSound("./sounds/on-melancholy-hill.mp3");
            }
            else if(currentSelection == 1){
                //help
            }
            else if(currentSelection == 2){
                System.exit(0);
            }
        }
    }

    public void keyReleased(int k) {

    }
}
