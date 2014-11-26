package spaceshooter;

import java.awt.geom.AffineTransform;
import static spaceshooter.SpaceShooter.*;

public class Projectile extends Sprite {
    final double ANGLE;
    static final int SPEED = 7, SIZE = 20;
    final boolean playerMissile;
    
    public Projectile(int xp, int yp, double aimAngle, boolean player) {
        x = xp;
        y = yp;
        ANGLE = aimAngle;
        playerMissile = player;
    }
    
    @Override
    void update() {
        x += Math.cos(ANGLE) * SPEED;
        y += Math.sin(ANGLE) * SPEED;
        
        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(ANGLE, SIZE / 2, SIZE / 2);
        painter.drawImage(ImageManager.MISSILE, tran, null);
        
        for (Debris debris : debrisSprites) {
            if (!debris.remove && x + SIZE > debris.x && y + SIZE > debris.y && x < debris.x + debris.SIZE && y < debris.y + debris.SIZE && debris.TYPE != Debris.HEALTH_FRAGMENT) {
                debris.health--;
                if (debris.health < 1) debris.remove = true;
                if (debris.remove) {
                    if (debris.SIZE == 80) explosionBuffer.add(new Explosion((int) debris.x, (int) debris.y, 2, (int) (debris.SIZE * 0.05), Explosion.ROCK_FRAGMENTS, true));
                    else if (debris.SIZE == 20) explosionBuffer.add(new Explosion((int) x, (int) y, 2, (int) (SIZE * 0.05), Explosion.NO_FRAGMENTS, false));
                    if (playerMissile) Player.score += debris.SIZE / 4;
                }
                
                if (!debris.remove) explosionBuffer.add(new Explosion((int) x, (int) y, 2, 1, Explosion.NO_FRAGMENTS, false));
                remove = true;
                break;
            }
        }
        
        for (Projectile bullet : bulletSprites) {
            if (bullet.playerMissile != playerMissile && !bullet.remove && x + SIZE > bullet.x && x < bullet.x + SIZE && y + SIZE > bullet.y && y < bullet.y + SIZE) {
                explosionBuffer.add(new Explosion((int) x, (int) y, 2, (int) (SIZE * 0.05), Explosion.NO_FRAGMENTS, false));
                remove = true;
                bullet.remove = true;
            }
        }
        if (playerMissile && !remove) {
            for (Alien alien : alienSprites) {
                if (!alien.remove && x + SIZE > alien.x && y + SIZE > alien.y && x < alien.x + Alien.SIZE && y < alien.y + Alien.SIZE) {
                    alien.health--;
                    if (alien.health < 1) {
                        alien.remove = true;
                        explosionBuffer.add(new Explosion((int) alien.x, (int) alien.y, 2, (int) (Alien.SIZE * 0.05), Explosion.METAL_FRAGMENTS, true));
                        Player.score += 15;
                    }
                    else explosionBuffer.add(new Explosion((int) x, (int) y, 2, (int) (SIZE * 0.05), Explosion.NO_FRAGMENTS, false));
                    remove = true;
                    break;
                }
            }
        }
        else if (x + SIZE > Player.x && x < Player.x + Player.SIZE && y + SIZE > Player.y && y < Player.y + Player.SIZE) {
            explosionBuffer.add(new Explosion(Player.x, Player.y, 2, (int) (Player.SIZE * 0.05), Explosion.NO_FRAGMENTS, false));
            explosionBuffer.add(new Explosion((int) x, (int) y, 2, (int) (SIZE * 0.05), Explosion.NO_FRAGMENTS, false));
            Player.takeDamage(10);
            remove = true;
        }
        
        if (x > WIDTH || y > HEIGHT || x < 0 - SIZE || y < 0 - SIZE) remove = true;
    }
    
}
