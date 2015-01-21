package spaceshooter;

import java.awt.geom.AffineTransform;
import static spaceshooter.SpaceShooter.*;

public class Drone extends Enemy {
    static final int SET_SIZE = 40;
    
    public Drone(double x, double y) {
        super(SET_SIZE, Enemy.DRONE);
        this.x = x;
        this.y = y;
        speed = ran.nextInt(4) + 3;
        angle = Math.atan2((Player.y + (Player.SIZE / 2)) - (y + (SIZE / 2)), (Player.x + (Player.SIZE / 2)) - (x + (SIZE / 2)));
        health = 1;
    }
    
    @Override
    void update() {
        super.update();
        angle = Math.atan2((Player.y + (Player.SIZE / 2)) - (y + (SIZE / 2)), (Player.x + (Player.SIZE / 2)) - (x + (SIZE / 2)));
        x += Math.cos(angle) * speed;
        y += Math.sin(angle) * speed;
        
        if (!remove && Player.x + Player.SIZE > x && Player.x < x + SIZE && Player.y + Player.SIZE > y && Player.y < y + SIZE) {
            explosionBuffer.add(new Explosion(x, y, SIZE));
            explosionBuffer.add(new Explosion(Player.x, Player.y, Player.SIZE));
            Player.takeDamage(40);
            remove = true;
        }
        
        if (x + SIZE < 0 || x > WIDTH || y + SIZE < 0 || y > HEIGHT) remove = true;
        
        AffineTransform tran = new AffineTransform();
        tran.translate(x, y);
        tran.rotate(angle, SIZE / 2, SIZE / 2);
        painter.drawImage(ImageManager.DRONE, tran, null);
    }
    
}
