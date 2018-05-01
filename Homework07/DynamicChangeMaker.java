import java.util.Arrays;

public class DynamicChangeMaker {
	
	public static Tuple denominations = null;
	public static int number_of_denominations = 0;
	public static int target = 0;
	public static Tuple[][] theTable = null;
	public static final Tuple IMPOSSIBLE = new Tuple( new int[0] );

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

	public int[] validateStringDenominations ( String[] d ) throws IllegalArgumentException {
		int[] denoms_values = null;
		if ( d.length > 0 ) {
			denoms_values = new int[d.length];
			for (int i=0; i<d.length; i++) {
				if (Integer.parseInt(d[i]) > 0 && (Arrays.binarySearch(denoms_values, Integer.parseInt(d[i])) < 0)) {
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

	public static int[] validateArrayDenominations ( int[] d ) throws IllegalArgumentException {
		int[] denoms_values = null;
		if ( d.length > 0 ) {
			denoms_values = new int[d.length];
			for (int i=0; i<d.length; i++) {
				if (d[i] > 0 && (Arrays.binarySearch(denoms_values, d[i]) < 0)) {
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

	public String makeChangeWithDynamicProgramming() {
		int row = 0;
		int col = 0;
		while (row < number_of_denominations) {
			col = 0;
			while (col <= target) {
				theTable[row][col] = new Tuple(number_of_denominations);
				if (col > 0) {
					if (denominations.getElement(row) <= col) {
						theTable[row][col].setElement(row,1);
						if ( theTable[row][col-denominations.getElement(row)].equals(Tuple.IMPOSSIBLE) == false ) {
							theTable[row][col] = theTable[row][col].add(theTable[row][col-denominations.getElement(row)]);
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
		return theTable[row-1][col-1].toString();
	}

	public static Tuple makeChangeWithDynamicProgramming( int[] denoms_input, int target ) {
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
