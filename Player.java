
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class Player
{
    private ArrayList<Cards> hand;
    public Player(Cards c)
    {
        hand.add(c);
    }
    
    public void addCard(Cards c)
    {
        hand.add(c);
    }
}
