/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  Quinn Lanners
 *  Date written  :  2018-02-27
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 *  @version 2.0.0  2018-02-27  Quinn Lanners Completed Class to function with ClockSolver.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.text.DecimalFormat;
import java.math.RoundingMode;

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double hour_deg_sec = 0.008333333333333;
   private static final double minute_deg_sec = 0.1;
   private double elapsed_sec = 0;
   private double tick_size;
   private double minute_angle;
   private double hour_angle;

  /**
   *  Constructor goes here
   */
   public Clock( double time_slice ) {
      this.tick_size = time_slice;

   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
      elapsed_sec += tick_size;
      return elapsed_sec;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException {
      if ((Double.parseDouble(argValue) < 360) & (Double.parseDouble(argValue) >= 0)) {
        return Double.parseDouble(argValue);
      } else {
        return -1.0;
      }
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) throws NumberFormatException {
      if ((Double.parseDouble(argValue) <= 1800) & (Double.parseDouble(argValue) > 0)) {
        return Double.parseDouble(argValue);
      } else {
        return -1.0;
      }
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      double hour_total_angle = elapsed_sec * hour_deg_sec;
      hour_angle = hour_total_angle % 360;
      return hour_angle;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      double minute_total_angle = elapsed_sec * minute_deg_sec;
      minute_angle = minute_total_angle % 360;
      return minute_angle;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      double hand_angle = Math.abs(hour_angle - minute_angle);
      double hand_angle2 = 360 - hand_angle;
      if (hand_angle2 < hand_angle) {
        hand_angle = hand_angle2;
      }
      return hand_angle;
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return elapsed_sec;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
      DecimalFormat dec1 = new DecimalFormat("00");
      DecimalFormat dec2 = new DecimalFormat("00.00");
      dec1.setRoundingMode(RoundingMode.DOWN);
      dec2.setRoundingMode(RoundingMode.DOWN);
      String hours = dec1.format((elapsed_sec / 3600));
      String minutes = dec1.format(((elapsed_sec%3600) / 60));
      String seconds =dec2.format((elapsed_sec % 60));
      return hours+":"+minutes+":"+seconds;
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock(60);
      System.out.println(clock.tick());
      clock.getHourHandAngle();
      clock.getMinuteHandAngle();
      System.out.println(clock.getHandAngle());
      System.out.println(clock.toString());
      System.out.println(clock.tick());
      clock.tick();
      clock.tick();
      clock.tick();
      clock.tick();
      clock.getHourHandAngle();
      clock.getMinuteHandAngle();      
      System.out.println(clock.getHandAngle());
      System.out.println(clock.toString());           
      System.out.println(clock.tick());
      clock.getHourHandAngle();
      clock.getMinuteHandAngle();      
      System.out.println(clock.getHandAngle());      
      System.out.println(clock.elapsed_sec);
      System.out.println( "    New clock created: " + clock.toString() );
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
   }
}
