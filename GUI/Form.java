package Snake.GUI;

import javax.swing.*;

public class Form extends JFrame {
    public Form(int gH,int gW) {
        setTitle("Snake");
        add(new GamePanel(gH,gW));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
