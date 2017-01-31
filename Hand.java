/**
 * Write a description of class Hands here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
public class Hand
{
    private String[] suits = {"SPADE", "CLUB", "DIAMOND", "HEART"};
    private ArrayList<Card> hand;
    public Hand(Card first, Card second)
    {
        hand = new ArrayList<Card>();
        hand.add(first);
        hand.add(second);
    }
    
    public Card replaceCard()
    {
        Card current = hand.get(0);
        hand.remove(0);
        return current;
    }
    
    public boolean checkSplit()
    {
        return getCard(0).getType() == getCard(1).getType() && getSize() == 2;
    }
    
    public void addCard(Card target)
    {
        hand.add(target);
    }
    
    public Card getCard(int index)
    {
        return hand.get(index);
    }
    
    public ArrayList<Card> getHand()
    {
        return hand;
    }
    
    public int getTotal()
    {
        int total = 0;
        int count = 0;
        int current;
        for (int i = 0; i < getHand().size(); i++)
        {
            current = hand.get(i).getValue();
            if (current == 11)
            {
                current = 0;
                count++;
            }
            total = total + current;
        }
        
        for (int i = 0; i < count; i++)
        {
            if (total + 11 > 21)
            {
                total++;
            }
            else
            {
                total = total + 11;
            }
        }
        return total;
    }
    
    public int getCardCount()
    {
        return hand.size();
    }

    
    public int getSize()
    {
        return hand.size();
    }
}
