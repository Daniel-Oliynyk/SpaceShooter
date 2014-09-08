package spaceshooter;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpaceShooter {
    
    static BufferedImage screen = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
    static Player player = new Player();
    static int FPS = 60;
    static double timeStart = System.nanoTime();
    
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
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        while (true) {
            if (System.nanoTime() - timeStart > 1000000000 / FPS) {
                timeStart = System.nanoTime();
                player.drawPlayer();
                frame.repaint();
            }
        }
        
    }
    
}
