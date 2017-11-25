package Snake.Model;

import Snake.GUI.Panelv2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static Snake.Constants.dotSize;

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
        return walls.contains(super.Snake.getHead());
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
            while (Snake.body.contains(tempWall)||(super.Buff.x==tempWall.x && super.Buff.y == tempWall.y));
            walls.add(tempWall);
        }
    }

    @Override
    public void Draw(Graphics g, int scoreHeight, Image wallImage, Panelv2 p){
        for(Point wall : walls)
            g.drawImage(wallImage, wall.x * dotSize,wall.y * dotSize + scoreHeight, p);
    }
}
