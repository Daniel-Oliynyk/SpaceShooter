package spaceshooter;

import java.awt.Image;
import static spaceshooter.SpaceShooter.*;

public class Explosion extends Sprite {
    static final int NO_FRAGMENTS = 0, ROCK_FRAGMENTS = 1, METAL_FRAGMENTS = 2;
    final int LENGTH_MULTIPLIER, SCALE, TYPE, HEALTH_CHANCE;
    int countDown;
    
    public Explosion(double x, double y, int size) {
        this.x = x;
        this.y = y;
        this.LENGTH_MULTIPLIER = 2;
        this.SCALE = size / 20;
        this.TYPE = NO_FRAGMENTS;
        countDown = ImageManager.EXPLOSION_SPRITES * LENGTH_MULTIPLIER;
        HEALTH_CHANCE = 0;
    }
    
    public Explosion(double x, double y, int time, int size, int fragments, boolean dropHealth) {
        this.x = x;
        this.y = y;
        this.LENGTH_MULTIPLIER = time;
        this.SCALE = size / 20;
        this.TYPE = fragments;
        countDown = ImageManager.EXPLOSION_SPRITES * LENGTH_MULTIPLIER;
        if (dropHealth) HEALTH_CHANCE = 5;
        else HEALTH_CHANCE = 0;
        
        if (TYPE != NO_FRAGMENTS) {
            int pieces = ran.nextInt(6) + 4;
            for (int i = 0; i < pieces; i++) {
                if (dropHealth && ran.nextInt(HEALTH_CHANCE) == 1) debrisBuffer.add(new HealthDrop(x + (SCALE * 10), y + (SCALE * 10), 2 * Math.PI / pieces * i, ran.nextDouble() * 3));
                else debrisBuffer.add(new SmallDebris(x + (SCALE * 10), y + (SCALE * 10), 2 * Math.PI / pieces * i, ran.nextDouble() * 3, TYPE));
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
