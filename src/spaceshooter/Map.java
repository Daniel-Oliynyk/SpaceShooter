package spaceshooter;

import java.awt.Color;
import java.awt.geom.Point2D;
import static spaceshooter.SpaceShooter.*;

public class Map {
    static final int STAR_AMOUNT = 300, UPDATE_AMOUNT = 1, ASTEROID_CHANCE = 20, ALIEN_CHANCE = 250, ALIEN_AMOUNT = 5;
    static Point2D.Double[] star = new Point2D.Double[STAR_AMOUNT];

    static void initialize() {
        for (int i = 0; i < STAR_AMOUNT; i++) {
            star[i] = new Point2D.Double(ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
        }
    }
    
    static void drawMap() {
        painter.setColor(new Color(0x0b1037));
        painter.fillRect(0, 0, WIDTH, HEIGHT);
        
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
        
        if (ran.nextInt(ASTEROID_CHANCE) == 1) {
            int option = ran.nextInt(4);
            int an = ran.nextInt(180);
            double sp = ran.nextDouble() * 5;
            int in = Debris.INITIAL_SIZE * -1;
            switch (option) {
                case 0: debrisBuffer.add(new Debris(in, ran.nextInt(HEIGHT), Math.toRadians(an + 270), sp, Debris.NON_FRAGMENT)); break;
                case 1: debrisBuffer.add(new Debris(ran.nextInt(WIDTH), in, Math.toRadians(an + 0), sp, Debris.NON_FRAGMENT)); break;
                case 2: debrisBuffer.add(new Debris(WIDTH, ran.nextInt(HEIGHT), Math.toRadians(an + 90), sp, Debris.NON_FRAGMENT)); break;
                case 3: debrisBuffer.add(new Debris(ran.nextInt(WIDTH), HEIGHT, Math.toRadians(an + 180), sp, Debris.NON_FRAGMENT)); break;
            }
        }
        if (ran.nextInt(ALIEN_CHANCE) == 1 && alienSprites.size() < ALIEN_AMOUNT) {
            int option = ran.nextInt(4);
            switch (option) {
                case 0: alienBuffer.add(new Alien(Alien.SIZE * -1, ran.nextInt(HEIGHT))); break;
                case 1: alienBuffer.add(new Alien(ran.nextInt(WIDTH), Alien.SIZE * -1)); break;
                case 2: alienBuffer.add(new Alien(WIDTH, ran.nextInt(HEIGHT))); break;
                case 3: alienBuffer.add(new Alien(ran.nextInt(WIDTH), HEIGHT)); break;
            }
        }
    }
}
