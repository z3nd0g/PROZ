package game;

/**
 * The look and movement of the fly sprite.
 */
public class Fly extends Sprite {

    /**
     * X-coordinate for a fly, after it reaches end of screen.
     */
    private final int INITIAL_X = 850;

    public Fly(int x, int y) {
        super(x, y);

        loadImage("fly_smaller.png");
        getImageDimensions();
    }

    public void move() {

        if (x < 0) {
            x = INITIAL_X;
        }

        x -= 3;
    }
}