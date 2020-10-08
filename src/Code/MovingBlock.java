package Code;

import java.awt.*;

public class MovingBlock extends Rectangle {

    private int leftBound, rightBound;
    private int move = 1;
    private int id;

    public MovingBlock(int x, int y, int id, int leftBound, int rightBound){
        this.id = id;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        setBounds(x,y,Blocks.blockSize, Blocks.blockSize);
    }

    public void tick(){
        if(x + width - GameState.xOffset >= rightBound - GameState.xOffset && move > 0){

            move *= -1;
        }

        if(x - GameState.xOffset <= leftBound - GameState.xOffset && move < 0){
            move *= -1;
        }

        x += move;
    }

    public void draw(Graphics g){
        if(id != 0){
            g.drawImage(Images.blocks[id-1],x - (int) GameState.xOffset,y - (int) GameState.yOffset,width,height,null);
        }
    }

    public int getMove(){
        return move;
    }

    public int getId(){
        return id;
    }
}
