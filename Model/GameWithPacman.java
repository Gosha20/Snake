package Snake.Model;

import java.util.ArrayList;

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
        int result = 999999999;
        for (int i = 0; i < Snake.body.size(); i++)
            if (Snake.body.get(i).x == enemy.x && Snake.body.get(i).y == enemy.y)
            {
                result = i;
            }
        return result;
    }
}
