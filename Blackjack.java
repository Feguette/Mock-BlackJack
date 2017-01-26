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
   private static Deck d = new Deck();
   private static Player a = new Player(d.draw(), d.draw());
   private static Hand thing1;
   public static void main(String []args)
   {
        //Gameplay g = new Gameplay();
        JFrame frame = new JFrame("Test");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton clickHit = new JButton("Hit");
        JButton clickStay = new JButton("Stay");
        JButton clickSplit = new JButton("Split");
        JButton clickDouble = new JButton("Double");
        //Player a = new Player();
        //Deck d = new Deck();
        Blackjack bj = new Blackjack();
        frame.add(clickHit);
        frame.add(clickStay);
        frame.add(clickDouble);
        clickHit.setLocation(100,0);
        clickDouble.setLocation(200,0);
        clickStay.setLocation(300,0);
        frame.setVisible(true);
        
        for (Hand thingy: a.getHand())
        {
            thing1 = thingy;
        class HitListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                thingy.addCard(d.draw());
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
                thingy.addCard(d.draw());
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
   }
    
   public void paintComponent(Graphics g2)
   {
       for (int i = 0; i < thing1.getCardCount(); i++)
       {
           x = x + 100;
           g2.drawImage(thing1.getCard(i).getImage(), x, 200, null);
       }
   }
}
