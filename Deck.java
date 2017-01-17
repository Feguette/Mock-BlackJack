/**
 * Write a description of class Cards here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Random;
public class Deck
{
    private ArrayList<Cards> deck = new ArrayList();
    //private String[] suits = {"S", "C", "D", "H"};
    private Random generator = new Random();
    private int totalCards = 52;
    private Cards current;
    public Deck()
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 1; j <= 13; j++)
            {
                deck.add(new Cards(j, i));
            }
        }
    }
    
    public Cards draw()
    {
        if (deck.size() == 0)
        {
            return null;
        }
        int index = generator.nextInt(deck.size());
        current = deck.get(index);
        deck.remove(index);
        return current;
    }
}
