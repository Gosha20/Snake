package Snake.GUI;

import Snake.Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.Timer;
import static Snake.Constants.*;

public class Panelv2 extends JPanel implements ActionListener {
    private Image wallImage = new ImageIcon(getClass().getResource("sprt/wall.png")).getImage();
    private Image snakeImageHead ;
    private Image snakeImageTail ;
    private Image enemyImage;
    private Image snakeImageBody = new ImageIcon(getClass().getResource("sprt/snakebody.png")).getImage();
    public GameWithBlocks gameWithBlock = null;
    public GameWithPacman gameWithPacman = null;
    public Game game;
    private Timer timer;
    private int delay = 400;
    private int scoreHeight ;
    private int scoreWidth;
    private String playerName;
    private String GameMode;

    Panelv2(int h, int w,String name, String mode){
        GameMode = mode;
        playerName = name;
        switch (mode){
            case "block":
                gameWithBlock = new GameWithBlocks(h, w,3);
                game = gameWithBlock;
                break;
            case "pacman":
                gameWithPacman = new GameWithPacman(h, w,3);
                game = gameWithPacman;
                break;
            default:
                game = new Game(h,w,3);
                break;
        }
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

    private void setImageEnemy(int i){
        Point course = gameWithPacman.enemies.get(i).getCourseEnemy();
        enemyImage = new ImageIcon(getClass().getResource("sprt/e" + course.x + course.y + ".png")).getImage();
    }
    private void SetImageHeadAndTail(){
        Point courseHead = game.Snake.getpCourseHead();
        Point courseTail = game.Snake.getNextCourseTail();
        snakeImageTail = new ImageIcon(getClass().getResource("sprt/t"+courseTail.x+""+courseTail.y+".png")).getImage();
        snakeImageHead = new ImageIcon(getClass().getResource("sprt/"+courseHead.x+""+courseHead.y+".png")).getImage();
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
        g.drawString("Game Mode: " + GameMode, 5, (int)(scoreHeight*0.75));
        g.drawString("Score: "+ game.Score, (int)(scoreWidth*0.65), (int)(scoreHeight*0.75));
        g.drawString("Speed: " + (400-delay), (int)(scoreWidth*0.65), (int)(scoreHeight*0.3));
    }

    private void drawEnemy(Graphics g){
        for (int i = 0; i< gameWithPacman.enemies.size();i++){
            setImageEnemy(i);
            Enemy enemy = gameWithPacman.enemies.get(i);
            g.drawImage(enemyImage, enemy.x*dotSize,enemy.y * dotSize + scoreHeight,this);
        }
    }
    private void drawSnake(Graphics g) {
        for (int i = 1; (i < game.Snake.body.size()-1); i++) {
            if (!(game.Snake.body.get(i+1).x == -1 && game.Snake.body.get(i+1).y == -1))
                g.drawImage(snakeImageBody,
                        game.Snake.body.get(i).x * dotSize,
                        game.Snake.body.get(i).y * dotSize + scoreHeight,
                        this);
        }
        g.drawImage(snakeImageHead, game.Snake.GetHead().x * dotSize, game.Snake.GetHead().y * dotSize + scoreHeight, this);
        g.drawImage(snakeImageTail, game.Snake.getTail().x * dotSize, game.Snake.getTail().y * dotSize + scoreHeight, this);
    }

    private void drawBackground(Graphics g)
    {
        Image back = new ImageIcon(getClass().getResource( "sprt/field.png")).getImage();
        for (int i = 0; i< game.getHeight(); i++)
            for (int j = 0; j< game.getWidth(); j++)
                g.drawImage(back,j*dotSize,i*dotSize+scoreHeight,this);
    }

    private void Draw(Graphics g) {
        if (game.gameOver){
            timer.stop();
            ExceptionsHandler.CloseWindowMsg("Score: "+ game.Score + "\n HighScore: " + ScoreHandler.readScore() ,"Game Over!");
            ScoreHandler.writeScore(playerName, game.Score);
            System.exit(1);
        }
        else {
            SetImageHeadAndTail();
            drawBackground(g);
            drawScorePanel(g);
            g.drawImage(game.Buff.getImage(), game.Buff.x * dotSize, game.Buff.y * dotSize + scoreHeight,this);
            drawSnake(g);
            if (gameWithBlock != null)
            {
                for(Point wall : gameWithBlock.walls)
                    g.drawImage(wallImage, wall.x * dotSize,wall.y * dotSize + scoreHeight, this);
            }
            if (gameWithPacman != null)
            {
                drawEnemy(g);
            }
            Toolkit.getDefaultToolkit().sync();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.stop();
        int curScore = game.Score;
        game.refreshField();
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


