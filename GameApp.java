package Snake;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;
import javax.swing.*;

public class GameApp extends JFrame {
    Game game;
    Graphics graphics;
    public GameApp(){

        super("Game Snake");
        game = new Game(10,10);
        JPanel graphicsPanel = new JPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200,200);
    }

}
