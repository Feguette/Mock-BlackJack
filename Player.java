/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class Player
{
    private ArrayList<Hand> bulk;
    private String[] suits = {"SPADE", "CLUB", "DIAMOND", "HEART"};
    public Player(Card ran1, Card ran2)
    {
        bulk = new ArrayList<Hand>();
        bulk.add(new Hand(ran1, ran2));
    }
    
    public Hand getHand(int index)
    {
        return bulk.get(index);
    }
    
    public void draw(int index, Card target)
    {
        bulk.get(index).addCard(target);
    }
    
    public void split(int index, Card ran1, Card ran2) //conditions are checked outside of Player
    {
        Card current = getHand(index).replaceCard();
        draw(index, ran1);
        bulk.add(new Hand(current, ran2));
    }
    
    /*public void removeCard(int index)
    {
        hand.remove(
    }*/
}
