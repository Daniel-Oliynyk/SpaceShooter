package spaceshooter;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import static spaceshooter.SpaceShooter.screen;

public class Player {
    
    static final int SPEED = 3, SIZE = 40;
    static int x = 300, y = 300, mouseX, mouseY;
    static HashSet<Integer> keys = new HashSet<>();
    static BufferedImage ship;

    void drawPlayer() {
        if (keys.contains(KeyEvent.VK_A) && x - SPEED > 0) x -= SPEED;
        if (keys.contains(KeyEvent.VK_D) && x + SIZE + SPEED < SpaceShooter.WIDTH) x += SPEED;
        if (keys.contains(KeyEvent.VK_W) && y - SPEED > 0) y -= SPEED;
        if (keys.contains(KeyEvent.VK_S) && y + SIZE + SPEED < SpaceShooter.HEIGHT) y += SPEED;
        
        Graphics2D painter = screen.createGraphics();
        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(Math.atan2(mouseY - (y + SIZE / 2), mouseX - (x + SIZE / 2)) - Math.PI / 2, SIZE / 2, SIZE / 2);
        painter.drawImage(ship, tran, null);
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
    
    static MouseMotionListener moveControl = new MouseMotionListener() {
        
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
    
    static MouseListener clickControl = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent me) {
            
        }

        @Override
        public void mousePressed(MouseEvent me) {
            SpaceShooter.bullets.add(new Projectile(x, y, Math.atan2(x - mouseX, y - mouseY)));
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            
        }

        @Override
        public void mouseExited(MouseEvent me) {
            
        }
    };
}
