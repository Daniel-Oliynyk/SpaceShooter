package spaceshooter;

/**
 * The parent class of all enemies that is not meant to be used independently.
 * @author Daniel Oliynyk
 */
public class Enemy extends Sprite {
    static final int DRONE = 0, ALIEN = 1, MOTHERSHIP = 2;
    final int SIZE, TYPE;
    double angle, speed;
    int health;

    /**
     * Contains all the variables the subclasses can change.
     * @param size The size of the enemy used in collision.
     * @param type The type of enemy. Can be one of the static ones declared in this class.
     */
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
