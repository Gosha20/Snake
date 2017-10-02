package Snake;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Course {

    public Map<String,Point> Course ;
    public Course(){
        this.Course = new HashMap<String,Point>();
        this.Course.put("RIGHT", new Point(1,0));
        this.Course.put("LEFT", new Point(-1,0));
        this.Course.put("UP", new Point(0,-1));
        this.Course.put("DOWN", new Point(0,1));
    }
}
