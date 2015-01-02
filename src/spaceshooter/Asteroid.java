package spaceshooter;

import static spaceshooter.SpaceShooter.*;

public class Asteroid extends Debris {

    public Asteroid(double x, double y, double angle, double speed) {
        super(x, y, angle, speed, 80, ASTEROID, ImageManager.randomImage(ImageManager.ASTEROID));
        health = 3;
    }

    @Override
    void update() {
        super.update();
        angle = angle + ((2 * Math.PI) / ROTATION_SPEED);
        if (!remove && Player.x + Player.SIZE > x && Player.x < x + SIZE && Player.y + Player.SIZE > y && Player.y < y + SIZE) {
            explosionBuffer.add(new Explosion(x, y, SIZE));
            explosionBuffer.add(new Explosion(Player.x, Player.y, Player.SIZE));
            Player.takeDamage(50);
            remove = true;
        }
    }
    
}