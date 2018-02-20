/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  Quinn Lanenrs
 *  Date          :  2018-02-20
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int count, int sides );          // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public int rollIndividual( int dieIndex );       // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int dieIndex );        // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds2 );       // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments. Body of code completed by Quinn Lanners in CMSI 186 Spring 2018
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
 *  @version 2.0.0  2018-02-20  Quinn Lanners Completed body of the class to create each argument and test program
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;
   private final int MINIMUM_SIDES = 4;   

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) {
      if ((count >= 1) && (sides >= MINIMUM_SIDES)) {
        this.count = count;
        this.ds = new Die[count];
        this.sides = sides;
        for (int i = 0; i < count; i++) {
          ds[i] = new Die(sides);
        }
      }
      else {
        throw new IllegalArgumentException();
      }
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
      int sum = 0;
      for (int i=0; i < ds.length; i++) {
        sum += ds[i].getValue();
      }
      return sum;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
    for (int i=0; i < ds.length; i++) {
      ds[i].roll();
    }
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) {
      if (dieIndex >= 0 && dieIndex < ds.length) {
        ds[dieIndex].roll();
        return ds[dieIndex].getValue();
      } else {
        throw new IllegalArgumentException();
      }
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int getIndividual( int dieIndex ) {
      if (dieIndex >= 0 && dieIndex < ds.length) {
        return ds[dieIndex].getValue();
      } else {
        throw new IllegalArgumentException();
      }
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
    public String toString() {
      StringBuilder output = new StringBuilder("");
      for (int i=0; i < ds.length; i++) {
        if (ds[i].getValue() == 0) {
          output.append("Die "+i+": {This die has not yet been rolled!}"+'\n');
        } else {
        output.append("Die "+i+": {"+Integer.toString(ds[i].getValue())+"} "+'\n');
        }
      } return output.toString();
    }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) {
      return ds.toString();
   }

  /**
   * @return  tru iff this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DiceSet ds2 ) {
      Boolean ident = true;
      if (ds.length == ds2.ds.length) {
        for (int i=0; i<ds.length; i++) {
          if (ds[i].getValue() == ds2.ds[i].getValue()) {
            continue;
          } else {
            ident = false;
            break;
          }
        }
      } else {
        ident = false;
      }
      return ident;
   }
  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      DiceSet test_die_set = null;
      DiceSet test_die_set2 = null;
      try {test_die_set = new DiceSet(2,5);}
      catch(IllegalArgumentException iae) {System.out.println("A die needs at leat 4 sides!");}
      try {test_die_set = new DiceSet(1,2);}
      catch(IllegalArgumentException iae) {System.out.println("A die needs at leat 4 sides!");}
      try {test_die_set = new DiceSet(4,15);}
      catch(IllegalArgumentException iae) {System.out.println("A die needs at leat 4 sides!");}
      try {test_die_set = new DiceSet(2,4);}
      catch(IllegalArgumentException iae) {System.out.println("A die needs at leat 4 sides!");}
      System.out.println(test_die_set.sum());
      test_die_set.roll();
      System.out.println(test_die_set.toString());
      try {test_die_set.rollIndividual(0);}
      catch(IllegalArgumentException iae) {System.out.println("Need to roll a die that is in the diceset! (Index starts at 0)");}
      System.out.println(test_die_set.toString());
      try {test_die_set.rollIndividual(2);}
      catch(IllegalArgumentException iae) {System.out.println("Need to roll a die that is in the diceset! (Index starts at 0)");}
      try {test_die_set.rollIndividual(1);}
      catch(IllegalArgumentException iae) {System.out.println("Need to roll a die that is in the diceset! (Index starts at 0)");}     
      System.out.println(test_die_set.toString());
      System.out.println(test_die_set.sum());
      try {test_die_set2 = new DiceSet(2,4);}
      catch(IllegalArgumentException iae) {System.out.println("A die needs at leat 4 sides!");}      
      test_die_set2.roll();
      System.out.println(test_die_set2.toString());
      System.out.println(test_die_set2.isIdentical(test_die_set));      
   }

}
