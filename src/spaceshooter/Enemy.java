package spaceshooter;

public class Enemy extends Sprite {
    static final int ALIEN = 0, MOTHERSHIP = 1;
    final int SIZE, TYPE;
    double angle, speed;
    int health;

    public Enemy(int size, int type) {
        this.SIZE = size;
        this.TYPE = type;
    }
    
}
