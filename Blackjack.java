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
   private int xComponent = 0;
   public static void main(String []args)
   {
        JFrame frame = new JFrame("Blackjack");
        JButton clickHit = new JButton("Hit");
        JButton clickStay = new JButton("Stay");
        JButton clickDouble = new JButton("Double");
        JButton clickSplit = new JButton("Split");
        Container contain = frame.getContentPane();
        contain.setLayout(new FlowLayout());
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clickHit.setPreferredSize(new Dimension(100,100));
        clickStay.setPreferredSize(new Dimension(100,100));
        clickDouble.setPreferredSize(new Dimension(100,100));
        clickSplit.setPreferredSize(new Dimension(100,100));
        Deck heap = new Deck();
        Player noob = new Player(500, heap.draw(), heap.draw());
        Dealer pro = new Dealer(heap.draw(), heap.draw());
        Blackjack bj = new Blackjack();
        contain.add(clickHit);
        contain.add(clickStay);
        contain.add(clickDouble);
        for (int i = 0; i < noob.getBulk().size(); i ++)
        {
        if (noob.getPortion(i).checkSplit())
        {
        clickSplit.setBounds(300,0,100,100);
        frame.add(clickSplit);
        }
        frame.setVisible(true);
        
        
        class HitListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                
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
       //g2.drawImage((image), 0, 200, null);
   }
}
