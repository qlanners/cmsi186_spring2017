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
