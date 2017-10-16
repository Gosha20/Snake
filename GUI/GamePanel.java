package Snake.GUI;

import Snake.Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.Timer;
public class GamePanel extends JPanel implements ActionListener {

    private Image snakeImage;
    private static GameModel game;
    private Timer timer;
    private final int dotSize = 30;
    private int delay = 400;

    GamePanel(int h, int w){
        game = new GameModel(20, 20,3);
        int panelHeight = (int) (Math.sqrt((double) (h * w * dotSize * dotSize))) + 30;
        int panelWidth = (int) (Math.sqrt((double) (h * w * dotSize * dotSize)));
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
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw(g);
    }

    private void Draw(Graphics g) {
        if (game.gameOver){
            timer.stop();
            ExceptionsHandler.CloseWindowMsg("Score: "+game.Score,"Game Over!");
            System.exit(1);
        }
        else {
            g.drawImage(game.Buff.getImage(), game.Buff.getX() * dotSize,  game.Buff.getY() * dotSize, this);
            for(Point point : game.Snake.Snake)
                g.drawImage(snakeImage, point.x * dotSize,point.y * dotSize, this);
            Toolkit.getDefaultToolkit().sync();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.stop();
        game.RefreshField();
        SetImage();
        repaint();
        if (delay > 60 && game.Score / 20 !=0)
        {
			delay -=20;
            timer = new Timer(delay, this);}
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


