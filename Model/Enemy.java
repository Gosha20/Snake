package Snake.Model;

import javax.swing.*;
import java.awt.*;

public class Enemy {
    public int x;
    public int y;
    private final Image image;

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {

        return x;
    }

    public Enemy(int x, int y){
        this.x=x;

        this.y=y;
        this.image = new ImageIcon(getClass().getResource( "granat" + ".png")).getImage();
    }
    void move(){
        int new_x = 1;
        int new_y = 1;
        this.x += new_x;
        this.y += new_y;
    }
}
