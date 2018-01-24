/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  Quinn Lanners
 *  Date          :  2018-01-23
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
 *  @version 2.0.0  2018-01-23  Quinn Lanners Body of code added to prototype to create functioning code
 */
public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY   = SUNDAY    + 2;
   private static final int WEDNESDAY = SUNDAY    + 3;
   private static final int THURSDAY  = SUNDAY    + 4;
   private static final int FRIDAY    = SUNDAY    + 5;
   private static final int SATURDAY  = SUNDAY    + 6;
  // you can finish the rest on your own
  
  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH      = JANUARY   + 2;
   private static final int APRIL      = JANUARY   + 3;
   private static final int MAY        = JANUARY   + 4;
   private static final int JUNE       = JANUARY   + 5;
   private static final int JULY       = JANUARY   + 6;
   private static final int AUGUST     = JANUARY   + 7;
   private static final int SEPTEMBER  = JANUARY   + 8;
   private static final int OCTOBER    = JANUARY   + 9;
   private static final int NOVEMBER   = JANUARY   + 10;
   private static final int DECEMBER   = JANUARY   + 11;
  // you can finish these on your own, too
  
  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */
   private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   private static int[]    days_leap   = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   private static int days_in_year = 365;
   private static int days_in_leap_year = 366;
  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      super();
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
      if ((year < 1000 ) || (year > 9999)) {
        System.out.println("Not a valid four-digit year");
        //System.exit(-1);
        return false;
      } else {
          if (((year%4) == 0) && ((year%100) != 0)) { 
             return true;
          } else if ((year%400) == 0){
             return true;
          } else{
             return false;
          } 
      }
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {
      month--;
      int month_size = 0;
      if (isLeapYear(year)) {
      	month_size = days_leap[(int)month];
      } else{
         month_size = days[(int)month];
      } 
      return month_size;
    }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if ((month1 == month2) && (day1 == day2) && (year1 == year2)) {
        return true;
      } else {
        return false;
      }
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if (year1 < year2) {
          return -1;
      } else if (year1 > year2) {
          return 1;
        } else {
          if (month1 < month2) {
            return -1;
          } else if (month1 > month2) {
            return 1;
          } else {
            if (day1 < day2) {
              return -1;
            } else if (day1 > day2) {
              return 1;
            } else {
              return 0;
            }
          }

        }

      }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
      month--;
      if ((year<0) || (month<0) || (month>11)) {
      	return false;
      } else {
      	if (isLeapYear(year)) {
            if ((day > 0) && (day <= days_leap[(int)month])) {
      		   return true;
      	    } else {
      		    return false;
      	     }
      	   }
        else {
      	if ((day > 0) && (day <= days[(int)month])) {
      		return true;
      	} else {
      		return false;	
      }
     }
    }
    }


  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
      String month_of_year;
      switch( month - 1 ) {
        case 0: month_of_year = "January";
                break;
        case 1: month_of_year = "February";
                break;
        case 2: month_of_year = "March";
                break;
        case 3: month_of_year = "April";
                break;
        case 4: month_of_year = "May";
                break;
        case 5: month_of_year = "June";
                break;
        case 6: month_of_year = "July";
                break;
        case 7: month_of_year = "August";
                break;
        case 8: month_of_year = "September";
                break;
        case 9: month_of_year = "October";
                break;   
        case 10: month_of_year = "November";
                break; 
        case 11: month_of_year = "December";
                break;                                                                            
        default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
      return month_of_year;
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
      String day_of_week;
      switch( day - 1 ) {
        case 0: day_of_week = "Sunday";
                break;
        case 1: day_of_week = "Monday";
                break;
        case 2: day_of_week = "Tuesday";
                break;
        case 3: day_of_week = "Wednesday";
                break;
        case 4: day_of_week = "Thursday";
                break;
        case 5: day_of_week = "Friday";
                break;
        case 6: day_of_week = "Saturday";
                break;                                               
        default: throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
      return day_of_week;
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
   	month1--;
   	month2--;
   	long days_between = 0; 
   	if (dateEquals(month1, day1, year1, month2, day2, year2)) {
   		days_between = 0;
   	} else {
   		if (year1 == year2) {
   			if (month1 == month2) {
   				days_between = Math.abs(day1 - day2);
   			} else {
   				if (isLeapYear(year1)) {
   					if (month1>month2) {
   						days_between += day1;
   						days_between += (days_leap[(int)month2] - day2);
   						for (int i = (int)month2+1; i < (int)month1; i++) {
   							days_between += days_leap[i];
   						}
   					} else {
   						days_between += day2;
   						days_between += (days_leap[(int)month1] - day1);
   						for (int i = (int)month1+1; i < (int)month2; i++) {
   							days_between += days_leap[i];
   						}   						
   					}
   				} else{
   					if (month1>month2) {
   						days_between += day1;
   						days_between += (days[(int)month2] - day2);
   						for (int i = (int)month2+1; i < (int)month1; i++) {
   							days_between += days[i];
   						}
   					} else {
   						days_between += day2;
   						days_between += (days[(int)month1] - day1);
   						for (int i = (int)month1+1; i < (int)month2; i++) {
   							days_between += days[i];
   						}   						
   					}   					

   				}
   			}
   		} else {
   			if (compareDate(month1, day1, year1, month2, day2, year2) == -1) {
   				days_between += (daysInMonth(month1+1, year1) - day1);
   				for (int i = (int)month1+1; i < 12; i++) {
   					if (isLeapYear(year1)) {
   						days_between += days_leap[i];
   					} else {
   						days_between += days[i];
   					}
   				}
   				for (int i = (int)year1 + 1; i <(int)year2; i++) {
   					if (isLeapYear(i)) {
   						days_between += days_in_leap_year;
   					} else {
   						days_between += days_in_year;
   					}
   				}
   				for (int i = 0; i <(int)month2; i++) {
   					if (isLeapYear(year2)) {
   						days_between += days_leap[i];
   					} else {
   						days_between += days[i];
   					}   					
   				}
   				days_between += (day2);
   			} else {
   				days_between += (daysInMonth(month2+1, year2) - day2);
   				for (int i = (int)month2+1; i < 12; i++) {
   					if (isLeapYear(year2)) {
   						days_between += days_leap[i];
   					} else {
   						days_between += days[i];
   					}
   				}
   				for (int i = (int)year2 + 1; i <(int)year1; i++) {
   					if (isLeapYear(i)) {
   						days_between += days_in_leap_year;
   					} else {
   						days_between += days_in_year;
   					}
   				}
   				for (int i = 0; i <(int)month1; i++) {
   					if (isLeapYear(year1)) {
   						days_between += days_leap[i];
   					} else {
   						days_between += days[i];
   					}   					
   				}
   				days_between += (day1);   				
   			}
   		}
   	}
   	return days_between;
   }


}
