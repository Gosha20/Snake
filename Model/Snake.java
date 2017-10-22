package Snake.Model;
import java.awt.*;
import java.util.Stack;

public class Snake {
    public Stack<Point> body = new Stack<>();
    Point pCourse;
    int SnakeLength;

    Point getCourse(){return pCourse;}
    public int getSnakeLength(){return SnakeLength;}

    public Snake(int size){
        for (int i = 0; i < size;i++){
            body.add(new Point(5 + i, 5 ));
        }
        pCourse = Course.DOWN;
        SnakeLength = body.size();
    }

    void EatBuff(Buff buff){
        if (buff.countScore > 0){
            for (int i = 0; i < buff.countScore;i++ )
            {
                Point t1 = body.get(SnakeLength-1);
                Point t2 = body.get(SnakeLength-2);
                body.add(new Point(-1,-1));
//                Snake.add(new Point(2*t1.x-t2.x,2*t1.y-t2.y));
                SnakeLength++;
            }
        }
        else{
            for (int i = 0; i > buff.countScore; i--){
                body.pop();
                SnakeLength+=buff.countScore;
            }
        }
    }

    void Move(){
        Point prev_segment;
        Point next_segment;
        prev_segment = body.get(0);
        int x = body.get(0).x + pCourse.x;
        int y = body.get(0).y + pCourse.y;
        body.set(0, new Point(x, y));
        for (int i = 0; i < SnakeLength-1; i++) {
            next_segment = body.get(i + 1);
            body.set(i + 1, prev_segment);
            prev_segment = next_segment;
        }
    }

    public void SetCourse(Point course) {
        if (!(course.x + pCourse.x == 0 && course.y + pCourse.y == 0))
            pCourse = course;
    }

    public Point GetHead(){return body.get(0);}
}
