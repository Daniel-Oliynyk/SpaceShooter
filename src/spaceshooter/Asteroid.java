package spaceshooter;

import static spaceshooter.SpaceShooter.*;

/**
 * The asteroid subclass of debris is a large asteroid that does high damage to the player.
 * It has high health and explodes into fragments for a score bonus.
 * @author Daniel Oliynyk
 */
public class Asteroid extends Debris {

    /**
     * Creates the asteroid on the specified x and y. A random image for it is generated.
     * @param x The x coordinate where the asteroid spawns.
     * @param y The y coordinate where the asteroid spawns.
     * @param angle The angle the asteroid is moving in.
     * @param speed The movement speed of the asteroid.
     */
    public Asteroid(double x, double y, double angle, double speed) {
        super(x, y, angle, speed, 80, ASTEROID, ImageManager.randomImage(ImageManager.ASTEROID));
        health = 3;
    }

    @Override
    void update() {
        super.update();
        angle = angle + ((2 * Math.PI) / ROTATION_SPEED);
        if (!remove && Player.x + Player.SIZE > x && Player.x < x + SIZE && Player.y + Player.SIZE > y && Player.y < y + SIZE) {
            explosionBuffer.add(new Explosion(x, y, SIZE));
            explosionBuffer.add(new Explosion(Player.x, Player.y, Player.SIZE));
            Player.takeDamage(50);
            remove = true;
        }
    }
    
}
