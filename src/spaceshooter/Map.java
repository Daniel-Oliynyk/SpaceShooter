package spaceshooter;

import java.awt.Color;
import java.awt.geom.Point2D;
import static spaceshooter.SpaceShooter.*;

public class Map {
    final int STAR_AMOUNT = 300, UPDATE_AMOUNT = 1, ASTEROIDS = 60;
    Point2D.Double[] star = new Point2D.Double[STAR_AMOUNT];

    public Map() {
        for (int i = 0; i < STAR_AMOUNT; i++) {
            star[i] = new Point2D.Double(ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
        }
    }
    
    void drawStars() {
        painter.setColor(new Color(0x0b1037));
        painter.fillRect(0, 0, screen.getWidth(), screen.getHeight());
        
        painter.setColor(Color.WHITE);
        Point2D.Double[] temp = new Point2D.Double[STAR_AMOUNT];
        
        for (int i = 0; i < UPDATE_AMOUNT; i++) {
            temp[i] = new Point2D.Double(ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
        }
        
        for (int i = 0; i < STAR_AMOUNT; i++) {
            if (i + UPDATE_AMOUNT < STAR_AMOUNT) temp[i + UPDATE_AMOUNT] = star[i];
            painter.fillOval((int) star[i].x, (int) star[i].y, 3, 3);
        }
        star = temp;
        
        int option = ran.nextInt(4);
        if (ran.nextInt(ASTEROIDS) == 3 && option == 0) asteroids.add(new Asteroid(-80, ran.nextInt(HEIGHT), Math.toRadians(ran.nextInt(180) + 270), ran.nextInt(5) + 2));
        else if (ran.nextInt(ASTEROIDS) == 3 && option == 1) asteroids.add(new Asteroid(ran.nextInt(WIDTH), -80, Math.toRadians(ran.nextInt(180) + 0), ran.nextInt(5) + 2));
        else if (ran.nextInt(ASTEROIDS) == 3 && option == 2) asteroids.add(new Asteroid(WIDTH, ran.nextInt(HEIGHT), Math.toRadians(ran.nextInt(180) + 90), ran.nextInt(5) + 2));
        else if (ran.nextInt(ASTEROIDS) == 3 && option == 3) asteroids.add(new Asteroid(ran.nextInt(WIDTH), HEIGHT, Math.toRadians(ran.nextInt(180) + 180), ran.nextInt(5) + 2));
    }
}
