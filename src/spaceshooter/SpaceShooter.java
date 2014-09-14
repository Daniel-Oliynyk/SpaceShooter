package spaceshooter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpaceShooter {
    
    static final int FPS = 60, WIDTH = 800, HEIGHT = 800;
    static final BufferedImage screen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    static Graphics2D painter = screen.createGraphics();
    static Random ran = new Random();
    
    static ArrayList<Projectile> bulletSprites = new ArrayList<>();
    static ArrayList<Debris> debrisSprites = new ArrayList<>();
    static ArrayList<Explosion> explosionSprites = new ArrayList<>();
    
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
        
        double timeStart = System.nanoTime();
        while (true) {
            if (System.nanoTime() - timeStart > 1000000000 / FPS) {
                timeStart = System.nanoTime();
                map.drawMap();
                
                for (Iterator<Debris> it = debrisSprites.iterator(); it.hasNext();) {
                    Debris debris = it.next();
                    debris.drawDebris();
                    if (debris.health < 1) it.remove();
                }
                for (Iterator<Projectile> it = bulletSprites.iterator(); it.hasNext();) {
                    Projectile bullet = it.next();
                    bullet.drawBullet();
                    if (bullet.remove) it.remove();
                }
                player.drawPlayer();
                for (Iterator<Explosion> it = explosionSprites.iterator(); it.hasNext();) {
                    Explosion explosion = it.next();
                    explosion.drawExplosion();
                    if (explosion.remove) it.remove();
                }
                
                frame.repaint();
            }
        }
    }
    
}
