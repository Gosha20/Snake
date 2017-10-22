package Snake.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartForm extends JFrame{
    private JTextField name = new JTextField("Player1");

    public StartForm()
    {
        setTitle("SnakeSettings");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(200,200);
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

        JLabel Size = new JLabel("Size:");
        Panel.add(Size);
        Size.setLocation(15,40);
        Size.setSize(65,10);

        JComboBox comboBoxSize = new JComboBox();
        comboBoxSize.addItem("20x20");
        comboBoxSize.addItem("15x15");
        comboBoxSize.addItem("10x10");
        comboBoxSize.setSelectedIndex(0);

        Panel.add(comboBoxSize);
        comboBoxSize.setLocation(50,37);
        comboBoxSize.setSize(40,16);


        JLabel LabelMode = new JLabel("Game mode:");
        Panel.add(LabelMode);
        LabelMode.setLocation(15,60);
        LabelMode.setSize(75,10);

        JComboBox comboBoxMode = new JComboBox();
        comboBoxMode.addItem("Classic");
        comboBoxMode.addItem("unusual");
        comboBoxMode.setSelectedIndex(0);

        Panel.add(comboBoxMode);
        comboBoxMode.setLocation(90,58);
        comboBoxMode.setSize(60,17);

        JButton StartGame = new JButton("Start Game");
        Panel.add(StartGame);
        StartGame.setLocation(50,100);
        StartGame.setSize(100,20);

        JButton highScore = new JButton("HIGHSCORE");
        Panel.add(highScore);
        highScore.setLocation(40,130);
        highScore.setSize(120,20);

        highScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScoreForm();
            }
        });

        StartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] size = comboBoxSize.getSelectedItem().toString().split("x");
                    int GameSizeH = Integer.parseInt(size[0]);
                    int GameSizeW = Integer.parseInt(size[1]);
                    String gameMode = comboBoxMode.getSelectedItem().toString();
                    new Form(new GamePanel(GameSizeH,GameSizeW,name.getText(), gameMode));
                    dispose();
                } catch (Exception exeption)
                {
                    System.out.println(exeption);
                    ExceptionsHandler.Message("Argument exeption", "Error");
                }
            }
        });
    }
}
