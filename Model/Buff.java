package Snake.Model;

import javax.swing.*;
import java.awt.*;

public class Buff {
    public String name;
    public int countScore;
    public int timeLive;
    public int x;
    public int y;
    public Image Image;

    public Buff(String name, int countScore, int timeLive){
        this.name = name;
        this.countScore = countScore;
        this.timeLive = timeLive;
        this.x=0;
        this.y=0;
        this.Image = new ImageIcon(getClass().getResource( name + ".png")).getImage();
    }

}
