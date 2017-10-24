package Snake.Model;
import java.awt.*;
import java.util.Stack;

public class Snake {
    public Stack<Point> body = new Stack<>();
    private Point pCourse;
//    Point SnakeSpawnPlace = new Point(5,5);
    public Point getpCourse(){return pCourse;}
    public Point GetHead(){return body.get(0);}

    public Snake(int size){
        for (int i = 0; i < size;i++){
            body.add(new Point(0, i));
        }
        pCourse = Course.RIGHT;
    }

    public Snake(int x, int y,int size, Point spawnplace) {
        if (!spawnplace.equals(Course.LEFT)) {
            for (int i = 0; i < size; i++) {
                body.add(new Point(x + i, y));
            }
        } else {
            for (int i = 0; i < size; i++) {
                body.add(new Point(x - i, y));
            }
        }
        pCourse = spawnplace;
    }

    void EatBuff(Buff buff){
        if (buff.countScore > 0){
            for (int i = 0; i < buff.countScore;i++ )
            {
                Point t1 = body.get(body.size()-1);
                Point t2 = body.get(body.size()-2);
                body.add(new Point(-1,-1));
//                Snake.add(new Point(2*t1.x-t2.x,2*t1.y-t2.y));
            }
        }
        else{
            for (int i = 0; i > buff.countScore; i--){
                body.pop();
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
        for (int i = 0; i < body.size()-1; i++) {
            next_segment = body.get(i + 1);
            body.set(i + 1, prev_segment);
            prev_segment = next_segment;
        }
    }

    public void SetCourse(Point course) {
        if (!(course.x + pCourse.x == 0 && course.y + pCourse.y == 0))
            pCourse = course;
    }
}
