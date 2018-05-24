package proz;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import static proz.Board.BOARD_HEIGHT;
import static proz.Board.BOARD_WIDTH;

public class Player {

    private int dx;
    private int dy;
    private int x = 30;
    private int y = 480;
    private final int width;
    private final int height;
    private final Image imageWalk1;
    private final Image imageWalk2;
    private Image imageCurrent;

    public Player() {

        ImageIcon ii = new ImageIcon("player_walk1.png");
        imageWalk1 = ii.getImage();
        
        ImageIcon jj = new ImageIcon("player_walk2.png");
        imageWalk2 = jj.getImage();
        
        imageCurrent = imageWalk1;

        width = imageWalk1.getWidth(null);
        height = imageWalk1.getHeight(null);
        
    }

    public void move() {
        x += dx;
        y += dy;
         
        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
        
        if (y > (BOARD_HEIGHT - height))
                y = BOARD_HEIGHT - height;
        
        if (x > (BOARD_WIDTH - width))
                x = BOARD_WIDTH - width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImage() {
        return imageCurrent;
    }
    
    private void animate() {
        if (imageCurrent == imageWalk1)
                    imageCurrent = imageWalk2;
                else imageCurrent = imageWalk1;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT:
                dx = -5;
                animate();
                break;
            case KeyEvent.VK_RIGHT:
                dx = 5;
                animate();
                break;
            case KeyEvent.VK_UP:
                dy = -5;
                animate();
                break;
            case KeyEvent.VK_DOWN:
                dy = 5;
                animate();
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT:
                dx = 0;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 0;
                break;
            case KeyEvent.VK_UP:
                dy = 0;
                break;
            case KeyEvent.VK_DOWN:
                dy = 0;
                break;
            default:
                break;
        }
    }
}
