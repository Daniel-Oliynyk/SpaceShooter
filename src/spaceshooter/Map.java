package spaceshooter;

import java.awt.Color;
import java.awt.geom.Point2D;
import static spaceshooter.SpaceShooter.*;

public class Map {
    static final int STAR_AMOUNT = 300, UPDATE_AMOUNT = 1, ASTEROID_CHANCE = (int) (75 / MULTIPLIER), DRONE_CHANCE = (int) (150 / MULTIPLIER);
    static final int ALIEN_CHANCE = (int) (250 / MULTIPLIER), MOTHERSHIP_CHANCE = (int) (500 / MULTIPLIER), MAX_ALIENS = 5, MAX_MOTHERSHIPS = 2;
    static Point2D.Double[] star = new Point2D.Double[STAR_AMOUNT];
    static int alienAmount, motherShipAmount;

    static void initialize() {
        for (int i = 0; i < STAR_AMOUNT; i++) {
            star[i] = new Point2D.Double(ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
        }
    }
    
    static void drawMap() {
        fixedGraphics.setColor(new Color(0x0b1037));
        fixedGraphics.fillRect(0, 0, WIDTH, HEIGHT);
        painter.clearRect(0, 0, WIDTH, HEIGHT);
        
        fixedGraphics.setColor(Color.WHITE);
        Point2D.Double[] temp = new Point2D.Double[STAR_AMOUNT];
        
        for (int i = 0; i < UPDATE_AMOUNT; i++) {
            temp[i] = new Point2D.Double(ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
        }
        for (int i = 0; i < STAR_AMOUNT; i++) {
            if (i + UPDATE_AMOUNT < STAR_AMOUNT) temp[i + UPDATE_AMOUNT] = star[i];
            int x = (int) star[i].x + transposeX;
            x = (transposeX > 0)? x % WIDTH : ((x < 0)? x + WIDTH : x);
            int y = (int) star[i].y + transposeY;
            y = (transposeY > 0)? y % HEIGHT : ((y < 0)? y + HEIGHT : y);
            fixedGraphics.fillOval(x, y, 3, 3);
        }
        star = temp;
        
        if (ran.nextInt(ASTEROID_CHANCE) == 1) {
            int an = ran.nextInt(180);
            double sp = ran.nextDouble() * 5;
            int in = Debris.SPAWN_DISTANCE * -1;
            int option = ran.nextInt(4);
            switch (option) {
                case 0: debrisBuffer.add(new Asteroid(in, ran.nextInt(HEIGHT), Math.toRadians(an + 270), sp)); break;
                case 1: debrisBuffer.add(new Asteroid(ran.nextInt(WIDTH), in, Math.toRadians(an + 0), sp)); break;
                case 2: debrisBuffer.add(new Asteroid(WIDTH, ran.nextInt(HEIGHT), Math.toRadians(an + 90), sp)); break;
                case 3: debrisBuffer.add(new Asteroid(ran.nextInt(WIDTH), HEIGHT, Math.toRadians(an + 180), sp)); break;
            }
        }
        
        if (ran.nextInt(DRONE_CHANCE) == 1) {
            int option = ran.nextInt(4);
            switch (option) {
                case 0: enemyBuffer.add(new Drone(Drone.SET_SIZE * -1, ran.nextInt(HEIGHT))); break;
                case 1: enemyBuffer.add(new Drone(ran.nextInt(WIDTH), Drone.SET_SIZE * -1)); break;
                case 2: enemyBuffer.add(new Drone(WIDTH, ran.nextInt(HEIGHT))); break;
                case 3: enemyBuffer.add(new Drone(ran.nextInt(WIDTH), HEIGHT)); break;
            }
        }
        else if (ran.nextInt(ALIEN_CHANCE) == 1 && enemySprites.size() < MAX_ALIENS) {
            alienAmount++;
            int option = ran.nextInt(4);
            switch (option) {
                case 0: enemyBuffer.add(new Alien(Alien.SET_SIZE * -1, ran.nextInt(HEIGHT))); break;
                case 1: enemyBuffer.add(new Alien(ran.nextInt(WIDTH), Alien.SET_SIZE * -1)); break;
                case 2: enemyBuffer.add(new Alien(WIDTH, ran.nextInt(HEIGHT))); break;
                case 3: enemyBuffer.add(new Alien(ran.nextInt(WIDTH), HEIGHT)); break;
            }
        }
        else if (ran.nextInt(MOTHERSHIP_CHANCE) == 1 && enemySprites.size() < MAX_MOTHERSHIPS) {
            motherShipAmount++;
            int option = ran.nextInt(4);
            switch (option) {
                case 0: enemyBuffer.add(new MotherShip(MotherShip.SET_SIZE * -1, ran.nextInt(HEIGHT))); break;
                case 1: enemyBuffer.add(new MotherShip(ran.nextInt(WIDTH), MotherShip.SET_SIZE * -1)); break;
                case 2: enemyBuffer.add(new MotherShip(WIDTH, ran.nextInt(HEIGHT))); break;
                case 3: enemyBuffer.add(new MotherShip(ran.nextInt(WIDTH), HEIGHT)); break;
            }
        }
    }
}
