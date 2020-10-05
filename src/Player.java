import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player {

    private int counter = 0;
    private int spriteCounter = 1;
    private int levelCounter;
    private int lastLevel = 2;

    //movement booleans
    private boolean right = false, left = false, jumping = false, falling = true;
    private boolean topCollision = false;
    private boolean rightCollision = false, leftCollision = false;
    private boolean lastDirectionRight = true;

    //bounds
    private double x,y;
    private int width, height;

    //move speed
    private double moveSpeed = 5;

    //jump speed
    private double jumpSpeed = 15;
    private double currentJumpSpeed = jumpSpeed;

    //fall speed
    private double maxFallSpeed = 8;
    private double currentFallSpeed = 0;

    public Player(int width, int height, int currentLevel){
       this.x = GamePanel.WIDTH / 2 - width / 2;
       this.y = 3 *GamePanel.HEIGHT / 5;
       this.levelCounter = currentLevel;
       this.width = width;
       this.height = height;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void tick(Blocks[][] b, ArrayList<MovingBlock> mb, Flag f, GameStateManager gsm) {

        int ix, iy;
        ix = (int) x;
        iy = (int) y;

        //death
        if(b[b.length-1][0].y + 4*Blocks.blockSize - GameState.yOffset < 3 *GamePanel.HEIGHT / 5){
            gsm.states.pop();
            gsm.states.push(new LevelState(gsm, "/map" + levelCounter + ".map", levelCounter,0));
        }

        //stationary
        if((rightCollision || leftCollision || (!right && !left && !jumping && !falling)) && lastDirectionRight){
            spriteCounter = 1;
            counter = 0;
        }
        else if((rightCollision || leftCollision || (!right && !left && !jumping && !falling)) && !lastDirectionRight){
            spriteCounter = 6;
            counter = 0;
        }
        //moving
        else if (((right || left) && (!jumping && !falling)) && lastDirectionRight){
            if(counter % 30 == 0) {
                spriteCounter++;
                counter = 1;
                if(spriteCounter > 3) spriteCounter = 2;
            }
            counter++;

        }
        else if (((right || left) && (!jumping && !falling)) && !lastDirectionRight){
            if(counter % 30 == 0) {
                spriteCounter++;
                counter = 1;
                if(spriteCounter > 8) spriteCounter = 7;
            }
            counter++;

        }
        //jumping
        else if(jumping && lastDirectionRight){
            spriteCounter = 4;
            counter = 0;
        }
        else if(jumping && !lastDirectionRight){
            spriteCounter = 9;
            counter = 0;
        }
        //falling
        else if (falling && lastDirectionRight){
            spriteCounter = 5;
            counter = 0;
        }
        else{
            spriteCounter = 10;
            counter = 0;
        }

        if(Collision.playerFlag(new Point(ix + width/2 + (int) GameState.xOffset, iy + height/2 + (int) GameState.yOffset), f)) {
            if(levelCounter < lastLevel) {
                levelCounter++;
                gsm.states.pop();
                gsm.states.push(new LevelState(gsm, "/map" + levelCounter + ".map", levelCounter,0));
            }
            else{gsm.states.pop();}
        }

        for(int i = 0; i < b.length; i++){
            for(int j = 0; j < b[i].length; j++){
                if(b[i][j].getId() != 0) {
                    if (Collision.playerBlock(new Point(ix + width + (int) GameState.xOffset + (int) moveSpeed, iy + (int) GameState.yOffset), b[i][j])
                            || Collision.playerBlock(new Point(ix + width + (int) GameState.xOffset + (int) moveSpeed, iy + height + (int) GameState.yOffset), b[i][j])) {
                        rightCollision = true;
                    }

                    if (Collision.playerBlock(new Point(ix + (int) GameState.xOffset - (int) moveSpeed, iy + (int) GameState.yOffset), b[i][j])
                            || Collision.playerBlock(new Point(ix + (int) GameState.xOffset - (int) moveSpeed, iy + height + (int) GameState.yOffset), b[i][j])) {
                        leftCollision = true;
                    }

                    if (Collision.playerBlock(new Point(ix + width + (int) GameState.xOffset - (int) moveSpeed, iy + height + (int) GameState.yOffset + (int) moveSpeed), b[i][j])
                            || Collision.playerBlock(new Point(ix + (int) GameState.xOffset + (int) moveSpeed, iy + height + (int) GameState.yOffset + (int) moveSpeed), b[i][j])) {

                        if(currentFallSpeed != 0){
                            //y = b[i][j].y - height - GameState.yOffset - 1;
                            GameState.yOffset += b[i][j].y - height - GameState.yOffset - 1 - y;
                        }

                        falling = false;
                        topCollision = true;
                        currentFallSpeed = 0;
                    } else {
                        if (!topCollision && !jumping) falling = true;
                    }

                    if (Collision.playerBlock(new Point(ix + width + (int) GameState.xOffset, iy + (int) GameState.yOffset - 2* (int) moveSpeed), b[i][j])
                            || Collision.playerBlock(new Point(ix + (int) GameState.xOffset, iy + (int) GameState.yOffset - 2* (int) moveSpeed), b[i][j])) {
                        jumping = false;
                        currentJumpSpeed = jumpSpeed;
                        falling = true;
                    }
                }
            }
        }


        //moving blocks
        for(int i = 0; i < mb.size(); i++){
            if(mb.get(i).getId() != 0) {
                if (Collision.playerMovingBlock(new Point(ix + width + (int) GameState.xOffset + (int) moveSpeed, iy + (int) GameState.yOffset), mb.get(i))
                        || Collision.playerMovingBlock(new Point(ix + width + (int) GameState.xOffset + (int) moveSpeed, iy + height + (int) GameState.yOffset), mb.get(i))) {
                    rightCollision = true;
                    if(x + width + GameState.xOffset < mb.get(i).x + 2) {
                        x = mb.get(i).x - width - GameState.xOffset - 2;
                        GameState.xOffset += mb.get(i).getMove();
                    }
                }

                if (Collision.playerMovingBlock(new Point(ix + (int) GameState.xOffset - (int) moveSpeed, iy + (int) GameState.yOffset), mb.get(i))
                        || Collision.playerMovingBlock(new Point(ix + (int) GameState.xOffset - (int) moveSpeed, iy + height + (int) GameState.yOffset), mb.get(i))) {
                    leftCollision = true;
                    if(mb.get(i).x + mb.get(i).width < x + GameState.xOffset + 2) {
                        x = mb.get(i).x + mb.get(i).width - GameState.xOffset + 2;
                        GameState.xOffset += mb.get(i).getMove();
                    }
                }

                if (Collision.playerMovingBlock(new Point(ix + width + (int) GameState.xOffset - (int) moveSpeed, iy + height + (int) GameState.yOffset + (int) moveSpeed), mb.get(i))
                        || Collision.playerMovingBlock(new Point(ix + (int) GameState.xOffset + (int) moveSpeed, iy + height + (int) GameState.yOffset + (int) moveSpeed), mb.get(i))) {
                    if ((mb.get(i).x + mb.get(i).width - x) > 4 || (mb.get(i).x + mb.get(i).width - x) < -4 && (x + width - mb.get(i).x) > 4 || (x + width - mb.get(i).x) < -4) {
                        if(currentFallSpeed != 0) {
                            y = mb.get(i).y - height - GameState.yOffset - 2;
                            //GameState.yOffset -= 0;
                        }
                    }
                    falling = false;
                    topCollision = true;
                    currentFallSpeed = 0;

                    GameState.xOffset += mb.get(i).getMove();

                } else {
                    if (!topCollision && !jumping) falling = true;
                }

                if (Collision.playerMovingBlock(new Point(ix + width + (int) GameState.xOffset, iy + (int) GameState.yOffset - (int) moveSpeed), mb.get(i))
                        || Collision.playerMovingBlock(new Point(ix + (int) GameState.xOffset, iy + (int) GameState.yOffset - (int) moveSpeed), mb.get(i))) {
                    jumping = false;
                    currentJumpSpeed = jumpSpeed;
                    falling = true;
                }
            }
        }

        topCollision = false;

        if(right && !rightCollision){
            GameState.xOffset+=moveSpeed;
        }
        if(left && !leftCollision){
            GameState.xOffset-=moveSpeed;
        }
        if(jumping){
            currentFallSpeed = 0;
            GameState.yOffset -= currentJumpSpeed;
            currentJumpSpeed -= .5;

            if(currentJumpSpeed <= 0){
                currentJumpSpeed = jumpSpeed;
                jumping = false;
                falling = true;
            }
        }
        if(falling){
            GameState.yOffset += currentFallSpeed;
            if(currentFallSpeed < maxFallSpeed) currentFallSpeed += .5;
            else currentFallSpeed = maxFallSpeed;
        }

        GameState.backgroundX = (int) GameState.xOffset/GameState.offsetBackgroundRatio;

        rightCollision = false;
        leftCollision = false;
    }

    public void draw(Graphics g){
        g.drawImage(Images.playerSprites[spriteCounter - 1],(int) x,(int) y,width,height,null);
    }

    public void keyPressed(int k){
        if(k == KeyEvent.VK_RIGHT) {
            right = true;
            Sounds.playSound("./sounds/bruh.mp3");
            if(!lastDirectionRight) {
                lastDirectionRight = true;
                spriteCounter = 1;
                counter = 0;
            }
        }
        if(k == KeyEvent.VK_LEFT) {
            left = true;
            if(lastDirectionRight) {
                lastDirectionRight = false;
                spriteCounter = 6;
                counter = 0;
            }
        }
        if(k == KeyEvent.VK_SPACE){
            if (!jumping && !falling){
                jumping = true;
            }
        }
    }

    public void keyReleased(int k){
        if(k == KeyEvent.VK_RIGHT) right = false;
        if(k == KeyEvent.VK_LEFT) left = false;
    }
}
