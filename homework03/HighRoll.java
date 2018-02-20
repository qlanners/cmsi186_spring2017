/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  Demonstrates the use of input from a command line for use with Yahtzee
 *  Author        :  Quinn Lanners
 *  Date          :  2018-02-20
 *  Description   :  HighRoll.java class that uses the Die.java and DiceSet.java classes to create a TUI
 *                   where the user can input the desired number of dice and sides per die and then execute
 *                   1 of 5 actions:
 *                   Roll all the dice, Roll an individual die, display total score, save high score, and
 *                   display high score
 *                   The program can be quit at any time by entering 'q' as input
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-14  B.J. Johnson  Initial writing and release
 *  @version 2.0.0  2018-02-20  Quinn Lanners Modified to create HighRoll.java class
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll{

   public static void main( String args[] ) {
      System.out.println( "\n   Welcome to the very exciting game of High Roll!\n" );
      System.out.println( "\n   Type q and press 'Enter' at any time to exit.\n" );
      DiceSet die_set = null;
      Integer number_dice = null;
      Integer high_score = null;
      BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
      while( true ) {
         System.out.print( ">>" );
         String inputLine = null;
         System.out.println( "\n   How many dice do you want?\n" );         
         try {
            inputLine = input.readLine();            
            if( 'q' == inputLine.charAt(0) ) {
               System.exit(0);
            }            
            if( 0 == inputLine.length() || Integer.parseInt(inputLine) < 0) {
               System.out.println("The number of dice must be >= 1");
            }
            if( 0 >= Integer.parseInt(inputLine)) {
               System.out.println("Must have at least one die");
            }
            else {
               number_dice = Integer.parseInt(inputLine);
               break;
            }
         }
         catch( IOException ioe ) {
            System.out.println( "Caught IOException" );
         }
      }
      while( true ) {
         System.out.print( ">>" );
         String inputLine = null;
         System.out.println( "\n   How many sides per dice?\n" );         
         try {
            inputLine = input.readLine();            
            if( 'q' == inputLine.charAt(0) ) {
               System.exit(0);
            }            
            if( 0 == inputLine.length() || Integer.parseInt(inputLine) < 4) {
               System.out.println("The number of sides per dice must be at least 4");
            }
            else {
               die_set = new DiceSet(number_dice,Integer.parseInt(inputLine));
               break;
            }
         }
         catch( IOException ioe ) {
            System.out.println( "Caught IOException" );
         }
      }
      while( true ) {
         System.out.print( "\n");
         String inputLine = null;
         System.out.println( "\n>>Pick one of the following actions by typing in the corresponding number and pressing enter!\n 1: Roll all the dice\n 2: Roll a single die\n 3: Calculate the score for this set\n 4: Save this score as a high score\n 5: Display the high score\n Once again type 'q' at any time to exit the program" );         
         System.out.print( ">>" );
         try {
            inputLine = input.readLine();
            if( 'q' == inputLine.charAt(0) ) {
               System.exit(0);
            }        
            int actions = Integer.parseInt(inputLine);        
            switch (actions) {
               case 1: die_set.roll(); break;
               case 2: System.out.println("Which die to you want to roll?");
                        inputLine = input.readLine();
                        if(Integer.parseInt(inputLine) >= 0 && Integer.parseInt(inputLine) < number_dice) {
                           die_set.rollIndividual(Integer.parseInt(inputLine));
                        } else {
                           System.out.println("Input of desired die must be in the range of the total number of dice! (Indexed starting at 0)");
                        } break;
               case 3: System.out.println("The current total score for this dice set is: "+die_set.sum()); break;
               case 4: high_score = die_set.sum(); break;
               case 5: System.out.println("The current high schore is: "+high_score); break;
               default: System.out.println("The entered number does not correspond to any of the listed actions. Please try again or type 'q' and press Enter to exit to program."); break;
            }
         }
         catch( IOException ioe ) {
            System.out.println( "Caught IOException" );
         }
      }            
   }
}
