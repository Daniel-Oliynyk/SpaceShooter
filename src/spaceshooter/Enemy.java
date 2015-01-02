package spaceshooter;

public class Enemy extends Sprite {
    static final int DRONE = 0, ALIEN = 1, MOTHERSHIP = 2;
    final int SIZE, TYPE;
    double angle, speed;
    int health;

    public Enemy(int size, int type) {
        this.SIZE = size;
        this.TYPE = type;
    }
    
    @Override
    void update() {
        if (remove) {
            if (TYPE == ALIEN) Map.alienAmount--;
            else if (TYPE == MOTHERSHIP) Map.motherShipAmount--;
        }
    }
    
}
