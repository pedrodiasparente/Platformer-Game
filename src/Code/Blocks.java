package Code;

import java.awt.*;

public class Blocks extends Rectangle {

    public static final int blockSize = 64;
    private int id;

    public Blocks(int x, int y, int id){
        this.id = id;
        setBounds(x,y,blockSize,blockSize);
    }

    public void tick(){

    }

    public void draw(Graphics g){
        if(id == 1) {
            g.drawImage(Images.blocks[id-1],x - (int) GameState.xOffset,y - (int) GameState.yOffset,width,height,null);
        }
    }

    public int getId(){
        return id;
    }
}
