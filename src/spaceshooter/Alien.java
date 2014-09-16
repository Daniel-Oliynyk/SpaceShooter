package spaceshooter;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import static spaceshooter.SpaceShooter.*;

public class Alien extends Sprite {
    static final int SIZE = 80;
    double angle, speed = 2, targetX, targetY;
    int countDown = ran.nextInt(100), health = 3;

    public Alien(double xp, double yp) {
        x = xp;
        y = yp;
        angle = Math.atan2(player.y - y, player.x - x);
        targetX = ran.nextInt(WIDTH - SIZE);
        targetY = ran.nextInt(HEIGHT - SIZE);
    }
    
    @Override
    void update() {
        angle = Math.atan2((Player.y + (Player.SIZE / 2)) - (y + (SIZE / 2)), (Player.x + (Player.SIZE / 2)) - (x + (SIZE / 2)));
        double direction = Math.atan2(targetY - y, targetX - x);
        if (x > targetX - 5 && x < targetX + 5 && y > targetY - 5 && y < targetY + 5) {
            speed = 0;
            countDown--;
            if (countDown % 30 == 0) bulletBuffer.add(new Projectile((int) (x + (SIZE / 2)), (int) (y + (SIZE / 2)), angle, false));
            if (countDown < 1) {
                countDown = ran.nextInt(100);
                targetX = ran.nextInt(WIDTH - SIZE);
                targetY = ran.nextInt(HEIGHT - SIZE);
                speed = 2;
            }
        }
        x += Math.cos(direction) * speed;
        y += Math.sin(direction) * speed;
        
        if (!remove && Player.x + Player.SIZE > x && Player.x < x + SIZE && Player.y + Player.SIZE > y && Player.y < y + SIZE) {
            explosionBuffer.add(new Explosion((int) x, (int) y, 2, (int) (SIZE * 0.05), Explosion.NO_FRAGMENTS));
            explosionBuffer.add(new Explosion(Player.x, Player.y, 2, 2, Explosion.NO_FRAGMENTS));
            remove = true;
        }
        
        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(angle, SIZE / 2, SIZE / 2);
        painter.drawImage(ImageManager.ENEMY, tran, null);
    }
    
}
