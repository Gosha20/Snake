package Snake.Model;

import java.awt.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class Test_Game {
    GameModel game = new GameModel(20, 21, 4, "classic");

    @Test
    public void test_GameModel_initialization() {
        assertEquals(game.getHeight(), 20);
        assertEquals(game.getWidth(), 21);
        assertEquals(game.Snake.body.size(), 4);
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
        System.out.println(game.Snake.body.size());
        while (game.Snake.body.size() > 1) {
            game.Snake.body.pop();
        }
        game.RefreshField();
        assertTrue(game.gameOver);
    }

    @Test
    public void test_Snake_is_not_in_corners() {
        assertNotEquals("Snake is in upper left corner", game.Snake.GetHead(), new Point(0, 0));
        assertNotEquals("Snake is in upper right corner", game.Snake.GetHead(), new Point(game.getWidth() - 1, 0));
        assertNotEquals("Snake is in lower left corner", game.Snake.GetHead(), new Point(0, game.getHeight() - 1));
        assertNotEquals("Snake is in lower right corner", game.Snake.GetHead(), new Point(game.getWidth() - 1, game.getHeight() - 1));
    }

    @Test
    public void test_Snake_head_setting_2() {

        int headX = game.Snake.GetHead().x;
        int headY = game.Snake.GetHead().y;

        if (game.getWidth() < 10 && game.getWidth() < 10)
            return;
        else {
            if (2 < headX && headX < game.getWidth() - 3 && 2 < headY && headY < game.getHeight() - 3)
                return;//исправить верхнюю строку в будущем
            else fail("Snake is too close to the boundaries");
        }
    }

    @Test
    public void test_CheckOnEatSelf_method_2() {
        GameModel game = new GameModel(15, 15, 6, "classic");
        if (game.Snake.getpCourse().equals(Course.UP))
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
        game.Buff.x = game.Snake.GetHead().x + game.Snake.getpCourse().x;
        game.Buff.y = game.Snake.GetHead().y + game.Snake.getpCourse().y;
        int sLength = game.Snake.body.size();
        int prevScore = game.Score;
        game.RefreshField();
        //game.RefreshField();//при втором рефреше тест работает, я не могу допереть почему с первого
        //рефреша еда сразу не съедается

        assertFalse("1", new Point(game.Buff.x, game.Buff.y) ==
                new Point(game.Snake.GetHead().x, game.Snake.GetHead().y));
        // /проверка на съеденную еду и зареспавненную в др. точке
        assertNotEquals(game.Snake.body.size(), sLength);
        assertNotEquals(game.Score, prevScore);
    }

    @Test
    public void test_reverse_direction_DOWN() {
        Point original = game.Snake.getpCourse();
//        Map<Point, String> reversed_courses = new HashMap<>();
//        reversed_courses.put(new Point(0,-1), "DOWN");
//        reversed_courses.put(new Point(-1,0), "RIGHT");
//        reversed_courses.put(new Point(0,1), "UP");
//        reversed_courses.put(new Point(1,0), "LEFT");

        game.Snake.SetCourse(Course.DOWN);
        game.RefreshField();
        game.Snake.SetCourse(Course.UP);
        assertEquals("The course should not change on the opposite", game.Snake.getpCourse(), original);
    }

    @Test
    public void test_reverse_direction_UP() {
        Point original = game.Snake.getpCourse();

        game.Snake.SetCourse(Course.UP);
        game.RefreshField();
        game.Snake.SetCourse(Course.DOWN);
        assertEquals("The course should not change on the opposite", game.Snake.getpCourse(), original);
    }

    @Test
    public void test_reverse_direction_RIGHT() {
        game.Snake.SetCourse(Course.RIGHT);
        Point original = game.Snake.getpCourse();
        game.RefreshField();
        game.Snake.SetCourse(Course.LEFT);
        assertEquals("The course should not change on the opposite", game.Snake.getpCourse(), original);
    }

    @Test
    public void test_reverse_direction_LEFT() {
        game.Snake.SetCourse(Course.LEFT);
        Point original = game.Snake.getpCourse();
        game.RefreshField();
        game.Snake.SetCourse(Course.RIGHT);
        assertEquals("The course should not change on the opposite", game.Snake.getpCourse(), original);
    }//не спрашивай зачем 4 раза почти одно и тоже, препод сказал сделать это

    @Test
    public void test_CheckOnOutBoard_method_1(){
        game.Snake.SetCourse(Course.DOWN);

        while(game.Snake.GetHead().y != game.getHeight() - 1)
            game.RefreshField();
        game.RefreshField();

        assertFalse(game.gameOver);
    }

    @Test
    public void test_CheckOnOutBoard_method_2(){
        game.Snake.SetCourse(Course.LEFT);

        while(game.Snake.GetHead().x != 0)
            game.RefreshField();
        game.RefreshField();

        assertFalse(game.gameOver);
    }

    @Test
    public void test_CheckOnWall_method(){
        game.Snake.SetCourse(Course.DOWN);
        game.walls.add(new Point(game.Snake.GetHead().x, game.Snake.GetHead().y + 1));

        game.RefreshField();

        assertTrue(game.gameOver);
    }

    @Test
    public void test_Refresh_method_1(){
        Point prevHead = game.Snake.GetHead();

        game.RefreshField();

        assertNotEquals(prevHead, game.Snake.GetHead());
        Point suggestedPoint = new Point(prevHead.x + game.Snake.getpCourse().x, prevHead.y + game.Snake.getpCourse().y);
        assertEquals(game.Snake.GetHead(),suggestedPoint);
    }
}


