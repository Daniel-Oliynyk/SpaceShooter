package spaceshooter;

import static spaceshooter.SpaceShooter.*;

public class Asteroid {
    double x, y;
    final double ANGLE;
    final int SPEED, TYPE;
    boolean remove = false;

    public Asteroid(int xp, int yp, double aimAngle, int speed) {
        x = xp;
        y = yp;
        ANGLE = aimAngle;
        SPEED = speed;
        TYPE = ran.nextInt(ImageManager.ASTEROID_COUNT);
    }
    
    void drawAsteroid() {
        x = x + Math.cos(ANGLE) * SPEED;
        y = y + Math.sin(ANGLE) * SPEED;
        if (x > HEIGHT || y > WIDTH || x < 0 - 80 || y < 0 - 80) remove = true;
        
        painter.drawImage(ImageManager.ASTEROID[TYPE], (int) x, (int) y, null);
    }
    
}
