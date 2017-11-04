package Snake.GUI;

import Snake.Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.Timer;

import static Snake.Constants.*;

public class GamePanel extends JPanel implements ActionListener {
    private Image wallImage = new ImageIcon(getClass().getResource("well.png")).getImage();
    private Image snakeImageHead ;
    private Image snakeImageTail ;
    private Image snakeImageBody = new ImageIcon(getClass().getResource("snakebody.png")).getImage();
    private static GameModel game;
    private Timer timer;
    private int delay = 400;
    private int scoreHeight ;
    private int scoreWidth;
    private String playerName;

    GamePanel(int h, int w,String name, String mode){
        playerName = name;
        game = new GameModel(h, w,3, mode);
        int panelHeight = (int) (Math.sqrt((double) (h * w * dotSize * dotSize)));
        int panelWidth = (int) (Math.sqrt((double) (h * w * dotSize * dotSize)));
        scoreHeight = (int)(panelHeight*0.15);
        scoreWidth = panelWidth;
        panelHeight += scoreHeight;
        setBackground(Color.white);
        setFocusable(true);
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        addKeyListener(new KAdapter());
        SetImageHeadAndTail();
        timer = new Timer(delay, this);
        timer.stop();
    }

    private void SetImageHeadAndTail(){
        Point course = game.Snake.getpCourseHead();
        Point courseTail = game.Snake.getNextCourseTail();
        snakeImageTail = new ImageIcon(getClass().getResource("t"+courseTail.x+""+courseTail.y+".png")).getImage();
        snakeImageHead = new ImageIcon(getClass().getResource(course.x+""+course.y+".png")).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw(g);
    }
    private void drawScorePanel(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, scoreWidth, scoreHeight);
        g.setColor(Color.GREEN);
        Font font = new Font("Tahoma", Font.BOLD|Font.ITALIC, (int)(scoreHeight*0.3));
        g.setFont(font);
        g.drawString("Player: "+playerName, 5, (int)(scoreHeight*0.3));
        g.drawString("Game Mode: " + game.mode, 5, (int)(scoreHeight*0.75));
        g.drawString("Score: "+ game.Score, (int)(scoreWidth*0.65), (int)(scoreHeight*0.75));
        g.drawString("Speed: " + (400-delay), (int)(scoreWidth*0.65), (int)(scoreHeight*0.3));
    }
    private void drawSnake(Graphics g)
    {
        g.drawImage(snakeImageHead,game.Snake.GetHead().x * dotSize,game.Snake.GetHead().y* dotSize+ scoreHeight,this);
        for(int i = 1; i< game.Snake.body.size()-1; i++)
            g.drawImage(snakeImageBody,
                    game.Snake.body.get(i).x * dotSize,
                    game.Snake.body.get(i).y * dotSize + scoreHeight,
                    this);
        g.drawImage(snakeImageTail,game.Snake.getTail().x* dotSize,game.Snake.getTail().y* dotSize+ scoreHeight,this);
    }
    private void drawBackground(Graphics g)
    {
        Image back = new ImageIcon(getClass().getResource( "field.png")).getImage();
        for (int i=0 ; i<game.getHeight();i++)
            for (int j=0 ; j<game.getWidth();j++)
                g.drawImage(back,j*dotSize,i*dotSize+scoreHeight,this);
    }
    private void Draw(Graphics g) {
        if (game.gameOver){
            timer.stop();
            ExceptionsHandler.CloseWindowMsg("Score: "+game.Score + "\n HighScore: " + ScoreHandler.readScore() ,"Game Over!");
            ScoreHandler.writeScore(playerName,game.Score);
            System.exit(1);
        }
        else {
            SetImageHeadAndTail();
            drawBackground(g);
            drawScorePanel(g);
            g.drawImage(game.Buff.getImage(),game.Buff.x * dotSize,game.Buff.y * dotSize + scoreHeight,this);
            g.drawImage(game.enemy.getImage(),game.enemy.x * dotSize,game.enemy.y * dotSize + scoreHeight,this);
            drawSnake(g);
            for(Point wall : game.walls)
                g.drawImage(wallImage, wall.x * dotSize,wall.y * dotSize + scoreHeight, this);

            Toolkit.getDefaultToolkit().sync();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.stop();
        int curScore = game.Score;
        game.RefreshField();
        repaint();
        if (delay > 60 && curScore != game.Score) {
            delay -= (game.Score-curScore)*speedCoef;
            timer = new Timer(delay, this);
        }
        timer.start();
    }

    private class KAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_DOWN:
                    game.Snake.SetCourse(Course.DOWN);
                    break;
                case KeyEvent.VK_UP:
                    game.Snake.SetCourse(Course.UP);
                    break;
                case KeyEvent.VK_LEFT:
                    game.Snake.SetCourse(Course.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    game.Snake.SetCourse(Course.RIGHT);
                    break;
                case KeyEvent.VK_SPACE:
                    timer.stop();
                    break;
                case KeyEvent.VK_ENTER:
                    timer.start();
                    break;
            }
        }
    }
}


