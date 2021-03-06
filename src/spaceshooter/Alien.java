package spaceshooter;

import java.awt.geom.AffineTransform;
import static spaceshooter.SpaceShooter.*;

/**
 * The alien subclass of enemy shoots at the player and has medium health and speed.
 * @author Daniel Oliynyk
 */
public class Alien extends Enemy {
    static final int SET_SIZE = 80;
    double targetX, targetY, targetAngle;
    int missileCoolDown = ran.nextInt(100) + 100;
    State current = State.Moving, next;
    
    /**
     * Creates the alien on the specified x and y. Speed and direction are randomly generated.
     * @param x The x coordinate where the alien spawns.
     * @param y The y coordinate where the alien spawns.
     */
    public Alien(double x, double y) {
        super(SET_SIZE, Enemy.ALIEN);
        this.x = x;
        this.y = y;
        targetX = ran.nextInt(WIDTH - SIZE);
        targetY = ran.nextInt(HEIGHT - SIZE);
        speed = ran.nextInt(3) + 2;
        angle = Math.atan2(targetY + (SIZE / 2) - (y + (SIZE / 2)), targetX + (SIZE / 2) - (x + (SIZE / 2)));
        health = 3;
    }
    
    @Override
    void update() {
        super.update();
        if (current == State.Turning) {
            if (angle < targetAngle) angle += 0.1;
            else angle -= 0.1;
            if (angle + 0.1 > targetAngle && angle < targetAngle && angle + 0.1 > targetAngle && angle < targetAngle) current = next;
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
            missileCoolDown--;
            if (missileCoolDown % 20 == 0) {
                int offset = (SIZE / 2) - (Projectile.SIZE / 2);
                bulletBuffer.add(new Projectile((int) x + offset, (int) y + offset, angle, Projectile.ENEMY_MISSILE));
            }
            if (missileCoolDown < 1) {
                missileCoolDown = ran.nextInt(100) + 100;
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
            Player.takeDamage(60);
            remove = true;
        }
        
        if (x + SIZE < 0 || x > WIDTH || y + SIZE < 0 || y > HEIGHT) remove = true;
        
        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(angle, SIZE / 2, SIZE / 2);
        painter.drawImage(ImageManager.ALIEN, tran, null);
    }
    
    static enum State {
        Moving, Turning, Shooting;
    }
    
}
