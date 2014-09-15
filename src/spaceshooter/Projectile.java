package spaceshooter;

import java.awt.geom.AffineTransform;
import static spaceshooter.SpaceShooter.*;

public class Projectile extends Sprite {
    final double ANGLE;
    final int SPEED = 10, SIZE = 20;

    public Projectile(int xp, int yp, double aimAngle) {
        x = xp;
        y = yp;
        ANGLE = aimAngle;
    }
    
    @Override
    void update() {
        x = x + Math.cos(ANGLE) * SPEED;
        y = y + Math.sin(ANGLE) * SPEED;

        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(ANGLE, SIZE / 2, SIZE / 2);
        painter.drawImage(ImageManager.MISSILE, tran, null);

        for (Debris debris : debrisSprites) {
            if (!debris.remove && x + SIZE > debris.x && y + SIZE > debris.y && x < debris.x + debris.SIZE && y < debris.y + debris.SIZE) {
                debris.health--;
                if (debris.health < 1) debris.remove = true;
                if (debris.remove) {
                    if (debris.SIZE == 80) explosionSprites.add(new Explosion((int) debris.x, (int) debris.y, 2, (int) (debris.SIZE * 0.05), Explosion.ROCK_FRAGMENTS));
                    else if (debris.SIZE == 20) explosionSprites.add(new Explosion((int) x, (int) y, 2, (int) (SIZE * 0.05), Explosion.NO_FRAGMENTS));
                }
                
                if (!debris.remove) explosionSprites.add(new Explosion((int) x, (int) y, 2, 1, Explosion.NO_FRAGMENTS));
                remove = true;
                break;
            }
        }
        if (x > HEIGHT || y > WIDTH || x < 0 - SIZE || y < 0 - SIZE) remove = true;
    }
    
}
