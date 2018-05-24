package proz;

import java.awt.EventQueue;
import javax.swing.JFrame;
/**
 *
 * @author z3nd0g
 */
public class Proz extends JFrame{
    
    public Proz() {

        add(new Board());

        setResizable(false);
        pack();
        
        setTitle("LIFE");    
        setLocationRelativeTo(null); //places the frame in the centre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }  

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame proz = new Proz();
            proz.setVisible(true);
        });
    }   
}
