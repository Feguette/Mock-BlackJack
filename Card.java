/**
 * Write a description of class Cards here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
public class Card
{
    private int value;
    private int type;
    private int suit;
    private BufferedImage image;
    public Card(int t, int s, int v, BufferedImage pict)
    {
        type = t;
        suit = s;
        value = v;
        image = pict;
    }
    
    public int getSuit()
    {
        return suit;
    }
    
    public int getValue()
    {
        return value;
    }
    
    public int getType()
    {
        return type;
    }
    
    public BufferedImage getImage()
    {
        return image;
    }
}
