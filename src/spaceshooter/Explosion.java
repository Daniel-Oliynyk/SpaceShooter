package spaceshooter;

import java.awt.Image;
import static spaceshooter.SpaceShooter.*;

public class Explosion extends Sprite {
    static final int NO_FRAGMENTS = 0, ROCK_FRAGMENTS = 1, METAL_FRAGMENTS = 2;
    final int LENGTH_MULTIPLIER, SCALE, TYPE, HEALTH_CHANCE;
    int countDown;

    public Explosion(int xp, int yp, int time, int size, int fragments, boolean dropHealth) {
        LENGTH_MULTIPLIER = time;
        SCALE = size;
        TYPE = fragments;
        x = xp;
        y = yp;
        countDown = ImageManager.EXPLOSION_SPRITES * LENGTH_MULTIPLIER;
        if (dropHealth) HEALTH_CHANCE = 5;
        else HEALTH_CHANCE = 0;
        
        if (TYPE != NO_FRAGMENTS) {
            int pieces = ran.nextInt(6) + 4;
            for (int i = 0; i < pieces; i++) {
                if (dropHealth && ran.nextInt(HEALTH_CHANCE) == 1) debrisBuffer.add(new Debris(x + (SCALE * 10), y + (SCALE * 10), 2 * Math.PI / pieces * i, ran.nextDouble() * 3, Debris.HEALTH_FRAGMENT));
                else debrisBuffer.add(new Debris(x + (SCALE * 10), y + (SCALE * 10), 2 * Math.PI / pieces * i, ran.nextDouble() * 3, TYPE));
            }
        }
    }
    
    @Override
    void update() {
        countDown--;
        if (countDown < 0) remove = true;
        
        int index = ImageManager.EXPLOSION_SPRITES - 1 - (int) (countDown / LENGTH_MULTIPLIER);
        if (SCALE == 1) painter.drawImage(ImageManager.EXPLOSION[index], (int) x, (int) y, null);
        else {
            Image scaledImage = ImageManager.EXPLOSION[index];
            scaledImage = scaledImage.getScaledInstance(20 * SCALE, 20 * SCALE, Image.SCALE_DEFAULT);
            painter.drawImage(scaledImage, (int) x, (int) y, null);
        }
    }
    
}
