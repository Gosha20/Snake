package Snake.Model;

import Snake.GUI.Panelv2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Snake.Constants.dotSize;

public class GameWithPacman extends Game{
    public ArrayList<Enemy> enemies = new ArrayList<>();

    public GameWithPacman(int h, int w, int snakeLen){
        super(h,w,snakeLen);
        enemies.add(new Enemy(3,4));
        enemies.add(new Enemy(7,7));
    }

    @Override
    public void refreshField(){
        collisionWithEnemy();
        for(Enemy enemy : enemies)
        {
            Point target = getTarget(enemy);
            enemy.move(getHeight(),getWidth(),target);
        }
        super.refreshField();
    }

    private Point getTarget(Enemy enemy){
        int minCountStepsToSnake = 1000;
        int countStepsToBuff = Math.abs(super.Buff.x - enemy.x) + Math.abs(super.Buff.y - enemy.y);
        Point target = new Point(super.Buff.x,super.Buff.y);
        for (Point snakePoint : super.Snake.body) {
             int temp = Math.abs(snakePoint.x - enemy.x) + Math.abs(snakePoint.y - enemy.y);
             if (temp < minCountStepsToSnake && temp < countStepsToBuff){
                 minCountStepsToSnake = temp;
                target = snakePoint;
             }
        }
        return target;
    }

    private void collisionWithEnemy(){
        for (Enemy enemy : enemies) {
            if (!gameOver)
            {
                int i = findPointMeeting(enemy);

                for (int j = Snake.body.size() - 1; j >= i; j--)
                    Snake.body.remove(j);
                gameOver = super.checkOnLittleSnakeSize();
            }
        }

    }
    private int findPointMeeting(Enemy enemy){
        for (int i = 0; i < Snake.body.size(); i++)
            if (Snake.body.get(i).x == enemy.x && Snake.body.get(i).y == enemy.y)
                return i;
        return Snake.body.size();
    }
    public void Draw(Graphics g, int scoreHeight, Image enemyImage, Panelv2 p){
        for (Enemy enemy1 : enemies) {
            Point course = enemy1.getCourseEnemy();
            enemyImage = new ImageIcon(getClass().getResource("sprt/e" + course.x + course.y + ".png")).getImage();
            Enemy enemy = enemy1;
            g.drawImage(enemyImage, enemy.x * dotSize, enemy.y * dotSize + scoreHeight, p);
        }
    }
}
