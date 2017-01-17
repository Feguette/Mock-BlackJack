/**
 * Write a description of class Cards here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cards
{
    private int value;
    private int suit;
    public Cards(int val, int s)
    {
        value = val;
        suit = s;
    }
    
    public int getSuit()
    {
        return suit;
    }
    
    public int getValue()
    {
        return value;
    }
}
