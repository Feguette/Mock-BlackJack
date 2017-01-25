/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class Player
{
    private ArrayList<Hand> hand;
    private String[] suits = {"S", "C", "D", "H"};
    public Player()
    {
        hand = new ArrayList<Hand>();
        
    }
    
    public boolean checkLoss(int handValue)
    {
        return (handValue > 21);
    }
    
    /*public void display(ArrayList<Card> decks)
    {
        for (int i = 0; i < decks.size(); i++)
        {
            Card current = decks.get(i);
            System.out.print(current.getValue());
            System.out.print(suits[current.getSuit()]);
            if (decks.size() > i + 1)
            System.out.print(", ");
        }
        System.out.println("");
    }*/
}
