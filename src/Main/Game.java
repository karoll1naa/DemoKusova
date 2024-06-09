package Main;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    public static List<GameObject> objects;
    public static boolean moving = false, hasMoved = true, somethingIsMoving = false;
    public static int direction = 0;
    private final Random rand = new Random();
    public int score;
    public Game() {
        init();
    }
    public void init() {
        objects = new ArrayList<GameObject>();
        moving = false;
        hasMoved = true;
        somethingIsMoving = false;
        score = 0;
        spawn();

    }

    public void update() {
        if (Keyboard.keyUp(KeyEvent.VK_R)){
            init();
        }
        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).update();
        }
        checkForValueIncrease();
        movingLogic();
    }

    public boolean hasFreeMoves() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                boolean cellIsEmpty = true;
                for (GameObject obj : objects) {
                    if (obj.x / 100 == x && obj.y / 100 == y) {
                        cellIsEmpty = false;
                        break;
                    }
                }
                if (cellIsEmpty) {
                    return true;
                }
            }
        }

        for (GameObject obj : objects) {
            int x = (int) (obj.x / 100);
            int y = (int) (obj.y / 100);
            if (y > 0) {
                for (GameObject neighbor : objects) {
                    if (neighbor.x / 100 == x && neighbor.y / 100 == y - 1 && neighbor.value == obj.value) {
                        return true;
                    }
                }
            }
            if (y < 3) {
                for (GameObject neighbor : objects) {
                    if (neighbor.x / 100 == x && neighbor.y / 100 == y + 1 && neighbor.value == obj.value) {
                        return true;
                    }
                }
            }
            if (x > 0) {
                for (GameObject neighbor : objects) {
                    if (neighbor.x / 100 == x - 1 && neighbor.y / 100 == y && neighbor.value == obj.value) {
                        return true;
                    }
                }
            }
            if (x < 3) {
                for (GameObject neighbor : objects) {
                    if (neighbor.x / 100 == x + 1 && neighbor.y / 100 == y && neighbor.value == obj.value) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void checkForValueIncrease() {
        for (int i = 0; i < objects.size(); i++) {
            for (int j = 0; j < objects.size(); j++) {
                if (i == j) continue;
                if (objects.get(i).x == objects.get(j).x && objects.get(i).y == objects.get(j).y && !objects.get(i).remove && !objects.get(j).remove) {
                    objects.get(j).remove = true;
                    objects.get(i).value *= 2;
                    score += objects.get(i).value;
                    objects.get(i).createSprite();
                }
            }
        }
        for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i).remove) objects.remove(i);
        }
    }

    private void spawn() {
        if(objects.size() == 16) return;
        boolean available = false;
        int x = 0, y = 0;
        while(!available) {
            x = rand.nextInt(4);
            y = rand.nextInt(4);
            boolean isAvailable = true;
            for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i).x / 100 == x && objects.get(i).y / 100 == y) {
                    isAvailable = false;
                }
            }
            if(isAvailable) available = true;
        }
        objects.add(new GameObject(x * 100, y * 100));
    }

    private void movingLogic() {
        somethingIsMoving = false;
        for(int i = 0; i < objects.size(); i++) {
            if (objects.get(i).moving) {
                somethingIsMoving = true;
                break;
            }
        }
        if(!somethingIsMoving) {
            moving = false;
            for (int i = 0; i < objects.size(); i++) {
                objects.get(i).hasMoved = false;
            }
        }
        if (!moving && hasMoved) {
            spawn();
            hasMoved = false;
        }
        if(!moving && !hasMoved) {
            if (Keyboard.keyDown(KeyEvent.VK_A)) {
                hasMoved = true;
                moving = true;
                direction = 0;
            }
            else if (Keyboard.keyDown(KeyEvent.VK_D)) {
                    hasMoved = true;
                    moving = true;
                    direction = 1;
            }
            else if (Keyboard.keyDown(KeyEvent.VK_W)) {
                hasMoved = true;
                moving = true;
                direction = 2;
            } else if (Keyboard.keyDown(KeyEvent.VK_S)) {
                hasMoved = true;
                moving = true;
                direction = 3;
            }
        }
    }
    public void render() {
        Renderer.renderBackground();
        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).render();
        }
        for(int i = 0; i < Main.pixels.length; i++) {
            Main.pixels[i] = Renderer.pixels[i];
        }
    }
    public void renderText(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(new Font("Verdana", 0, 100));
        g.setColor(Color.BLACK);

        for (int i = 0; i< objects.size(); i++) {
            GameObject o = objects.get(i);
            String s = o.value + "";
            int sw = (int) (g.getFontMetrics().stringWidth(s) / 2 / Main.scale);
            g.drawString(s, (int) (o.x + o.width / 2 - sw) * Main.scale, (int) (o.y + o.height / 2 + 18) * Main.scale);
        }
        renderScore(g);
    }
    public void renderScore(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(new Font("Verdana", Font.BOLD, 24));
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 40);
    }
    public int getScore() {
        return score;
    }
}

