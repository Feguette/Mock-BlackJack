
/**
 * Write a description of class Test here.
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
public class Test extends JPanel
{
    private Player noob;
    private Deck heap;
    public Test()
    {
        heap = new Deck();
        noob = new Player(500, heap.draw(), heap.draw());
    }
    
    public static void main(String [] args)
    {
        Test thing = new Test();
        JFrame frame = new JFrame("Blackjack");
        frame.add(thing);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thing.repaint();
        display(thing.getNoob().getPortion(0));
        JButton clickEnd = new JButton("End me.");
        frame.add(clickEnd);
        class PleaseEnd implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("This process needs to break.");
            }
        }
        ActionListener pleaseEnd = new PleaseEnd();
        clickEnd.addActionListener(pleaseEnd);
        System.out.println("You did it!");
    }
    
    public Player getNoob()
    {
        return noob;
    }
    
    public static void display(Hand pack)
    {
        String[] suits = {"S", "C", "D", "H"};
        ArrayList<Card> pile = pack.getHand();
        for (int i = 0; i < pile.size(); i++)
        {
            Card current = pile.get(i);
            System.out.print(current.getType());
            System.out.print(suits[current.getSuit()]);
            if (pile.size() > i + 1)
                System.out.print(", ");
        }
        System.out.println(" = " + pack.getTotal());
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        //g2.drawRect(10, 10, 200, 200); 
        g2.drawImage(noob.getPortion(0).getCard(0).getImage(), 300, 300, null);
        g2.drawImage(noob.getPortion(0).getCard(1).getImage(), 325, 300 , null);
    }
}
