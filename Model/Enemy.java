package Snake.Model;

import java.awt.*;
import java.util.Random;

public class Enemy {
    public int x;
    public int y;
    private final static Point[] courses = {
                                new Point(0,1),
                                new Point(0,-1),
                                new Point(1,0),
                                new Point(-1,0)};

    private Point courseEnemy = new Point(0, -1);
    public Point getCourseEnemy() { return courseEnemy; }
    void setCourseEnemy(Point course){ courseEnemy = course;}
    private int countSteps;

    Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.countSteps = 1;
    }
    private int getLengthBtwPoints(int x1,int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    void move(int height, int width, Point target) {
        double minLenPath = getLengthBtwPoints(x,y,target.x,target.y);

        for (Point course : courses)
        {
            double lenPath = getLengthBtwPoints(x+course.x,y+course.y,target.x,target.y);
            if ( lenPath < minLenPath)
                courseEnemy = course;
        }
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
