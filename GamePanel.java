package Snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

    private Image food ;
    private Image snake ;
    private static GameModel game = new GameModel(30, 30);
    private Timer timer;
    private final int pWidth = 300;
    private final int pHeight = 300;
    private final int dotSize = 10;
    private final int delay = 400;
    private void SetImage(){
         food = new ImageIcon(getClass().getResource("Sprite/apple.png")).getImage();
         snake = new ImageIcon(getClass().getResource("Sprite/circle.png")).getImage();

    }
    public GamePanel(){
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(pWidth, pHeight));
        addKeyListener(new KAdapter());
        SetImage();
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw(g);
    }

    private void Draw(Graphics g) {
        if (!game.CheckOnEatSelf()){
            g.drawImage(food, game.Food.y * dotSize,  game.Food.x * dotSize, this);
            for(Point point : game.Snake)
                g.drawImage(snake, point.y * dotSize,point.x * dotSize, this);
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
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (pWidth - metr.stringWidth(msg)) / 2, pHeight / 2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        game.RefreshField();
        repaint();
    }

    private class KAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_DOWN:
                    game.Set_Course("DOWN");
                    break;
                case KeyEvent.VK_UP:
                    game.Set_Course("UP");
                    break;
                case KeyEvent.VK_LEFT:
                    game.Set_Course("LEFT");
                    break;
                case KeyEvent.VK_RIGHT:
                    game.Set_Course("RIGHT");
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


