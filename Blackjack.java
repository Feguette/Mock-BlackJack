/**
 * Write a description of class Blackjack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Blackjack
{
    public static void main (String args[])
    {
        Deck d = new Deck();
        Player a = new Player();
        a.addCard(d.draw());
        a.addCard(d.draw());
        a.display(d.getDeck());
        a.display(a.getHand());
    }
}
