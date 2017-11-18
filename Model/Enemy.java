package Snake.Model;

import java.awt.*;
import java.util.Random;

public class Enemy {
    public int x;
    public int y;
    static Point[] courses = {
                                new Point(0,1),
                                new Point(0,-1),
                                new Point(1,0),
                                new Point(-1,0)};

    private Point courseEnemy = new Point(0, -1);
    public Point getCourseEnemy() { return courseEnemy; }
    void setCourseEnemy(Point course){ courseEnemy = course;}
    int countSteps;

    Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.countSteps = 1;
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
        int i = rnd.nextInt(4);
        while ((courseEnemy.x == courses[i].x && courseEnemy.y == courses[i].x))
        {
            i = rnd.nextInt(4);
        }
        return courses[i];
    }
}
