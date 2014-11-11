package spaceshooter;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import static spaceshooter.SpaceShooter.*;

public class Player {
    
    static final int SPEED = 3, SIZE = 40, SHOOT_DELAY = 10;
    static int x = WIDTH / 2 - (SIZE / 2), y = HEIGHT / 2 - (SIZE / 2), Delay;
    
    void drawPlayer() {
        if (keys.contains(KeyEvent.VK_A) && x - SPEED > 0) x -= SPEED;
        if (keys.contains(KeyEvent.VK_D) && x + SIZE + SPEED < WIDTH) x += SPEED;
        if (keys.contains(KeyEvent.VK_W) && y - SPEED > 0) y -= SPEED;
        if (keys.contains(KeyEvent.VK_S) && y + SIZE + SPEED < HEIGHT) y += SPEED;
        
        Delay++;
        if (mouseButton == MouseEvent.BUTTON1 && Delay > SHOOT_DELAY) {
            int add = SIZE / 2;
            double angle = Math.atan2(mouseY - (y + add), mouseX - (x + add));
            int offset = (SIZE / 2) - (Projectile.SIZE / 2);
            bulletBuffer.add(new Projectile(x + offset, y + offset, angle, true));
            Delay = 0;
        }
        
        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(Math.atan2(mouseY - (y + SIZE / 2), mouseX - (x + SIZE / 2)), SIZE / 2, SIZE / 2);
        painter.drawImage(ImageManager.SHIP, tran, null);
    }
    
}
