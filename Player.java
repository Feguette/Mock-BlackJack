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
    private int wager;
    private String[] suits = {"SPADE", "CLUB", "DIAMOND", "HEART"};
    public Player(int starter, Card ran1, Card ran2)
    {
        wager = starter;
        bulk = new ArrayList<Hand>();
        bulk.add(new Hand(ran1, ran2));
    }
    
    public void bet(int chips)
    {
        if (wager - chips >= 0)
            wager -= chips;
    }
    
    public void earn(int pot)
    {
        wager += pot;
    }
    
    public ArrayList<Hand> getBulk()
    {
        return bulk;
    }
    
    public Hand getPortion(int index)
    {
        return bulk.get(index);
    }
    
    public void draw(int index, Card target)
    {
        bulk.get(index).addCard(target);
    }
    
    public void split(int index, Card ran1, Card ran2) //conditions are checked outside of Player
    {
        Card current = getPortion(index).replaceCard();
        draw(index, ran1);
        bulk.add(new Hand(current, ran2));
    }
    
    /*public void removeCard(int index)
    {
        hand.remove(
    }*/
}
