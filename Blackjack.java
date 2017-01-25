/**
 * Write a description of class Blackjack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.Scanner;
public class Blackjack extends JPanel
{   
    private int x = 0;
    private static Player a = new Player();
    private static Deck d = new Deck();
    public static void main(String []args)
    {
        Gameplay g = new Gameplay();
        JFrame frame = new JFrame("Test");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton clickHit = new JButton("Hit");
        //Player a = new Player();
        //Deck d = new Deck();
        Blackjack bj = new Blackjack();
        frame.add(clickHit);
        
        class HitListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                a.addCard(d.draw());
                bj.repaint();
            }
        }
        
        ActionListener hitListener = new HitListener();
        clickHit.addActionListener(hitListener);
        
        
        class StayListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                
            }
        }
        
        ActionListener stayListener = new StayListener();
        clickHit.addActionListener(stayListener);
        
        
        class DoubleListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                a.addCard(d.draw());
                bj.repaint();
            }
        }
        
        ActionListener doubleListener = new DoubleListener();
        clickHit.addActionListener(doubleListener);
        
        
        class SplitListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                
            }
        }
        
        ActionListener splitListener = new SplitListener();
        clickHit.addActionListener(splitListener);
    }
    
    public void paintComponent(Graphics g2)
    {
        for (int i = 0; i < a.getHand().size(); i++)
        {
            x = x + 100;
            g2.drawImage(a.getHand().get(i).getImage(), x, 200, null);
        }
    }
    
    
}
