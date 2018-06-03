package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Room extends JPanel {

    public static final int ROOM_WIDTH = 800;
    public static final int ROOM_HEIGHT = 600;
    
    private final Image backgroundImage;
    
    private final Timer timer;
    private final Student student;
    private final Furniture furniture;
    private ArrayList<Fly> flies;
    private ArrayList<Mushroom> mushrooms;
    
    private final int DELAY = 90;
    
    // initial positions of flies
    private final int[][] pos = {
        {2380, 429}, {2500, 359}, {1380, 389}, {1580, 109}, {580, 339}, {1080, 339},
        {1590, 259}, {1560, 450}, {1590, 350}, {1580, 209}, {960, 545}, {910, 120}
    };
    
    public Room() throws Exception {
        
        timer = new Timer(DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               tick();
            }
        });
        timer.start(); 
        
        // When this component has the keyboard focus, key
        // events will be handled by its key listener.
        setFocusable(true);

        // pochodzi z Component
        addKeyListener(new GameKeyAdapter());
                
        backgroundImage = new ImageIcon("floor2.jpg").getImage();
        
        setBackground(Color.darkGray);
        setPreferredSize(new Dimension(ROOM_WIDTH, ROOM_HEIGHT));
        
        // all the drawing from this component will be done in an offscreen painting buffer. 
        setDoubleBuffered(true);

        // loop background music
        SoundClip.loopBackgroundMusic();
        
        student = new Student(30, 480);
        furniture = new Furniture();
        initFlies();
        initMushrooms();                   
    }
     
    public void initFlies() {       
        flies = new ArrayList<>();

        for (int[] p : pos) {
            flies.add(new Fly(p[0], p[1]));
        }
    }
    
     public void initMushrooms() {       
        mushrooms = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            mushrooms.add(new Mushroom((int) (600 * Math.random() + 70), (int) ( 500 * Math.random() + 50)));
        }
    }
 
    //wywoływana automatycznie, gdy potrzeba narysować jakąś część aplikacji
    //nie należy wywoływać samodzielnie
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        // boolean Graphics.drawImage(Image img, int x, int y, ImageObserver observer) observer usually null
        g2d.drawImage(backgroundImage, 0, 0, null);
        
        // draw furniture
        g2d.drawImage(furniture.getDoor().getImage(), furniture.getDoor().getX(), furniture.getDoor().getY(), this);
        g2d.drawImage(furniture.getBed().getImage(), furniture.getBed().getX(), furniture.getBed().getY(), this);
        g2d.drawImage(furniture.getLamp().getImage(), furniture.getLamp().getX(), furniture.getLamp().getY(), this);
        g2d.drawImage(furniture.getToilet().getImage(), furniture.getToilet().getX(), furniture.getToilet().getY(), this);
        g2d.drawImage(furniture.getCarpet().getImage(), furniture.getCarpet().getX(), furniture.getCarpet().getY(), this);
        g2d.drawImage(furniture.getWindow().getImage(), furniture.getWindow().getX(), furniture.getWindow().getY(), this);   
        g2d.drawImage(furniture.getDesk().getImage(), furniture.getDesk().getX(), furniture.getDesk().getY(), this);   
        g2d.drawImage(furniture.getComputer().getImage(), furniture.getComputer().getX(), furniture.getComputer().getY(), this);   
       
        // draw stats
        Font font = new Font("Helvetica", Font.BOLD, 18);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Energy: " + student.getEnergyScore(), ROOM_WIDTH-150, 40);
        g.drawString("Food: " + student.getFoodScore(), ROOM_WIDTH-150, 60);
        g.drawString("Toilet: " + student.getToiletScore(), ROOM_WIDTH-150, 80);
        g.drawString("Knowledge: " + student.getKnowledgeScore(), ROOM_WIDTH - 350, 40);
        g.drawString("Flies: " + flies.size(), ROOM_WIDTH - 350, 60);
    
        // draw the character
        g2d.drawImage(student.getImage(), student.getX(), student.getY(), this);
        
        // draw flies
        for (Fly fly : flies) {
            if (fly.isVisible()) {
                g.drawImage(fly.getImage(), fly.getX(), fly.getY(), this);
            }
        }
        
        // draw mushrooms
        for (Mushroom mushroom : mushrooms) {
            if (mushroom.isVisible()) {
                g.drawImage(mushroom.getImage(), mushroom.getX(), mushroom.getY(), this);
            }
        }     
    }
      
     public void tick() {        
        // gets called every DELAY ms
        student.move();
        student.updateStats();

        checkCollisions();
        updateFlies();
        updateMushrooms();
        
        // repaint() wywołoje metody paintComponent() wszystkich komponentóœ
        // Repaints this component. Inherited from Component.
        repaint();
    }
     
    private void updateFlies() {
         if (flies.size() == 0)
            initFlies();
        for (int i = 0; i < flies.size(); i++) {
            if (flies.get(i).isVisible()) {
                flies.get(i).move();
            } else {
                flies.remove(i);
            }
        }
    }
    
    private void updateMushrooms() {
        if (mushrooms.size() < 2)
            initMushrooms();
        for (int i = 0; i < mushrooms.size(); i++) {
            if (!mushrooms.get(i).isVisible()) {
                mushrooms.remove(i);
            }
        }
    }
    
    public void checkCollisions() {
        Rectangle r1 = student.getBounds();

        for (Fly fly : flies) {           
            Rectangle r2 = fly.getBounds();
            if (r1.intersects(r2)) {
                student.setCurrentImage(student.getImagePlayerHurt()); 
                SoundClip.play("Jab-SoundBible.com-1806727891.wav");
                student.updateEnergyScore(-20);
                fly.setVisible(false);
            }
        }
        
         for (Mushroom mushroom : mushrooms) {           
            Rectangle r3 = mushroom.getBounds();
            if (r1.intersects(r3) && mushroom.isVisible()) {
                student.setCurrentImage(student.getImagePlayerHappy());
                SoundClip.play("Evil_Laugh_1-Timothy-64737261.wav");
                student.updateFoodScore(20);
                mushroom.setVisible(false);
            }
        }        
    }
    
    // An abstract adapter class for receiving keyboard events. Implements KeyListener. 
    private class GameKeyAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            student.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            student.keyPressed(e);
        }
    }
}
