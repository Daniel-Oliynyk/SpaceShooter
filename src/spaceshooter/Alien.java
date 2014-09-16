package spaceshooter;

import java.awt.geom.AffineTransform;
import static spaceshooter.SpaceShooter.*;

public class Alien extends Sprite {
    static final int SIZE = 80, RANGE = 400;
    double angle, direction, speed = 1;

    public Alien() {
        angle = Math.atan2(player.y - y, player.x - x);
        direction = Math.atan2(player.y - y, player.x - x);
    }
    
    @Override
    void update() {
        angle = Math.atan2(player.y - y, player.x - x);
        if (Math.sqrt((x - player.x) * (x - player.x) + (y - player.y) * (y - player.y)) > RANGE) {
            x += Math.cos(angle) * speed;
            y += Math.sin(angle) * speed;
        }
        
        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(angle, SIZE / 2, SIZE / 2);
        painter.drawImage(ImageManager.ENEMY, tran, null);
    }
    
}
