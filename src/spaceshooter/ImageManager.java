package spaceshooter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import static spaceshooter.SpaceShooter.*;

/**
 * The class for importing and managing images.
 * @author Daniel Oliynyk
 */
public class ImageManager {
    static final int ASTEROID_SPRITES = 3, EXPLOSION_SPRITES = 7, ROCK_SPRITES = 3, METAL_SPRITES = 3;
    static BufferedImage SHIP, MISSILE, PLASMA, DRONE, ALIEN, MOTHERSHIP, HEALTH;
    static BufferedImage[] ASTEROID = new BufferedImage[ASTEROID_SPRITES];
    static BufferedImage[] EXPLOSION = new BufferedImage[EXPLOSION_SPRITES];
    static BufferedImage[] ROCK = new BufferedImage[ROCK_SPRITES];
    static BufferedImage[] METAL = new BufferedImage[METAL_SPRITES];
            
    static void initialize() {
        try {
            SHIP = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/player/ship.png"));
            MISSILE = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/player/missile.png"));
            PLASMA = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/enemy/plasma.png"));
            DRONE = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/enemy/drone.png"));
            ALIEN = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/enemy/alien.png"));
            MOTHERSHIP = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/enemy/mothership.png"));
            HEALTH = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/debris/health.png"));
            
            for (int i = 0; i < ASTEROID_SPRITES; i++) ASTEROID[i] = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/debris/asteroid" + i + ".png"));
            for (int i = 0; i < EXPLOSION_SPRITES; i++) EXPLOSION[i] = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/explosions/explosion" + i + ".png"));
            for (int i = 0; i < ROCK_SPRITES; i++) ROCK[i] = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/debris/rock" + i + ".png"));
            for (int i = 0; i < METAL_SPRITES; i++) METAL[i] = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/debris/metal" + i + ".png"));
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    static BufferedImage randomImage(BufferedImage[] array) {
        return array[ran.nextInt(array.length)];
    }
    
}
