package spaceshooter;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import static spaceshooter.SpaceShooter.*;

/**
 * The parent class of all debris that is not meant to be used independently.
 * @author Daniel Oliynyk
 */
public class Debris extends Sprite {
    static final int ASTEROID = 0, ROCK_FRAGMENT = 1, METAL_FRAGMENT = 2, HEALTH_FRAGMENT = 3, SPAWN_DISTANCE = 80;
    final int ROTATION_SPEED, SIZE, TYPE;
    final double SPEED, DIRECTION;
    final BufferedImage IMAGE;
    int health;
    double angle;

    /**
     * Contains all the variables the subclasses can change.
     * @param x The x coordinate where the debris spawns.
     * @param y The y coordinate where the debris spawns.
     * @param angle The angle the debris is moving in.
     * @param speed The movement speed of the debris.
     * @param size The size of the debris used in collision.
     * @param type The type of astroid. Can be one of the static ones declared in this class.
     * @param image The image used for the debris.
     */
    public Debris(double x, double y, double angle, double speed, int size, int type, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.DIRECTION = angle;
        this.SPEED = speed;
        this.ROTATION_SPEED = ran.nextInt(180) + 360;
        this.SIZE = size;
        this.TYPE = type;
        this.IMAGE = image;
        this.angle = Math.toRadians(ran.nextInt(360));
    }
    
    @Override
    void update() {
        x += Math.cos(DIRECTION) * SPEED;
        y += Math.sin(DIRECTION) * SPEED;
        
        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(angle, SIZE / 2, SIZE / 2);
        painter.drawImage(IMAGE, tran, null);
        
        if (x > WIDTH || y > HEIGHT || x < 0 - SIZE || y < 0 - SIZE) remove = true;
    }
    
}
