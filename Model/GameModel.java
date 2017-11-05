package Snake.Model;

import Snake.Constants;
import java.awt.*;
import java.util.*;

public class GameModel {
    public Buff Buff;
    public Snake Snake;
    public boolean gameOver = false;
    public ArrayList<Point> walls = new ArrayList<>();
    public String mode;
    public int Score;
    private final int height;
    private final int width;
    private int timeLiveBuff;
    boolean existBuff;
    private static Buff[] Buffs = new Buff[]{
                                new Buff("apple", 1, 20),
                                new Buff("poison", -1, 20),
                                new Buff("banan", 3, 15),
                                new Buff("grapes", 5,15),
                                new Buff("granat", 7,10)};
    public Enemy enemy;

    public GameModel(int h, int w, int snakeLength, String mode){
        this.Snake = new Snake(snakeLength);
        this.existBuff = false;
        this.height = h;
        this.width = w;
        this.mode = mode;
        this.enemy = new Enemy(5,5);
        SpawnFood();
        SpawnWalls(mode);
    }

    public void RefreshField(){
        timeLiveBuff--;
        if (timeLiveBuff <= 0)
            existBuff = false;
        Snake.Move();
        enemy.move();
        eatBuff();
        fixHeadPosition();
        collisionWithEnemy();
        if(checkOnLittleSnakeSize() || CheckOnEatSelf() || CheckOnWall())
            gameOver = true;
        SpawnFood();
    }


    private void SpawnFood(){
        Random rnd = new Random();
        int n = rnd.nextInt(Buffs.length);
        while (!existBuff){
            int x = rnd.nextInt(height);
            int y = rnd.nextInt(width);
            Point tempBuff = new Point(x,y);
            if (!Snake.body.contains(tempBuff) && !walls.contains(tempBuff))
            {
               Buff = Buffs[n];
               Buff.x = tempBuff.x;
               Buff.y = tempBuff.y;
               existBuff = true;
               timeLiveBuff = Buff.timeLive;
            }
        }
    }

    private boolean CheckOnWall()
    {
        return walls.contains(Snake.GetHead());
    }

    private void fixHeadPosition(){
        Point head = Snake.GetHead();
        if (head.x >= width)
            head.x = 0;
        if (head.x < 0)
            head.x = width-1;
        if (head.y >= height)
            head.y = 0;
        if (head.y < 0)
            head.y = height-1;
    }

    private void eatBuff(){
        if (Snake.GetHead().x == Buff.x && Snake.GetHead().y == Buff.y)
        {
            Score += Buff.countScore;
            Snake.EatBuff(Buff);
            existBuff = false;
        }
    }

    private boolean CheckOnEatSelf(){
        Point snakeHead = Snake.GetHead();
        for (int i = 1; i<Snake.body.size();i++){
            if (snakeHead.x == Snake.body.get(i).x && snakeHead.y == Snake.body.get(i).y)
                return true;
        }
        return false;
    }

    private void SpawnWalls(String mode){
        if (mode.equals("unusual")){
            Random rnd = new Random();
            for (int i = 0; i < height; i++){
                Point tempWall;
                do {
                    int x = rnd.nextInt(height);
                    int y = rnd.nextInt(width);
                    tempWall = new Point(x,y);
                }
                while (Snake.body.contains(tempWall));
                    walls.add(tempWall);
                }
        }
    }
    private boolean checkOnLittleSnakeSize(){
        return Snake.body.size() < Constants.minSnakeSize;
    }

    private void collisionWithEnemy(){
        for (int i = 0; i < Snake.body.size(); i++)
            if (Snake.body.get(i).x == enemy.x && Snake.body.get(i).y == enemy.y)
                for (int j = Snake.body.size() - 1; j >= i; j--)
                    Snake.body.remove(j);
        }
    
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
