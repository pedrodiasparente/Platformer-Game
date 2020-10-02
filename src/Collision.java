import java.awt.*;

public class Collision {

    public static boolean playerBlock(Point p, Blocks b){
        return b.contains(p);
    }

    public static boolean playerMovingBlock(Point p, MovingBlock b){
        return b.contains(p);
    }

    public static boolean playerFlag(Point p, Flag f){
        return f.contains(p);
    }
}
