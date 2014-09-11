package spaceshooter;

import java.awt.geom.AffineTransform;
import static spaceshooter.SpaceShooter.*;

public class Projectile {
    double x, y;
    final double ANGLE;
    final int SPEED = 10, SIZE = 20, EXPLODE_SPEED = 2;
    boolean explode, remove;
    int explodeCount = 6 * EXPLODE_SPEED;

    public Projectile(int xp, int yp, double aimAngle) {
        x = xp;
        y = yp;
        ANGLE = aimAngle;
    }
    
    void drawBullet() {
        if (x > player.mouseX) explode = true;
        if (!explode) {
            x = x + Math.cos(ANGLE) * SPEED;
            y = y + Math.sin(ANGLE) * SPEED;
            if (x > HEIGHT || y > WIDTH || x < 0 - SIZE || y < 0 - SIZE) remove = true;
            
            AffineTransform tran = new AffineTransform();
            tran.translate(x - SIZE / 2, y - SIZE / 2);
            tran.rotate(ANGLE - Math.PI * 1.5, SIZE / 2, SIZE / 2);
            painter.drawImage(ImageManager.MISSILE, tran, null);
        }
        else {
            explodeCount--;
            if (explodeCount < 1) remove = true;
            painter.drawImage(ImageManager.EXPLOSION[5 - (int) explodeCount / EXPLODE_SPEED], (int) (x - SIZE), (int) (y - SIZE), null);
        }
    }
    
}
