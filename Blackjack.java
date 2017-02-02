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
    private Deck heap;
    private Player noob;
    private Dealer pro;
    private ArrayList<BufferedImage> album;
    private int yComponent = 100;
    private static int choice;
    private int cardHeight = 200; //Initialize when card height is known
    private static boolean noobCont = true;
    private static boolean proCont = true;
    private BufferedImage image;
    private boolean check = true;
    
    public Blackjack()
    {
        heap = new Deck();
        noob = new Player(500, heap.draw(), heap.draw());
        pro = new Dealer(heap.draw(), heap.draw());
    }
    
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
        frame.add(bj);
        contain.add(clickHit);
        contain.add(clickStay);
        contain.add(clickDouble);
        contain.add(clickRQ);
        int comWin = 0;
        int currint = 0;
        Hand current = bj.getPlayer().getPortion(currint);
        if (current.checkSplit())
        {
            contain.add(clickSplit);
        }
        frame.setVisible(true);
        bj.repaint();
        
                    class HitListener implements ActionListener
                    {
                        public void actionPerformed(ActionEvent event)
                        {
                            choice = 2;
                            current.addCard(bj.getDeck().draw());
                            System.out.println("Hit");
                            bj.checkBust(current);
                        }
                    }        
                    ActionListener hitListener = new HitListener();
                    clickHit.addActionListener(hitListener);                
                    class StayListener implements ActionListener
                    {
                        public void actionPerformed(ActionEvent event)
                        {
                            choice = 1;
                            bj.turnEnded();
                            System.out.println("Stay");
                            bj.repaint();
                        }
                    }        
                    ActionListener stayListener = new StayListener();
                    clickStay.addActionListener(stayListener);
                    class DoubleListener implements ActionListener
                    {
                        public void actionPerformed(ActionEvent event)
                        {
                            choice = 3;
                            current.addCard(bj.getDeck().draw());
                            System.out.println("Double");
                            bj.checkBust(current);
                            bj.repaint();
                        }
                    }        
                    ActionListener doubleListener = new DoubleListener();
                    clickDouble.addActionListener(doubleListener);        
                    class SplitListener implements ActionListener
                    {
                        public void actionPerformed(ActionEvent event)
                        {
                            choice = 4;
                            bj.getPlayer().split(currint, bj.getDeck().draw(), bj.getDeck().draw());
                            System.out.println("Split");
                            bj.repaint();
                        }
                    } 
                    ActionListener splitListener = new SplitListener();
                    clickSplit.addActionListener(splitListener);
                    class RQListener implements ActionListener
                    {
                        public void actionPerformed(ActionEvent event)
                        {
                            choice = 0;
                            bj.redo();
                            System.out.println("RAGE");
                        }
                    }
                    ActionListener rqListener = new RQListener();
                    clickRQ.addActionListener(rqListener);

                    noobCont = true;
    }
                 
    public boolean checkBust(Hand current)
    {
        if (current.getTotal() >= 21)
        {
            return true;
        }
        else
            return false;
    }
        
    public void redo()
    {
        heap = new Deck();
        noob = new Player(500, heap.draw(), heap.draw());
        pro = new Dealer(heap.draw(), heap.draw());
    }

    
    public void turnEnded()
    {            
        int comWin = 0;
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
            noob.addBulk(heap.draw(), heap.draw());
            noob.addBulk(heap.draw(), heap.draw());
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
                for (int i = 0; i < noob.getBulk().size(); i++)
                {
                    for (int j = 0;  j < noob.getBulk().get(i).getHand().size(); j++)
                    {
                        xComponent = j*100;
                        yComponent = i*cardHeight;
                        g2.drawImage(noob.getBulk().get(i).getHand().get(j).getImage(), xComponent, yComponent, null);
                    }
                }
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
                                System.out.print("We have a problem");
                            }
                            g2.drawImage(image, xComponent, yComponent, null);
                        }
                        else
                        {
                            g2.drawImage(pro.getBulk().get(i).getHand().get(j).getImage(), xComponent, yComponent, null);
                        }
                    }
                }

            if (proCont == false)
            {
                g2.drawImage(pro.getBulk().get(0).getHand().get(0).getImage(), 0, cardHeight, null);
            }
        }
    }
