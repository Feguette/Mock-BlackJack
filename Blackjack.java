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
    private int cardHeight = 200; //Initialize when card height is known
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
    gameLoop:while (opCont)
        {
            int comWin = 0;
            playerLoop:for (int i = 0; i < noob.getBulk().size(); i ++) //Stores objects of bulk storage onto Hand current. alternate does not work since index is needed for split
            {
                Hand current = noob.getPortion(i);
                handLoop:while (noobCont)
                {
                    int choice = -1;
                    if (current.getTotal() >= 21)
                    {
                        break handLoop;
                    }
                    if (current.checkSplit())
                    {
                        contain.add(clickSplit);
                    }
                    class HitListener implements ActionListener
                    {
                        public void actionPerformed(ActionEvent event)
                        {
                            choice = 2;
                        }
                    }        
                    ActionListener hitListener = new HitListener();
                    clickHit.addActionListener(hitListener);                
                    class StayListener implements ActionListener
                    {
                        public void actionPerformed(ActionEvent event)
                        {
                            choice = 1;
                        }
                    }        
                    ActionListener stayListener = new StayListener();
                    clickHit.addActionListener(stayListener);
                    class DoubleListener implements ActionListener
                    {
                        public void actionPerformed(ActionEvent event)
                        {
                            choice = 3;
                        }
                    }        
                    ActionListener doubleListener = new DoubleListener();
                    clickHit.addActionListener(doubleListener);        
                    class SplitListener implements ActionListener
                    {
                        public void actionPerformed(ActionEvent event)
                        {
                            choice = 4;
                        }
                    } 
                    ActionListener splitListener = new SplitListener();
                    clickHit.addActionListener(splitListener);
                    class RQListener implements ActionListener
                    {
                        public void actionPerformed(ActionEvent event)
                        {
                            choice = 0;
                        }
                    }
                    ActionListener rqListener = new RQListener();
                    clickRQ.addActionListener(rqListener);
                    if (choice == 0)
                    {
                        break gameLoop;
                    }
                    
                    if (choice == 4 && current.checkSplit()) // checks if option 4 is chosen and checks if 1 pair
                    {
                        noob.split(i, heap.draw(), heap.draw());
                    }
                    
                    if (choice == 1 || choice == 3)
                    {
                        noobCont = false;
                    }
                    
                    if (choice == 2 || choice == 3)
                    {
                        current.addCard(heap.draw());
                    }
                }
                noobCont = true;
            }
            
            dealerLoop:while (proCont)
            {
                Hand opFor = pro.getPortion(0);
                Hand current = noob.getPortion(0);
                if (!dealerAutoWin(noob))
                {
                    while (opFor.getTotal() < 17)
                    {
                        opFor.addCard(heap.draw());
                    }
                }
                
                for (int i = 0; i < noob.getBulk().size(); i ++) //Stores objects of bulk storage onto Hand current. alternate does not work since index is needed for split
                {
                    
                    current = noob.getPortion(i);
                    comWin = compareWin(current, opFor);
                    
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
    
    public static void displayHidden(Hand pack)
    {
        String[] suits = {"S", "C", "D", "H"};
        ArrayList<Card> pile = pack.getHand();
        for (int i = 0; i < pile.size(); i++)
        {
            Card current = pile.get(i);
            if (i > 0)
            {
                System.out.print(current.getType());
                System.out.print(suits[current.getSuit()]);
            }
            else
            {
                System.out.print("UU");
            }
            if (pile.size() > i + 1)
                System.out.print(", ");
        }
        System.out.println("");
    }
    
    public static boolean dealerAutoWin(Player noob)
    {
        for (int i = 0; i < noob.getBulk().size(); i ++) //Stores objects of bulk storage onto Hand current. alternate does not work since index is needed for split
        {
            Hand current = noob.getPortion(i);
            if (current.getTotal() <= 21)
                return false;
        }
        return true;
    }
    
    public static int compareWin(Hand noob, Hand pro)
    {
        int friendly = noob.getTotal();
        int opFor = pro.getTotal();
        if (friendly > 21)
        {
            return 2;
        }
        
        if (opFor > 21)
        {
            return 0;
        }
        
        if (opFor > friendly)
        {
            return 2;
        }
        
        if (friendly > opFor)
        {
            return 0;
        }
        
        if (opFor == 21 && friendly == 21)
        {
            if (noob.getSize() == 2 && pro.getSize() > 2)
            {
                return 0;
            }
        
            if (pro.getSize() == 2 && noob.getSize() > 2)
            {
                return 2;
            }
        }
        return 1;
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
        for (int i = 0; i < noob.getBulk().size(); i++)
        {
            for (int j = 0;  j < noob.getBulk().get(i).getHand().size(); j++)
            {
                xComponent = j*100;
                yComponent = i*cardHeight;
                g2.drawImage(substitute = noob.getBulk().get(i).getHand().get(j).getImage(), xComponent, yComponent, null);
            }
        }
    }
}
