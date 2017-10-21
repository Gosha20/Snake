package Snake.Model;

import java.awt.*;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

public class Test_Game {
    GameModel game = new GameModel(20,21, 4, "classic");

    @Test
    public void test_GameModel_initialization()
    {
        assertEquals(game.height, 20);
        assertEquals(game.width, 21);
        assertEquals(game.Snake.SnakeLength, 4);
        assertEquals(game.mode, "classic");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_wrong_initalization() {
         GameModel game = new GameModel(-1,-5, 3, "classic");
    }

    @Test
    public void test_exist_Snake() {
        assertNotNull(game.Snake);
    }

    @Test
    public void test_Score_initialization(){
        assertEquals(game.Score, 0);
    }

    @Test
    public void test_initalization_SpawnFood_method(){
        assertNotNull(game.Buff);
    }

    @Test
    public void test_SpawnFood_method_in_game(){
        game.walls.add(new Point(game.Buff.x, game.Buff.y));
        game.existBuff = false;

        game.RefreshField();

        assertTrue(game.existBuff);
    }

    @Test
    public void test_spawn_walls_method(){
        GameModel game = new GameModel(10,10, 3, "unusual");
        assertNotNull(game.walls);
        String n = game.walls.get(0).getClass().getName();
        assertTrue(game.walls.getClass().getName() == "java.util.ArrayList");
        assertTrue(game.walls.get(0).getClass().getName() == "java.awt.Point");
    }


//    @Test
//    public void test_Snake_head_setting_1(){
//        GameModel game_1 = new GameModel(1,7);
//
//        assertTrue(game_1.Snake.get(0).x != 0 && game_1.Snake.get(0).y != 0);
//        assertTrue(game_1.Snake.get(0).x != game_1.width - 1 && game_1.Snake.get(0).y != 0);
//        assertTrue(game_1.Snake.get(0).x != 0 && game_1.Snake.get(0).y != game_1.height - 1);
//        assertTrue(game_1.Snake.get(0).x != game_1.width-1 && game_1.Snake.get(0).y != game_1.height - 1);
//    }
//
//    @Test
//    public void test_Snake_head_setting_2(){
//        GameModel game = new GameModel(10,10);
//        int headX = game.Snake.get(0).x;
//        int headY = game.Snake.get(0).y;
//
//        if (game.width < 10 && game.width < 10)
//            return;
//        else{
//            if( 2 < headX && headX < game.width - 3 && 2 < headY && headY < game.height - 3 )
//                return;
//            else fail("Snake is too close to the boundaries");
//        }
//    }
//
//    @Test
//    public void test_CheckOnEatSelf_method_1(){
//        game.RefreshField();
//
//        assertFalse(game.CheckOnEatSelf());
//    }
//
//    @Test
//    public void test_CheckOnEatSelf_method_2(){
//        if (game.SnakeLength >= 5) {
//            game.Set_Course(Course.DOWN);
//
//            game.RefreshField();
//            game.Set_Course(Course.LEFT);
//            game.RefreshField();
//            game.Set_Course(Course.UP);
//            game.RefreshField();
//            game.Set_Course(Course.RIGHT);
//            game.RefreshField();
//
//            assertTrue(game.CheckOnEatSelf());
//        }
//    }

//    @Test
//    public void test_correct_eating_1(){
//        GameModel game = new GameModel(6,6);
//        game.Set_Course("UP");
//
//        game.Buff.x = game.Snake.get(0).x;//это корректно, что х и у перепутаны местами или я запутался?
//        game.Buff.y = game.Snake.get(0).y-1;
//        int sLength = game.SnakeLength;
//        int prevScore = game.Score;
//        game.RefreshField();
//
//        assertFalse(game.Buff == new Point(game.Snake.get(0).x,game.Snake.get(0).y-1));
//                                // /проверка на съеденную еду и зареспавненную в др. точке
//        assertEquals(game.SnakeLength,sLength+1);
//        assertNotEquals(game.Score, prevScore);
//    }

//    @Test
//    public void test_reverse_direction(){
//        Map<Point,String> reversedCourses = new HashMap<>();
//        reversedCourses.put(new Point(0,1), "UP");
//        reversedCourses.put(new Point(0,-1), "DOWN");
//        reversedCourses.put(new Point(1,0), "LEFT");
//        reversedCourses.put(new Point(-1,0), "RIGHT");
//        GameModel game = new GameModel(8, 8);
//        Point original = game.pCourse;
//
//        game.RefreshField();
//        String reversedCourse = reversedCourses.get(game.pCourse);
//        game.Set_Course(reversedCourse);/* ты в словаре(строка, строка) обращаешься словать(поинт)*/
//
//        assertEquals("The course should not change on the opposite",game.pCourse, original);
//    }
}
