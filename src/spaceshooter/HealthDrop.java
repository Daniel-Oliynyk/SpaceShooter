package spaceshooter;

import java.awt.Color;

public class HealthDrop extends Debris {

    public HealthDrop(double x, double y, double angle, double speed) {
        super(x, y, angle, speed, 20, HEALTH_FRAGMENT, ImageManager.HEALTH);
    }

    @Override
    void update() {
        super.update();
        boolean colliding = Player.x + Player.SIZE > x && Player.x < x + SIZE && Player.y + Player.SIZE > y && Player.y < y + SIZE;
        if (colliding && !remove && Player.health < 100) {
            Player.health = (Player.health >= 75)? 100 : Player.health + 20;
            Gui.addTextDisplay(x, y, "+20", Color.CYAN);
            remove = true;
        }
    }
    
}
