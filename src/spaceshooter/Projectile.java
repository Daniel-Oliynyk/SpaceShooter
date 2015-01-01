package spaceshooter;

import java.awt.geom.AffineTransform;
import static spaceshooter.SpaceShooter.*;

public class Projectile extends Sprite {
    static final int SPEED = 7, SIZE = 20;
    final double ANGLE;
    final boolean COLLIDE_WITH_ENEMY;
    
    public Projectile(int x, int y, double angle, boolean playerMissile) {
        this.x = x;
        this.y = y;
        this.ANGLE = angle;
        this.COLLIDE_WITH_ENEMY = playerMissile;
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
                    if (debris.SIZE == 80) explosionBuffer.add(new Explosion(debris.x, debris.y, 2, debris.SIZE, Explosion.ROCK_FRAGMENTS, true));
                    else if (debris.SIZE == 20) explosionBuffer.add(new Explosion(x, y, SIZE));
                    if (COLLIDE_WITH_ENEMY) Player.score += debris.SIZE / 4;
                }
                
                if (!debris.remove) explosionBuffer.add(new Explosion(x, y, SIZE));
                remove = true;
                break;
            }
        }
        
        for (Projectile bullet : bulletSprites) {
            if (bullet.COLLIDE_WITH_ENEMY != COLLIDE_WITH_ENEMY && !bullet.remove && x + SIZE > bullet.x && x < bullet.x + SIZE && y + SIZE > bullet.y && y < bullet.y + SIZE) {
                explosionBuffer.add(new Explosion(x, y, SIZE));
                remove = true;
                bullet.remove = true;
            }
        }
        if (COLLIDE_WITH_ENEMY && !remove) {
            for (Enemy enemy : enemySprites) {
                if (!enemy.remove && x + SIZE > enemy.x && y + SIZE > enemy.y && x < enemy.x + enemy.SIZE && y < enemy.y + enemy.SIZE) {
                    enemy.health--;
                    if (enemy.health < 1) {
                        enemy.remove = true;
                        explosionBuffer.add(new Explosion(enemy.x, enemy.y, 2, enemy.SIZE, Explosion.METAL_FRAGMENTS, true));
                        Player.score += 15;
                    }
                    else explosionBuffer.add(new Explosion(x, y, SIZE));
                    remove = true;
                    break;
                }
            }
        }
        else if (x + SIZE > Player.x && x < Player.x + Player.SIZE && y + SIZE > Player.y && y < Player.y + Player.SIZE) {
            explosionBuffer.add(new Explosion(Player.x, Player.y, 2, Player.SIZE, Explosion.NO_FRAGMENTS, false));
            explosionBuffer.add(new Explosion(x, y, SIZE));
            Player.takeDamage(10);
            remove = true;
        }
        
        if (x > WIDTH || y > HEIGHT || x < 0 - SIZE || y < 0 - SIZE) remove = true;
    }
    
}
