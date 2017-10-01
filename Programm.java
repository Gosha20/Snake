package Snake;

import java.util.concurrent.TimeUnit;

public class Programm {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game(5, 5);
        game.Course = Game.eCourse.DOWN;
        game.Print();
        System.out.println();

        for(int i=0;i<100;i++){
            game.RefreshField();
            game.Print();
            System.out.println();
        }
    }
}
