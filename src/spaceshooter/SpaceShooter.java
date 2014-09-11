package spaceshooter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpaceShooter {
    
    static final int FPS = 60, WIDTH = 800, HEIGHT = 800;
    static final BufferedImage screen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    static Graphics2D painter = screen.createGraphics();
    static double timeStart = System.nanoTime();
    static Random ran = new Random();
    
    static ArrayList<Projectile> bullets = new ArrayList<>();
    static ArrayList<Asteroid> asteroids = new ArrayList<>();
    
    static Player player = new Player();
    static Map map = new Map();
    static ImageManager images = new ImageManager();
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Space Shooter Game");
        frame.addKeyListener(Player.keyControl);
        frame.setResizable(false);
        
        JPanel panel = new JPanel();
        panel.add(new JLabel(new ImageIcon(screen)));
        panel.addMouseMotionListener(Player.moveControl);
        panel.addMouseListener(Player.clickControl);
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(WIDTH + 7, HEIGHT + 34);
        frame.setLocationRelativeTo(null);
        
        while (true) {
            if (System.nanoTime() - timeStart > 1000000000 / FPS) {
                timeStart = System.nanoTime();
                
                map.drawStars();
                
                ArrayList<Projectile> tempBul = new ArrayList<>();
                for (Projectile bullet : bullets) {
                    bullet.drawBullet();
                    if (!bullet.remove) tempBul.add(bullet);
                }
                bullets = tempBul;
                
                ArrayList<Asteroid> tempAst = new ArrayList<>();
                for (Asteroid asteroid : asteroids) {
                    asteroid.drawAsteroid();
                    if (!asteroid.remove) tempAst.add(asteroid);
                }
                asteroids = tempAst;
                
                player.drawPlayer();
                frame.repaint();
            }
        }
        
    }
    
}
