package Snake.Model;

import java.awt.*;
import java.util.*;

public class GameModel {
    public Buff Buff;
    public int height;
    public int width;
    public boolean existBuff;
    public int Score;
    public ArrayList<Buff> Buffs = new ArrayList<>();
    public Snake Snake;

    public GameModel(int h, int w, int snakeLength){
        Snake = new Snake(snakeLength);
        existBuff = false;
        this.height = h;
        this.width = w;
        Set_Buffs();
        SpawnFood();
    }
    public void RefreshField(){
        CheckOnEatBuff();
        Snake.Move();
        CheckOnOutBoard();
        CheckOnEatSelf();
        SpawnFood();

    }

    public void Print(){
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++){
                Point cp = new Point(j,i);
                if (cp.x == Buff.x && cp.y == Buff.y){
                    System.out.print("o");
                }
                else{
                    if (Snake.Snake.contains(cp)){
                        if (cp.x == Snake.GetHead().x && cp.y == Snake.GetHead().y)
                            System.out.print("S");
                        else
                            System.out.print("s");}
                    else
                        System.out.print(".");}
            }
            System.out.println();
        }
    }

    private void SpawnFood(){
        Random rnd = new Random();
        int n = rnd.nextInt(Buffs.size());

           while (!existBuff){
               Buff = Buffs.get(n);
               int x = rnd.nextInt(height);
               int y = rnd.nextInt(width);
               Point tempBuff = new Point(x,y);
               if (!Snake.Snake.contains(tempBuff))
               {
                   Buff.x = tempBuff.x;
                   Buff.y = tempBuff.y;
                   existBuff = true;
               }
           }
    }
    public void CheckOnOutBoard(){
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

    public void CheckOnEatBuff(){
        if (Snake.GetHead().x == Buff.x && Snake.GetHead().y == Buff.y)
        {
            Score += Buff.countScore;
            Snake.EatBuff(Buff);
            existBuff = false;
        }
    }
    public boolean CheckOnEatSelf(){
        Point snakeHead = Snake.GetHead();
        for (int i = 1; i<Snake.SnakeLength;i++){
            if (snakeHead.x == Snake.Snake.get(i).x && snakeHead.y == Snake.Snake.get(i).y)
                return true;
        }
        return false;
    }
    public boolean LittleSnakeLength(){
        if (Snake.SnakeLength < 2)
            return true;
        return false;
    }
    private void Set_Buffs(){
        Buff apple = new Buff("apple", 1, 10);
        Buff poison = new Buff("poison", -1, 20);
        Buff banan = new Buff("banan", 3, 7);
        Buffs.add(apple);
        Buffs.add(banan);
        Buffs.add(poison);
    }
}