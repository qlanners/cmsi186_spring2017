/**
 *  File name     :  CountTheDays.java
 *  Purpose       :  Returns the number of days between two specified dates
 *  Author        :  Quinn Lanners
 *  Date          :  2018-01-23
 *  Description   :  This file uses the code from CalendarStuff.java to return the number of days between
 *                   two specified dates. Ensure that the dates are entered in the format month1, day1,
 *                   year1, month2, day2, year2 with month ranges between [1,12].
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-01-23  Quinn Lanners Initial release of functionable program
 */

public class CountTheDays {
	public static int month1 = 0;
	public static int day1 = 0;
	public static int year1 = 0;
	public static int month2 = 0;
	public static int day2 = 0;
	public static int year2 = 0;

	public static void main (String args[]) {
		System.out.println("\n Welcome to CountTheDay! The Tool to find the number of days between two dates! \n");
		if (args.length != 6) {
			System.out.println("\n You need 6 arguments listed in the following order: month1, day1, year1, month2, day2, year2 \n");
			System.exit(-1);
		} else {
			try {
				month1 = Integer.parseInt(args[0]);
				day1 = Integer.parseInt(args[1]);
				year1 = Integer.parseInt(args[2]);
				month2 = Integer.parseInt(args[3]);
				day2 = Integer.parseInt(args[4]);
				year2 = Integer.parseInt(args[5]);
				DaysBetween();
			} catch( Exception e ) {
				System.err.println(e.getMessage());
			}
		}
	}
	static void DaysBetween() {
		try {
			long days_between = 0;
			days_between = CalendarStuff.daysBetween( month1, day1, year1, month2, day2, year2 );
		    System.out.println("Days between " + month1 + "/" + day1 + "/" + year1 + " and " + month2 + "/" + day2 + "/" + year2 + ":  " + days_between);
		} catch( Exception e) {
			System.err.println("Error in arguments entered");
		}
	}
}
