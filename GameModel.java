package Snake;

import java.util.HashMap;
import java.util.Map;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;


public class GameModel {
    public Point Food;
    private int height;
    private int weight;
//    public int[][] Field;
    public ArrayList<Point> Snake;
    public Map<String,Point> eCourset ;
//    public enum eCourse {RIGHT , LEFT, UP, DOWN};
    public Point Course;
    public int SnakeLength;
    public boolean existFood;
    public int Score;

    GameModel(int h, int w){
        existFood = false;
        SpawnFood();
        SetDictionary();
        this.Course = eCourset.get("DOWN");
        this.height = h;
        this.weight = w;
        this.Snake = new ArrayList<Point>();
        SetSnake();
        SpawnFood();
        this.SnakeLength = 3;
    }
    private void SetDictionary(){
        this.eCourset = new HashMap<String,Point>();
        this.eCourset.put("RIGHT", new Point(1,0));
        this.eCourset.put("LEFT", new Point(-1,0));
        this.eCourset.put("UP", new Point(0,-1));
        this.eCourset.put("DOWN", new Point(0,1));
    }
//    public void Set_Course(KeyEvent event) {
//        switch (event.getKeyCode()) {
//            case KeyEvent.VK_RIGHT:
//                Course = eCourse.RIGHT;
//                break;
//            case KeyEvent.VK_LEFT:
//                Course = eCourse.LEFT;
//                break;
//            case KeyEvent.VK_DOWN:
//                Course = eCourse.DOWN;
//                break;
//            case KeyEvent.VK_UP:
//                Course = eCourse.UP;
//                break;
//        }
//    }
    public void RefreshField() {
        int addX = 0;
        int addY = 0;
        SpawnFood();
//        switch (Course) {
//            case LEFT:
//                addX = -1;
//                break;
//            case RIGHT:
//                addX = 1;
//                break;
//            case UP:
//                addY = -1;
//                break;
//            case DOWN:
//                addY = 1;
//                break;
//        }

        Point prev_segment;
        Point next_segment;
        prev_segment = this.Snake.get(0);
        for (int i = 0; i < this.SnakeLength - 1; i++) {
            if (i == 0) {
                int x = Snake.get(i).x + Course.x;
                int y = Snake.get(i).y + Course.y;

                if (y < 0)//переделай!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    y = -1;
                if (y > this.weight - 1)
                    y = 0;
                if (x < 0)
                    x = this.height - 1;
                if (x > this.weight - 1)
                    x = 0;

                Snake.set(i, new Point(x, y));
            }
            if (i < SnakeLength) {
                next_segment = Snake.get(i + 1);
                Snake.set(i + 1, prev_segment);
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
            for (int j = 0; j < weight; j++){
                Point cp = new Point(i,j);
                if (cp.x == Food.x && cp.y == Food.y){
                    System.out.print("o");
                }
                else{
                    if (Snake.contains(cp))
                        System.out.print("s");
                    else
                        System.out.print(".");}
            }
            System.out.println();
        }
    }
    private void SpawnFood(){
       if (!existFood){
            Food = new Point(4,4);
            existFood = true;
       }
    }
    private void SetSnake(){
        for (int i = 0; i < 3;i++){
            this.Snake.add(new Point(0,0 + i ));
        }
    }
}