package spaceshooter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.util.HashSet;
import static spaceshooter.SpaceShooter.*;

public class Player {
    
    static final int SPEED = 3, SIZE = 40;
    static int x = WIDTH / 2 - (SIZE / 2), y = HEIGHT / 2 - (SIZE / 2), mouseX, mouseY;
    static HashSet<Integer> keys = new HashSet<>();

    void drawPlayer() {
        if (keys.contains(KeyEvent.VK_A) && x - SPEED > 0) x -= SPEED;
        if (keys.contains(KeyEvent.VK_D) && x + SIZE + SPEED < WIDTH) x += SPEED;
        if (keys.contains(KeyEvent.VK_W) && y - SPEED > 0) y -= SPEED;
        if (keys.contains(KeyEvent.VK_S) && y + SIZE + SPEED < HEIGHT) y += SPEED;
        
        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(Math.atan2(mouseY - (y + SIZE / 2), mouseX - (x + SIZE / 2)) - Math.PI / 2, SIZE / 2, SIZE / 2);
        painter.drawImage(ImageManager.SHIP, tran, null);
    }
    
    static KeyAdapter keyControl = new KeyAdapter() {
        
        @Override
        public void keyPressed(KeyEvent key) {
            keys.add(key.getKeyCode());
        }
        
        @Override
        public void keyReleased(KeyEvent key) {
            keys.remove(key.getKeyCode());
        }
    };
    
    static MouseMotionAdapter moveControl = new MouseMotionAdapter() {
        
        @Override
        public void mouseDragged(MouseEvent me) {
            mouseX = me.getX();
            mouseY = me.getY();
        }

        @Override
        public void mouseMoved(MouseEvent me) {
            mouseX = me.getX();
            mouseY = me.getY();
        }
    };
    
    static MouseAdapter clickControl = new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent me) {
            if (me.getButton() == MouseEvent.BUTTON1) bullets.add(new Projectile(x + (SIZE / 2) - 10,
                    y + (SIZE / 2) - 10, Math.atan2(mouseY - (y + (SIZE / 2)), mouseX - (x + (SIZE / 2)))));
        }
    };
}
