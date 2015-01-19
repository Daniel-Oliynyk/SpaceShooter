package spaceshooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpaceShooter {
    
    static final int FPS = 60, WIDTH = 800, HEIGHT = 800;
    static final double MULTIPLIER = 60.0 / FPS;
    static final BufferedImage fixedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    static Graphics2D fixedGraphics = fixedImage.createGraphics();
    static final BufferedImage relativeImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    static Graphics2D painter = relativeImage.createGraphics();
    static Random ran = new Random();
    
    static List<Debris> debrisSprites = new ArrayList<>();
    static List<Projectile> bulletSprites = new ArrayList<>();
    static List<Explosion> explosionSprites = new ArrayList<>();
    static List<Enemy> enemySprites = new ArrayList<>();
    
    static List<Debris> debrisBuffer = new ArrayList<>();
    static List<Projectile> bulletBuffer = new ArrayList<>();
    static List<Explosion> explosionBuffer = new ArrayList<>();
    static List<Enemy> enemyBuffer = new ArrayList<>();
    
    static HashSet<Integer> keys = new HashSet<>();
    static int mouseButton, mouseX, mouseY, transposeX, transposeY;
    static boolean resetSprites;
    
    public static void main(String[] args) {
        System.err.println(MULTIPLIER);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Space Shooter Game");
        frame.addKeyListener(keyControl);
        frame.addWindowListener(windowControl);
        frame.setResizable(false);
        
        painter.setBackground(new Color(255, 255, 255, 0));
        Map.initialize();
        ImageManager.initialize();
        
        JPanel panel = new JPanel();
        panel.add(new JLabel(new ImageIcon(fixedImage)));
        panel.addMouseMotionListener(moveControl);
        panel.addMouseListener(clickControl);
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(WIDTH + 7, HEIGHT + 34);
        frame.setLocationRelativeTo(null);
        
        double timeStart = System.nanoTime();
        while (true) {
            if (System.nanoTime() - timeStart > 1000000000 / FPS) {
                fixedGraphics.clearRect(0, 0, WIDTH, HEIGHT);
                timeStart = System.nanoTime();
                Map.drawMap();
                
                debrisSprites = updateSprite(debrisSprites, debrisBuffer);
                debrisBuffer.clear();
                
                bulletSprites = updateSprite(bulletSprites, bulletBuffer);
                bulletBuffer.clear();
                
                Player.drawPlayer();
                
                explosionSprites = updateSprite(explosionSprites, explosionBuffer);
                explosionBuffer.clear();
                
                enemySprites = updateSprite(enemySprites, enemyBuffer);
                enemyBuffer.clear();
                
                Gui.drawTextOverlay();
                
                if (resetSprites && debrisSprites.size() + bulletSprites.size() + explosionSprites.size() + enemySprites.size() == 0) resetSprites = false;
                
//                transposeX = (-1 * (int) Player.x) + (WIDTH / 2) - 25;
//                transposeY = (-1 * (int) Player.y) + (HEIGHT / 2) - 25;
                
                fixedGraphics.drawImage(relativeImage, transposeX, transposeY, null);
                Gui.drawGui();
                
                frame.repaint();
            }
        }
    }
    
    static List updateSprite(List list, List buffer) {
        List both = list;
        if (resetSprites) {
            both.clear();
            Gui.clearText();
        }
        else {
            for (Iterator<Sprite> it = both.iterator(); it.hasNext();) {
                Sprite sprite = it.next();
                sprite.update();
                if (sprite.remove) it.remove();
            }
            both.addAll(buffer);
        }
        return both;
    }
    
    static KeyAdapter keyControl = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent key) {
            keys.add(key.getKeyCode());
        }
        
        @Override
        public void keyReleased(KeyEvent key) {
            keys.remove(key.getKeyCode());
        }
    };
    
    static MouseMotionAdapter moveControl = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent me) {
            mouseX = me.getX() + (transposeX * -1);
            mouseY = me.getY() + (transposeY * -1);
        }

        @Override
        public void mouseMoved(MouseEvent me) {
            mouseX = me.getX() + (transposeX * -1);
            mouseY = me.getY() + (transposeY * -1);
        }
    };
    
    static MouseAdapter clickControl = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent me) {
            mouseButton = me.getButton();
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            mouseButton = MouseEvent.NOBUTTON;
        }
    };
    
    static WindowAdapter windowControl = new WindowAdapter() {
        @Override
        public void windowIconified(WindowEvent we) {
            keys = new HashSet<>();
            mouseButton = 0;
        }
        
        @Override
        public void windowLostFocus(WindowEvent we) {
            keys = new HashSet<>();
            mouseButton = 0;
        }
        
        @Override
        public void windowDeactivated(WindowEvent we) {
            keys = new HashSet<>();
            mouseButton = 0;
        }
    };
}
