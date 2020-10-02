import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Map {

    private String path;
    private String line;
    private int width, height;
    private int spawnPointX, spawnPointY, endPointX, endPointY;

    private Blocks[][] blocks;

    private Flag flag;

    private ArrayList<MovingBlock> movingBlocks;

    public Map(String path, Player p) {
        this.path = path;

        loadMap(p);
    }

    public void draw(Graphics g){
        for(int i = 0; i < blocks.length; i++){
            for (int j = 0; j < blocks[i].length; j++){
                blocks[i][j].draw(g);
            }
        }

        for(int i = 0; i < movingBlocks.size(); i++){
            movingBlocks.get(i).draw(g);
        }

        flag.draw(g);
    }

    public Blocks[][] getBlocks(){
        return blocks;
    }

    public ArrayList<MovingBlock> getMovingBlocks(){
        return movingBlocks;
    }

    public Flag getFlag(){
        return flag;
    }

    public void tick(){
        for(int i = 0; i < movingBlocks.size(); i++){
            movingBlocks.get(i).tick();
        }
    }

    public void loadMap(Player p){
        InputStream is = this.getClass().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try{
            String spawnLine = br.readLine();
            String[] spawnTokens = spawnLine.split(" ");
            spawnPointX = Integer.parseInt(spawnTokens[0]);
            spawnPointY = Integer.parseInt(spawnTokens[1]);
            String endLine = br.readLine();
            String[] endTokens = endLine.split(" ");
            endPointX = Integer.parseInt(endTokens[0]);
            endPointY = Integer.parseInt(endTokens[1]);
            width = Integer.parseInt(br.readLine());
            height = Integer.parseInt(br.readLine());
            GameState.spawnPoint = spawnPointX;
            GameState.endPoint = width;

            int playerXOffset = (GamePanel.WIDTH / 2 - (spawnPointX)*Blocks.blockSize + p.getWidth());
            int playerYOffset = (3* GamePanel.HEIGHT / 5 + p.getHeight()) - (height - spawnPointY + 1)*Blocks.blockSize;

            blocks = new Blocks[height][width];

            flag = new Flag(endPointX* Blocks.blockSize - Flag.flagSize + playerXOffset, (endPointY +1) * Blocks.blockSize - Flag.flagSize + playerYOffset);

            for(int y = 0; y < height; y++) {
                String line = br.readLine();
                String[] tokens = line.split(" ");
                for(int x = 0; x < width; x++){
                    blocks[y][x] = new Blocks(x * Blocks.blockSize + playerXOffset, y*Blocks.blockSize + playerYOffset, Integer.parseInt(tokens[x]));
                }
            }

            line = br.readLine();
            line = br.readLine();
            int length = Integer.parseInt(line);
            movingBlocks = new ArrayList<MovingBlock>();

            for(int i = 0; i < length; i++){
                line = br.readLine();
                String[] tokens = line.split(" ");

                movingBlocks.add(new MovingBlock((Integer.parseInt(tokens[0])) * Blocks.blockSize + playerXOffset - Blocks.blockSize,
                        Integer.parseInt(tokens[1]) * Blocks.blockSize + playerYOffset,
                        Integer.parseInt(tokens[2]),
                        Integer.parseInt(tokens[3]) * Blocks.blockSize + playerXOffset - Blocks.blockSize,
                        Integer.parseInt(tokens[4]) * Blocks.blockSize + playerXOffset - Blocks.blockSize));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
