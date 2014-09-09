package spaceshooter;

import java.awt.Color;
import java.awt.Graphics2D;
import static spaceshooter.SpaceShooter.screen;

public class Projectile {
    static int x, y;
    static double angle;
    static boolean isDead = false;

    public Projectile(int xp, int yp, double aimAngle) {
        x = xp;
        y = yp;
        angle = aimAngle;
    }
    
    void drawBullet() {
        x++;
        y++;
        if (x > 600) isDead = true;
        Graphics2D painter = screen.createGraphics();
        painter.setColor(Color.YELLOW);
        painter.fillOval(x - 5, y - 5, 10, 10);
    }
    
}
