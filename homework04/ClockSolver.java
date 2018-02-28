/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  Quinn Lanners
 *  Date written  :  2018-02-27
 *  Description   :  This class uses the Clock.java class to determine times at which the hands of
 *					 a clock are at a vertain angle. ClockSolver takes two arguments: args[0]: desired angle
 *					 args[1]: seconds passed per tick (optional. defaults to 60 seconds)
 *
 *  Notes         :  Input arguments directly on the Commandline along with ClockSolver:
 *					 java ClockSolver <angle> [timeSlice]
 *					 Takes at least one argument but no more than 2 arguments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 *  @version 2.0.0  2018-02-27	Quinn Lanenrs Completed form of ClockSolver that performs desired action
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final static double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private final static double EPSILON_VALUE              = 0.1;      // small value for double-precision comparisons

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */

   public void handleInitialArguments( String args[] ) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // you may want to consider using args[2] for an "angle window"

      Clock dummy_clock = new Clock(0);
      System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
      if( 0 == args.length ) {
         System.out.println( "   Sorry you must enter at least one argument\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
         System.exit( 1 );
      }
      if ( 2== args.length ) {
      	if (dummy_clock.validateTimeSliceArg( args[1] ) == -1.0) {
      		System.out.println( "The TimeSliceArg (Arg[1]) must be between 0-1800 seconds");
      		System.exit(1);
      	}
      }
      if (args.length > 2) {
      	System.out.println( "Too many arguments. ClockSolver accepts either 1 or 2 arguments.");
      	System.exit(1);
      }

   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
      ClockSolver cse = new ClockSolver();
      cse.handleInitialArguments( args );
      double args_time_slice = 0;
      if ( 2 == args.length) {
      	args_time_slice = Double.parseDouble(args[1]);
      }
      else {
      	args_time_slice = DEFAULT_TIME_SLICE_SECONDS;
      }
      Clock clock = new Clock(args_time_slice);
      double desired_angle = (Math.abs(Double.parseDouble(args[0]) % 180));
      while( clock.getTotalSeconds() < (60*60*12) ) {
      	clock.tick();
      	clock.getHourHandAngle();
      	clock.getMinuteHandAngle();
      	if ((clock.getHandAngle() >= (desired_angle - EPSILON_VALUE)) & (clock.getHandAngle() <= (desired_angle + EPSILON_VALUE))) {
      		System.out.println(clock.toString());
      	}
      }
      System.exit( 0 );
   }
}
