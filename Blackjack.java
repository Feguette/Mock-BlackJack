
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
    public static void main (String args[])
    {
        Deck d = new Deck();
        Player a = new Player();
        
        boolean opCont = true;
        a.addCard(d.draw());
        a.addCard(d.draw());
        a.display(d.getDeck());
        a.display(a.getHand());
        int choice;
        Scanner in = new Scanner(System.in);
        gameLoop:while (opCont)
        {
            playerLoop:while (p1)
            {
                System.out.println("1. Stay");
                System.out.println("2. Hit");
                //System.out.println("3. Double Down");
                System.out.println("4. Split");
                choice = -1;
                choice = in.nextInt();
                if (choice == 0)
                {
                    break gameLoop;
                }
                
                if (choice == 1)
                {
                    break playerLoop;
                }
                
                if (choice == 2)
                {
                    a.addCard(d.draw);
                    p1 = !a.checkLoss();
                }
                
                if (choice == 3)
                {
                    a.addCard(d.draw);
                    break playerLoop;
                }
                
                if (choice == 4)
                {
                    
                }
            }
            
            dealerLoop:while (d1)
            {
                if ($player.checkLoss())
                {
                    System.out.println("You Lose");
                    break dealerLoop;
                }
                else
                {
                    while ($dealer.getTotal() < 17)
                    {
                        $dealer.addCard($deck.draw);
                    }
                    if ($dealer.getLoss() == true)
                    {
                        System.out.println("You win");
                        break dealer;
                    }
                    else
                    {
                        if ($dealer.getTotal() < $player.getTotal())
                        {
                            
                        }
                    }
                }
            }
        }
    }
}   
    
