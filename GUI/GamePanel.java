package Snake.GUI;

import Snake.Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.Timer;
public class GamePanel extends JPanel implements ActionListener {
    private Image wallImage;
    private Image snakeImage;
    private static GameModel game;
    private Timer timer;
    private final int dotSize = 30;
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
        SetImage();
        timer = new Timer(delay, this);
        timer.start();
    }

    private void SetImage(){
        snakeImage = new ImageIcon(getClass().getResource("circle.png")).getImage();
        wallImage = new ImageIcon(getClass().getResource("circle.png")).getImage();
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
    private void Draw(Graphics g) {
        if (game.gameOver){
            timer.stop();
            ExceptionsHandler.CloseWindowMsg("Score: "+game.Score,"Game Over!");
            ScoreHandler.writeScore(playerName,game.Score);
            System.exit(1);
        }
        else {
            drawScorePanel(g);
            g.drawImage(game.Buff.getImage(), game.Buff.getX() * dotSize,  game.Buff.getY() * dotSize + scoreHeight , this);
            for(Point point : game.Snake.Snake)
                g.drawImage(snakeImage, point.x * dotSize,point.y * dotSize + scoreHeight, this);
            for(Point wall : game.walls)
                g.drawImage(wallImage, wall.x * dotSize,wall.y * dotSize + scoreHeight, this);
            Toolkit.getDefaultToolkit().sync();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.stop();
        game.RefreshField();
        SetImage();
        repaint();
        if (delay > 60) {
            delay -= 0;
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


