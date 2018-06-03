package game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author z3nd0g
 */

public class Game implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Current Thread");
        final JFrame frame = new JFrame("Room");
        frame.setLocation(20, 20);
        
        // Card Layout for the Welcome Screen, Game, High Scores & Instructions
        final CardLayout cl = new CardLayout();
        final JPanel contentPanel = new JPanel(cl);
        frame.add(contentPanel, BorderLayout.CENTER);
        
        // Main playing area
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