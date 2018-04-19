/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  Quinn Lanners
 * Date       :  2018-04-18
 * Description:  A class that is able to handle operations with large numbers through storing their values in int[] format
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *  2.0.0  2018-04-18 Quinn Lanners Filled in the template script to perform functions. Implemented using int[]
 *                                  and added a few additional methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class BrobInt {

   public static final char[] accepted_digits = new char[] {'0','1','2','3','4','5','6','7','8','9'};
   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types (did not use in my code...)
  ///  these can help speed up the math if they fit into the proper memory space
   // public static final BrobInt MAX_INT  = new BrobInt( new Integer( Integer.MAX_VALUE ).toString() );
   // public static final BrobInt MIN_INT  = new BrobInt( new Integer( Integer.MIN_VALUE ).toString() );
   // public static final BrobInt MAX_LONG = new BrobInt( new Long( Long.MAX_VALUE ).toString() );
   // public static final BrobInt MIN_LONG = new BrobInt( new Long( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
   private static final int Chars_Space = 8;
   private String internalValue = "";        // internal String representation of this BrobInt
   private byte   sign          = 0;         // "0" is positive, "1" is negative
   private String reversed      = "";        // the backwards version of the internal String representation
   private int[] intVersion   = null;      // byte array for storing the string values; uses the reversed string
   private String abs_value = "";           //  internal String representation of this BrobInts abs value

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String value ) {
    if (value.charAt(0) == '0' && value.length() > 1) {
      value = value.substring(1);
    }    
    internalValue = value;
    StringBuilder abs_value_builder = new StringBuilder(value);
    if (abs_value_builder.charAt(0) == '-' || abs_value_builder.charAt(0) == '+') {
      abs_value_builder.deleteCharAt(0);
    }
    abs_value = abs_value_builder.toString();    
    try { 
      if (validateDigits() == true) {
        String validated = "yes";
      }
    } catch (IllegalArgumentException e) { System.out.println(e.toString());
      System.exit(0);
    }
    if (value.charAt(0) == '-') {
      sign = 1;
    }
    else {
      sign = 0;
    }
    StringBuilder rsb = new StringBuilder(value);
    if (value.charAt(0) == '-' || value.charAt(0) == '+') {
      rsb.deleteCharAt(0);
    }
    rsb = rsb.reverse();
    if (sign == 1) {
      rsb.insert(0,'-');
    }
    reversed = rsb.toString();
    int i = 0;
    int length_of_value = abs_value.length();
    int start_point = length_of_value - Chars_Space;
    int array_size = (int)(Math.ceil( length_of_value / Chars_Space) + 1);
    intVersion = new int[array_size];

    while (length_of_value >= 8) {
      intVersion[i] = Integer.parseInt( abs_value.substring( start_point, length_of_value ));
      start_point -= Chars_Space;
      length_of_value -= Chars_Space;
      i++;
    }

    if (length_of_value > 0) {
      intVersion[i] = Integer.parseInt( abs_value.substring(0, length_of_value));
    }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() {
      Boolean validated = false;
      if (internalValue.charAt(0) == '+' || internalValue.charAt(0) == '-' || Arrays.binarySearch(accepted_digits, internalValue.charAt(0)) >= 0) {
        validated = true;
      }
      else {
        throw new IllegalArgumentException( "\n         Sorry, that operation is not yet implemented." );
      }
      int i = 1;
      while (i<internalValue.length()) {
        if (Arrays.binarySearch(accepted_digits, internalValue.charAt(i)) >= 0) {
          validated = true;
        }
        else {
          throw new IllegalArgumentException( "\n         Sorry, that operation is not yet implemented." );
        }
        i++;
      }
      return validated;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
      return new BrobInt(reversed);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt gint ) {
      return new BrobInt(gint.reversed);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using byte array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt gint ) {
    BrobInt tester = new BrobInt(internalValue);
    int larger_abs_value = tester.compareTo(gint);
    int addition_case = 0;
    if ((sign == 1) && (gint.sign == 1)) {
      addition_case = 1;
    }
    else if ((sign == 0) && (gint.sign == 1)) {
      addition_case = 2;
      larger_abs_value = -1 * larger_abs_value;      
    }
    else if ((sign == 1) && (gint.sign == 0)) {
      addition_case = 3;
    }
    BrobInt summation_brob = null;
    switch (addition_case) {
      case 0: summation_brob = doublepositive( intVersion, gint.intVersion);
              break;
      case 1: summation_brob = doublenegative( intVersion, gint.intVersion);
              break;
      case 2: summation_brob = firstpositive( intVersion, gint.intVersion, larger_abs_value);
              break;
      case 3: summation_brob = firstpositive( gint.intVersion, intVersion, larger_abs_value);
              break;
    }
    return summation_brob;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of two positive values organized in int arrays. Used to add two positive numbers or subtract a negative number from a positive number.
   *  @param  first        First positive number passed as an int array
   *  @param  second       Second positive number passed as an int array
   *  @return BrobInt that is the sum of the positive values passed in as int arrays
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt doublepositive( int[] first, int[] second ) {
    int largest_array_length = 0; //denotes length of larger array, to be assigned later
    int smallest_array_length = 0; //denotes length of smaller array, to be assigned later
    int longer_array = 0; //0 if first array is longer, 1 if equal, 2 if second array is longer
    if (first.length > second.length) {
      largest_array_length = first.length;
      smallest_array_length = second.length;
    }
    else if (first.length == second.length) {
      largest_array_length = first.length;
      smallest_array_length = second.length;
      longer_array = 1;
    }      
    else {
      largest_array_length = second.length;
      smallest_array_length = first.length;
      longer_array = 2;
    }
    int[] sum = new int[largest_array_length]; //creates new int array that will hold the sum of these two passed int arrays
    int i = 0;
    int carry = 0; //used to keep track of carries
    int added_array_sums = 0;
    String added_array_sums_string = "";
    int shortened_array_sums = 0;
    while (i<smallest_array_length) { //a loop to add up all array lengths where each arg has an array
      added_array_sums = second[i] + first[i] + carry;
      if (added_array_sums > 99999999) {
        added_array_sums_string = Integer.toString(added_array_sums);
        carry = Integer.parseInt(added_array_sums_string.substring(0,1));
        shortened_array_sums = Integer.parseInt(added_array_sums_string.substring(1));
      }
      else {
        shortened_array_sums = added_array_sums;
        carry = 0;
      }
      sum[i] = shortened_array_sums;
      i = i + 1;
      }
      i = smallest_array_length;
      if (longer_array == 0) {
        while (i<largest_array_length) { //adds on final array lengths of longer array
          added_array_sums = first[i] + carry;
          if (added_array_sums > 99999999) {
            added_array_sums_string = Integer.toString(added_array_sums);
            carry = Integer.parseInt(added_array_sums_string.substring(0,1));
            shortened_array_sums = Integer.parseInt(added_array_sums_string.substring(1));
          }
          else {
            shortened_array_sums = added_array_sums;
            carry = 0;
          }
          sum[i] = shortened_array_sums;
          i = i + 1;          
        }
      }
      if (longer_array == 2) {
        while (i<largest_array_length) { //does same as above section but for the opposite possible case
          added_array_sums = second[i] + carry;
          if (added_array_sums > 99999999) {
            added_array_sums_string = Integer.toString(added_array_sums);
            carry = Integer.parseInt(added_array_sums_string.substring(0,1));
            shortened_array_sums = Integer.parseInt(added_array_sums_string.substring(1));
          }
          else {
            shortened_array_sums = added_array_sums;
            carry = 0;
          }
          sum[i] = shortened_array_sums;
          i = i + 1;          
        }
      }      
      String string_sum = ""; //create string to be used to create BrobInt
      String next_array = "";      
      if (carry != 0) {
        string_sum = Integer.toString(carry); //add on final carry if any left-over
      }
      string_sum = string_sum + Integer.toString(sum[largest_array_length-1]);
      i = 2;
      while (i<=largest_array_length) {
        next_array = Integer.toString(sum[largest_array_length-i]);
        while (next_array.length() < 8) {
          next_array = "0" + next_array;
        }
        string_sum = string_sum + next_array;
        i = i + 1;
      } 
    BrobInt BrobInt_sum = new BrobInt(string_sum); //create new BrobInt of sum of these two values
    return BrobInt_sum;    
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of two negative values organized in int arrays. Used to add two negative numbers or subtract a positive number from a negative number.
   *  @param  first        First positive number passed as an int array
   *  @param  second       Second positive number passed as an int array
   *  @return BrobInt that is the sum of the negative values passed in as int arrays
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt doublenegative( int[] first, int[] second ) { //follows same idealogy as above method
    int largest_array_length = 0;
    int smallest_array_length = 0;
    int longer_array = 0; //0 if first array is longer, 1 if equal, 2 if second array is longer
    if (first.length > second.length) {
      largest_array_length = first.length;
      smallest_array_length = second.length;
    }
    else if (first.length == second.length) {
      largest_array_length = first.length;
      smallest_array_length = second.length;
      longer_array = 1;
    }      
    else {
      largest_array_length = second.length;
      smallest_array_length = first.length;
      longer_array = 2;
    }
    int[] sum = new int[largest_array_length];
    int i = 0;
    int carry = 0;
    int added_array_sums = 0;
    String added_array_sums_string = "";
    int shortened_array_sums = 0;
    while (i<smallest_array_length) {
      added_array_sums = second[i] + first[i] + carry;
      if (added_array_sums > 99999999) {
        added_array_sums_string = Integer.toString(added_array_sums);
        carry = Integer.parseInt(added_array_sums_string.substring(0,1));
        shortened_array_sums = Integer.parseInt(added_array_sums_string.substring(1));
      }
      else {
        shortened_array_sums = added_array_sums;
        carry = 0;
      }
      sum[i] = shortened_array_sums;
      i = i + 1;
      }
      i = smallest_array_length;
      if (longer_array == 0) {
        while (i<largest_array_length) {
          added_array_sums = first[i] + carry;
          if (added_array_sums > 99999999) {
            added_array_sums_string = Integer.toString(added_array_sums);
            carry = Integer.parseInt(added_array_sums_string.substring(0,1));
            shortened_array_sums = Integer.parseInt(added_array_sums_string.substring(1));
          }
          else {
            shortened_array_sums = added_array_sums;
            carry = 0;
          }
          sum[i] = shortened_array_sums;
          i = i + 1;          
        }
      }
      if (longer_array == 2) {
        while (i<largest_array_length) {
          added_array_sums = second[i] + carry;
          if (added_array_sums > 99999999) {
            added_array_sums_string = Integer.toString(added_array_sums);
            carry = Integer.parseInt(added_array_sums_string.substring(0,1));
            shortened_array_sums = Integer.parseInt(added_array_sums_string.substring(1));
          }
          else {
            shortened_array_sums = added_array_sums;
            carry = 0;
          }
          sum[i] = shortened_array_sums;
          i = i + 1;          
        }
      }      
      String string_sum = "-";
      String next_array = "";
      if (carry != 0) {
        string_sum = string_sum + Integer.toString(carry);
      }
      string_sum = string_sum + Integer.toString(sum[largest_array_length-1]);
      i = 2;
      while (i<=largest_array_length) {
        next_array = Integer.toString(sum[largest_array_length-i]);
        while (next_array.length() < 8) {
          next_array = "0" + next_array;
        }
        string_sum = string_sum + next_array;
        i = i + 1;
      }
    BrobInt BrobInt_sum = new BrobInt(string_sum);
    return BrobInt_sum;    
   }   

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a positive value and a negative value both organized in int arrays. Used to add one positive and one negative number or subtract a positive from a positive or a negative from a negative.
   *  @param  first        First positive number passed as an int array
   *  @param  second       Second positive number passed as an int array
   *  @param  larger_value Int value denoting which of the passed args has a larger absolute value. pos for larger first arg, neg for larger second arg
   *  @return BrobInt that is the sum of the positive values passed in as int arrays
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt firstpositive( int[] first, int[] second , int larger_value) { //follows same ideology of above method with a few caveats
    int largest_array_length = 0;
    int smallest_array_length = 0;
    int longer_array = 0; //0 if first array is longer, 1 if equal, 2 if second array is longer
    if (first.length > second.length) {
      largest_array_length = first.length;
      smallest_array_length = second.length;
    }
    else if (first.length == second.length) {
      largest_array_length = first.length;
      smallest_array_length = second.length;
      longer_array = 1;
    }      
    else {
      largest_array_length = second.length;
      smallest_array_length = first.length;
      longer_array = 2;
    }
    int[] sum = new int[largest_array_length];
    int i = 0;
    int reverse_carry = 0; //denotes whether or not the larger value needed to pull from a higher level array for the subtraction of a lower level array
    int added_array_sums = 0;
    String added_array_sums_string = "";
    int shortened_array_sums = 0;
    if (larger_value > 0) {
      while (i<smallest_array_length) {
        if (reverse_carry == 1) {
          first[i] = first[i] - 1;
          reverse_carry = 0;
        }
        added_array_sums = first[i] - second[i];
        if (added_array_sums < 0) {
          added_array_sums = (100000000 + first[i]) - second[i];
          reverse_carry = 1;
        }
        sum[i] = added_array_sums;
        i = i + 1;
      }
      i = smallest_array_length;
      while (i<largest_array_length) {
        if (reverse_carry == 1) {
          first[i] = first[i] - 1;
          reverse_carry = 0;
        }
        sum[i] = first[i];
        i = i + 1;          
      }
    }
    if (larger_value < 0) {
      while (i<smallest_array_length) {
        if (reverse_carry == 1) {
          second[i] = second[i] - 1;
          reverse_carry = 0;
        }
        added_array_sums = second[i] - first[i];
        if (added_array_sums < 0) {
          added_array_sums = (100000000 + second[i]) - first[i];
          reverse_carry = 1;
        }
        added_array_sums_string = Integer.toString(added_array_sums);
        sum[i] = Integer.parseInt(added_array_sums_string);
        i = i + 1;
      }
      i = smallest_array_length;
      while (i<largest_array_length) {
        if (reverse_carry == 1) {
          second[i] = second[i] - 1;
          reverse_carry = 0;
        }
        sum[i] = second[i];
        i = i + 1;          
      }
    }    
    String string_sum = "";
    String next_array = "";    
    if (larger_value < 0) {
      string_sum = "-"; //make value negative if the larger value was negative
    }
    string_sum = string_sum + Integer.toString(sum[largest_array_length-1]);    
    i = 2;
    while (i<=largest_array_length) {
      next_array = Integer.toString(sum[largest_array_length-i]);
      while (next_array.length() < 8) {
        next_array = "0" + next_array;
      }
      string_sum = string_sum + next_array;
      i = i + 1;
    }
    if (larger_value == 0) {
      string_sum = "0";
    }
    BrobInt BrobInt_sum = new BrobInt(string_sum);
    return BrobInt_sum; 
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt gint ) {
    BrobInt tester = new BrobInt(internalValue);
    int larger_abs_value = tester.compareTo(gint);
    int addition_case = 0;
    if ((sign == 1) && (gint.sign == 0)) {
      addition_case = 1;
    }
    else if ((sign == 0) && (gint.sign == 1)) {
      addition_case = 2;
    }
    else if ((sign == 1) && (gint.sign == 1)) {
      addition_case = 3;
      larger_abs_value = -1 * larger_abs_value;
    }
    BrobInt summation_brob = null;
    switch (addition_case) {
      case 0: summation_brob = firstpositive( intVersion, gint.intVersion, larger_abs_value);
              break;
      case 1: summation_brob = doublenegative( intVersion, gint.intVersion);
              break;
      case 2: summation_brob = doublepositive( intVersion, gint.intVersion);
              break;
      case 3: summation_brob = firstpositive( gint.intVersion, intVersion, larger_abs_value);
              break;
    }
    return summation_brob;    
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt gint ) {
    int doubled = 0;
    int halved = 0;
    int largest_array_length = 0;
    int smallest_array_length = 0;
    int longer_array = 0; //0 if first array is longer, 1 if equal, 2 if second array is longer
    if (intVersion.length > gint.intVersion.length) {
      largest_array_length = intVersion.length;
      smallest_array_length = gint.intVersion.length;
    }
    else if (intVersion.length == gint.intVersion.length) {
      largest_array_length = intVersion.length;
      smallest_array_length = gint.intVersion.length;
      longer_array = 1;
    }      
    else {
      largest_array_length = gint.intVersion.length;
      smallest_array_length = intVersion.length;
      longer_array = 2;
    }
    int prod_array_length = (largest_array_length+(largest_array_length-smallest_array_length));
    int[] product = new int[prod_array_length];
    int i = 0;
    int t = 0;
    String carry = "";
    int mult_array_prod = 0;
    String mult_array_prod_string = "";
    int shortened_array_sums = 0;
    while (i<smallest_array_length) {
      doubled = intVersion[i];
      while (t<largest_array_length) {
        halved = gint.intVersion[t];
        while (halved >= 1) {
          if ((halved % 2) != 0) {
            mult_array_prod += doubled;
          }
          halved = halved / 2;
          doubled = doubled * 2;
        }
        if (carry.length() > 0) {
          mult_array_prod = mult_array_prod + Integer.parseInt(carry);
        }
        mult_array_prod_string = Integer.toString(mult_array_prod);
        carry = "";
        while (mult_array_prod_string.length() > 8) {
          carry = carry + mult_array_prod_string.substring(0,1);
          mult_array_prod_string = mult_array_prod_string.substring(1);
        }
        product[(i+t)] = Integer.parseInt(mult_array_prod_string);
        t += 1;
      }
      i += 1;
    }
    String string_prod = "";
    if ((sign ==1 && gint.sign == 0) || (sign == 0 && gint.sign == 1)) {
      string_prod = "-";
    }
    String next_array = "";
    if (carry.length() > 0) {
      string_prod = string_prod + carry;
    }
    string_prod = string_prod + Integer.toString(product[prod_array_length-1]);
    i = 2;
    while (i<=prod_array_length) {
      next_array = Integer.toString(product[prod_array_length-i]);
      while (next_array.length() < 8) {
        next_array = "0" + next_array;
      }
      string_prod = string_prod + next_array;
      i = i + 1;
    }
    BrobInt BrobInt_prod = new BrobInt(string_prod);
    return BrobInt_prod;   
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
   *        THAT was easy.....
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public int compareTo( BrobInt gint ) {
       if( internalValue.length() > gint.internalValue.length() ) {
          return 1;
       } else if( internalValue.length() < gint.internalValue.length() ) {
          return (-1);
       } else {
          for( int i = 0; i < internalValue.length(); i++ ) {
             Character a = new Character( internalValue.charAt(i) );
             Character b = new Character( gint.internalValue.charAt(i) );
             if( new Character(a).compareTo( new Character(b) ) > 0 ) {
                return 1;
             } else if( new Character(a).compareTo( new Character(b) ) < 0 ) {
                return (-1);
             }
          }
       }
       return 0;
    }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.internalValue ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( Long.toString(value) );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
    String intVersionOutput = "";
    if (sign == 1) {
      intVersionOutput = "-";
    }
    intVersionOutput = intVersionOutput + Integer.toString(intVersion[intVersion.length-1]);
    int i = 2;
    String next_array = "";
    while (i<=intVersion.length) {
      next_array = Integer.toString(intVersion[intVersion.length-i]);
      while (next_array.length() < 8) {
        next_array = "0" + next_array;
      }      
      intVersionOutput = intVersionOutput + next_array;
      i = i + 1;
    }
    return intVersionOutput;
   }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these. used to fix small bugs in code without having to use full test harness script
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );
      BrobInt gi = new BrobInt(args[0]);
      BrobInt go = new BrobInt(args[1]);
      System.out.println(gi.compareTo(go));
      BrobInt sum = gi.add(go);    
      BrobInt sub = gi.subtract(go);       
      //BrobInt prod = gi.multiply(go);
      System.out.println("Hello");       
      System.out.println(gi.toString());
      System.out.println(go.toString());
      System.out.println(sum.toString());
      System.out.println(sub.toString());
      //System.out.println(prod.toString());
      // BrobInt revgi = gi.reverser();
      // BrobInt revgi2 = reverser(gi);
      // int compared = gi.compareTo(revgi);
      // System.out.println(compared);
      // System.out.println(gi.equals(revgi));
      System.exit( 0 );

   }
}
