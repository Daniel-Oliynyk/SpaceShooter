package spaceshooter;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
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
        if (!explode) {
            x = x + Math.cos(ANGLE) * SPEED;
            y = y + Math.sin(ANGLE) * SPEED;
            
            AffineTransform tran = new AffineTransform();
            tran.translate(x, y);
            tran.rotate(ANGLE - Math.PI * 1.5, SIZE / 2, SIZE / 2);
            painter.drawImage(ImageManager.MISSILE, tran, null);
            
            for (Asteroid asteroid : asteroids) {
                if (!asteroid.remove && x + SIZE > asteroid.x && y + SIZE > asteroid.y && x < asteroid.x + asteroid.SIZE && y < asteroid.y + asteroid.SIZE) {
                    asteroid.remove = true;
                    explode = true;
                    break;
                }
            }
            if (x > HEIGHT || y > WIDTH || x < 0 - SIZE || y < 0 - SIZE) remove = true;
        }
        else {
            explodeCount--;
            if (explodeCount < 1) remove = true;
            Image scaled = ImageManager.EXPLOSION[5 - (int) explodeCount / EXPLODE_SPEED];
            scaled = scaled.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
            painter.drawImage(scaled, (int) (x - SIZE), (int) (y - SIZE), null);
        }
    }
    
}
