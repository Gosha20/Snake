package Snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {

    private Image food = new ImageIcon(getClass().getResource("Sprite/apple.png")).getImage();
    private Image snake = new ImageIcon(getClass().getResource("Sprite/circle.png")).getImage();
    private static GameModel game = new GameModel(35, 35);

    public GamePanel(){
        setFocusable(true);
        addKeyListener(new KAdapter());
        new Timer(200, this).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw(g);
    }

    private void Draw(Graphics g) {
        g.drawImage(food, game.Food.y * 20,  game.Food.x * 20, this);
        for(Point point : game.Snake)
            g.drawImage(snake, point.y * 20,point.x * 20, this);
        Toolkit.getDefaultToolkit().sync();
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
            }
        }
    }
}


