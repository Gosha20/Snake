package Snake;

import java.awt.*;
import java.util.*;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test_Game {
    GameModel game = new GameModel(20,21);

    @Test
    public void test_correct_hw() {
        assertEquals(game.height, 20);
        assertEquals(game.width, 21);
    }
    @Test
    public void test_exist_Snake() {
        assertNotNull(game.Snake);
    }
    @Test
    public void test_Snake_size() {
        assertTrue(game.Snake.size() >= 3);
        assertTrue(game.Snake.size() <= 6);
    }

    @Test
    public void test_correct_Course(){
        assertNotNull(game.pCourse);
    }
    @Test
    public void test_Score(){
        assertEquals(game.Score, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_initialization_1() {
         GameModel game = new GameModel(0,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_initialization_2(){
         GameModel game = new GameModel(-5,-1001);
    }




    /*
    БЕЛЕБЕРДА КАКАЯ ТО С КООРДИНАТАМИ В ТЕСТЕ ПРО ГОЛОВУ
    ПРАВИЛА УСТАНОВКИ ГОЛОВЫ: 1) Х и У СТОЯТ КАК ОБЫЧНО
    2) НАЧАЛЬНАЯ КООРДИНАТА = game.width/2, game.height/2 + i
     */
    @Test
    public void test_correct_Snake_head_setting_1(){
        GameModel game_1 = new GameModel(1,7);

        assertTrue(game_1.Snake.get(0).x != 0 && game_1.Snake.get(0).y != 0);
        assertTrue(game_1.Snake.get(0).x != game_1.width - 1 && game_1.Snake.get(0).y != 0);
        assertTrue(game_1.Snake.get(0).x != 0 && game_1.Snake.get(0).y != game_1.height - 1);
        assertTrue(game_1.Snake.get(0).x != game_1.width-1 && game_1.Snake.get(0).y != game_1.height - 1);
    }

    @Ignore
    @Test
    public void test_correst_Snake_head_setting_2(){
        //проверка на то что голова не врезается в края сразу (3 клетки до любого края или ребра)
        GameModel game = new GameModel(10,10);

    }


    @Test
    public void test_correct_eating_1(){
        GameModel game = new GameModel(10,10);
        game.Food.x = 5;//это корректно, что х и у перепутаны местами или я запутался?
        game.Food.y = 0;
        int sLength = game.SnakeLength;
        for (int i = 0; i < 5 ;i++){
            game.Print();
            game.RefreshField();

        }
        game.RefreshField();
        assertFalse(game.Food == new Point(5,0));//проверка на съеденную еду и зареспавненную в др. точке
        assertEquals(game.SnakeLength,sLength+1);
    }

    @Test
    public void test_correct_reverse_direction(){
        Map<String,String> reversedCourses = new HashMap<>();
        reversedCourses.put("DOWN", "UP");
        reversedCourses.put("UP", "DOWN");
        reversedCourses.put("RIGHT", "LEFT");
        reversedCourses.put("LEFT", "RIGHT");
        GameModel game = new GameModel(8, 8);
        Point original = game.pCourse;

        game.RefreshField();
        game.RefreshField();
        game.Set_Course(reversedCourses.get(game.pCourse));/* ты в словаре(строка, строка) обращаешься словать(поинт)*/

        assertEquals(game.pCourse, original);
    }
}


