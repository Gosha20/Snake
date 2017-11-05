package Snake.Model;

import java.awt.*;
import java.util.Random;

public class Enemy {
    public int x;
    public int y;
    private Point courseEnemy = new Point(0, 1);

    public Point getCourseEnemy() { return courseEnemy; }
    
    Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }
    void move() {
        Random rnd = new Random();

        int newX = rnd.nextInt(3) - 1;
        int newY = 0;
        while(newX == 0 && newY == 0) // не диагональ и не нулевая скорость
            newY = rnd.nextInt(3) - 1; // диапазон [-1;1]
        courseEnemy = new Point(newX, newY);

        this.x += newX;
        this.y += newY;
    }
}
