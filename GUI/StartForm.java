package Snake.GUI;

import Snake.Model.Game;
import Snake.Model.GameWithBlocks;
import Snake.Model.GameWithPacman;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class StartForm extends JFrame{
    private JTextField name = new JTextField("Player1");

    public StartForm()
    {
        setTitle("PythonSettings");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(270,200);
        setLocationRelativeTo(null);

        JPanel Panel = new JPanel(null);

        add(Panel);

        JLabel LabelNamePlayer = new JLabel("Your name:");
        Panel.add(LabelNamePlayer);
        LabelNamePlayer.setLocation(15,15);
        LabelNamePlayer.setSize(90,10);

        Panel.add(name);
        name.setLocation(90,10);
        name.setSize(80,20);

        JLabel Size = new JLabel("Size:");
        Panel.add(Size);
        Size.setLocation(52,40);
        Size.setSize(65,10);

        JComboBox comboBoxSize = new JComboBox();
        comboBoxSize.addItem("20x20");
        comboBoxSize.addItem("15x15");
        comboBoxSize.addItem("10x10");
        comboBoxSize.setSelectedIndex(2);

        Panel.add(comboBoxSize);
        comboBoxSize.setLocation(90,35);
        comboBoxSize.setSize(80,20);

        JLabel LabelMode = new JLabel("Game mode:");
        Panel.add(LabelMode);
        LabelMode.setLocation(8,65);
        LabelMode.setSize(75,10);

        JComboBox comboBoxMode = new JComboBox();
        comboBoxMode.addItem("Classic");
        comboBoxMode.addItem("block");
        comboBoxMode.addItem("pacman");
        comboBoxMode.setSelectedIndex(0);

        Panel.add(comboBoxMode);
        comboBoxMode.setLocation(90,60);
        comboBoxMode.setSize(80,20);

        JButton StartGame = new JButton("Start Game");
        Panel.add(StartGame);
        StartGame.setLocation(43,100);
        StartGame.setSize(100,20);

        JButton highScore = new JButton("HIGHSCORE");
        Panel.add(highScore);
        highScore.setLocation(33,130);
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
                    Game game;
                    switch (gameMode){
                        case "block":
                            game = new GameWithBlocks(GameSizeH, GameSizeW,3);
                            break;
                        case "pacman":
                            game = new GameWithPacman(GameSizeH, GameSizeW,3);
                            break;
                        default:
                            game = new Game(GameSizeH, GameSizeW,3);
                            break;
                    }
                    new Form(new Panelv2(GameSizeH,GameSizeW,name.getText(),game, gameMode));
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
