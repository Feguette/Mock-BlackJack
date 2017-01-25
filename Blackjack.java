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
    private Player a = new Player();
    private Deck d = new Deck();    
    private int x = 0;
    private BufferedImage dotjpeg;
    private String[] suits = {"SPADE", "CLUB", "DIAMOND", "HEART"};
    public static void main(String []args)
    {
        Gameplay g = new Gameplay();
        JFrame window = new JFrame("Test");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton clickHit = new JButton("Hit");
        frame.add(clickHit);
        
        class HitListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                a.addCard(d.draw());
                for (int i = 0; i < a.getHand().size(); i++)
                {
                g2.drawImage(a.getHand.get(i).getImage());
                }
                repaint();
            }
        }
        
        ActionListener hitListener = new HitButtonListener();
        clickHit.addActionListener(hitListener);
        
        
        class StayListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                
            }
        }
        
        ActionListener stayListener = new StayButtonListener();
        clickHit.addActionListener(stayListener);
        
        
        class DoubleListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                
            }
        }
        
        ActionListener doubleListener = new DoubleButtonListener();
        clickHit.addActionListener(doubleListener);
        
        
        class SplitListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                
            }
        }
        
        ActionListener splitListener = new SplitButtonListener();
        clickHit.addActionListener(splitListener);
    }
    
    public void paintComponent(Graphics g2)
    {
        for (int i = 0; i < a.getHand.size(); i++)
        {
        x = x + 100;
        g2.drawImage(a.getHand().get(i).getImage(), x, 200, null);
        }
    }
    
    
}
