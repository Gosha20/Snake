package Snake.Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameWithBlocks extends Game{
    public ArrayList<Point> walls = new ArrayList<>();
    public GameWithBlocks(int h, int w, int snakeLen){
        super(h,w,snakeLen);
        spawnWalls();
    }

    @Override
    public void refreshField(){
        super.refreshField();
        if (checkOnWall())
            gameOver = true;
    }

    private boolean checkOnWall()
    {
        return walls.contains(Snake.getHead());
    }

    private void spawnWalls(){
        Random rnd = new Random();
        for (int i = 0; i < getHeight(); i++){
            Point tempWall;
            do {
                int x = rnd.nextInt(getHeight());
                int y = rnd.nextInt(getWidth());
                tempWall = new Point(x, y);
            }
            while (Snake.body.contains(tempWall)||(Buff.x==tempWall.x && Buff.y == tempWall.y));
            walls.add(tempWall);
        }
    }
}
