package Snake;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JFrame {
    GameModel game;
    Graphics graphics;
    public GamePanel(){
        super("GameModel Snake");
        game = new GameModel(10,10);
        JPanel graphicsPanel = new JPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200,200);
    }

}
