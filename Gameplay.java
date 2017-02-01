
/**
 * Write a description of class Gameplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.util.*;
public class Gameplay //extends JPanel
{
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
    
    
    public static void main (String args[])
    {
        //Gameplay bj = new Gameplay();
        Deck heap = new Deck();
        Player noob = new Player(500, heap.draw(), heap.draw());
        Dealer pro = new Dealer(heap.draw(), heap.draw());
        
        boolean noobCont = true;
        boolean proCont = true;
        boolean opCont = true;
        //display(heap.getDeck());
        //display(noob.getPortion(0).getHand());
        int choice;
        Scanner in = new Scanner(System.in);
        Scanner inquire = new Scanner(System.in);
        gameLoop:while (opCont)
        {
            int comWin = 0;
            playerLoop:for (int i = 0; i < noob.getBulk().size(); i ++) //Stores objects of bulk storage onto Hand current. alternate does not work since index is needed for split
            {
                Hand current = noob.getPortion(i);
                handLoop:while (noobCont)
                {
                    display(current);
                    displayHidden(pro.getPortion(0));
                    if (current.getTotal() >= 21)
                    {
                        break handLoop;
                    }
                    System.out.println("0. Ragequit");
                    System.out.println("1. Stay");
                    System.out.println("2. Hit");
                    System.out.println("3. Double Down");
                    
                    if (current.checkSplit())
                        System.out.println("4. Split"); 
                    
                    System.out.print("Input option: ");
                    choice = in.nextInt();
                    System.out.println("");
                    if (choice == 0)
                    {
                        System.out.println("Get rekted, noob");
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
                System.out.println("");
            }
            String[] victory = {"You won. ", "You tied. ", "You died. "};    
            dealerLoop:while (proCont)
            {
                Hand opFor = pro.getPortion(0);
                Hand current = noob.getPortion(0);
                
                System.out.print("Before: ");
                display(opFor);
                if (!dealerAutoWin(noob))
                {
                    while (opFor.getTotal() < 17)
                    {
                        opFor.addCard(heap.draw());
                    }
                }
                System.out.print("After: ");
                display(opFor);
                System.out.println("");
                for (int i = 0; i < noob.getBulk().size(); i ++) //Stores objects of bulk storage onto Hand current. alternate does not work since index is needed for split
                {
                    System.out.println("Compare (" + (i + 1) + "): ");
                    current = noob.getPortion(i);
                    comWin = compareWin(current, opFor);
                    display(current);
                    display(pro.getPortion(0));
                    System.out.println(victory[comWin]);
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
            System.out.println("0. Ragequit");
            System.out.println("Any. Continue to get rekted");
            System.out.print("Input option: ");
            
            if (inquire.nextLine().equals("0"))
                opCont = false;
            System.out.println("");
        }
    }
}   
    
