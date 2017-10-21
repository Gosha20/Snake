package Snake.GUI;

import Snake.Model.Course;
import Snake.Model.GameModel;

import java.util.Scanner;

public class ConsoleGUI {

    public static void main(String[] args){
        GameModel game = new GameModel(10, 10, 3, "classic");
        Scanner scanner = new Scanner(System.in);
        while(!game.gameOver){
            game.Print();
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
}
