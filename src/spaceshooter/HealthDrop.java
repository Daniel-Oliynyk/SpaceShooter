package spaceshooter;

import java.awt.Color;

/**
 * The health pack subclass of debris is a small health pack that can heal the player. It is occasionally dropped in explosions.
 * @author Daniel Oliynyk
 */
public class HealthDrop extends Debris {

    /**
     * Creates the health pack on the specified x and y.
     * @param x The x coordinate where the health pack spawns.
     * @param y The y coordinate where the health pack spawns.
     * @param angle The angle the health pack is moving in.
     * @param speed The movement speed of the health pack.
     */
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
