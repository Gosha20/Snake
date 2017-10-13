package Snake.GUI;

import Snake.Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

    private Image snake ;
    private static GameModel game = new GameModel(20, 20,3);
    private Timer timer;
    private final int pWidth = 600; /* allPoont*30*30 = pW*pH */
    private final int pHeight = 630;
    private final int dotSize = 30;
    private final int delay = 200;

    public GamePanel(){
        setBackground(Color.white);
        setFocusable(true);
        setPreferredSize(new Dimension(pWidth, pHeight));
        addKeyListener(new KAdapter());
        SetImage();
        timer = new Timer(delay, this);
        timer.start();
    }
    private void SetImage(){
        snake = new ImageIcon(getClass().getResource("circle.png")).getImage();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw(g);
    }

    private void Draw(Graphics g) {
        if (!(game.CheckOnEatSelf() || (game.LittleSnakeLength()))){
            g.drawImage(game.Buff.Image, game.Buff.x * dotSize,  game.Buff.y * dotSize, this);
            for(Point point : game.Snake.Snake)
                g.drawImage(snake, point.x * dotSize,point.y * dotSize, this);
            Toolkit.getDefaultToolkit().sync();
        }
        else {
            gameMassage(g, "Game Over!");
            timer.stop();
        }
    }
    private void gameMassage(Graphics g, String msg) {
        Font small = new Font("Helvetica", Font.BOLD, 40);
        FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.BLACK);
        g.setFont(small);
        g.drawString(msg, (pWidth - metr.stringWidth(msg)) / 2, pHeight / 2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        game.RefreshField();
        SetImage();
        repaint();
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


