/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Dynamic Programming exercise to compute the optimal way of making change for a specified target value from specified denomination values
 * @author    :  Quinn Lanners
 * Date       :  2018-05-03
 * Description:  A class that determines the optimal combination of coins to create change for a target value
 * Notes      :  The program can be run directly from the command line in the following format:
 *					java DynamicChangeMaker a,b,c,... t 
 				 where a,b,c,... are the specified denominations, with all values >0, at least one denomination, and no repeats. And t is the target value >0.
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2018-05-03  Quinn Lanners Creation of the code which can be run from command line or the DynamicChangemakerTestHarness.java file
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.util.Arrays;

public class DynamicChangeMaker {
	
  /**
   * private instance data
   */	
	public static Tuple denominations = null;
	public static int number_of_denominations = 0;
	public static int target = 0;
	public static Tuple[][] theTable = null;
	public static final Tuple IMPOSSIBLE = new Tuple( new int[0] );

  /**
   *  Constructor takes two string values and creates an instance of DynamicChangeMaker with a stored value of denominations and target value.
   *  @param  value  String value of the denominations available to make change for this instance
   *  @param  targ   String value of the target value of change for this instance
   *  @catch  IllegalArgumentException If the inputted arguments do not meet the specifications as outlined in the validation methods below
   */
	public DynamicChangeMaker ( String denoms, String targ ) {
		try {
			this.target = validateStringTargetValue(targ);
		}
		catch (IllegalArgumentException e) { 
			System.out.println(e.toString());
			System.exit(-1);
		}
		String[] denoms_list = denoms.split(",");
		try {
			this.denominations = new Tuple( validateStringDenominations(denoms_list) );
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.toString());
			System.exit(-1);
		}
		this.number_of_denominations = denoms_list.length;
		this.theTable = new Tuple[number_of_denominations][target+1]; 

	}

  /**
   * Validates the target value for a created instance of a DynamicChangeMaker
   * @param  target value in form of String pulled as an arg from the command line
   * @return the integer value of the target value
   * @trhows IllegalArgumentException if the target value is not an integer or <0
   */
	public int validateStringTargetValue( String t ) throws IllegalArgumentException {
		int target = 0;
		if (Integer.parseInt(t) > 0) {
			target = Integer.parseInt(t);
		}
		else {
			throw new IllegalArgumentException("Sorry that is not a valid target value. Target value must be a positive integer >0.");
		}
		return target;
	}


  /**
   * Validates the denomination values for a created instance of a DynamicChangeMaker
   * @param  denomination values in form of String of denominations seperated by commas pulled as an arg from the command line
   * @return the integer array of the denomination values
   * @trhows IllegalArgumentException if there isn't at least 1 denomination value, there are repeat denomination values, or there are denominations <1 or not integers
   */
	public int[] validateStringDenominations ( String[] d ) throws IllegalArgumentException {
		int[] denoms_values = null;
		if ( d.length > 0 ) {
			denoms_values = new int[d.length];
			for (int i=0; i<d.length; i++) {
				if (Integer.parseInt(d[i]) > 0 && (Arrays.binarySearch(denoms_values, Integer.parseInt(d[i])) < 0)) { //checks to ensure that this value is not already in the denoms_value array
					denoms_values[i] = Integer.parseInt(d[i]);					
				}
				else {
					throw new IllegalArgumentException("Sorry those are not valid denomination values. There must be >=1 denomination(s), they must all be >0, and there can be no duplicates.");
				}
			}
		}
		else {
			throw new IllegalArgumentException("Sorry those are not valid denomination values. There must be >=1 denomination(s), they must all be >0, and there can be no duplicates.");
		}
		return denoms_values;
	}


  /**
   * Static method to validate the target value for static makeChangeWithDynamicProgramming method
   * @param  target value in form of an int inputted as arg to the static makeChangeWithDynamicProgramming method
   * @return the integer value of the target value
   * @trhows IllegalArgumentException if the target value is not an integer or <0
   */
	public static int validateIntTargetValue( int t ) throws IllegalArgumentException {
		int target = 0;
		if (t > 0) {
			target = t;
		}
		else {
			throw new IllegalArgumentException("Sorry that is not a valid target value. Target value must be a positive integer >0.");
		}
		return target;
	}		

  /**
   * Static method to validate the denomination values for static makeChangeWithDynamicProgramming method
   * @param  denomination values in form of int array inputted as arg to the static makeChangeWithDynamicProgramming method
   * @return the integer array of the denomination values
   * @trhows IllegalArgumentException if there isn't at least 1 denomination value, there are repeat denomination values, or there are denominations <1 or not integers
   */
	public static int[] validateArrayDenominations ( int[] d ) throws IllegalArgumentException {
		int[] denoms_values = null;
		if ( d.length > 0 ) {
			denoms_values = new int[d.length];
			for (int i=0; i<d.length; i++) {
				if (d[i] > 0 && (Arrays.binarySearch(denoms_values, d[i]) < 0)) { //checks to ensure that this value is not already in the denoms_value array
					denoms_values[i] = d[i];					
				}
				else {
					throw new IllegalArgumentException("BAD DATA");
				}
			}
		}
		else {
			throw new IllegalArgumentException("BAD DATA");
		}
		return denoms_values;
	}

  /**
   * Method to act on an instance of a DynamicChangeMaker to determine the optimal combination of the inputted denominations to create change for the target value
   * @return the string representation of the array of the denomination values corresponding to the optimal solution
   */
	public String makeChangeWithDynamicProgramming() {
		int row = 0;
		int col = 0;
		while (row < number_of_denominations) {
			col = 0;
			while (col <= target) {
				theTable[row][col] = new Tuple(number_of_denominations);
				if (col > 0) {
					if (denominations.getElement(row) <= col) {
						theTable[row][col].setElement(row,1); //if col value fits into the denomination, put a 1 in that element of the Tuple
						if ( theTable[row][col-denominations.getElement(row)].equals(Tuple.IMPOSSIBLE) == false ) { //go back as many col as the denomination value
							theTable[row][col] = theTable[row][col].add(theTable[row][col-denominations.getElement(row)]); //if not IMP, add together these Tuples
						}
						else {
							theTable[row][col] = IMPOSSIBLE; //if IMP, this Tuple becomes IMP
						}
					}
					else {
						theTable[row][col] = IMPOSSIBLE; //If col value isn't larger than denomination value, Tuple = IMP
					}
					if (row >= 1) { //If not in row 0, look up
						if (theTable[row][col].equals(Tuple.IMPOSSIBLE) == true) { //if this tuple is IMP, because what previous rows Tuple is
							theTable[row][col] = theTable[row-1][col];
						}
						else if ((theTable[row-1][col].equals(Tuple.IMPOSSIBLE) == false) && (theTable[row-1][col].total() < theTable[row][col].total())) { //if this tuple isn't IMP and tuple above isn't IMP check to see which has larger total
							theTable[row][col] = theTable[row-1][col]; //If this tuples total > row above's total, become the Tuple above
						}
					}
				}
				col += 1; //index a column
			}
			row += 1; //index a row
		}
		return theTable[row-1][col-1].toString();
	}

  /**
   * Static method to determine the optimal combination of the inputted denominations to create change for the target value
   * @param  denomination values in form of int array
   * @param  target value in the form of an int
   * @return the Tuple representation of the denomination values corresponding to the optimal solution
   */
	public static Tuple makeChangeWithDynamicProgramming( int[] denoms_input, int target ) { //pretty much the same as the above method, just a static version of it which takes args
		int [] denom_values = validateArrayDenominations(denoms_input);
		Tuple denoms = new Tuple(denom_values);
		target = validateIntTargetValue(target);
		Tuple[][] theTable = new Tuple[denom_values.length][target+1];
		int row = 0;
		int col = 0;
		while (row < denom_values.length) {
			col = 0;
			while (col <= target) {
				theTable[row][col] = new Tuple(denom_values.length);
				if (col > 0) {
					if (denoms.getElement(row) <= col) {
						theTable[row][col].setElement(row,1);
						if ( theTable[row][col-denoms.getElement(row)].equals(Tuple.IMPOSSIBLE) == false ) {
							theTable[row][col] = theTable[row][col].add(theTable[row][col-denoms.getElement(row)]);
						}
						else {
							theTable[row][col] = IMPOSSIBLE;
						}
					}
					else {
						theTable[row][col] = IMPOSSIBLE;
					}
					if (row >= 1) {
						if (theTable[row][col].equals(Tuple.IMPOSSIBLE) == true) {
							theTable[row][col] = theTable[row-1][col];
						}
						else if ((theTable[row-1][col].equals(Tuple.IMPOSSIBLE) == false) && (theTable[row-1][col].total() < theTable[row][col].total())) {
							theTable[row][col] = theTable[row-1][col];
						}
					}
				}
				col += 1;
			}
			row += 1;
		}
		return theTable[row-1][col-1];
	}

  /**
   *  The main program which allows the DynamicChangeMaker to be run from the command line for any specified denomination values and target value
   *  @param  args  String array of the arguments from the command line
   *                args[0] are the denominations which are to be used to make change with (seperated by commas)
   *                args[1] is the target value of change desired
   */	
	public static void main( String[] args ) {
		DynamicChangeMaker change_maker = new DynamicChangeMaker(args[0],args[1]);
		System.out.println("Hello");
		System.out.println("Your input denominations are valid and are:\n"+change_maker.denominations.toString());
		System.out.println("Your target value is valid and is:\n"+change_maker.target);
		String optimal_solution = change_maker.makeChangeWithDynamicProgramming();
		int total_coins = change_maker.theTable[number_of_denominations-1][change_maker.target].total();
		System.out.println("The smallest number of coins needed to create change for this target value with the specified denominations is "+total_coins+" with a combination of "+optimal_solution);

	}
}
