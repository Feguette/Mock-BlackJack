
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
    public static void display(ArrayList<Card> pack)
    {
        String[] suits = {"S", "C", "D", "H"};
        for (int i = 0; i < pack.size(); i++)
        {
            Card current = pack.get(i);
            System.out.print(current.getValue());
            System.out.print(suits[current.getSuit()]);
            if (pack.size() > i + 1)
                System.out.print(", ");
        }
        System.out.println("");
    }
    
    public static boolean dealerWin(Player noob)
    {
        for (int i = 0; i < noob.getBulk().size(); i ++) //Stores objects of bulk storage onto Hand current. alternate does not work since index is needed for split
        {
            Hand current = noob.getPortion(i);
            if (current.getTotal() < 21)
                return false;
        }
        return true;
    }
    
    public static void main (String args[])
    {
        Deck heap = new Deck();
        Player noob = new Player(500, heap.draw(), heap.draw());
        Dealer pro = new Dealer(heap.draw(), heap.draw());
        
        boolean noobCont = true;
        boolean proCont = true;
        boolean opCont = true;
        display(heap.getDeck());
        display(noob.getPortion(0).getHand());
        int choice;
        Scanner in = new Scanner(System.in);
        gameLoop:while (opCont)
        {
            playerLoop:for (int i = 0; i < noob.getBulk().size(); i ++) //Stores objects of bulk storage onto Hand current. alternate does not work since index is needed for split
            {
                Hand current = noob.getPortion(i);
                handLoop:while (noobCont)
                {
                    boolean paired = current.getCard(0).getType() == current.getCard(1).getType() && current.getSize() == 2;
                    display(current.getHand());
                    if (current.getTotal() == 21)
                    {
                        break handLoop;
                    }
                    System.out.println("0. Ragequit");
                    System.out.println("1. Stay");
                    System.out.println("2. Hit");
                    System.out.println("3. Double Down");
                    
                    if (paired)
                        System.out.println("4. Split"); 
                    
                    System.out.print("Input option: ");
                    choice = in.nextInt();
                    if (choice == 0)
                    {
                        System.out.println("Get rekted, noob");
                        break gameLoop;
                    }
                    
                    if (choice == 4 && paired) // checks if option 4 is chosen and checks if 1 pair
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
                
                if (dealerWin(noob))
                {
                    System.out.println("Get rekted, noob");
                }
                else 
                {
                    while (pro.getPortion(0).getTotal())
                    {
                        
                    }
                }
            }
        }
    }
}   
    
