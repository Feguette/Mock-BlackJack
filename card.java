
/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
public class card

{
    int NUMBERS;
    int SUITS;
    int generator;
    int numbers;
    int suits;
    int deck[];  
    String symbols;
    String newNumbers;
    int value;
    /**
     * Constructor
     */
  public card(int num,int suit)
  {
      numbers = num;
      suits = suit;
      //int deck[]  = new int [SUITS];
  }
  /**
   * Gives assigns the card suits to a number
   * @return, returns the suit name of the card
   */
  public String getSuits()
  {
      if(suits == 1)
      {
         symbols = "SPADE";
        }
      if(suits ==2)
      {
          symbols = "CLUB";
        }
      if(suits ==3)
      {
          symbols = "DIAMOND";
      }
      if(suits ==4)
      {
          symbols = "HEART";
        }
        return symbols;
    }
    
    /**
     * Gets the name of the card image file
     * @return returns file name of the image
     */
  public String getName()
    {
        if(numbers == 11)
        {
           newNumbers = "J_";
        }
        else if(numbers == 12)
        {
            newNumbers = "Q_";
        }
        else if(numbers==13)
        {
            newNumbers = "K_";
        }
        else if(numbers ==1)
        {
            newNumbers = "A_";
        }
        else
        {
            newNumbers = numbers + "_";
        }
        //Q_SPADE.jpg
     return newNumbers + getSuits()+ ".jpg";   
    }
     
    /**
     * Gets the value of the cards
     * @return value of card
     */
    public int getValue()
    {
        int i = numbers;
        if(i == 11)
        {
            i = 10;
        }
        if(i == 12)
        {
            i=10;
        }
        if(i == 13)
        {
            i=10;
        }
    /*    if(i== 1)
        {
            i =11;
        }
        */
        
        
        return i;
    }

}
