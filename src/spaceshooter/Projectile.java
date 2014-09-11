package spaceshooter;

import java.awt.geom.AffineTransform;
import static spaceshooter.SpaceShooter.*;

public class Projectile {
    double x, y;
    final double ANGLE;
    final int SPEED = 10, SIZE = 20;
    boolean remove = false;

    public Projectile(int xp, int yp, double aimAngle) {
        x = xp;
        y = yp;
        ANGLE = aimAngle;
    }
    
    void drawBullet() {
        x = x + Math.cos(ANGLE) * SPEED;
        y = y + Math.sin(ANGLE) * SPEED;
        if (x > HEIGHT || y > WIDTH || x < 0 - SIZE || y < 0 - SIZE) remove = true;
        
        
        AffineTransform tran = new AffineTransform();
        tran.translate(x - SIZE / 2, y - SIZE / 2);
        tran.rotate(ANGLE - Math.PI * 1.5, SIZE / 2, SIZE / 2);
        painter.drawImage(ImageManager.MISSILE, tran, null);
    }
    
}
