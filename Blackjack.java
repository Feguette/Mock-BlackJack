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
    private static int choice;
    private int cardHeight = 200; //Initialize when card height is known
    private static boolean noobCont = true;
    private static boolean proCont = true;
    private BufferedImage image;
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
        boolean opCont = true;
        Blackjack bj = new Blackjack();
        contain.add(clickHit);
        contain.add(clickStay);
        contain.add(clickDouble);
        frame.setVisible(true);
        gameLoop:while (opCont)
        {
            int comWin = 0;
            playerLoop:for (int i = 0; i < bj.getPlayer().getBulk().size(); i ++) //Stores objects of bulk storage onto Hand current. alternate does not work since index is needed for split
            {
                Hand current = bj.getPlayer().getPortion(i);
                handLoop:while (noobCont)
                {
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
                            bj.repaint();
                        }
                    }        
                    ActionListener hitListener = new HitListener();
                    clickHit.addActionListener(hitListener);                
                    class StayListener implements ActionListener
                    {
                        public void actionPerformed(ActionEvent event)
                        {
                            choice = 1;
                            bj.repaint();
                        }
                    }        
                    ActionListener stayListener = new StayListener();
                    clickHit.addActionListener(stayListener);
                    class DoubleListener implements ActionListener
                    {
                        public void actionPerformed(ActionEvent event)
                        {
                            choice = 3;
                            bj.repaint();
                        }
                    }        
                    ActionListener doubleListener = new DoubleListener();
                    clickHit.addActionListener(doubleListener);        
                    class SplitListener implements ActionListener
                    {
                        public void actionPerformed(ActionEvent event)
                        {
                            choice = 4;
                            bj.repaint();
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
                        bj.getPlayer().split(i, bj.getDeck().draw(), bj.getDeck().draw());
                    }
                    
                    if (choice == 1 || choice == 3)
                    {
                        noobCont = false;
                    }
                    
                    if (choice == 2 || choice == 3)
                    {
                        current.addCard(bj.getDeck().draw());
                    }
                }
                noobCont = true;
            }
            
            dealerLoop:while (proCont)
            {
                Hand opFor = bj.getDealer().getPortion(0);
                Hand current = bj.getPlayer().getPortion(0);
                if (!dealerAutoWin(bj.getPlayer()))
                {
                    while (opFor.getTotal() < 17)
                    {
                        opFor.addCard(bj.getDeck().draw());
                    }
                }
                
                for (int i = 0; i < bj.getPlayer().getBulk().size(); i ++) //Stores objects of bulk storage onto Hand current. alternate does not work since index is needed for split
                {
                    
                    current = bj.getPlayer().getPortion(i);
                    comWin = compareWin(current, opFor);
                    
                }
                break dealerLoop;
            }
            for (Hand clearing: bj.getPlayer().getBulk())
            {
                bj.getDeck().returnCards(clearing.getHand());
            }
            bj.getDeck().returnCards(bj.getDealer().getPortion(0).getHand());
            bj.getDealer().clearBulk();
            bj.getPlayer().clearBulk();
            bj.getDealer().addBulk(bj.getDeck().draw(), bj.getDeck().draw());
            bj.getPlayer().addBulk(bj.getDeck().draw(), bj.getDeck().draw());
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
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        if (check)
    {
        while (noobCont)
        {
            for (int i = 0; i < noob.getBulk().size(); i++)
            {
                for (int j = 0;  j < noob.getBulk().get(i).getHand().size(); j++)
                {
                    xComponent = j*100;
                    yComponent = i*cardHeight;
                    g2.drawImage(noob.getBulk().get(i).getHand().get(j).getImage(), xComponent, yComponent, null);
                }
            }
        }
        while (proCont)
        {
            for (int i = 0; i < pro.getBulk().size(); i++)
            {
                for (int j = 1;  j < pro.getBulk().get(i).getHand().size(); j++)
                {
                    xComponent = j*100;
                    yComponent = cardHeight;
                    if (j==1)
                    {
                        try {
                            String name = "BACK.jpg";
                            image = ImageIO.read(new File(name));
                        }
                        catch (Exception e)
                        {
                            
                        }
                        g2.drawImage(image, xComponent, yComponent, null);
                    }
                    else
                    {
                        g2.drawImage(pro.getBulk().get(i).getHand().get(j).getImage(), xComponent, yComponent, null);
                    }
                }
            }
        }
        while (proCont == false)
        {
            g2.drawImage(pro.getBulk().get(0).getHand().get(0).getImage(), 0, cardHeight, null);
        }
    }
    else
    {
        //g2.drawString("");
    }
    }
}
