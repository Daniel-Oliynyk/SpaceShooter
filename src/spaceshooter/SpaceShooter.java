package spaceshooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpaceShooter {
    
    static final int FPS = 60, WIDTH = 600, HEIGHT = 600;
    static BufferedImage screen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    static double timeStart = System.nanoTime();
    
    static Player player = new Player();
    static Map map = new Map();
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Space Shooter Game");
        frame.setResizable(false);
        frame.addKeyListener(Player.keyControl);
        
        JPanel panel = new JPanel();
        panel.add(new JLabel(new ImageIcon(screen)));
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(WIDTH + 7, HEIGHT + 34);
        frame.setLocationRelativeTo(null);
        
        while (true) {
            if (System.nanoTime() - timeStart > 1000000000 / FPS) {
                timeStart = System.nanoTime();
                
                Graphics2D painter = screen.createGraphics();
                painter.setColor(Color.BLACK);
                painter.fillRect(0, 0, screen.getWidth(), screen.getHeight());
                
                map.drawStars();
                player.drawPlayer();
                frame.repaint();
            }
        }
        
    }
    
}
