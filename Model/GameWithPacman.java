package Snake.Model;

import Snake.GUI.Panelv2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static Snake.Constants.dotSize;

public class GameWithPacman extends Game{
    public ArrayList<Enemy> enemies = new ArrayList<>();

    private long startTime;
    private long timer;
    private int countScore;

    public GameWithPacman(int h, int w, int snakeLen){
        super(h,w,snakeLen);
        setChallenge();
        enemies.add(new Enemy(3,4));
//        enemies.add(new Enemy(7,7));
    }
    private void setChallenge(){
        Random rnd = new Random();
        long timeInMils = (rnd.nextInt(4)+1)*60000;
        timer = timeInMils;
        countScore = rnd.nextInt(50);
        System.out.println(timer);
        System.out.println(countScore);
    }

    @Override
    void eatBuff(){
        if (Snake.getHead().x == Buff.x && Snake.getHead().y == Buff.y)
        {
            Score += Buff.countScore;
            countScore -= Buff.countScore;
            Snake.EatBuff(Buff);
            existBuff = false;
        }
    }

    @Override
    public void refreshField(){
        if (startTime == 0)
            startTime = System.currentTimeMillis();
        gameOver = loseChallenge();
        collisionWithEnemy();
        if (!gameOver){
            for(Enemy enemy : enemies)
            {
                Point target = getTarget(enemy);
                enemy.move(getHeight(),getWidth(),target);
            }
        }
        enemyEatBuff();
        super.refreshField();
    }

    private boolean timeIsUp(){
        return timer <=0;
    }

    private boolean loseChallenge(){
        timer = timer - (System.currentTimeMillis()-startTime);
        if (countScore <= 0 && !timeIsUp()){
            setChallenge();
            return false;
        }
        else
            return timeIsUp();
    }

    private Point getTarget(Enemy enemy){
        int minlenToSnakePoint = Integer.MAX_VALUE;
        int countStepsToBuff = getLengthBtwPoints(super.Buff.x,super.Buff.y,enemy.x,enemy.y);
        Point target = new Point(super.Buff.x,super.Buff.y);
        for (Point snakePoint : super.Snake.body) {
             int lenToSnakePoint = getLengthBtwPoints(snakePoint.x,snakePoint.y,enemy.x,enemy.y);
             if (lenToSnakePoint < minlenToSnakePoint && lenToSnakePoint < countStepsToBuff){
                 minlenToSnakePoint = lenToSnakePoint;
                target = snakePoint;
             }
        }
        return target;
    }
    private void enemyEatBuff(){
        for(Enemy enemy : enemies)
        {
            if (enemy.x == Buff.x && enemy.y == Buff.y)
            {
                super.existBuff = false;
            }
        }
    }
    private int getLengthBtwPoints(int x1,int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
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

    @Override
    public void Draw(Graphics g, int scoreHeight,int scoreW, Image enemyImage, Panelv2 p){
        for (Enemy enemy1 : enemies) {
            Point course = enemy1.getCourseEnemy();
            enemyImage = new ImageIcon(getClass().getResource("sprt/e" + course.x + course.y + ".png")).getImage();
            g.drawImage(enemyImage, enemy1.x * dotSize, enemy1.y * dotSize + scoreHeight, p);

            g.drawString("Need: "+ countScore, (int)(scoreW*0.7), (int)(scoreHeight*0.75));
            g.drawString("timer: " + timer, (int)(scoreW*0.7), (int)(scoreHeight*0.3));
        }
    }
}
