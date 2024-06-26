package Main;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main extends Canvas implements Runnable {

    public static final int WIDTH = 400, HEIGHT = 400;
    public static float scale = 2.0f;
    public JFrame frame;
    public Thread thread;
    public Keyboard key;
    public Game game;
    public boolean running = false;

    public static BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    public static int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Main() {
        setPreferredSize(new Dimension((int) (WIDTH * scale), (int) (HEIGHT * scale)));
        frame = new JFrame();
        game = new Game();
        key = new Keyboard();
        addKeyListener(key);
        try {
            BufferedImage icon = ImageIO.read(getClass().getResource("/Resource/icon.png"));
            frame.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        running = true;
        thread = new Thread(this, "loopThread");
        thread.start();

    }

    public void stop() {
        try {
            thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTimeInNanoSeconds = System.nanoTime();
        long timer = System.currentTimeMillis();
        double nanoSecondsPerUpdate = 1000000000.0 / 60.0;
        double updatesToPerform = 0.0;
        int updates = 0;
        requestFocus();

        while(running) {
            long currentTimeInNanoSeconds = System.nanoTime();
            updatesToPerform += (currentTimeInNanoSeconds - lastTimeInNanoSeconds) / nanoSecondsPerUpdate;
            if (updatesToPerform >= 1) {
                update();
                updates++;
                updatesToPerform--;
            }
            lastTimeInNanoSeconds = currentTimeInNanoSeconds;
            render();
            if (System.currentTimeMillis() - timer > 1000) {
                frame.setTitle("2048");
                updates = 0;
                timer += 1000;
            }
        }

    }

    public void update() {
        game.update();
        key.update();
        if (!game.hasFreeMoves()) {
            running = false;
            int finalScore = game.getScore();
            SwingUtilities.invokeLater(() -> {
                frame.dispose();
                EndDialog endDialog = new EndDialog(finalScore);
                endDialog.setVisible(true);
            });
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs==null) {
            createBufferStrategy(3);
            return;
        }
        game.render();
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.drawImage(image, 0, 0, (int)(WIDTH * scale), (int)(HEIGHT * scale), null);
        game.renderText(g);
        g.dispose();
        bs.show();

    }
    public static void main(String[] args) {
        new Menu();
    }

}