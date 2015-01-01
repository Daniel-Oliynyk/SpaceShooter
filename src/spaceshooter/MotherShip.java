package spaceshooter;

import static spaceshooter.SpaceShooter.*;

public class MotherShip extends Enemy {
    static final int SET_SIZE = 160, MISSILE_AMOUNT = 16;
    int counter = ran.nextInt(100) + 100, missileCoolDown = 30;
    State state = State.Moving;
    
    public MotherShip(double x, double y) {
        super(SET_SIZE);
        this.x = x;
        this.y = y;
        speed = ran.nextInt(2) + 1;
        angle = Math.atan2((Player.y + (Player.SIZE / 2)) - (y + (SIZE / 2)), (Player.x + (Player.SIZE / 2)) - (x + (SIZE / 2)));
        health = 6;
    }
    
    @Override
    void update() {
        if (state == State.Moving) {
            angle = Math.atan2((Player.y + (Player.SIZE / 2)) - (y + (SIZE / 2)), (Player.x + (Player.SIZE / 2)) - (x + (SIZE / 2)));
            x += Math.cos(angle) * speed;
            y += Math.sin(angle) * speed;
            counter--;
            if (counter < 1) {
                state = State.Shooting;
                speed = ran.nextInt(2) + 1;
                counter = ran.nextInt(100) + 100;
            }
        }
        else if (state == State.Shooting) {
            angle = Math.atan2((Player.y + (Player.SIZE / 2)) - (y + (SIZE / 2)), (Player.x + (Player.SIZE / 2)) - (x + (SIZE / 2)));
            missileCoolDown--;
            if (missileCoolDown < 1) {
                int offset = (SIZE / 2) - (Projectile.SIZE / 2);
                double slice = Math.toRadians(360 / MISSILE_AMOUNT);
                for (int i = 0; i < MISSILE_AMOUNT; i++) bulletBuffer.add(new Projectile((int) x + offset, (int) y + offset, slice * i, Projectile.ENEMY_PLASMA));
                missileCoolDown = 30;
            }
            counter--;
            if (counter < 1) {
                state = State.Moving;
                counter = ran.nextInt(100) + 100;
            }
        }
        
        if (!remove && Player.x + Player.SIZE > x && Player.x < x + SIZE && Player.y + Player.SIZE > y && Player.y < y + SIZE) {
            explosionBuffer.add(new Explosion(x, y, SIZE));
            explosionBuffer.add(new Explosion(Player.x, Player.y, Player.SIZE));
            Player.takeDamage(50);
            remove = true;
        }
        
        if (x + SIZE < 0 || x > WIDTH || y + SIZE < 0 || y > HEIGHT) remove = true;
        
        painter.drawImage(ImageManager.MOTHERSHIP, (int) x, (int) y, null);
    }
    
    static enum State {
        Moving, Shooting;
    }
    
}
