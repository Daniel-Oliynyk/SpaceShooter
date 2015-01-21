package spaceshooter;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import static spaceshooter.SpaceShooter.*;

public class Projectile extends Sprite {
    static final int SPEED = 7, SIZE = 20, PLAYER_MISSILE = 0, ENEMY_MISSILE = 1, ENEMY_PLASMA = 2;
    final double ANGLE;
    final boolean COLLIDE_WITH_ENEMY;
    final BufferedImage IMAGE;
    
    public Projectile(int x, int y, double angle, int missileType) {
        this.x = x;
        this.y = y;
        this.ANGLE = angle;
        COLLIDE_WITH_ENEMY = missileType == PLAYER_MISSILE;
        if (missileType == ENEMY_PLASMA) IMAGE = ImageManager.PLASMA;
        else IMAGE = ImageManager.MISSILE;
    }
    
    @Override
    void update() {
        x += Math.cos(ANGLE) * SPEED;
        y += Math.sin(ANGLE) * SPEED;
        
        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(ANGLE, SIZE / 2, SIZE / 2);
        painter.drawImage(IMAGE, tran, null);
        
        for (Debris debris : debrisSprites) {
            if (!debris.remove && x + SIZE > debris.x && y + SIZE > debris.y && x < debris.x + debris.SIZE && y < debris.y + debris.SIZE && debris.TYPE != Debris.HEALTH_FRAGMENT) {
                debris.health--;
                if (debris.health < 1) {
                    debris.remove = true;
                    if (debris.TYPE == Debris.ASTEROID) explosionBuffer.add(new Explosion(debris.x, debris.y, 2, debris.SIZE, Explosion.ROCK_FRAGMENTS, true));
                    else if (debris.TYPE == Debris.ROCK_FRAGMENT || debris.TYPE == Debris.METAL_FRAGMENT) explosionBuffer.add(new Explosion(x, y, SIZE));
                    if (COLLIDE_WITH_ENEMY) {
                        int bonus = 0;
                        if (debris.TYPE == Debris.ASTEROID) bonus = 25;
                        else if (debris.TYPE == Debris.ROCK_FRAGMENT || debris.TYPE == Debris.METAL_FRAGMENT) bonus = 10;
                        Player.score += bonus;
                        Gui.addTextDisplay(x, y, "+" + bonus, Color.YELLOW);
                    }
                }
                else {
                    explosionBuffer.add(new Explosion(x, y, SIZE));
                    Gui.addTextDisplay(x, y, "-50", Color.RED);
                }
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
                        if (enemy.TYPE != Enemy.DRONE) explosionBuffer.add(new Explosion(enemy.x, enemy.y, 2, enemy.SIZE, Explosion.METAL_FRAGMENTS, true));
                        else explosionBuffer.add(new Explosion(enemy.x, enemy.y, enemy.SIZE));
                        int bonus = 0;
                        if (enemy.TYPE == Enemy.DRONE) bonus = 15;
                        else if (enemy.TYPE == Enemy.ALIEN) bonus = 35;
                        else if (enemy.TYPE == Enemy.MOTHERSHIP) bonus = 50;
                        Player.score += bonus;
                        Gui.addTextDisplay(x, y, "+" + bonus, Color.YELLOW);
                    }
                    else {
                        explosionBuffer.add(new Explosion(x, y, SIZE));
                        Gui.addTextDisplay(x, y, "-50", Color.RED);
                    }
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
