package Snake.Model;

import javax.swing.*;
import java.awt.*;

public class Buff {
    private final String name;
    final int  countScore;
    final int timeLive;
    public int x;
    public int y;
    private final Image Image;
    public String getName() {
        return name;
    }
    public Image getImage() {
        return Image;
    }

    Buff(String name, int countScore, int timeLive){
        this.name = name;
        this.countScore = countScore;
        this.timeLive = timeLive;
        this.x=0;
        this.y=0;
        this.Image = new ImageIcon(getClass().getResource( name + ".png")).getImage();
    }
}
