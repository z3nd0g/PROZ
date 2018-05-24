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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author z3nd0g
 */
public class OldBoard extends JPanel implements Runnable, ActionListener {

    private Timer timer;
    private Player spaceShip;
    private final int DELAY = 10;

    private final int BOARD_WIDTH = 800;
    private final int BOARD_HEIGHT = 500;
    private final int INITIAL_X = -40;
    private final int INITIAL_Y = -40;
    //private final int DELAY = 25;

    private final Image playerImage;
    private Thread animator;
    private int x, y;

    public OldBoard() {

        addKeyListener(new GameKeyAdapter());
        setFocusable(true);
        setBackground(Color.blue);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setDoubleBuffered(true);

        spaceShip = new Player();

        timer = new Timer(DELAY, this); // Timer(between-event delay, actionListener)
        // To perform a task repeatedly (animation)
        timer.start();

        ImageIcon ii = new ImageIcon("player_walk2.png");
        playerImage = ii.getImage();

        x = INITIAL_X;
        y = INITIAL_Y;
    }

    @Override
    //wywoływana automatycznie, gdy potrzeba narysować jakąś część aplikacji
    //nie należy wywoływać samodzielnie
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        drawStar(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(spaceShip.getImage(), spaceShip.getX(),
                spaceShip.getY(), this);
    }

    @Override
    //The actionPerformed() method is called every DELAY ms.
    public void actionPerformed(ActionEvent e) {

        spaceShip.move();

        repaint(spaceShip.getX() - 1, spaceShip.getY() - 1,
                spaceShip.getWidth() + 2, spaceShip.getHeight() + 2);
    }

    private class GameKeyAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            spaceShip.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            spaceShip.keyPressed(e);
        }
    }

    //This method is called by the toolkit internally and should not be called directly by programs.
    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    /*
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawStar(g);
    }*/
    private void drawStar(Graphics g) {

        g.drawImage(playerImage, x, y, this);
        //Synchronizes this toolkit's graphics state. Some window systems may do buffering of graphics events.
        //This method ensures that the display is up-to-date. It is useful for animation.
        Toolkit.getDefaultToolkit().sync();
    }

    private void cycle() {

        x += 1;
        y += 1;

        if (y > BOARD_HEIGHT) {

            y = INITIAL_Y;
            x = INITIAL_X;
        }
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            cycle();
            //wywołoje metody paintComponent() wszystkich komponentóœ
            repaint(); // Repaints this component. Inherited from Component.

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {

                String msg = String.format("Thread interrupted: %s", e.getMessage());

                JOptionPane.showMessageDialog(this, msg, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }
    }
}
