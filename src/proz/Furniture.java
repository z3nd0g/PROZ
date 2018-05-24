package proz;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import static proz.Board.BOARD_HEIGHT;
import static proz.Board.BOARD_WIDTH;

public class Furniture{
    
    //private ArrayList<Sprite> furnitureItems;
    
    private final Sprite door;
    private final Sprite bed;
    private final Sprite toilet;
    private final Sprite lamp;
    private final Sprite window;
    

    
    public Furniture() {

        door = new Sprite("wallDoorway_NW.png");
        door.setX(BOARD_WIDTH - door.getWidth());
        door.setY(BOARD_HEIGHT - door.getHeight());
        
        bed = new Sprite("bedDouble_SE.png");
        bed.setX(0);
        bed.setY(0);
        
        lamp = new Sprite("lampRoundFloor_NE.png");
        lamp.setX(200);
        lamp.setY(0);
        
        toilet = new Sprite("toilet_SE.png");
        toilet.setX(20);
        toilet.setY(200);
       
        window = new Sprite("wallWindowSlide_NE.png");
        window.setX(0);
        window.setY(300);
       

        
    }

    public Sprite getDoor() {
        return door;
    }    

    public Sprite getBed() {
        return bed;
    }

    public Sprite getToilet() {
        return toilet;
    }

    public Sprite getLamp() {
        return lamp;
    }

    public Sprite getWindow() {
        return window;
    }
}
