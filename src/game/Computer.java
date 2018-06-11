package game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * A frame for flash cards.
 */
public class Computer extends JFrame {

    private ArrayList<ImageIcon> questions;
    private ArrayList<ImageIcon> answers;
    
    /**
     * index for the current flash card
     */
    private int i;

    public Computer() {
        i = 0;

        setSize(500, 500);
        setBackground(Color.BLACK);
        setTitle("Computer");
        setLocation(850, 50);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        initComputer();
    }

    /**
     * Creates a card layout on the Computer frame, where flash cards - 
     * questions and answers are displayed.
     */
    
    private void initComputer() {
        questions = new ArrayList<>();
        answers = new ArrayList<>();
        questions.add(new ImageIcon("flash_card_1.png"));
        questions.add(new ImageIcon("flash_card_2.png"));
        answers.add(new ImageIcon("flash_card_1a.png"));

        final CardLayout cl = new CardLayout();
        final JPanel contentPanel = new JPanel(cl);
        add(contentPanel, BorderLayout.CENTER);

        JLabel questionCard = new JLabel(questions.get(i));
        contentPanel.add(questionCard, "Question");

        JLabel answerCard = new JLabel(answers.get(i));
        contentPanel.add(answerCard, "Answer");

        final JPanel controlPanel = new JPanel();
        add(controlPanel, BorderLayout.SOUTH);

        final JButton nextQuestion = new JButton("Next Question");
        nextQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(contentPanel, "Question");
            }
        });
        controlPanel.add(nextQuestion);

        final JButton answer = new JButton("Answer");
        answer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(contentPanel, "Answer");
            }
        });
        controlPanel.add(answer);

        pack();
    }
}
