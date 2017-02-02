
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
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        //g2.drawRect(10, 10, 200, 200); 
        g2.drawImage(noob.getPortion(0).getCard(0).getImage(), 0, 0, null);
        g2.drawImage(noob.getPortion(0).getCard(1).getImage(), 25, 0 , null);
    }
}
