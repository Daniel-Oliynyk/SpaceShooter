package spaceshooter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageManager {
    static BufferedImage SHIP, MISSILE, ASTEROID;
            
    public ImageManager() {
        try {
            SHIP = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/ship.png"));
            MISSILE = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/missile.png"));
            ASTEROID = ImageIO.read(SpaceShooter.class.getResourceAsStream("img/asteroid.png"));
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
}
