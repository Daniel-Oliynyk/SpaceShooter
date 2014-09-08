package spaceshooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Random;
import static spaceshooter.SpaceShooter.screen;

public class Map {
    static final int STAR_AMOUNT = 300, UPDATE_AMOUNT = 2;
    static Random ran = new Random();
    static Point2D.Double[] star = new Point2D.Double[STAR_AMOUNT];

    public Map() {
        for (int i = 0; i < STAR_AMOUNT; i++) {
            star[i] = new Point2D.Double(ran.nextInt(SpaceShooter.WIDTH), ran.nextInt(SpaceShooter.HEIGHT));
        }
    }
    
    void drawStars() {
        Graphics2D painter = screen.createGraphics();
        painter.setColor(Color.WHITE);
        
        Point2D.Double[] temp = new Point2D.Double[STAR_AMOUNT];
        for (int i = 0; i < UPDATE_AMOUNT; i++) {
            temp[i] = new Point2D.Double(ran.nextInt(SpaceShooter.WIDTH), ran.nextInt(SpaceShooter.HEIGHT));
        }
        
        for (int i = 0; i < STAR_AMOUNT; i++) {
            if (i + UPDATE_AMOUNT < STAR_AMOUNT) temp[i + UPDATE_AMOUNT] = star[i];
            painter.fillOval((int) star[i].x, (int) star[i].y, 3, 3);
        }
        star = temp;
    }
}
