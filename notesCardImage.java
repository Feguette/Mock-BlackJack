
/**
 * Write a description of class notesCardImage here.
 * 
 * @author (Kent Ngo, Jonathan Nguyen) 
 * @version (2.1.17)
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.Arrays;
import java.util.Collections;

public class notesCardImage extends JPanel
{
    BufferedImage image;
    BufferedImage image2;
    BufferedImage image3;
    BufferedImage image4;
    BufferedImage image5;
    ArrayList<BufferedImage> hand;
    ArrayList<card> hand2;
    ArrayList<BufferedImage> dealer;
    PlayingDeck a;
    int player;
    int enemy;
    boolean cont;
    boolean cont2;
    boolean reveal;
    boolean stopitLuc;
    boolean instantWin;
    boolean clear;

    /**
     * Constructor
     */
    public notesCardImage()throws Exception
    {
        hand = new ArrayList<BufferedImage>();
        dealer = new ArrayList<BufferedImage>();
        a = new PlayingDeck();
        hand2 = new ArrayList<card>();
        card b = a.giveCard();
        card c = a.giveCard();
        card d = a.giveCard();
        card e = a.giveCard();
        clear = false;
        //       try
        //     {
        stopitLuc = false;
        image = ImageIO.read(new File(b.getName()));
        image2 = ImageIO.read(new File(c.getName()));
        hand.add(image);
        hand.add(image2);
        hand2.add(b);
        hand2.add(c);
        image3 = ImageIO.read(new File("BACK.jpg"));

        image4 = ImageIO.read(new File(e.getName()));
        image5 = ImageIO.read(new File(d.getName()));
        dealer.add(image3);
        dealer.add(image4);
        reveal = false;
        instantWin = false;
        player = 0;
        enemy = 0;
        int ace = b.getValue();
        int ace2 = c.getValue();
        cont = true;
        //This is for the ace, it can be 1 or 11 based on situations
        if(ace == 1 && ace2 ==1)
        {
        }
        else if(ace ==1 || ace2 ==1)
        {

            player = player + 10;
            cont = false;
        }
        //same as above, but for the dealer hand
        int ace3 = d.getValue();
        int ace4 = e.getValue();
        cont2 = true;
        if(ace3 == 1 && ace4 == 1)
        {
        }
        else if(ace3==1 || ace4 ==1)
        {
            enemy = enemy + 10;
            cont2 = false;

        }
        player = player + b.getValue() + c.getValue();
        enemy = enemy + d.getValue() + e.getValue();
        if(player ==21)
        {
            instantWin = true;
        }

        //   }
        //   catch (Exception e)
        //    {
        //      System.out.print("Funny.. haha. you a");
        //  }
    }

    /**
     * Draws the images
     * @param g, object for the graphics
     */
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        int height = 0;
        int width = 0;

        //draws player cards

        if(clear == true)
        {
            g2.setColor(Color.white);
            Rectangle a = new Rectangle(0,0,1000,1000);
            g2.draw(a);
            g2.fill(a);
            clear = false;
        }
        for(int i = 0; i < hand.size(); i++)
        {
            g2.drawImage(hand.get(i),width,0,null);
            width = width + 50;

        }

        //draws dealer cards
        for(int j = 0; j < dealer.size();j++)
        {
            g2.drawImage(dealer.get(j),height, 200, null);
            height = height + 50;

        }
        //winning text

        repaint();
        g2.setFont(new Font("TimesRoman", Font.PLAIN,24));
        g2.setColor(Color.blue);
        if(instantWin == true)
        {
            g2.drawString("Instant win",50,500);
        }
        else if(reveal == true && enemy <22 && enemy > player)
        {
            g2.drawString("Dealer win", 50,500);
        }
        else if (reveal == true &&  player > 21)
        {
            g2.drawString("Busted. Dealer win", 50,500);
        }
        else if (reveal == true && player == enemy)
        {
            g2.drawString("Tie", 50,500);
        }
        else if (reveal == true &&  player <22 && enemy < player)
        {
            g2.drawString("You win", 50,500);
        }
        else if (reveal == true &&  player <22 && enemy > 21)
        {
            g2.drawString("You win", 50,500);
        }

    }

    //    g2.drawString("You win", 300,300);

    /**
     * When the player chooses hit, a card is drawn and is added to the hand
     */
    public void drawCard() //throws Exception
    {
        if(stopitLuc == false)
        {
            if (player < 21)
            {
                try{
                    // System.out.println("You lose");
                    //  PlayingDeck a = new PlayingDeck();
                    card f = a.giveCard();
                    BufferedImage newCard = ImageIO.read(new File(f.getName()));
                    hand.add(newCard); //you need an image
                    int ifAce = f.getValue();
                    if(cont == false)
                    { player = player - 10;
                        cont = true;
                    }

                    player = player + f.getValue();
                    System.out.println(player);
                    repaint();
                    /*    ArrayList<card> kf = 
                    for(int i = 0; hand2.size;i++)
                    {
                    int g = hand2.get(i).getValue();
                    System.out.println(g);
                    }*/
                }
                catch (Exception e){}
                if(player >21)
                {reveal = true;
                    repaint();
                }
            }
        }
        else
        {
        }
    }

    /**
     * Runs the rest of the game when player presses stand. Dealer draws, win or
     * lose is displayed
     */
    public void game() throws Exception
    {
        while(enemy < 16)
        {
            card gambler = a.giveCard();
            BufferedImage dealerCard = ImageIO.read(new File(gambler.getName()));
            dealer.add(dealerCard);
            int ifAce2 = gambler.getValue();
            if(cont2 == false)
            {
                enemy = enemy -10;
                cont = true;
            }

            enemy += gambler.getValue();
            System.out.println(enemy);
        }
        stopitLuc = true;
        /*  for(int i = 0; i < dealer.size();i++)
        {
        int add = dealer.get(i).getValue();
        System.out.println(add);
        }*/

        /*    if(enemy <22 && enemy > player)
        {
        System.out.println("Dealer wins");
        }
        else if (player > 21)
        {
        System.out.println("Dealer wins");
        }
        else if (player == enemy)
        {
        System.out.println("Tie");
        }
        else if (player <22 && enemy < player)
        {
        System.out.println("Player wins");
        }
        else if (player <22 && enemy > 21)
        {
        System.out.println("Player wins");
        }*/
        dealer.set(0,image5);
        reveal = true;
    }

    public void reset() throws Exception
    {
        clear = true;
        hand = new ArrayList<BufferedImage>();
        dealer = new ArrayList<BufferedImage>();
        a = new PlayingDeck();
        hand2 = new ArrayList<card>();

        card b = a.giveCard();
        card c = a.giveCard();
        card d = a.giveCard();
        card e = a.giveCard();

        //       try
        //     {
        stopitLuc = false;
        image = ImageIO.read(new File(b.getName()));
        image2 = ImageIO.read(new File(c.getName()));
        hand.add(image);
        hand.add(image2);
        hand2.add(b);
        hand2.add(c);
        image3 = ImageIO.read(new File("BACK.jpg"));

        image4 = ImageIO.read(new File(e.getName()));
        image5 = ImageIO.read(new File(d.getName()));
        dealer.add(image3);
        dealer.add(image4);
        repaint();
        reveal = false;
        instantWin = false;
        player = 0;
        enemy = 0;
        int ace = b.getValue();
        int ace2 = c.getValue();
        cont = true;
        //This is for the ace, it can be 1 or 11 based on situations
        if(ace == 1 && ace2 ==1)
        {
        }
        else if(ace ==1 || ace2 ==1)
        {

            player = player + 10;
            cont = false;
        }
        //same as above, but for the dealer hand
        int ace3 = d.getValue();
        int ace4 = e.getValue();
        cont2 = true;
        if(ace3 == 1 && ace4 == 1)
        {
        }
        else if(ace3==1 || ace4 ==1)
        {
            enemy = enemy + 10;
            cont2 = false;

        }
        player = player + b.getValue() + c.getValue();
        enemy = enemy + d.getValue() + e.getValue();
        if(player ==21)
        {
            instantWin = true;
        }
        revalidate();

    }

    /**
     * Gets the player's hand value
     * @return returns the value of cards in the player's hand
     */
    public int getPlayer()
    {
        return player;
    }

    /**
     * Main method, sets up JFrame and JButtons
     */
    public static void main(String[] args) throws Exception
    {
        JFrame frame = new JFrame();
        frame.setSize(1000,1000);
        notesCardImage c = new notesCardImage();
        JButton hit = new JButton("HIT");
        c.add(hit);
        JButton stand = new JButton("STAND");
        JButton reset = new JButton("Reset");
        if(c.getPlayer()>21)
        {
            hit.addActionListener(e->hit.setVisible(false));
            stand.addActionListener(e -> stand.setVisible(false));

            //   stand.setActionCommand("disable");
        }
        c.add(stand);
        c.add(reset);

        class HitButtonListener implements ActionListener
        {
            public void actionPerformed (ActionEvent event)
            {
                try
                {
                    c.drawCard();

                }
                catch (Exception e)
                {
                    //c.game();
                }
            }
        }

        class StandButtonListener implements ActionListener
        {               
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    c.game();
                }
                catch (Exception e)
                {
                }
            }
        }

        class resetButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                try
                {

                    c.reset();

                }
                catch (Exception e)
                {
                }
            }
        }

        ActionListener hitListener = new HitButtonListener();
        hit.addActionListener(hitListener);
        ActionListener standListener = new StandButtonListener();
        stand.addActionListener(standListener);
        ActionListener resetListener = new resetButtonListener();
        reset.addActionListener(resetListener);
        frame.add(c);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
/*class Card
{
private String namel
private BufferedImage image;

public Card(String newName)
{
name = newName;
try
{
image = ImageIO
}
}
}*/
