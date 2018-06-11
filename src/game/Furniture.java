package game;

import static game.Room.ROOM_WIDTH;
import static game.Room.ROOM_HEIGHT;

/**
 * Contains all furniture drawn in the room.
 * 
 */

public class Furniture {
    
    private final Sprite door;
    private final Sprite bed;
    private final Sprite toilet;
    private final Sprite lamp;
    private final Sprite carpet;
    private final Sprite window;
    private final Sprite desk;
    private final Sprite computer;
    
    
    public Furniture() {

        door = new Sprite("wallDoorway_NW.png");
        door.setX(ROOM_WIDTH - door.getWidth());
        door.setY(ROOM_HEIGHT - door.getHeight());
        
        bed = new Sprite("bedDouble_SE.png");
        bed.setX(0);
        bed.setY(0);
        
        lamp = new Sprite("lampRoundFloor_NE.png");
        lamp.setX(200);
        lamp.setY(0);
        
        toilet = new Sprite("toilet_SE.png");
        toilet.setX(20);
        toilet.setY(200);
        
        carpet = new Sprite("rugRound_SE.png");
        carpet.setX(100);
        carpet.setY(135);
       
        window = new Sprite("wallWindowSlide_NE.png");
        window.setX(0);
        window.setY(300);  
        
        desk = new Sprite("desk_SW.png");
        desk.setX(ROOM_WIDTH - desk.getWidth());
        desk.setY(135);
        
        computer = new Sprite("computerScreen_SW.png");
        computer.setX(ROOM_WIDTH - computer.getWidth() - 15);
        computer.setY(135);
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
    
    public Sprite getCarpet() {
        return carpet;
    }

    public Sprite getWindow() {
        return window;
    }

    public Sprite getDesk() {
        return desk;
    }

    public Sprite getComputer() {
        return computer;
    }
}
