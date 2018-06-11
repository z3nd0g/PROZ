package game;

/**
 * Specifies what the mushrooms are supposed to look like.
 */
public class Mushroom extends Sprite {
    
    public Mushroom(int x, int y) {
        super(x, y);

        loadImage("mushroom_redGroup_SE.png");
        getImageDimensions();
    }
    
}
