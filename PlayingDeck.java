
/**
 * Write a description of class PlayingDeck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class PlayingDeck
{
    private ArrayList<card> Deck;
    /**
     * Sets up the ArrayList for the deck
     */
    public PlayingDeck()
    {
      Deck = new ArrayList<card>();
      for(int i = 1; i < 14; i++)
      {
          for (int j = 1; j < 5; j++)
          {
              card a = new card(i,j);
              Deck.add(a);
              
            }
        }   
    }   

    /**
     * Assigns 2 random cards to the players
     */
    public card giveCard()
    {
        Random generator = new Random();
        int i = Deck.size();
        int index = generator.nextInt(i);
        card a = Deck.get(index);
        Deck.remove(index);
        return a;
    }
    
   // public void aceMethod()
   // {//}
/* public static void main(String[] args)
    {
        Scanner user = new Scanner(System.in);
        PlayingDeck a = new PlayingDeck();
       // boolean playAgain = true;
        //String confirm = "y";
                String cont = "";
                boolean hitting = true;
                int choice = 0;
               
   
    //game starts here
    
    //while(playAgain == true)
    //{
    
    
    System.out.println("Player's cards");
        card b = a.giveCard();
        System.out.println(b.getName());
        int player = b.getValue();
        b = a.giveCard(); //recalls the method, changes the card
        System.out.println(b.getName());
        player += b.getValue();
        
        System.out.println("Dealer's cards");
         b = a.giveCard();
        System.out.println(b.getName());
        int dealer = b.getValue();
        b = a.giveCard(); //recalls the method, changes the card
        System.out.println(b.getName());
        dealer += b.getValue();
        
        while (hitting == true)
        {
        System.out.println("Press [1] to hit");
        System.out.println("Press [2] to stand");
        choice = user.nextInt();
        if (choice == 1)
        {
            b = a.giveCard();
            System.out.println(b.getName());
            player += b.getValue();
            
            if (player > 21)
            {
            hitting = false;
        }
           // player = player + a.getValue();
        }
        
        else
        {
            hitting = false;
            
            while (dealer < 16)
            {
              
                 b = a.giveCard();
            System.out.println(b.getName());
            dealer += b.getValue();
                //dealer = dealer + a.getValue();
            }
            
            
        }
        
    }
       
        if (dealer <22 && dealer > player)
        {
            cont = "Dealer wins";
        }
        if (player > 21)
        {
            cont = "Dealer wins";
        }
        if (player == dealer)
        {
            cont = "Tie";
        }
        if (player <22 && dealer < player)
        {
            cont = "Player wins";
        }
        if (player <22 && dealer > 21)
        {
            cont = "Player wins";
        }
        System.out.println(cont);
        
        //System.out.println("Play again? (y/n)");
        //confirm = user.nextLine();
       // if (confirm .equals ("n"))
       // playAgain = false;
    }
    }
//}*/
}
