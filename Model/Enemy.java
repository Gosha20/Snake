package Snake.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy {
    public int x;
    public int y;
    private final Image image;

    public Image getImage() {
        return image;
    }

    Enemy(int x, int y){
        this.x=x;

        this.y=y;
        this.image = new ImageIcon(getClass().getResource( "granat" + ".png")).getImage();
    }
    void move(){
        Random rnd = new Random();
        int new_x = rnd.nextInt(1);
        int new_y = rnd.nextInt(1);
        this.x += new_x;
        this.y += new_y;
    }
}
