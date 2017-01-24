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
    
    public static void main(String []args)
    {
        JFrame window = new JFrame("Test");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton clickHit = new JButton("Hit");
        frame.add(clickHit);
        
        class HitListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                #player.addCard(#deck.draw());
                g2.drawImage(#player.getHand.get(#no).getImage())
            }
        }
        
        ActionListener hitListener = new HitButtonListener();
        clickHit.addActionListener(hitListener);
        
    }
    
    public paintComponent(Graphics g2)
    {
        
    }
    
    
}

