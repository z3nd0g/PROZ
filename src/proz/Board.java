package proz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author z3nd0g
 */
public class Board extends JPanel implements ActionListener { //Runnable

    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 600;
    
    private final Image backgroundImage;
    
    private final Timer timer;
    private final Player player;
    private final Furniture furniture;
    private final int DELAY = 50;
    



    public Board() {

        // pochodzi z Component
        addKeyListener(new GameKeyAdapter());
        
        //ludek nie chodzi bez tego
        setFocusable(true);
        
        
        ImageIcon ii = new ImageIcon("floor2.jpg");
        backgroundImage = ii.getImage();
        
        setBackground(Color.darkGray);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        
        // all the drawing from this component will be done in an offscreen painting buffer. 
        setDoubleBuffered(true);

        player = new Player();
        furniture = new Furniture();

        timer = new Timer(DELAY, this); // Timer(between-event delay, actionListener)
        // To perform a task repeatedly (animation)
        timer.start();

    }

    
    
    
    @Override
    //wywoływana automatycznie, gdy potrzeba narysować jakąś część aplikacji
    //nie należy wywoływać samodzielnie
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        // boolean Graphics.drawImage(Image img, int x, int y, ImageObserver observer) observer usually null
        g2d.drawImage(backgroundImage, 0, 0, null);

        g2d.drawImage(furniture.getDoor().getImage(), furniture.getDoor().getX(), furniture.getDoor().getY(), this);
        g2d.drawImage(furniture.getBed().getImage(), furniture.getBed().getX(), furniture.getBed().getY(), this);
        g2d.drawImage(furniture.getLamp().getImage(), furniture.getLamp().getX(), furniture.getLamp().getY(), this);
        g2d.drawImage(furniture.getToilet().getImage(), furniture.getToilet().getX(), furniture.getToilet().getY(), this);
  
        g2d.drawImage(furniture.getWindow().getImage(), furniture.getWindow().getX(), furniture.getWindow().getY(), this);
        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
        
    }

    @Override
    //The actionPerformed() method is called every DELAY ms.
    public void actionPerformed(ActionEvent e) {
        
        // gets called every DELAY ms
        player.move();

        // a small optimisation technique that repaints only the small area of the window that actually changed
        // Repaints the specified rectangle of this component. (x.y,width,height)
        // repaint() wywołoje metody paintComponent() wszystkich komponentóœ
        // Repaints this component. Inherited from Component.
        repaint(player.getX() - 4, player.getY() - 4,
                player.getWidth() + 10, player.getHeight() + 10);
    }

    //An abstract adapter class for receiving keyboard events. Implements KeyListener. 
    private class GameKeyAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }

}
