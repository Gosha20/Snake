package Snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class GameModel {
    public Point Food;
    public int height;
    public int width;
    public ArrayList<Point> Snake;
    public Course DirCourse;
    public Point pCourse;
    public int SnakeLength;
    public boolean existFood;
    public int Score;

    GameModel(int h, int w){
        existFood = false;
        DirCourse = new Course();
        this.pCourse = DirCourse.Course.get("UP");
        this.height = h;
        this.width = w;
        this.Snake = new ArrayList<Point>();
        SetSnake();
        SpawnFood();
        this.SnakeLength = Snake.size();
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
        if (x == Food.x && y == Food.y )
        {
            Score++;
            Snake.add(Food);
            SnakeLength++;
            existFood = false;
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
                if (cp.x == Food.x && cp.y == Food.y){
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
           while (!existFood){
               Random rnd = new Random();
               int x = rnd.nextInt(height);
               int y = rnd.nextInt(width);
               Point tempFood = new Point(x,y);
               if (!Snake.contains(tempFood))
               {
                   Food = tempFood;
                   existFood = true;
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