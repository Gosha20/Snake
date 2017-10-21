package Snake.Model;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class Test_Game {
    GameModel game = new GameModel(20, 21, 4, "classic");

    @Test
    public void test_GameModel_initialization() {
        assertEquals(game.height, 20);
        assertEquals(game.width, 21);
        assertEquals(game.Snake.SnakeLength, 4);
        assertEquals(game.mode, "classic");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_wrong_initalization() {
        GameModel game = new GameModel(-1, -5, 3, "classic");
    }

    @Test
    public void test_exist_Snake() {
        assertNotNull(game.Snake);
    }

    @Test
    public void test_Score_initialization() {
        assertEquals(game.Score, 0);
    }

    @Test
    public void test_initalization_SpawnFood_method() {
        assertNotNull(game.Buff);
    }

    @Test
    public void test_SpawnFood_method_in_game() {
        game.walls.add(new Point(game.Buff.x, game.Buff.y));
        game.existBuff = false;

        game.RefreshField();

        assertTrue(game.existBuff);
    }

    @Test
    public void test_spawn_walls_method() {
        GameModel game = new GameModel(10, 10, 3, "unusual");

        assertNotNull(game.walls);
        assertTrue(game.walls.getClass().getName() == "java.util.ArrayList");
        assertTrue(game.walls.get(0).getClass().getName() == "java.awt.Point");
    }

    @Test
    public void test_LittleSnakeLenght_method() {
        while (game.Snake.Snake.size() > 1) {
            game.Snake.Snake.pop();
            game.Snake.SnakeLength--;//ненормально то, что я сам должен вычитать после метода pop(). думаю как исправить
        }

        game.RefreshField();

        assertTrue(game.gameOver);
    }

    @Test
    public void test_Snake_is_not_in_corners() {
        assertNotEquals("Snake is in upper left corner", game.Snake.GetHead(), new Point(0, 0));
        assertNotEquals("Snake is in upper right corner", game.Snake.GetHead(), new Point(game.width - 1, 0));
        assertNotEquals("Snake is in lower left corner", game.Snake.GetHead(), new Point(0, game.height - 1));
        assertNotEquals("Snake is in lower right corner", game.Snake.GetHead(), new Point(game.width - 1, game.height - 1));
    }

    @Test
    public void test_Snake_head_setting_2() {

        int headX = game.Snake.GetHead().x;
        int headY = game.Snake.GetHead().y;

        if (game.width < 10 && game.width < 10)
            return;
        else {
            if (2 < headX && headX < game.width - 3 && 2 < headY && headY < game.height - 3)
                return;//исправить верхнюю строку в будущем
            else fail("Snake is too close to the boundaries");
        }
    }

    @Test
    public void test_CheckOnEatSelf_method_2() {
        GameModel game = new GameModel(15, 15, 6, "classic");
        if (game.Snake.getCourse().equals(Course.UP))
            game.Snake.SetCourse(Course.LEFT);

        game.Snake.SetCourse(Course.DOWN);
        game.RefreshField();
        game.Snake.SetCourse(Course.LEFT);
        game.RefreshField();
        game.Snake.SetCourse(Course.UP);
        game.RefreshField();
        game.Snake.SetCourse(Course.RIGHT);
        game.RefreshField();
        //game.RefreshField();//!!!почему-то надо 2 раза рефрешить чтобы она действительно врезалась

        assertTrue(game.gameOver);
    }

    @Test
    public void test_correct_eating_1() {
        GameModel game = new GameModel(6, 6, 3, "classic");
        game.Snake.SetCourse(Course.DOWN);
        game.Buff.x = game.Snake.GetHead().x + game.Snake.getCourse().x;
        game.Buff.y = game.Snake.GetHead().y + game.Snake.getCourse().y;
        int sLength = game.Snake.SnakeLength;
        int prevScore = game.Score;
        game.RefreshField();
        //game.RefreshField();//при втором рефреше тест работает, я не могу допереть почему с первого
        //рефреша еда сразу не съедается

        assertFalse("1", new Point(game.Buff.x, game.Buff.y) ==
                new Point(game.Snake.GetHead().x, game.Snake.GetHead().y));
        // /проверка на съеденную еду и зареспавненную в др. точке
        assertNotEquals(game.Snake.SnakeLength, sLength);
        assertNotEquals(game.Score, prevScore);
    }


    @Test
    public void test_reverse_direction_DOWN() {
        Point original = game.Snake.getCourse();
//        Map<Point, String> reversed_courses = new HashMap<>();
//        reversed_courses.put(new Point(0,-1), "DOWN");
//        reversed_courses.put(new Point(-1,0), "RIGHT");
//        reversed_courses.put(new Point(0,1), "UP");
//        reversed_courses.put(new Point(1,0), "LEFT");

        game.Snake.SetCourse(Course.DOWN);
        game.RefreshField();
        game.Snake.SetCourse(Course.UP);
        assertEquals("The course should not change on the opposite", game.Snake.getCourse(), original);
    }

    @Test
    public void test_reverse_direction_UP() {
        Point original = game.Snake.getCourse();

        game.Snake.SetCourse(Course.UP);
        game.RefreshField();
        game.Snake.SetCourse(Course.DOWN);
        assertEquals("The course should not change on the opposite", game.Snake.getCourse(), original);
    }

    @Test
    public void test_reverse_direction_RIGHT() {
        Point original = game.Snake.getCourse();

        game.Snake.SetCourse(Course.RIGHT);
        game.RefreshField();
        game.Snake.SetCourse(Course.LEFT);
        assertEquals("The course should not change on the opposite", game.Snake.getCourse(), original);
    }

    @Test
    public void test_reverse_direction_LEFT() {
        Point original = game.Snake.getCourse();

        game.Snake.SetCourse(Course.LEFT);
        game.RefreshField();
        game.Snake.SetCourse(Course.RIGHT);
        assertEquals("The course should not change on the opposite", game.Snake.getCourse(), original);
    }//не спрашивай зачем 4 раза почти одно и тоже, препод сказал сделать это
    
}


