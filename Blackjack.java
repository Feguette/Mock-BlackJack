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
   private Deck heap = new Deck();
   private Player noob = new Player(500, heap.draw(), heap.draw());
   private Dealer pro = new Dealer(heap.draw(), heap.draw());
   private ArrayList<BufferedImage> album;
   private int yComponent = 100;
   private int choice;
   public static void main(String []args)
   {
        JFrame frame = new JFrame("Blackjack");
        JButton clickHit = new JButton("Hit");
        JButton clickStay = new JButton("Stay");
        JButton clickDouble = new JButton("Double");
        JButton clickSplit = new JButton("Split");
        JButton clickRQ = new JButton("Rage Quit");
        Container contain = frame.getContentPane();
        contain.setLayout(new FlowLayout());
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clickHit.setPreferredSize(new Dimension(100,100));
        clickStay.setPreferredSize(new Dimension(100,100));
        clickDouble.setPreferredSize(new Dimension(100,100));
        clickSplit.setPreferredSize(new Dimension(100,100));
        clickRQ.setPreferredSize(new Dimension(100,100));
        //Deck heap = new Deck();
        //Player noob = new Player(500, heap.draw(), heap.draw());
        //Dealer pro = new Dealer(heap.draw(), heap.draw());
        Blackjack bj = new Blackjack();
        contain.add(clickHit);
        contain.add(clickStay);
        contain.add(clickDouble);
        contain.add(clickRQ);
        gameLoop:for (int i = 0; i < bj.getPlayer().getBulk().size(); i ++)
        {
        if (bj.getPlayer().getPortion(i).checkSplit())
        {
        clickSplit.setBounds(300,0,100,100);
        contain.add(clickSplit);
        }
        frame.setVisible(true);
        
        Hand current = bj.getPlayer().getPortion(i);
        bj.repaint();
        
        class HitListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                current.addCard(bj.getDeck().draw());
                bj.repaint();
            }
        }
        
        ActionListener hitListener = new HitListener();
        clickHit.addActionListener(hitListener);
        
        
        class StayListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                
                bj.repaint();
            }
        }
        
        ActionListener stayListener = new StayListener();
        clickHit.addActionListener(stayListener);
        
        
        class DoubleListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                current.addCard(bj.getDeck().draw());
                bj.repaint();
            }
        }
        
        ActionListener doubleListener = new DoubleListener();
        clickHit.addActionListener(doubleListener);
        
        
        class SplitListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                bj.getPlayer().split(i, bj.getDeck().draw(), bj.getDeck().draw());
                bj.repaint();
            }
        }
        
        ActionListener splitListener = new SplitListener();
        clickHit.addActionListener(splitListener);
        
        class RQListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                break gameLoop;
            }
        }
        
        ActionListener rqListener = new RQListener();
        clickRQ.addActionListener(rqListener);
        
        dealerLoop:while (proCont)
            {
                Hand opFor = pro.getPortion(0);
                Hand current = noob.getPortion(0);
                    
                
                display(opFor); //
        if (!dealerAutoWin(noob))
                {
                    while (opFor.getTotal() < 17)
                    {
                        opFor.addCard(heap.draw());
                    }
        }
                
                display(opFor); //
 
                for (int i = 0; i < noob.getBulk().size(); i ++)
                {
                    
                    current = noob.getPortion(i);
                    comWin = compareWin(current, opFor);
                    display(current); //
                    display(pro.getPortion(0)); //
                }
                break dealerLoop;
            }
        for (Hand clearing: noob.getBulk())
        {
           heap.returnCards(clearing.getHand());
        }
        heap.returnCards(pro.getPortion(0).getHand());
        pro.clearBulk();
        noob.clearBulk();
        pro.addBulk(heap.draw(), heap.draw());
        noob.addBulk(heap.draw(), heap.draw());
        
       } 
    }

   public Player getPlayer()
   {
       return noob;
   }
   
   public Deck getDeck()
   {
       return heap;
   }
   
   public Dealer getDealer()
   {
       return pro;
   }
   
   public void defineImage()
   {
       Card substitute;
       for (int i = 0; i < noob.getBulk().size(); i++)
       {
           for (int j = 0;  j < noob.getBulk().get(i).getHand().size(); j++)
           {
               substitute = noob.getBulk().get(i).getHand().get(j);
               album.add(substitute.getImage());
           }
       }
   }
   
   public void paintComponent(Graphics g2)
   {
       defineImage();
       xComponent = xComponent + 100;
       for (int i = 0; i < album.size(); i++)
       {
           g2.drawImage(album.get(i), xComponent, yComponent, null);
       }
   }
}
