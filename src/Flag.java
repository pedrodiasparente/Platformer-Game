import java.awt.*;

public class Flag extends Rectangle {

    public static final int flagSize = 80;

    public Flag(int x, int y){
        setBounds(x,y,flagSize,flagSize);
    }

    public void tick(){

    }

    public void draw(Graphics g){
        g.drawImage(Images.flag,x - (int) GameState.xOffset,y - (int) GameState.yOffset,width,height,null);
    }
}
