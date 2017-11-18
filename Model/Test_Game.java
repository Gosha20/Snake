package Snake.Model;

import java.awt.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class Test_Game {
    Game game = new Game(20, 21, 4);

    @Test
    public void test_GameModel_initialization() {
        assertEquals(game.getHeight(), 20);
        assertEquals(game.getWidth(), 21);
        assertEquals(game.Snake.body.size(), 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_wrong_initalization() {
        Game game = new Game(-1, -5, 3);
    }

    @Test
    public void test_Snake_not_null() {
        assertNotNull(game.Snake);
    }

    @Test
    public void test_Score_initialization() {
        assertEquals(game.Score, 0);
    }

    @Test
    public void test_Buff_not_null() {
        assertNotNull(game.Buff);
    }

    @Test
    public void test_random_respawning_buff() {
        GameWithBlocks game = new GameWithBlocks(20,20,4);
        Point prevBuffPoint = new Point(game.Buff.x, game.Buff.y);
        game.walls.add(new Point(game.Buff.x, game.Buff.y));
        game.existBuff = false;

        game.refreshField();
        Point newBuffPoint = new Point(game.Buff.x, game.Buff.y);

        assertTrue(game.existBuff);
        assertNotEquals(newBuffPoint, prevBuffPoint);
    }

    @Test
    public void test_block_mode_has_walls() {
        GameWithBlocks game = new GameWithBlocks(10, 10, 3);

        assertNotNull(game.walls);
        assertTrue(game.walls.size() > 0);
    }

    @Test
    public void test_little_snake_size_gameover() {
        while (game.Snake.body.size() > 1) {
            game.Snake.body.pop();
        }
        game.refreshField();
        assertTrue(game.gameOver);
    }

    @Test
    public void test_snake_head_spawns_not_in_corners() {
        assertNotEquals("Snake is in upper left corner", game.Snake.GetHead(), new Point(0, 0));
        assertNotEquals("Snake is in upper right corner", game.Snake.GetHead(), new Point(game.getWidth() - 1, 0));
        assertNotEquals("Snake is in lower left corner", game.Snake.GetHead(), new Point(0, game.getHeight() - 1));
        assertNotEquals("Snake is in lower right corner", game.Snake.GetHead(), new Point(game.getWidth() - 1, game.getHeight() - 1));
    }

    @Test
    public void test_spawn_snake_head_futher_than_3_cells_to_boundaries() {

        int headX = game.Snake.GetHead().x;
        int headY = game.Snake.GetHead().y;


        if (2 > headX && headX > game.getWidth() - 3 && 2 > headY && headY > game.getHeight() - 3)
            fail("Snake is too close to the boundaries");
    }


    @Test
    public void test_CheckOnEatSelf_gameover() {
        Game game = new Game(15, 15, 6);
        if (game.Snake.getpCourseHead().equals(Course.UP))
            game.Snake.SetCourse(Course.LEFT);

        game.Snake.SetCourse(Course.DOWN);
        game.refreshField();
        game.Snake.SetCourse(Course.LEFT);
        game.refreshField();
        game.Snake.SetCourse(Course.UP);
        game.refreshField();
        game.Snake.SetCourse(Course.RIGHT);
        game.refreshField();

        assertTrue(game.gameOver);
    }

    @Test
    public void test_correct_eating() {
        Game game = new Game(6, 6, 3);
        game.Snake.SetCourse(Course.DOWN);
        game.Buff.x = game.Snake.GetHead().x + game.Snake.getpCourseHead().x;
        game.Buff.y = game.Snake.GetHead().y + game.Snake.getpCourseHead().y;
        int sLength = game.Snake.body.size();
        int prevScore = game.Score;
        game.refreshField();

        assertFalse(new Point(game.Buff.x, game.Buff.y) ==
                new Point(game.Snake.GetHead().x, game.Snake.GetHead().y));
        assertNotEquals(game.Snake.body.size(), sLength);
        assertNotEquals(game.Score, prevScore);
    }

    @Test
    public void test_reverse_direction_DOWN() {
        game.Snake = new Snake(5, 5, 3, Course.DOWN);
        Point original = game.Snake.getpCourseHead();

        game.refreshField();
        game.Snake.SetCourse(Course.UP);

        assertEquals("The course should not change on the opposite", game.Snake.getpCourseHead(), original);
    }

    @Test
    public void test_reverse_direction_UP() {
        game.Snake = new Snake(5, 5, 3, Course.UP);
        Point original = game.Snake.getpCourseHead();

        game.refreshField();
        game.Snake.SetCourse(Course.DOWN);

        assertEquals("The course should not change on the opposite", game.Snake.getpCourseHead(), original);
    }

    @Test
    public void test_reverse_direction_RIGHT() {
        game.Snake = new Snake(5, 5, 3, Course.RIGHT);
        Point original = game.Snake.getpCourseHead();

        game.refreshField();
        game.Snake.SetCourse(Course.LEFT);

        assertEquals("The course should not change on the opposite", game.Snake.getpCourseHead(), original);
    }

    @Test
    public void test_reverse_direction_LEFT() {
        game.Snake = new Snake(5, 5, 3, Course.LEFT);
        Point original = game.Snake.getpCourseHead();

        game.refreshField();
        game.Snake.SetCourse(Course.RIGHT);

        assertEquals("The course should not change on the opposite", game.Snake.getpCourseHead(), original);
    }

    @Test
    public void test_vertical_out_of_bounds() {
        Game game = new Game(5,5,2);
        game.Snake.SetCourse(Course.RIGHT);
        game.refreshField();
        game.Snake.SetCourse(Course.DOWN);
        while (game.Snake.GetHead().y != game.getHeight() - 1)
            game.refreshField();
        game.refreshField();
        assertFalse(game.gameOver);
    }

    @Test
    public void test_horizontal_out_of_bounds() {
        game.Snake.SetCourse(Course.LEFT);

        while (game.Snake.GetHead().x != 0)
            game.refreshField();
        game.refreshField();

        assertFalse(game.gameOver);
    }

    @Test
    public void test_snake_bump_in_walls_gameover() {
        GameWithBlocks game = new GameWithBlocks(10,10,4);
        game.Snake.SetCourse(Course.DOWN);
        game.walls.add(new Point(game.Snake.GetHead().x, game.Snake.GetHead().y + 1));

        game.refreshField();

        assertTrue(game.gameOver);
    }

    @Test
    public void test_Snake_move_after_refreshing() {
        Game game = new Game(5,5,3);
        Point prevHead = game.Snake.GetHead();
        game.refreshField();
        assertNotEquals(prevHead, game.Snake.GetHead());
        Point suggestedPoint = new Point(prevHead.x + game.Snake.getpCourseHead().x, prevHead.y + game.Snake.getpCourseHead().y);
        assertEquals(game.Snake.GetHead(), suggestedPoint);
    }

    @Test
    public void test_snake_getTail_basic() {
        Snake snake = new Snake(2, 2, 5, new Point(2, 2));
        assertEquals(snake.getTail(), snake.body.get(snake.body.size() - 1));
    }

    @Test
    public void test_snake_getTail_with_minus_one() {
        Snake snake = new Snake(2, 2, 5, new Point(2, 2));
        snake.body.add(new Point(-1, -1));
        assertEquals(snake.getTail(), snake.body.get(snake.body.size() - 2));
    }
    @Test
    public void test_snake_getTail_course_1() {
        Snake snake = new Snake(2, 2, 3, new Point(2, 2));
        assertEquals(snake.getNextCourseTail(), new Point(-1,0));
    }
    @Test
    public void test_enemy_change_course() {
        Enemy enemy = new Enemy(1,2);
        enemy.countSteps = 1;
        enemy.setCourseEnemy(new Point(1,0));
        Point oldCourse = enemy.getCourseEnemy();
        enemy.move(3,3);
        assertEquals(oldCourse,enemy.getCourseEnemy());
        enemy.move(3,3);
        assertNotEquals(oldCourse,enemy.getCourseEnemy());
    }
//    @Test
//    public void test_enemy_collision_snake_len3() {
//        GameWithPacman game = new GameWithPacman(5,5,3);
//        System.out.println(game.Snake.body);
//        game.enemies.countSteps = 1;
//        game.enemies.setCourseEnemy(new Point(-1,0));
//        game.enemies.x = 2;
//        game.enemies.y = 2;
//        game.Snake.SetCourse(new Point(0,0));
//        game.refreshField();
//        game.refreshField();
//        assertEquals(2,game.Snake.body.size());
//    }
//    @Test
//    public void test_enemy_collision_snake_len2() {
//        GameWithPacman game = new GameWithPacman(5,5,2);
//        game.enemies.setCourseEnemy(new Point(-1,0));
//        game.enemies.countSteps = 1;
//        game.enemies.x = 2;
//        game.enemies.y = 1;
//        game.Snake.SetCourse(new Point(0,0));
//        game.refreshField();
//        game.refreshField();
//        assertEquals(1,game.Snake.body.size());
//        assertEquals(true,game.gameOver);
//    }
//    @Test
//    public void test_enemy_collision_snake_len5() {
//        GameWithPacman game = new GameWithPacman(5,5,5);
//        game.enemies.setCourseEnemy(new Point(-1,0));
//        game.enemies.x = 2;
//        game.enemies.y = 2;
//        game.enemies.countSteps = 1;
//        game.Snake.SetCourse(new Point(0,0));
//        game.refreshField();
//        game.refreshField();
//        assertEquals(2,game.Snake.body.size());
//    }
}


