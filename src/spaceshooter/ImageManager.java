package spaceshooter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageManager {
    static final int ASTEROID_COUNT = 3;
    static BufferedImage SHIP, MISSILE;
    static BufferedImage[] ASTEROID = new BufferedImage[ASTEROID_COUNT];
            
    public ImageManager() {
        try {
            SHIP = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/ship.png"));
            MISSILE = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/missile.png"));
            
            for (int i = 0; i < ASTEROID_COUNT; i++) ASTEROID[i] = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/asteroid" + i + ".png"));
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
}
