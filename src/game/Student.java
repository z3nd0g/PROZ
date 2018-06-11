package game;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import static game.Room.ROOM_WIDTH;
import static game.Room.ROOM_HEIGHT;

/**
 * Class for the main character.
 * 
 */

public class Student extends Sprite {
    
    private int studentVelocity;
    
    private final Image imageWalk1;
    private final Image imageWalk2;
    private final Image imageWalk1Left;
    private final Image imageWalk2Left;
    private final Image imagePlayerInBed;
    private final Image imagePlayerBack;
    private final Image imagePlayerByComputer;
    private final Image imagePlayerHurt;
    private final Image imagePlayerHappy;

    private int energyScore;
    private int foodScore;
    private int toiletScore;
    private int knowledgeScore;
    
    private long clockForStats;
    private long clock;
    private long timeInBed;
    private long timeInToilet;
    private long timeByComputer;
    
    private boolean computerOn = false;
    private boolean toiletFlushed = false;
    private boolean gotSleep = false;
    private final Computer computer;
    
    
    public Student(int x, int y) {
        super(x, y);
        
        studentVelocity = 8;
        
        imageWalk1 = new ImageIcon("player_walk1.png").getImage();
        imageWalk2 = new ImageIcon("player_walk2.png").getImage();
        imageWalk1Left = new ImageIcon("player_walk1_left.png").getImage();
        imageWalk2Left = new ImageIcon("player_walk2_left.png").getImage();
        imagePlayerInBed = new ImageIcon("player_slide.png").getImage();
        imagePlayerBack = new ImageIcon("player_back.png").getImage();
        imagePlayerByComputer = new ImageIcon("player_talk.png").getImage();
        imagePlayerHurt = new ImageIcon("head_hurt.png").getImage();
        imagePlayerHappy = new ImageIcon("head_focus.png").getImage();
        
        currentImage = imageWalk1;
        
        getImageDimensions();
        
        energyScore = 100;
        foodScore = 100;
        toiletScore = 100;
        knowledgeScore = 0;
             
        clockForStats = System.nanoTime();
        clock = 0;
        timeInBed = 0;
        timeInToilet = 0;
        timeByComputer = 0;
        
        computer = new Computer();
    }

    public void updateStats() {
        if (energyScore < -30)
            energyScore = -30;
        
        if (foodScore < -30)
            foodScore = -30;
        else if (foodScore > 150)
            foodScore = 150;
        
        if (toiletScore < -30)
            toiletScore = -30;
        else if (toiletScore > 100)
            toiletScore = 100;         
        
        // if elapsed time > 5s
        if (((System.nanoTime() - clockForStats)/1000000000) > 5) {
            clockForStats = System.nanoTime();
            energyScore -= 2;
            foodScore -= 2;
            toiletScore--;
        }        
    }
    
    /**
     * Determines the movement and behaviour of the main character.
     * Determines how the character's image, stats and speed change
     * depending on his position in the room
     */
    public void move() {
        
        // Print current position
        System.out.println(x + ", " + y);
        
        if (energyScore < 0 || foodScore < 0 || toiletScore < 0)
            studentVelocity = 3;
        else if (energyScore >= 100 && foodScore >= 100)
            studentVelocity = 8;
        
        x += velocityX;
        y += velocityY;
        
        if (velocityX != 0 || velocityY != 0) {
        
            if (direction == Direction.LEFT) {
                if (currentImage != imageWalk1Left)
                    currentImage = imageWalk1Left;
                else currentImage = imageWalk2Left;
            }
            else {
                if (currentImage == imageWalk1)
                    currentImage = imageWalk2;
                else currentImage = imageWalk1;
            }
        }
        
        // Board boundaries
        if (x < 1) 
            x = 1;

        if (y < 1)
            y = 1;
        
        if (y > (ROOM_HEIGHT - height))
                y = ROOM_HEIGHT - height;
        
        if (x > (ROOM_WIDTH - width)) {
                x = ROOM_WIDTH - width;
        }
        
        // Lying in bed
        if (x < 53 && y <5) {
            if (timeInBed == 0)
                clock = System.nanoTime();
            
            timeInBed = System.nanoTime() - clock;
            
            // after 1s in bed
            if (timeInBed> 1000000000) {
                energyScore += 10;
                timeInBed = 0;
            }
            currentImage = imagePlayerInBed;
            x = 50;
            y = 0; 
            if (!gotSleep){
                SoundClip.play("Silly_Snoring-Snore_Man-618028931.wav");
                gotSleep = true;
            }
        } else gotSleep = false;       
        
        // Toilet break
        if (y > 150 && y < 190 && x < 38) {
            if (timeInToilet == 0)
                clock = System.nanoTime();
            
            timeInToilet = System.nanoTime() - clock;
            
            if (timeInToilet> 1000000000) {
                toiletScore += 10;
                timeInToilet = 0;
            }
            currentImage = imagePlayerBack;
            x = 35;
            y = 170;
            if (!toiletFlushed){
                SoundClip.play("Flushing The Toilet-SoundBible.com-399247839.wav");
                toiletFlushed = true;
            }
        } else toiletFlushed = false;
        
        // By computer
        if (y > 70 && y < 120 && x > (ROOM_WIDTH - width - 33)) {
            
            if (timeByComputer == 0)
                clock = System.nanoTime();
            
            timeByComputer = System.nanoTime() - clock;
            
            if (timeByComputer> 2000000000) {
                knowledgeScore += 1;
                timeByComputer = 0;
            }
            
            currentImage = imagePlayerByComputer;
            x = ROOM_WIDTH - width - 30;
            y = 90;
            
            if (!computerOn) {
                SoundClip.play("light-switch-pull-chain-daniel_simon.wav");
                computerOn = true;
                computer.setVisible(true);
            }
        } else computerOn = false;       
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

         if (key == KeyEvent.VK_LEFT) {
            velocityX = -studentVelocity;
            direction = Direction.LEFT;
        }

        if (key == KeyEvent.VK_RIGHT) {
            velocityX = studentVelocity;
            direction = Direction.RIGHT;
        }

        if (key == KeyEvent.VK_UP) {
            velocityY = -studentVelocity;
        }

        if (key == KeyEvent.VK_DOWN) {
            velocityY = studentVelocity;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            velocityX = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            velocityX = 0;
        }

        if (key == KeyEvent.VK_UP) {
            velocityY = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            velocityY = 0;
        }
    }

    public int getEnergyScore() {
        return energyScore;
    }
    
    public void updateEnergyScore(int i) {
        energyScore += i;
    }
    
    public void updateFoodScore(int i) {
        foodScore += i;
    }

    public int getFoodScore() {
        return foodScore;
    }

    public int getToiletScore() {
        return toiletScore;
    }  

    public Image getImagePlayerHurt() {
        return imagePlayerHurt;
    }
    
    public Image getImagePlayerHappy() {
        return imagePlayerHappy;
    }

    public int getKnowledgeScore() {
        return knowledgeScore;
    }
}