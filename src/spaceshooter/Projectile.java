package spaceshooter;

import java.awt.geom.AffineTransform;
import static spaceshooter.SpaceShooter.painter;

public class Projectile {
    double x, y;
    final double ANGLE;
    final int SPEED = 10;
    boolean remove = false;

    public Projectile(int xp, int yp, double aimAngle) {
        x = xp;
        y = yp;
        ANGLE = aimAngle;
    }
    
    void drawBullet() {
        x = x + Math.cos(ANGLE) * SPEED;
        y = y + Math.sin(ANGLE) * SPEED;
        if (x > SpaceShooter.HEIGHT || y > SpaceShooter.WIDTH || x < 0 || y < 0) remove = true;
        
        
        AffineTransform tran = new AffineTransform();
        tran.translate(x - 5, y - 5);
        tran.rotate(ANGLE - Math.PI / 2, 5, 5);
        painter.drawImage(ImageManager.MISSILE, tran, null);
    }
    
}
