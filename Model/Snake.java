package Snake.Model;
import java.awt.*;
import java.util.Stack;

public class Snake {
    public Stack<Point> body = new Stack<>();
    private Point pCourseHead;
    public Point getpCourseHead(){return pCourseHead;}
    public Point GetHead(){return body.get(0);}

    public Snake(int size){
        for (int i = 0; i < size;i++){
            body.add(new Point(1, i));
        }
        pCourseHead = Course.RIGHT;
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
        pCourseHead = spawnplace;
    }

    void EatBuff(Buff buff){
        if (buff.countScore > 0){
            for (int i = 0; i < buff.countScore;i++ )
            {
                body.add(new Point(-1,-1));
            }
        }
        else{
            for (int i = 0; i > buff.countScore; i--){
                body.pop();
            }
        }
    }

    void Move(){
        if (!(pCourseHead.x == 0 && pCourseHead.y == 0)){
            Point prev_segment;
            Point next_segment;
            prev_segment = body.get(0);
            int x = body.get(0).x + pCourseHead.x;
            int y = body.get(0).y + pCourseHead.y;
            body.set(0, new Point(x, y));
            for (int i = 0; i < body.size()-1; i++) {
                next_segment = body.get(i + 1);
                body.set(i + 1, prev_segment);
                prev_segment = next_segment;
            }
        }
    }

    public void SetCourse(Point course) {
        if (!(course.x + pCourseHead.x == 0 && course.y + pCourseHead.y == 0))
            pCourseHead = course;
    }

    public Point getTail (){
        for (int i = body.size()-1; i >= 0; i--){
            if (body.get(i).x != -1 && body.get(i).y != -1)
                return body.get(i);
        }
        return body.get(body.size()-1);
    }

    public Point getNextCourseTail (){
        Point last = null ;
        Point prelast = null;
        for (int i = body.size()-1; i >= 0; i--){
            if (body.get(i).x != -1 && body.get(i).y != -1)
            {
                last = body.get(i);
                prelast = body.get(i-1);
                break;
            }
        }
        Point tempPoint = new Point(prelast.x - last.x,prelast.y-last.y);
        Point course = new Point(0,0);
        if (tempPoint.x != 0)
            course.x = (int)(Math.signum(tempPoint.x));
            if (Math.abs(tempPoint.x)>1)
                course.x = - course.x;
        if (tempPoint.y != 0)
            course.y = (int)(Math.signum(tempPoint.y));
            if (Math.abs(tempPoint.y)>1)
                course.y = - course.y;
        return course;
    }
}
