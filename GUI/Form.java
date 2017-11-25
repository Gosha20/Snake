package Snake.GUI;

import javax.swing.*;

public class Form extends JFrame {
    public Form(Panelv2 gamePanel) {
        setTitle("Python");
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
