package spaceshooter;

import static spaceshooter.SpaceShooter.painter;

public class Asteroid {
    double x, y;
    final double ANGLE;
    final int SPEED;
    boolean remove = false;

    public Asteroid(int xp, int yp, double aimAngle, int speed) {
        x = xp;
        y = yp;
        ANGLE = aimAngle;
        SPEED = speed;
    }
    
    void drawAsteroid() {
        x = x + Math.cos(ANGLE) * SPEED;
        y = y + Math.sin(ANGLE) * SPEED;
        if (x > SpaceShooter.HEIGHT || y > SpaceShooter.WIDTH || x < 0 || y < 0) remove = true;
        
//        AffineTransform tran = new AffineTransform();
//        tran.translate(x - 5, y - 5);
//        tran.rotate(ANGLE - Math.PI / 2, 5, 5);
        painter.drawImage(ImageManager.ASTEROID, (int) x, (int) y, null);
    }
    
}
