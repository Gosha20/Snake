package Snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameModel {
    public Point Buff;
    public int height;
    public int width;
    public ArrayList<Point> Snake;
    public Course DirCourse;
    public Point pCourse;
     int SnakeLength;
     boolean existBuff;
     int Score;
    Map<String,Buff> Buffs = new HashMap<>();

    GameModel(int h, int w){
        existBuff = false;
        DirCourse = new Course();
        this.pCourse = DirCourse.Course.get("UP");
        this.height = h;
        this.width = w;
        this.Snake = new ArrayList();
        Set_Buffs();
        SetSnake();
        SpawnFood();
        this.SnakeLength = Snake.size();
    }
    private void Set_Buffs(){
        Buff apple = new Buff("apple", 1);
        Buff poison = new Buff("poison", -1);
        Buff banan = new Buff("banan", 3);
        Buffs.put("apple", apple);
        Buffs.put("banan", banan);
        Buffs.put("poison", poison);

    }
    public void Set_Course(String event) {
        Point pEvent = DirCourse.Course.get(event);
        if (!(pEvent.x + pCourse.x == 0 && pEvent.y + pCourse.y == 0))
            this.pCourse = DirCourse.Course.get(event);
    }

    private void moveHead(){
        int x = this.Snake.get(0).x + pCourse.x;
        int y = this.Snake.get(0).y + pCourse.y;

        if (y < 0)
            y = this.height-1;
        if (y > this.height - 1)
            y = 0;
        if (x < 0)
            x = this.width - 1;
        if (x > this.width - 1)
            x = 0;
        if (x == Buff.x && y == Buff.y )
        {
            Score++;
            Snake.add(Buff);
            SnakeLength++;
            existBuff = false;
            SpawnFood();
        }
        Snake.set(0, new Point(x, y));

    }

    public void RefreshField() {
        SpawnFood();
        Point prev_segment;
        Point next_segment;
        prev_segment = this.Snake.get(0);
        moveHead();
        for (int i = 0; i < this.SnakeLength-1; i++) {
            next_segment = Snake.get(i + 1);
            Snake.set(i + 1, prev_segment);
            prev_segment = next_segment;
        }
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
                    if (Snake.contains(cp)){
                        if (cp.x == Snake.get(0).x && cp.y == Snake.get(0).y)
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
           while (!existBuff){
               Random rnd = new Random();
               int x = rnd.nextInt(height);
               int y = rnd.nextInt(width);
               int rndB = rnd.nextInt(Buffs.size());
               Point tempBuff = new Point(x,y);
               if (!Snake.contains(tempBuff))
               {
                   Buff = tempBuff;
                   existBuff = true;
               }
           }
    }

    public boolean CheckOnEatSelf(){
        Point snakeHead = Snake.get(0);
        for (int i = 1; i<SnakeLength;i++){
            if (snakeHead.x == Snake.get(i).x && snakeHead.y == Snake.get(i).y)
                return true;
        }
        return false;
    }

    private void SetSnake(){
        for (int i = 0; i < 3;i++){
            this.Snake.add(new Point(height/2, width/2+i ));
        }
    }
}