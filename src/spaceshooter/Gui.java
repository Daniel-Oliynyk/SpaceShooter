package spaceshooter;

import java.awt.Color;
import static spaceshooter.SpaceShooter.*;

public class Gui {
    
    static final int OUTLINE_WIDTH = 4;
    
    static void drawGui() {
        painter.setColor(Color.BLACK);
        painter.fillRect(10 - OUTLINE_WIDTH, 10 - OUTLINE_WIDTH, 100 + OUTLINE_WIDTH * 2, 10 + OUTLINE_WIDTH * 2);
        if (Player.health > 0) {
            painter.setColor(Color.CYAN);
            painter.fillRect(10, 10, Player.health, 10);
            painter.setColor(Color.BLUE);
            painter.fillRect(Player.health + 10, 10, 100 - Player.health, 10);
            if (Player.health < 100) {
                painter.setColor(Color.BLACK);
                painter.fillRect(Player.health + 10 - (OUTLINE_WIDTH / 2), 10, OUTLINE_WIDTH, 10);
            }
        }
        painter.setColor(Color.YELLOW);
        String score = Player.score + "";
        painter.drawString(score, WIDTH - (20 + (score.length() - 1) * 7), 20);
    }
    
}
