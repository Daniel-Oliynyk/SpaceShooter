package spaceshooter;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import static spaceshooter.SpaceShooter.*;

public class Gui {
    
    static final int OUTLINE_WIDTH = 4;
    static List<String> text = new ArrayList<>();
    static List<Point> locations = new ArrayList<>();
    static List<Color> colors = new ArrayList<>();
    static List<Integer> countDowns = new ArrayList<>();
    
    static void drawGui() {
        fixedGraphics.setColor(Color.BLACK);
        fixedGraphics.fillRect(10 - OUTLINE_WIDTH, 10 - OUTLINE_WIDTH, 100 + OUTLINE_WIDTH * 2, 10 + OUTLINE_WIDTH * 2);
        if (Player.health > 0) {
            fixedGraphics.setColor(Color.CYAN);
            fixedGraphics.fillRect(10, 10, Player.health, 10);
            fixedGraphics.setColor(Color.BLUE);
            fixedGraphics.fillRect(Player.health + 10, 10, 100 - Player.health, 10);
            if (Player.health < 100) {
                fixedGraphics.setColor(Color.BLACK);
                fixedGraphics.fillRect(Player.health + 10 - (OUTLINE_WIDTH / 2), 10, OUTLINE_WIDTH, 10);
            }
        }
        fixedGraphics.setColor(Color.YELLOW);
        String score = Player.score + "";
        fixedGraphics.drawString(score, WIDTH - (20 + (score.length() - 1) * 7), 20);
    }
    
    static void drawTextOverlay() {
        for (int i = 0; i < text.size(); i++) {
            countDowns.set(i, countDowns.get(i) - 1);
            if (countDowns.get(i) < 1) {
                text.remove(i);
                locations.remove(i);
                colors.remove(i);
                countDowns.remove(i);
            }
            else {
                painter.setColor(colors.get(i));
                painter.drawString(text.get(i), locations.get(i).x, locations.get(i).y);
            }
        }
    }
    
    static void addTextDisplay(double x, double y, String text, Color color) {
        Gui.text.add(text);
        Gui.locations.add(new Point((int) x, (int) y));
        Gui.colors.add(color);
        countDowns.add(40);
    }
    
    static void clearText() {
        text.clear();
        locations.clear();
        colors.clear();
        countDowns.clear();
     }
    
}
