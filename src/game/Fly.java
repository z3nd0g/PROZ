package game;

public class Fly extends Sprite {

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