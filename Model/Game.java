package Snake.Model;

import Snake.Constants;
import Snake.GUI.Panelv2;

import java.awt.*;
import java.util.Random;

public class Game {
    public Buff Buff;
    public Snake Snake;
    public boolean gameOver = false;
    public int Score;
    private final int height;
    private final int width;
    private int timeLiveBuff;
    boolean existBuff;
    private static Buff[] Buffs = new Buff[]{
            new Buff("apple", 1, 20),
            new Buff("poison", -1, 20),
            new Buff("banan", 3, 15),
            new Buff("grapes", 5,15),
            new Buff("granat", 7,10)
    };

    public Game(int h, int w, int snakeLength){
        this.Snake = new Snake(snakeLength);
        this.existBuff = false;
        this.height = h;
        this.width = w;
        SpawnFood();
    }

    public void refreshField() {
        if (!gameOver) {
            timeLiveBuff--;
            if (timeLiveBuff <= 0)
                existBuff = false;
            Snake.Move();
            fixPositionSnakeHead(Snake.getHead());
            eatBuff();
            SpawnFood();
            if (checkOnLittleSnakeSize() || CheckOnEatSelf())
                gameOver = true;
        }
    }


    private void SpawnFood(){
        Random rnd = new Random();
        int n = rnd.nextInt(Buffs.length);
        while (!existBuff){
            int x = rnd.nextInt(height);
            int y = rnd.nextInt(width);
            Point tempBuff = new Point(x,y);
            if (!Snake.body.contains(tempBuff))
            {
                Buff = Buffs[n];
                Buff.x = tempBuff.x;
                Buff.y = tempBuff.y;
                existBuff = true;
                timeLiveBuff = Buff.timeLive;
            }
        }
    }

    private void fixPositionSnakeHead(Point snakeHead){
        if (snakeHead.x >= width)
            snakeHead.x = 0;
        if (snakeHead.x < 0)
            snakeHead.x = width - 1;
        if (snakeHead.y >= height)
            snakeHead.y = 0;
        if (snakeHead.y < 0)
            snakeHead.y = height - 1;
    }

    void eatBuff(){
        if (Snake.getHead().x == Buff.x && Snake.getHead().y == Buff.y)
        {
            Score += Buff.countScore;
            Snake.EatBuff(Buff);
            existBuff = false;
        }
    }

    private boolean CheckOnEatSelf(){
        Point snakeHead = Snake.getHead();
        for (int i = 1; i<Snake.body.size();i++){
            if (snakeHead.x == Snake.body.get(i).x && snakeHead.y == Snake.body.get(i).y)
                return true;
        }
        return false;
    }

    boolean checkOnLittleSnakeSize(){
        return Snake.body.size() < Constants.minSnakeSize;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void Draw(Graphics g, int scoreHeight,int scoreW, Image Image, Panelv2 p){};
}
