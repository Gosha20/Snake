package Snake.Model;
import java.awt.*;
import java.util.Stack;

public class Snake {
    public Stack<Point> Snake = new Stack<>();
    private Point pCourse;
    int SnakeLength;

    public Snake(int size){
        for (int i = 0; i < size;i++){
            Snake.add(new Point(i, 0 ));
        }
        pCourse = Course.DOWN;
        SnakeLength = Snake.size();
    }

    void EatBuff(Buff buff){
        if (buff.countScore > 0){
            for (int i = 0; i < buff.countScore;i++ )
            {
                Point t1 = Snake.get(SnakeLength-1);
                Point t2 = Snake.get(SnakeLength-2);
                Snake.add(new Point(-1,-1));
//                Snake.add(new Point(2*t1.x-t2.x,2*t1.y-t2.y));
                SnakeLength++;
            }
        }
        else{
            for (int i = 0; i > buff.countScore; i--){
                Snake.pop();
                SnakeLength+=buff.countScore;
            }
        }
    }

    void Move(){
        Point prev_segment;
        Point next_segment;
        prev_segment = Snake.get(0);
        int x = Snake.get(0).x + pCourse.x;
        int y = Snake.get(0).y + pCourse.y;
        Snake.set(0, new Point(x, y));
        for (int i = 0; i < SnakeLength-1; i++) {
            next_segment = Snake.get(i + 1);
            Snake.set(i + 1, prev_segment);
            prev_segment = next_segment;
        }
    }

    public void SetCourse(Point course) {
        if (!(course.x + pCourse.x == 0 && course.y + pCourse.y == 0))
            pCourse = course;
    }

    Point GetHead(){return Snake.get(0);}
}
