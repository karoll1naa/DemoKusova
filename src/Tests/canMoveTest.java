package Tests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Main.Game;
import Main.GameObject;

public class canMoveTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testCanMoveWhenNoCollisions() {
        GameObject obj = new GameObject(50, 100);
        Game.objects.clear();
        Game.objects.add(obj);

        boolean canMove = obj.canMove();
        assertTrue(canMove);
    }

    @Test
    public void testCanMoveWhenCollisions() {
        GameObject obj1 = new GameObject(50, 50);
        GameObject obj2 = new GameObject(50, 50);
        Game.objects.clear();
        Game.objects.add(obj1);
        Game.objects.add(obj2);

        boolean canMove = obj1.canMove();

        assertFalse(canMove);
    }
}
