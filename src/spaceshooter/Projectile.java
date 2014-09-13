package spaceshooter;

import java.awt.geom.AffineTransform;
import static spaceshooter.SpaceShooter.*;

public class Projectile {
    double x, y;
    final double ANGLE;
    final int SPEED = 10, SIZE = 20;
    boolean remove;

    public Projectile(int xp, int yp, double aimAngle) {
        x = xp;
        y = yp;
        ANGLE = aimAngle;
    }
    
    void drawBullet() {
        x = x + Math.cos(ANGLE) * SPEED;
        y = y + Math.sin(ANGLE) * SPEED;

        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(ANGLE - Math.PI * 1.5, SIZE / 2, SIZE / 2);
        painter.drawImage(ImageManager.MISSILE, tran, null);

        for (Asteroid asteroid : asteroids) {
            if (!asteroid.remove && x + SIZE > asteroid.x && y + SIZE > asteroid.y
                    && x < asteroid.x + asteroid.SIZE && y < asteroid.y + asteroid.SIZE) {
                asteroid.remove = true;
                remove = true;
                explosions.add(new Explosion((int) asteroid.x, (int) asteroid.y, 2, 2));
                break;
            }
        }
        if (x > HEIGHT || y > WIDTH || x < 0 - SIZE || y < 0 - SIZE) remove = true;
    }
    
}
