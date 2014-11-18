package spaceshooter;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import static spaceshooter.SpaceShooter.*;

public class Debris extends Sprite {
    static final int NON_FRAGMENT = 0, ROCK_FRAGMENT = 1, METAL_FRAGMENT = 2, INITIAL_SIZE = 80;
    double SPEED, DIRECTION, ANGLE;
    final int ROTATION_SPEED, SIZE;
    final BufferedImage IMAGE;
    int health;

    public Debris(double xp, double yp, double angle, double speed, int type) {
        x = xp;
        y = yp;
        DIRECTION = angle;
        SPEED = speed;
        ROTATION_SPEED = ran.nextInt(180) + 360;
        ANGLE = Math.toRadians(ran.nextInt(360));
        
        if (type == NON_FRAGMENT) {
            health = 3;
            SIZE = 80;
            IMAGE = ImageManager.ASTEROID[ran.nextInt(ImageManager.ASTEROID_SPRITES)];
        }
        else {
            health = 1;
            SIZE = 20;
            if (type == ROCK_FRAGMENT) IMAGE = ImageManager.ROCK[ran.nextInt(ImageManager.ROCK_SPRITES)];
            else IMAGE = ImageManager.METAL[ran.nextInt(ImageManager.METAL_SPRITES)];
        }
    }
    
    @Override
    void update() {
        x += Math.cos(DIRECTION) * SPEED;
        y += Math.sin(DIRECTION) * SPEED;
        
        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(ANGLE, SIZE / 2, SIZE / 2);
        painter.drawImage(IMAGE, tran, null);
        
        ANGLE = ANGLE + ((2 * Math.PI) / ROTATION_SPEED);
        if (x > WIDTH || y > HEIGHT || x < 0 - SIZE || y < 0 - SIZE) remove = true;
        if (!remove && Player.x + Player.SIZE > x && Player.x < x + SIZE && Player.y + Player.SIZE > y && Player.y < y + SIZE) {
            explosionBuffer.add(new Explosion((int) x, (int) y, 2, (int) (SIZE * 0.05), Explosion.NO_FRAGMENTS));
            explosionBuffer.add(new Explosion(Player.x, Player.y, 2, 2, Explosion.NO_FRAGMENTS));
            Player.takeDamage(SIZE / 2);
            remove = true;
        }
    }
    
}
