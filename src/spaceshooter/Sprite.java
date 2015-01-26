package spaceshooter;

/**
 * The parent class of all sprites that is not meant to be used independently.
 * This skeleton class only exists for organization so far.
 * @author Daniel Oliynyk
 */
public class Sprite {
    double x, y;
    boolean remove;
    
    void update() {
        System.err.println("No update method in sprite class");
    }
}
