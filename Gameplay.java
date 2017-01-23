
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
public class Gameplay //extend JPanel
{
    public static void main (String args[])
    {
        Deck d = new Deck();
        Player a = new Player();
        a.addCard(d.draw());
        a.addCard(d.draw());
        a.display(d.getDeck());
        a.display(a.getHand());
        int choice;
        Scanner in = new Scanner(System.in);
        while (opCont)
        {
            playerLoop:while (p1)
            {
                System.out.println("1. Stay");
                System.out.println("2. Hit");
                System.out.println("3. Double Down");
                System.out.println("4. Split");
                choice = 0;
                if (choice != {1,2,3,4})
                    choice = in.nextInt();
                
                if (choice == 1)
                {
                    break
                }
            }
            
            dealerLoop:while (d1)
            {
                if (a.checkLoss())
                {
                    break;
                }
            }
        }
    }
}
