package Snake;

public class Programm {
    public static void main(String[] args) {
        Game game = new Game(3, 5);
        game.Course = Game.eCourse.DOWN;
        game.Print();
        System.out.println();

        for(int i=0;i<3;i++){
            game.RefreshField();
            game.Print();
            System.out.println();}
    }
}
