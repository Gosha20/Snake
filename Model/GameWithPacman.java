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
            enemy.move(getHeight(),getWidth());
        super.refreshField();
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
        for (int i = 0; i< enemies.size();i++){
            Point course = enemies.get(i).getCourseEnemy();
            enemyImage = new ImageIcon(getClass().getResource("sprt/e" + course.x + course.y + ".png")).getImage();
            Enemy enemy = enemies.get(i);
            g.drawImage(enemyImage, enemy.x*dotSize,enemy.y * dotSize + scoreHeight,p);
        }
    }
    private Image setImageEnemy(int i){
        Point course = enemies.get(i).getCourseEnemy();
        return new ImageIcon(getClass().getResource("sprt/e" + course.x + course.y + ".png")).getImage();
    }
}
