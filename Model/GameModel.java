package Snake.Model;

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
                                new Buff("grapes", 5,15)};

    public GameModel(int h, int w, int snakeLength, String mode){
        this.Snake = new Snake(snakeLength);
        this.existBuff = false;
        this.height = h;
        this.width = w;
        this.mode = mode;
        SpawnFood();
        SpawnWalls(mode);
    }

    public void RefreshField(){
        timeLiveBuff--;
        if (timeLiveBuff <= 0)
            existBuff = false;
        Snake.Move();
        CheckOnEatBuff();
        CheckOnOutBoard();
        CheckOnEatSelf();
        CheckOnWall();
        LittleSnakeLength();
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
    private void CheckOnWall()
    {
        if (walls.contains(Snake.GetHead()))
            gameOver = true;
    }
    private void CheckOnOutBoard(){
        Point head = Snake.GetHead();
        if (head.x > width)
            head.x = 0;
        if (head.x < 0)
            head.x = width-1;
        if (head.y > height)
            head.y = 0;
        if (head.y < 0)
            head.y = height-1;
    }

    private void CheckOnEatBuff(){
        if (Snake.GetHead().x == Buff.x && Snake.GetHead().y == Buff.y)
        {
            Score += Buff.countScore;
            Snake.EatBuff(Buff);
            existBuff = false;
        }
    }

    private void CheckOnEatSelf(){
        Point snakeHead = Snake.GetHead();
        for (int i = 1; i<Snake.body.size();i++){
            if (snakeHead.x == Snake.body.get(i).x && snakeHead.y == Snake.body.get(i).y)
                gameOver = true;
        }
    }
    private void SpawnWalls(String mode){
        if (mode.equals("unusual")){
            Random rnd = new Random();
            for (int i = 0; i < height; i++){
                int x = rnd.nextInt(height);
                int y = rnd.nextInt(width);
                Point tempWall = new Point(x,y);
                if (!Snake.body.contains(tempWall))
                {
                    walls.add(tempWall);
                }
                else i-=1;
                }
        }
    }
    private void LittleSnakeLength(){
        if (Snake.body.size() < 2)
            gameOver = true;
    }
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
