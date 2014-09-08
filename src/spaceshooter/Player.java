package spaceshooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static spaceshooter.SpaceShooter.screen;

public class Player {
    
    static int x = 300, y = 300, xDir = 0, yDir = 0, SPEED = 3;
    
    void drawPlayer() {
        x += xDir;
        y += yDir;
        
        Graphics2D painter = screen.createGraphics();
        painter.setColor(Color.BLACK);
        painter.fillRect(0, 0, screen.getWidth(), screen.getHeight());
        painter.setColor(Color.RED);
        painter.fillOval(x, y, 10, 10);
    }
    
    static KeyListener keyControl = new KeyListener() {
        
        @Override
        public void keyTyped(KeyEvent ke) {
            
        }
        
        @Override
        public void keyPressed(KeyEvent ke) {
            int key = ke.getKeyCode();
            if (key == KeyEvent.VK_D) xDir = SPEED;
            else if (key == KeyEvent.VK_A) xDir = -1 * SPEED;
            
            if (key == KeyEvent.VK_S) yDir = SPEED;
            else if (key == KeyEvent.VK_W) yDir = -1 * SPEED;
        }
        
        @Override
        public void keyReleased(KeyEvent ke) {
            int key = ke.getKeyCode();
            if (key == KeyEvent.VK_D || key == KeyEvent.VK_A) xDir = 0;
            if (key == KeyEvent.VK_S || key == KeyEvent.VK_W) yDir = 0;
        }
    };
}
