package spaceshooter;

import java.awt.Image;
import static spaceshooter.SpaceShooter.*;

public class Explosion {
    final int LENGTH_MULTIPLIER, SCALE;
    int countDown, x, y;
    boolean remove;

    public Explosion(int xp, int yp, int time, int size) {
        LENGTH_MULTIPLIER = time;
        SCALE = size;
        x = xp;
        y = yp;
        countDown = ImageManager.EXPLOSION_SPRITES * LENGTH_MULTIPLIER;
    }
    
    void drawExplosion() {
        countDown--;
        if (countDown < 0) remove = true;
        int index = ImageManager.EXPLOSION_SPRITES - 1 - (int) (countDown / LENGTH_MULTIPLIER);
        if (SCALE == 1) painter.drawImage(ImageManager.EXPLOSION[index], x, y, null);
        else {
            Image scaledImage = ImageManager.EXPLOSION[index];
            scaledImage = scaledImage.getScaledInstance(40 * SCALE, 40 * SCALE, Image.SCALE_DEFAULT);
            painter.drawImage(scaledImage, x, y, null);
        }
    }
    
}
