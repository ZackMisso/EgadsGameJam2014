package core;
import gameState.GameStateManager;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Arrays;

public class GameThread extends JPanel implements Runnable {

    private Game game;
    private GameStateManager gsm;
    private static int WIN_WIDTH = 800;
    private static int WIN_HEIGHT = 800;

    public GameThread(Game param) {
        game = param;
        gsm=new GameStateManager(this);
    }

    // the update Loop
    public void update() {
        gsm.update();
    }
    
    public void reset(){
        
    }

    public void paint(Graphics g) {
        BufferedImage backBuffer = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D)backBuffer.getGraphics();
        gsm.draw(g2);
        g.drawImage(backBuffer, 0, 0, game.getWindow());
        g2.dispose();
    }

    // the Main Loop (you can ignore this)
    public void run() {
        boolean running = true;
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;
        int ticks = 0;
        int frames = 0;
        long lastTimer = System.currentTimeMillis();
        double delta = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = false;
            while (delta >= 1) {
                ticks++;
                update();
                delta -= 1;
                shouldRender = true;
            }
            if (shouldRender) {
                frames++;
                paint(game.getWindow().getGraphics());
            }
            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
				// Uncomment the code below to print out the games ticks and frame rate
                //System.out.println("FPS :: "+ticks+" ticks, "+frames+" frames");
                frames = 0;
                ticks = 0;
            }
        }
        repaint();
    }

    // getter methods
    public Game getReference() {
        return game;
    }
    
    public GameStateManager getGSM(){return gsm;}
}