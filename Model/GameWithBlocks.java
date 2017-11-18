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
        return walls.contains(Snake.GetHead());
    }

    private void spawnWalls(){
        Random rnd = new Random();
        for (int i = 0; i < super.getHeight(); i++){
            Point tempWall;
            do {
                int x = rnd.nextInt(super.getHeight());
                int y = rnd.nextInt(super.getWidth());
                tempWall = new Point(x, y);
            }
            while (super.Snake.body.contains(tempWall));
            walls.add(tempWall);
        }
    }
}
