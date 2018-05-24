package proz;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author z3nd0g
 */
public class Item {

    private final Image image;
    private final int width;
    private final int height;
    private final int x;
    private final int y;

    Item() {
        ImageIcon ii = new ImageIcon("player_walk1.png");
        imageWalk1 = ii.getImage();

        width = imageWalk1.getWidth(null);
        height = imageWalk1.getHeight(null);
    }

    private void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

}
