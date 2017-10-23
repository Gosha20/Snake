package Snake.GUI;

import Snake.Model.Course;
import Snake.Model.GameModel;

import java.awt.*;
import java.util.Scanner;

public class ConsoleGUI extends GameModel {

    public ConsoleGUI(int h, int w, int snakeLength, String mode) {
        super(h, w, snakeLength, mode);
    }


    public static void main(String[] args){
        ConsoleGUI game = new ConsoleGUI(10, 10, 3, "classic");
        Scanner scanner = new Scanner(System.in);
        while(!game.gameOver){
            game.Print();
//            game.Print();
            int course = scanner.nextInt();
            switch (course){
                case 8: game.Snake.SetCourse(Course.UP); break;
                case 2: game.Snake.SetCourse(Course.DOWN); break;
                case 4: game.Snake.SetCourse(Course.LEFT); break;
                case 6: game.Snake.SetCourse(Course.RIGHT); break;
                case 0: break;
            }
            game.RefreshField();
        }
    }


    public void Print(){
        for (int i = 0; i < this.getHeight(); i++)
        {
            for (int j = 0; j < this.getWidth(); j++){
                Point cp = new Point(j,i);
                if (cp.x == Buff.x && cp.y == Buff.y){
                    switch (Buff.getName()){
                        case "apple": System.out.print("a"); break;
                        case "poison": System.out.print("p"); break;
                        case "banan": System.out.print("b"); break;
                        case "grapes": System.out.print("g"); break;
                        default:System.out.print("o"); break;
                    }
                }
                else{
                    if (Snake.body.contains(cp)){
                        if (cp.x == Snake.GetHead().x && cp.y == Snake.GetHead().y)
                            System.out.print("S");
                        else
                            System.out.print("s");}
                    else{
                        if (walls.contains(cp))
                            System.out.print("#");
                        else System.out.print(".");
                    }
                }
            }
            System.out.println();
        }
    }
}
