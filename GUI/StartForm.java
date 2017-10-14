package Snake.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartForm extends JFrame{
    private JTextField name = new JTextField("Player1");
    private JTextField width= new JTextField("20");
    private JTextField height= new JTextField("20");
    private JTextField mode= new JTextField("classic");

    public StartForm()
    {
        setTitle("SnakeSettings");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(300,300);
        setLocationRelativeTo(null);

        JPanel Panel = new JPanel(null);
        add(Panel);

        JLabel LabelNamePlayer = new JLabel("Your name:");
        Panel.add(LabelNamePlayer);
        LabelNamePlayer.setLocation(15,15);
        LabelNamePlayer.setSize(65,10);

        Panel.add(name);
        name.setLocation(85,10);
        name.setSize(50,20);

        JLabel LabelH = new JLabel("SizeH:");
        Panel.add(LabelH);
        LabelH.setLocation(15,50);
        LabelH.setSize(65,10);

        Panel.add(height);
        height.setLocation(85,45);
        height.setSize(30,20);

        JLabel LabelW = new JLabel("SizeW:");
        Panel.add(LabelW);
        LabelW.setLocation(15,85);
        LabelW.setSize(65,10);

        Panel.add(width);
        width.setLocation(85,80);
        width.setSize(30,20);

        JLabel LabelMode = new JLabel("Game mode:");
        Panel.add(LabelMode);
        LabelMode.setLocation(15,120);
        LabelMode.setSize(75,10);

        Panel.add(mode);
        mode.setLocation(100,115);
        mode.setSize(50,20);

        JButton StartGame = new JButton("Start Game");
        Panel.add(StartGame);
        StartGame.setLocation(100,200);
        StartGame.setSize(100,20);

        StartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    int GameHeight = Integer.parseInt(height.getText());
                    int GameWight = Integer.parseInt(width.getText());
                    new Form(GameHeight, GameWight);
                    dispose();
                } catch (Exception exeption)
                {
                    DialogExeptions.Massage("Argument exeption", "Error");
                }
            }
        });
    }
}
