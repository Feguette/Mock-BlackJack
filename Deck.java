/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
public class Deck
{
    private ArrayList<Card> deck = new ArrayList();
    private String[] suits = {"S", "C", "D", "H"};
    private Random generator = new Random();
    private int totalCard = 52;
    private Card current;
    private BufferedImage image;
    public Deck()
    {
        String[] suit = {""};
        int value;
        for (int i = 0; i < 4; i++)
        {
            for (int j = 1; j <= 13; j++)
            {
                String type = "" + j;
                if (j == 1)
                    type = "A";
                if (j == 11)
                    type = "J";
                if (j == 12)
                    type = "Q";
                if (j == 13)
                    type = "K";
                    try{
                image = ImageIO.read(new File(type + "_" + suit[i] + ".jpg"));}
                catch (Exception e){
            }
                value = j;
                if (j == 1)
                    value = 11;
                if (j >= 11)
                    value = 10;
                deck.add(new Card(j, i, value, image));
            }
        }
    }
    
    public ArrayList<Card> getDeck()
    {
        return deck;
    }
    
    public Card draw()
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
