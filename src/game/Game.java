package game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * The main class of the package. Contains the event dispatching thread.
 * 
 * @author Bogdan Król
 * 
 */

public class Game implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Current Thread");
        final JFrame frame = new JFrame("Room");
        frame.setLocation(20, 20);
        
        /**
         * Card Layout for the main screen. High Scores and Instructions 
         * can be added later.
         */
        final CardLayout cl = new CardLayout();
        final JPanel contentPanel = new JPanel(cl);
        frame.add(contentPanel, BorderLayout.CENTER);
        
        /**
         * Main playing area.
         */
        final Room room;                
        try {
            room = new Room();
            contentPanel.add(room, "Room");
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": Current Thread");
        SwingUtilities.invokeLater(new Game());
    }
}