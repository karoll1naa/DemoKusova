package Tests;
import Main.Sprite;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpriteTest {

    @Test
    public void testSpriteConstructor() {
        int width = 100;
        int height = 100;
        int color = 0xFFFF00FF; // Червоний колір
        Sprite sprite = new Sprite(width, height, color);

        assertNotNull(sprite);
        assertEquals(width, sprite.width);
        assertEquals(height, sprite.height);

        int[] pixels = sprite.pixels;
        assertNotNull(pixels);
        assertEquals(width * height, pixels.length);

        // Перевіряємо, чи всі пікселі мають встановлений колір
        for (int pixel : pixels) {
            assertEquals(color, pixel);
        }

        // Перевіряємо, чи рамка розміром 3 пікселя навколо спрайту має правильний колір
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x % 100 < 3 || x % 100 > 97 || y % 100 < 3 || y % 100 > 97) {
                    assertEquals(0xFFFF00FF, pixels[x + y * width]); // Колір має бути фіолетовий (0xFFFF00FF)
                }
            }
        }
    }
}