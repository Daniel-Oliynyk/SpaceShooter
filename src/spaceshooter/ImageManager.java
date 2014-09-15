package spaceshooter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageManager {
    static final int ASTEROID_SPRITES = 3, EXPLOSION_SPRITES = 7, ROCK_SPRITES = 3, METAL_SPRITES = 3;
    static BufferedImage SHIP, MISSILE;
    static BufferedImage[] ASTEROID = new BufferedImage[ASTEROID_SPRITES];
    static BufferedImage[] EXPLOSION = new BufferedImage[EXPLOSION_SPRITES];
    static BufferedImage[] ROCK = new BufferedImage[ROCK_SPRITES];
    static BufferedImage[] METAL = new BufferedImage[METAL_SPRITES];
            
    public ImageManager() {
        try {
            SHIP = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/ship.png"));
            MISSILE = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/missile.png"));
            
            for (int i = 0; i < ASTEROID_SPRITES; i++) ASTEROID[i] = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/debris/asteroid" + i + ".png"));
            for (int i = 0; i < EXPLOSION_SPRITES; i++) EXPLOSION[i] = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/explosions/explosion" + i + ".png"));
            for (int i = 0; i < ROCK_SPRITES; i++) ROCK[i] = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/debris/rock" + i + ".png"));
            for (int i = 0; i < METAL_SPRITES; i++) METAL[i] = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/debris/metal" + i + ".png"));
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
}
