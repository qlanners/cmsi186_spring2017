/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  StringStuff.java
 *  Purpose       :  A file full of stuff to do with the Java String class
 *  Author        :  Quinn Lanners
 *  Date          :  2018-02-01
 *  Description   :  This file presents a bunch of String-style helper methods.  Although pretty much
 *                   any and every thing you'd want to do with Strings is already made for you in the
 *                   Jave String class, this exercise gives you a chance to do it yourself [DIY] for some
 *                   of it and get some experience with designing code that you can then check out using
 *                   the real Java String methods [if you want]
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-19  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-01-22  B.J. Johnson  Fill in methods to make the program actually work
 *  @version 2.0.0  2018-02-01  Quinn Lanners Filled in skeleton of methods to make them function properly
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuff {

   private static final String[] vowels = {"a","e","i","o","u","A","E","I","O","U"};
   private static final String alphabet = new String("abcdefghijklmnopqrstuvwxyz");

  /**
   * Method to determine if a string contains one of the vowels: A, E, I, O, U, and sometimes Y.
   * Both lower and upper case letters are handled.  In this case, the normal English rule for Y means
   * it gets included.
   *
   * @param s String containing the data to be checked for &quot;vowel-ness&quot;
   * @return  boolean which is true if there is a vowel, or false otherwise
   */
   public static boolean containsVowel( String s ) {
      boolean is_vowel = false;
      for (int i = 0; i < vowels.length; i++) {
         if (s.contains(vowels[i])) {
            is_vowel = true;
         }
      }
      return is_vowel;
   }

  /**
   * Method to determine if a string is a palindrome.  Does it the brute-force way, checking
   * the first and last, second and last-but-one, etc. against each other.  If something doesn't
   * match that way, returns false, otherwise returns true.
   *
   * @param s String containing the data to be checked for &quot;palindrome-ness&quot;
   * @return  boolean which is true if this a palindrome, or false otherwise
   */
   public static boolean isPalindrome( String s ) {
     StringBuilder reversed = new StringBuilder("");
     for (int i = (s.length()-1); i >= 0; i--) {
     	reversed.append(s.charAt(i));
     }
     if (s.contentEquals(reversed)) {
     	return true;
     } else {
     	return false;
     }
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet.  The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
   * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input
   */
   public static String evensOnly( String s ) {
      String s_lower = s.toLowerCase();
      StringBuilder even_word = new StringBuilder("");
      for (int i = 0; i<=(s_lower.length()-1); i++) {
        if ((alphabet.indexOf(s_lower.charAt(i))) >= 0) {
          if (((alphabet.indexOf(s_lower.charAt(i))+1) % 2 == 0)) {
      		  even_word.append(s.charAt(i));
          }
      	}
      }   
      return even_word.toString();
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet.  The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
   * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input
   */
   public static String oddsOnly( String s ) {
      String s_lower = s.toLowerCase();
      StringBuilder odd_word = new StringBuilder("");
      for (int i = 0; i<=(s_lower.length()-1); i++) {
      	if ((alphabet.indexOf(s_lower.charAt(i))) >= 0) {
          if (((alphabet.indexOf(s_lower.charAt(i))+1) % 2 != 0)) {
      		  odd_word.append(s.charAt(i));
          }
        }
      }
      return odd_word.toString();
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input without duplicates
   */
   public static String evensOnlyNoDupes( String s ) {
      String even_s = evensOnly(s);
      String evens_no_dupes_s = removeDupes(even_s);
      return evens_no_dupes_s;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input without duplicates
   */
   public static String oddsOnlyNoDupes( String s ) {
      String odd_s = oddsOnly(s);
      String odds_no_dupes_s = removeDupes(odd_s);
      return odds_no_dupes_s;
   }

  /**
   * Method to return the reverse of a string passed as an argument
   *
   * @param s String containing the data to be reversed
   * @return  String containing the reverse of the input string
   */
   public static String reverse( String s ) {
      StringBuilder reversed_s = new StringBuilder(s);
      return reversed_s.reverse().toString();
   }


   public static String removeDupes( String s ) {
      StringBuilder no_dupes_word = new StringBuilder("");
      for (int i = 0; i <= (s.length()-1); i++) {
      	String char_there = Character.toString(s.charAt(i));
      	if (no_dupes_word.toString().contains(char_there)) {
      		continue;
      	} else {
      		no_dupes_word.append(s.charAt(i));
      	}
      }
      return no_dupes_word.toString();
   }

  /**
   * Main method to test the methods in this class
   *
   * @param args String array containing command line parameters
   */
   public static void main( String args[] ) {
      String blah = new String( "Blah blah blah" );
      String woof = new String( "BCDBCDBCDBCDBCD" );
      String pal1 = new String( "a" );
      String pal2 = new String( "ab" );
      String pal3 = new String( "aba" );
      String pal4 = new String( "amanaplanacanalpanama" );
      String pal5 = new String( "abba" );
      System.out.println( containsVowel( blah ) );
      System.out.println( containsVowel( woof ) );
      System.out.println( isPalindrome( pal1 ) );
      System.out.println( isPalindrome( pal2 ) );
      System.out.println( isPalindrome( pal3 ) );
      System.out.println( isPalindrome( pal4 ) );
      System.out.println( isPalindrome( pal5 ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REHEARSALSZ" ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REhearSALsz" ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "WHATSup22Dude!!" ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "MiNNesoTa19191" ) );            
      System.out.println( "evensOnlyNoDupes() returns: " + evensOnlyNoDupes( "REhearSALsz" ) );
      System.out.println( "evensOnlyNoDupes() returns: " + evensOnlyNoDupes( "YYyd99KodEkkl)!!" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "xylophones" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "XYloPHonES" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "WHATSup22Dude!!" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "MiNNesoTa19191" ) );        
      System.out.println( "oddsOnlyNoDupes()  returns: " + oddsOnlyNoDupes( "XYloPHonES" ) );
      System.out.println( "oddsOnlyNoDupes()  returns: " + oddsOnlyNoDupes( "Hellodudesanddudettes9999" ) );      
      System.out.println( "reverse()          returns: " + reverse( "REHEARSALSZ" ) );
      System.out.println( "reverse()          returns: " + reverse( "234REHEARSALSZ??><" ) );      
   }
}
