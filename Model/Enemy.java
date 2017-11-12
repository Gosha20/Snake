package Snake.Model;

import java.awt.*;
import java.util.Random;

public class Enemy {
    public int x;
    public int y;
    private Point courseEnemy = new Point(0, -1);
    public Point getCourseEnemy() { return courseEnemy; }
    void setCourseEnemy(Point course){ courseEnemy = course;}
    int countSteps;
    Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.countSteps = 0;
    }

    void move(int height, int width) {
        if (countSteps % 5 == 0)
            courseEnemy = changeCourse();
        while(x+courseEnemy.x < 0 || y+courseEnemy.y>=height || y+courseEnemy.y<0 || x+courseEnemy.x>=width )
            courseEnemy = changeCourse();
        x += courseEnemy.x;
        y += courseEnemy.y;
        countSteps += 1;
    }

    private Point changeCourse()
    {
        Random rnd = new Random();
        int newX = rnd.nextInt(3) - 1;
        int newY = rnd.nextInt(3) - 1;
        while((newX == 0 && newY == 0) || Math.abs(newX*newY) == 1 || (courseEnemy.x == newX && courseEnemy.y == newY))
        {
            newY = rnd.nextInt(3) - 1;
            newX = rnd.nextInt(3) - 1;
        }
        return new Point(newX,newY);
    }
}
