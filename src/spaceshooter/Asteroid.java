package spaceshooter;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import static spaceshooter.SpaceShooter.*;

public class Asteroid {
    double x, y,DIRECTION, ANGLE;
    final int SPEED, ROTATION_SPEED, SIZE, PIECES;
    final BufferedImage IMAGE;
    int health;

    public Asteroid(double xp, double yp, double angle, int speed, boolean isFragment) {
        x = xp;
        y = yp;
        DIRECTION = angle;
        SPEED = speed;
        ROTATION_SPEED = ran.nextInt(180) + 360;
        ANGLE = Math.toRadians(ran.nextInt(360));
        if (isFragment) {
            health = 1;
            IMAGE = ImageManager.FRAGMENT[ran.nextInt(ImageManager.FRAGMENT_SPRITES)];
            SIZE = 20;
            PIECES = 0;
        }
        else {
            health = 3;
            IMAGE = ImageManager.ASTEROID[ran.nextInt(ImageManager.ASTEROID_SPRITES)];
            SIZE = 80;
            PIECES = ran.nextInt(6) + 2;
        }
    }
    
    void drawAsteroid() {
        x = x + Math.cos(DIRECTION) * SPEED;
        y = y + Math.sin(DIRECTION) * SPEED;
        
        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(ANGLE, SIZE / 2, SIZE / 2);
        painter.drawImage(IMAGE, tran, null);
        
        ANGLE = ANGLE + ((2 * Math.PI) / ROTATION_SPEED);
        if (x > HEIGHT || y > WIDTH || x < 0 - SIZE || y < 0 - SIZE) health = 0;
        if (health > 0 && Player.x + Player.SIZE > x && Player.x < x + SIZE && Player.y + Player.SIZE > y && Player.y < y + SIZE) {
            explosions.add(new Explosion((int) x, (int) y, 2, (int) (SIZE * 0.05)));
            if (!Player.collide) explosions.add(new Explosion(Player.x, Player.y, 2, 2));
            health = 0;
            Player.collide = true;
        }
        else Player.collide = false;
    }
    
    void takeDamage() {
        health--;
        if (health == 0) {
            explosions.add(new Explosion((int) x, (int) y, 2, (int) (SIZE * 0.05)));
            for (int i = 0; i < PIECES; i++)
                asteroids.add(new Asteroid(x + ran.nextInt(SIZE / 2), y + ran.nextInt(SIZE / 2), Math.toRadians(ran.nextInt(360)), ran.nextInt(4) + 1, true));
        }
    }
    
}
