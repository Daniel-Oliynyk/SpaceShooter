package spaceshooter;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import static spaceshooter.SpaceShooter.*;

public class Debris extends Sprite {
    static final int ASTEROID = 0, ROCK_FRAGMENT = 1, METAL_FRAGMENT = 2, HEALTH_FRAGMENT = 3, SPAWN_DISTANCE = 80;
    final int ROTATION_SPEED, SIZE, TYPE;
    final double SPEED, DIRECTION;
    final BufferedImage IMAGE;
    int health;
    double angle;

    public Debris(double x, double y, double angle, double speed, int size, int type, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.DIRECTION = angle;
        this.SPEED = speed;
        this.ROTATION_SPEED = ran.nextInt(180) + 360;
        this.SIZE = size;
        this.TYPE = type;
        this.IMAGE = image;
        this.angle = Math.toRadians(ran.nextInt(360));
    }
    
    @Override
    void update() {
        x += Math.cos(DIRECTION) * SPEED;
        y += Math.sin(DIRECTION) * SPEED;
        
        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(angle, SIZE / 2, SIZE / 2);
        painter.drawImage(IMAGE, tran, null);
        
        if (x > WIDTH || y > HEIGHT || x < 0 - SIZE || y < 0 - SIZE) remove = true;
    }
    
}
