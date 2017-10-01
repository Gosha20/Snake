package Snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;




public class Game {
    public Point Food;
    private int height;
    private int weight;
    public int[][] Field;
    public ArrayList<Point> Snake;
    public enum eCourse {RIGHT , LEFT, UP, DOWN};
    public eCourse Course;
    public int SnakeLength;
    private boolean existFood;
    public int Score;

    Game(int h, int w){

        this.existFood = false;
        this.Course = eCourse.LEFT;
        this.height = h;
        this.weight = w;
        this.Field = new int[h][w];
        this.Snake = new ArrayList<Point>();
        SetSnake();
        SpawnFood();
        for(int i=0; i<Snake.size();i++){
            if (i == 0)
                Field[Snake.get(i).x][Snake.get(i).y] = 2;
            else
                Field[Snake.get(i).x][Snake.get(i).y] = 1;
        }
        this.SnakeLength = 3;

    }
    public void Set_Course(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                Course = eCourse.RIGHT;
                break;
            case KeyEvent.VK_LEFT:
                Course = eCourse.LEFT;
                break;
            case KeyEvent.VK_DOWN:
                Course = eCourse.DOWN;
                break;
            case KeyEvent.VK_UP:
                Course = eCourse.UP;
                break;
        }
    }
    public void RefreshField() {
        int addX = 0;
        int addY = 0;
        SpawnFood();
        switch (Course) {
            case LEFT:
                addX = -1;
                break;
            case RIGHT:
                addX = 1;
                break;
            case UP:
                addY = -1;
                break;
            case DOWN:
                addY = 1;
                break;
        }
        ClearField();
        Point prev_segment;
        Point next_segment;
        prev_segment = this.Snake.get(0);
        for (int i = 0; i < this.SnakeLength - 1; i++) {
            if (i == 0) {
                int x = Snake.get(i).x + addY;
                int y = Snake.get(i).y + addX;

                if (y < 0)
                    y = -1;
                if (y > this.weight - 1)
                    y = 0;
                if (x < 0)
                    x = this.height - 1;
                if (x > this.height - 1)
                    x = 0;

                Snake.set(i, new Point(x, y));
                Field[Snake.get(i).x][Snake.get(i).y] = 2;
            }
            if (i < SnakeLength) {
                next_segment = Snake.get(i + 1);
                Snake.set(i + 1, prev_segment);
                Field[prev_segment.x][prev_segment.y] = 1;
                prev_segment = next_segment;
            }
            if (Snake.get(0) == Food)
            {
                Score++;
                Snake.add(Food);
                SnakeLength++;
                existFood = false;
            }
        }
    }
    public void Print(){
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < weight; j++)
                System.out.print(Field[i][ j]);
            System.out.println();
        }
    }

    private void ClearField(){
        for (int i=0;i<this.height;i++)
            for (int j=0;j<this.weight;j++)
            {
                if ((i != Food.x) && (j != Food.y))
                this.Field[i][j] = 0;
            }
    }
    private void SpawnFood(){
       if (!existFood){
            Food = new Point(2,4);
            existFood = true;
            Field[Food.x][Food.y] = 3;
       }
    }
    private void SetSnake(){
        for (int i = 0; i < 3;i++){
            this.Snake.add(new Point(0,0 + i ));
        }
    }
}