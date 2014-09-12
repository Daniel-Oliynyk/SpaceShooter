package spaceshooter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageManager {
    static final int ASTEROID_COUNT = 3;
    static final int EXPLOSION_SPRITES = 6;
    static BufferedImage SHIP, MISSILE;
    static BufferedImage[] ASTEROID = new BufferedImage[ASTEROID_COUNT];
    static BufferedImage[] EXPLOSION = new BufferedImage[EXPLOSION_SPRITES];
            
    public ImageManager() {
        try {
            SHIP = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/ship.png"));
            MISSILE = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/missile.png"));
            
            for (int i = 0; i < ASTEROID_COUNT; i++) ASTEROID[i] = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/asteroids/asteroid" + i + ".png"));
            for (int i = 0; i < EXPLOSION_SPRITES; i++) EXPLOSION[i] = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/explosions/explosion" + i + ".png"));
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
}
