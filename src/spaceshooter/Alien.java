package spaceshooter;

import java.awt.geom.AffineTransform;
import static spaceshooter.SpaceShooter.*;

public class Alien extends Sprite {
    static final int SIZE = 80;
    double angle, speed = ran.nextInt(2) + 2, targetX, targetY, targetAngle;
    int countDown = ran.nextInt(100) + 100, health = 3;
    State current = State.Moving, next;
    
    public Alien(double x, double y) {
        this.x = x;
        this.y = y;
        this.targetX = ran.nextInt(WIDTH - SIZE);
        this.targetY = ran.nextInt(HEIGHT - SIZE);
        this.angle = Math.atan2(targetY + (SIZE / 2) - (y + (SIZE / 2)), targetX + (SIZE / 2) - (x + (SIZE / 2)));
    }
    
    @Override
    void update() {
        if (current == State.Turning) {
            if (angle < targetAngle) angle += 0.1;
            else angle -= 0.1;
            if (angle + 0.1 > targetAngle && angle < targetAngle
                    && angle + 0.1 > targetAngle && angle < targetAngle)
                current = next;
        }
        else if (current == State.Moving) {
            x += Math.cos(angle) * speed;
            y += Math.sin(angle) * speed;
            if (x > targetX - speed && x < targetX + speed && y > targetY - speed && y < targetY + speed) {
                speed = ran.nextInt(2) + 2;
                current = State.Turning;
                next = State.Shooting;
                targetAngle = Math.atan2((Player.y + (Player.SIZE / 2)) - (y + (SIZE / 2)), (Player.x + (Player.SIZE / 2)) - (x + (SIZE / 2)));
            }
        }
        else if (current == State.Shooting) {
            angle = Math.atan2((Player.y + (Player.SIZE / 2)) - (y + (SIZE / 2)), (Player.x + (Player.SIZE / 2)) - (x + (SIZE / 2)));
            countDown--;
            if (countDown % 20 == 0) {
                int offset = (SIZE / 2) - (Projectile.SIZE / 2);
                bulletBuffer.add(new Projectile((int) x + offset, (int) y + offset, angle, false));
            }
            if (countDown < 1) {
                countDown = ran.nextInt(100) + 100;
                speed = ran.nextInt(2) + 2;
                targetX = ran.nextInt(WIDTH - SIZE);
                targetY = ran.nextInt(HEIGHT - SIZE);
                targetAngle = Math.atan2(targetY - (y + (SIZE / 2)), targetX - (x + (SIZE / 2)));
                current = State.Turning;
                next = State.Moving;
            }
        }
        
        if (!remove && Player.x + Player.SIZE > x && Player.x < x + SIZE && Player.y + Player.SIZE > y && Player.y < y + SIZE) {
            explosionBuffer.add(new Explosion(x, y, SIZE));
            explosionBuffer.add(new Explosion(Player.x, Player.y, Player.SIZE));
            Player.takeDamage(25);
            remove = true;
        }
        
        if (x + SIZE < 0 || x > WIDTH || y + SIZE < 0 || y > HEIGHT) remove = true;
        
        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(angle, SIZE / 2, SIZE / 2);
        painter.drawImage(ImageManager.ENEMY, tran, null);
    }
    
    static enum State {
        Moving, Turning, Shooting;
    }
    
}
