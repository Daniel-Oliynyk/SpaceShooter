package spaceshooter;

import java.awt.geom.AffineTransform;
import static spaceshooter.SpaceShooter.*;

public class Asteroid {
    double x, y;
    double DIRECTION, ANGLE;
    final int SPEED, TYPE, ROTATION_SPEED, SIZE = 80;
    boolean remove = false;

    public Asteroid(int xp, int yp, double angle, int speed) {
        x = xp;
        y = yp;
        DIRECTION = angle;
        SPEED = speed;
        ROTATION_SPEED = ran.nextInt(180) + 360;
        ANGLE = Math.toRadians(ran.nextInt(360));
        TYPE = ran.nextInt(ImageManager.ASTEROID_COUNT);
    }
    
    void drawAsteroid() {
        x = x + Math.cos(DIRECTION) * SPEED;
        y = y + Math.sin(DIRECTION) * SPEED;
        
        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(ANGLE, SIZE / 2, SIZE / 2);
        painter.drawImage(ImageManager.ASTEROID[TYPE], tran, null);
        
        ANGLE = ANGLE + ((2 * Math.PI) / ROTATION_SPEED);
        if (x > HEIGHT || y > WIDTH || x < 0 - SIZE || y < 0 - SIZE) remove = true;
        if (!remove && Player.x + Player.SIZE > x && Player.x < x + SIZE && Player.y + Player.SIZE > y && Player.y < y + SIZE) {
            explosions.add(new Explosion(Player.x, Player.y, 2, 1));
            if (!Player.collide) explosions.add(new Explosion((int) x, (int) y, 2, 2));
            remove = true;
            Player.collide = true;
        }
        else Player.collide = false;
    }
    
}
