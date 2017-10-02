package Snake;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Program {
    public static void main (String [] args) {
        GameModel game = new GameModel(5,10);
        for (int i = 0; i< 100 ; i++){
            System.out.println(game.Snake);
            game.Print();
            System.out.println();
            Scanner scan = new Scanner(System.in);
            String s = scan.next();
            System.out.println(s);
            if (s.length() > 1){
                game.pCourse = game.DirCourse.Course.get(s);}
            game.RefreshField();
        }

    }
}
