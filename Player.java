/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class Player
{
    private ArrayList<Card> hand;
    private String[] suits = {"S", "C", "D", "H"};
    public Player()
    {
        hand = new ArrayList<Card>();
    }
    
    public ArrayList<Card> getHand()
    {
        return hand;
    }
    
    public void addCard(Card c)
    {
        hand.add(c);
    }
    
    public Card getCard(int index)
    {
        return hand.get(index);
    }
    
    public void display(ArrayList<Card> deck)
    {
        for (int i = 0; i < deck.size(); i++)
        {
            Card current = getCard(i);
            System.out.print(current.getValue());
            System.out.print(suits[current.getSuit()]);
            System.out.println(", ");
        }
    }
}
