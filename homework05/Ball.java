import java.lang.Math;

public class Ball {

	public static final double field_size = 300;
	public double x_position;
	public double y_position;
	public double x_velocity;
	public double y_velocity;
	public boolean in_play = true;
	public boolean is_moving = true;
	public double size_of_ball = 0.37083333333;


	public Ball( double x_pos, double y_pos, double x_vel, double y_vel) {
		this.x_position = x_pos;
		this.y_position = y_pos;
		this.x_velocity = x_vel;
		this.y_velocity = y_vel;
	}

	public boolean validate_init_pos( String argValue0, String argValue1, String argValue2, String argValue3) throws NumberFormatException {
		double test_argValue2 = Double.parseDouble(argValue2);
		double test_argValue3 = Double.parseDouble(argValue3);
		return in_play(Double.parseDouble(argValue0), Double.parseDouble(argValue1));

	}

	public boolean in_play ( double x_pos, double y_pos) {
		if ((x_pos <= field_size) && (x_pos >= -field_size) && (y_pos <= field_size) && (y_pos >= -field_size)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean still_in_play () {
		if ((x_position <= field_size) && (x_position >= -field_size) && (y_position <= field_size) && (y_position >= -field_size)) {
			return in_play;
		} else {
			in_play = false;
			return in_play;
		}
	}
	public double update_x_position (double time_slice) {
		x_position += (x_velocity*time_slice);
		return x_position;
	}

	public double update_y_position (double time_slice) {
		y_position += (y_velocity*time_slice);
		return y_position;
	}

	public double update_x_vel (double time_slice) {
		x_velocity -= (x_velocity*(0.01*time_slice));
		return x_velocity;	
	}

	public double update_y_vel (double time_slice) {
		y_velocity -= (y_velocity*(0.01*time_slice));
		return y_velocity;	
	}

	public boolean is_moving () {
		if (Math.sqrt((x_velocity*x_velocity)+(y_velocity*y_velocity)) >= 0.083333333333) {
			is_moving = true;
		} else {
			x_velocity = 0;
			y_velocity = 0;
			is_moving = false;
		}
		return this.is_moving;
	}

	public boolean collision (double other_x_pos, double other_y_pos) {
		if (Math.sqrt(Math.pow((other_y_pos-x_position), 2) + Math.pow((other_y_pos-y_position), 2)) <= (size_of_ball*2)) {
			return true;
		}
		else {
			return false;
		}
	}
	public String show_info () {
		if (is_moving == true && in_play == true) {
			return "still in motion with:\nX position: "+x_position+"\nY Position: "+y_position+"\nX velocity: "+x_velocity+"\nY velocity: "+y_velocity;
		} else if (is_moving == false && in_play == true) {
			return "at rest at:\nX position: "+x_position+"\nY Position: "+y_position;
		} else {
			return "out of play";
		}
	}





	public static void main(String args[]) {

		System.out.println("Ball Testing Started");
		Ball ball1 = new Ball(0,0,20,20);
		System.out.println(ball1.show_info());
		double time_slice = 0.001;


	}
}
