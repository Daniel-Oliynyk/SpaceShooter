package spaceshooter;

public class HealthDrop extends Debris {

    public HealthDrop(double x, double y, double angle, double speed) {
        super(x, y, angle, speed, 20, HEALTH_FRAGMENT, ImageManager.randomImage(ImageManager.HEALTH));
        health = 1;
    }

    @Override
    void update() {
        super.update();
        if (!remove && Player.x + Player.SIZE > x && Player.x < x + SIZE && Player.y + Player.SIZE > y && Player.y < y + SIZE) {
            Player.health = (Player.health >= 75)? 100 : Player.health + 20;
            remove = true;
        }
    }
    
}
