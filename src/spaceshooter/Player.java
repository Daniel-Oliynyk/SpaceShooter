package spaceshooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import static spaceshooter.SpaceShooter.screen;

public class Player {
    
    static final int SPEED = 3, SIZE = 40;
    static int x = 300, y = 300;
    static HashSet<Integer> keys = new HashSet<>();
    BufferedImage ship;

    void drawPlayer() {
        if (keys.contains(KeyEvent.VK_A) && x - SPEED > 0) x -= SPEED;
        if (keys.contains(KeyEvent.VK_D) && x + SIZE + SPEED < SpaceShooter.WIDTH) x += SPEED;
        if (keys.contains(KeyEvent.VK_W) && y - SPEED > 0) y -= SPEED;
        if (keys.contains(KeyEvent.VK_S) && y + SIZE + SPEED < SpaceShooter.HEIGHT) y += SPEED;
        
        Graphics2D painter = screen.createGraphics();
//        painter.setColor(Color.RED);
//        painter.fillOval(x, y, SIZE, SIZE);
        painter.drawImage(ship, x, y, null);
    }
    
    static KeyListener keyControl = new KeyListener() {
        
        @Override
        public void keyTyped(KeyEvent key) {
            
        }
        
        @Override
        public void keyPressed(KeyEvent key) {
            keys.add(key.getKeyCode());
        }
        
        @Override
        public void keyReleased(KeyEvent key) {
            keys.remove(key.getKeyCode());
        }
    };
}
