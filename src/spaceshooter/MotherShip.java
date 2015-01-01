package spaceshooter;

import java.awt.geom.AffineTransform;
import static spaceshooter.SpaceShooter.*;

public class MotherShip extends Enemy {
    static final int SET_SIZE = 160;
    double targetX, targetY, targetAngle;
    int countDown = ran.nextInt(100) + 100;
    State current = State.Moving, next;
    
    public MotherShip(double x, double y) {
        super(SET_SIZE);
        this.x = x;
        this.y = y;
        targetX = ran.nextInt(WIDTH - SIZE);
        targetY = ran.nextInt(HEIGHT - SIZE);
        speed = ran.nextInt(2) + 2;
        angle = Math.atan2(targetY + (SIZE / 2) - (y + (SIZE / 2)), targetX + (SIZE / 2) - (x + (SIZE / 2)));
        health = 6;
    }
    
    @Override
    void update() {
        if (current == State.Turning) {
            if (angle < targetAngle) angle += 0.1;
            else angle -= 0.1;
            if (angle + 0.1 > targetAngle && angle < targetAngle && angle + 0.1 > targetAngle && angle < targetAngle)
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
        
        painter.drawImage(ImageManager.MOTHERSHIP, (int) x, (int) y, null);
    }
    
    static enum State {
        Moving, Turning, Shooting;
    }
    
}
