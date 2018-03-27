/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Timer.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  @author       :  Quinn Lanners
 *  Date written  :  2018-03-26
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 5.
 *
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-26  Quinn Lanners Completed Class to function with SoccerSim.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */


import java.text.DecimalFormat;
import java.math.RoundingMode;

public class Timer {

	public double elapsed_sec = 0;
	public double tick_size;


	public Timer (double tick) {
		this.tick_size = tick;
	}

	public double tick () {
		elapsed_sec += tick_size;
		return elapsed_sec;
	}

   public boolean validateTimeSliceArg( String argValue ) throws NumberFormatException {
      if (Double.parseDouble(argValue) > 0) {
        return true;
      } else {
        return false;
      }
   }	

	public String show_time() {
      DecimalFormat dec1 = new DecimalFormat("00");
      DecimalFormat dec2 = new DecimalFormat("00.00");
      dec1.setRoundingMode(RoundingMode.DOWN);
      dec2.setRoundingMode(RoundingMode.DOWN);
      String hours = dec1.format((elapsed_sec / 3600));
      String minutes = dec1.format(((elapsed_sec%3600) / 60));
      String seconds =dec2.format((elapsed_sec % 60));
      return "Elapsed Time = "+hours+":"+minutes+":"+seconds;
	}
}
