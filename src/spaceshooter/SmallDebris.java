package spaceshooter;

import static spaceshooter.SpaceShooter.*;

/**
 * The small debris subclass of debris is either a small rock or metal fragment that does low damage to the player.
 * It has no health and provides only a small score bonus.
 * @author Daniel Oliynyk
 */
public class SmallDebris extends Debris {

    /**
     * Creates the fragment on the specified x and y. A random image for it is generated.
     * @param x The x coordinate where the fragment spawns.
     * @param y The y coordinate where the fragment spawns.
     * @param angle The angle the fragment is moving in.
     * @param speed The movement speed of the fragment.
     * @param type The type of fragment. Can be one of the static ones declared in this class.
     */
    public SmallDebris(double x, double y, double angle, double speed, int type) {
        super(x, y, angle, speed, 20, type, type == ROCK_FRAGMENT? ImageManager.randomImage(ImageManager.ROCK) : ImageManager.randomImage(ImageManager.METAL));
        health = 1;
    }

    @Override
    void update() {
        super.update();
        angle = angle + ((2 * Math.PI) / ROTATION_SPEED);
        if (!remove && Player.x + Player.SIZE > x && Player.x < x + SIZE && Player.y + Player.SIZE > y && Player.y < y + SIZE) {
            explosionBuffer.add(new Explosion(x, y, SIZE));
            explosionBuffer.add(new Explosion(Player.x, Player.y, Player.SIZE));
            Player.takeDamage(20);
            remove = true;
        }
    }
    
}
