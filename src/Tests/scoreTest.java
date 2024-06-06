package Tests;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import Main.Game;
import Main.GameObject;

public class scoreTest {

    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testScoreIncreaseWhenMerging() {
        Game.objects.clear();
        Game.objects.add(new GameObject(2, 2));
        Game.objects.add(new GameObject(2, 2));

        game.checkForValueIncrease();

        assertEquals(4, game.getScore());
    }

    @Test
    public void testScoreNotIncreasedWhenNoMerge() {
        Game.objects.clear();
        Game.objects.add(new GameObject(1, 2));
        Game.objects.add(new GameObject(5, 9));

        game.checkForValueIncrease();

        assertEquals(0, game.getScore());
    }
}

