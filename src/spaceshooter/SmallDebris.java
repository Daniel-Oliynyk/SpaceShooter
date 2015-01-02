package spaceshooter;

import static spaceshooter.SpaceShooter.*;

public class SmallDebris extends Debris {

    public SmallDebris(double x, double y, double angle, double speed, int type) {
        super(x, y, angle, speed, 20, type, type == ROCK_FRAGMENT? ImageManager.randomImage(ImageManager.ROCK) : ImageManager.randomImage(ImageManager.METAL));
        health = 1;
    }

    @Override
    void update() {
        super.update();
        angle = angle + ((2 * Math.PI) / ROTATION_SPEED);
        if (!remove && Player.x + Player.SIZE > x && Player.x < x + SIZE && Player.y + Player.SIZE > y && Player.y < y + SIZE) {
            explosionBuffer.add(new Explosion(x, y, SIZE));
            explosionBuffer.add(new Explosion(Player.x, Player.y, Player.SIZE));
            Player.takeDamage(20);
            remove = true;
        }
    }
    
}
