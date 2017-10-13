package Snake.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SpriteDictionary {
    public Map<String,Image> Sprites ;

    public SpriteDictionary(){
        this.Sprites = new HashMap<String,Image>();
        this.Sprites.put("apple", new ImageIcon(getClass().getResource("apple.png")).getImage());
        this.Sprites.put("poison", new ImageIcon(getClass().getResource("poison.png")).getImage());
        this.Sprites.put("banan", new ImageIcon(getClass().getResource("banan.png")).getImage());
    }
}
