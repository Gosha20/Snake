package Snake.Model;

import java.awt.*;
import java.util.*;

public class GameModel {
    public Buff Buff;
    final int height;
    final int width;
    boolean existBuff;
    public int Score;
    private static Buff[] Buffs = new Buff[]{
                                new Buff("apple", 1, 20),
                                new Buff("poison", -1, 20),
                                new Buff("banan", 3, 15),
                                new Buff("grapes", 5,15)};
    public Snake Snake;
    private int timeLiveBuff;
    public boolean gameOver = false;
    public ArrayList<Point> walls = new ArrayList<>();
    public String mode;

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

    public void Print(){
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++){
                Point cp = new Point(j,i);
                if (cp.x == Buff.getX() && cp.y == Buff.getY()){
                    switch (Buff.getName()){
                        case "apple": System.out.print("a"); break;
                        case "poison": System.out.print("p"); break;
                        case "banan": System.out.print("b"); break;
                        case "grapes": System.out.print("g"); break;
                        default:System.out.print("o"); break;
                    }
                }
                else{
                    if (Snake.Snake.contains(cp)){
                        if (cp.x == Snake.GetHead().x && cp.y == Snake.GetHead().y)
                            System.out.print("S");
                        else
                            System.out.print("s");}
                    else{
                        if (walls.contains(cp))
                            System.out.print("#");
                        else System.out.print(".");
                    }
                }
            }
            System.out.println();
        }
    }

    private void SpawnFood(){
        Random rnd = new Random();
        int n = rnd.nextInt(Buffs.length);
      while (!existBuff){
           int x = rnd.nextInt(height);
           int y = rnd.nextInt(width);
           Point tempBuff = new Point(x,y);
           if (!Snake.Snake.contains(tempBuff) && !walls.contains(tempBuff))
           {
               Buff = Buffs[n];
               Buff.x = tempBuff.x;
               Buff.y = tempBuff.y;
               existBuff = true;
               timeLiveBuff = Buff.timeLive;
           }
       }
    }
    void CheckOnWall()
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
        for (int i = 1; i<Snake.SnakeLength;i++){
            if (snakeHead.x == Snake.Snake.get(i).x && snakeHead.y == Snake.Snake.get(i).y)
                gameOver = true;
        }
    }
    void SpawnWalls(String mode){
        if (mode.equals("unusual")){
            Random rnd = new Random();
            for (int i = 0; i < height; i++){
                int x = rnd.nextInt(height);
                int y = rnd.nextInt(width);
                Point tempWall = new Point(x,y);
                if (!Snake.Snake.contains(tempWall))
                {
                    walls.add(tempWall);
                }
                else i-=1;
                }
        }
    }
    void LittleSnakeLength(){
        if (Snake.SnakeLength < 2)
            gameOver = true;
    }
}
