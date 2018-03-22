public class Soccer {
	
	public static boolean collision = false;


	public Soccer () {
		super();
	}

	public void handle_args ( String args[] ) {
		if ((args.length % 4) == 0) {
			double number_of_balls = (args.length/4);
			int ball_number = 0;
			while (ball_number < number_of_balls) {
				Ball dummy_ball = new Ball(0,0,0,0);
				try{
					if (dummy_ball.validate_init_pos(args[(ball_number*4)],args[(ball_number*4)+1],args[(ball_number*4)+2],args[(ball_number*4)+3]) == false) {
					System.out.println("The initial ball placement must be on the field");
					System.exit(1);
					}
				}
				catch(NumberFormatException nfe) {
					System.out.println("args must be numerical values");
					System.exit(1);
				}
				ball_number += 1;
			}
		}
		else if (((args.length % 4) == 1) && (args.length > 4)) {
			double number_of_balls = (args.length/4);
			int ball_number = 0;
			while (ball_number < number_of_balls) {
				Ball dummy_ball = new Ball(0,0,0,0);
				try{
					if (dummy_ball.validate_init_pos(args[(ball_number*4)],args[(ball_number*4)+1],args[(ball_number*4)+2],args[(ball_number*4)+3]) == false) {
					System.out.println("The initial ball placement must be on the field");
					System.exit(1);
					}
				}
				catch(NumberFormatException nfe) {
					System.out.println("args must be numerical values");
					System.exit(1);
				}
				ball_number += 1;
			}
			Timer dummy_timer = new Timer(0);
			try{
				if (dummy_timer.validateTimeSliceArg(args[(ball_number*4)]) == false) {
					System.out.println("The time slice arg must be greater than 0");
					System.exit(1);
				}
			}
			catch(NumberFormatException nfe) {
				System.out.println("args must be numerical values");
				System.exit(1);
			}

		}

		else {
			System.out.println("Invalide number of arguments. Command line takes either a multiple of four\ncommands to create args/4 balls or a multiple of four commands plus\none additional command to create args/4 balls and a custom tick size.");
			System.exit(1);
		}
	}


	public static void main( String args[] ) {
		Soccer game = new Soccer();
		game.handle_args( args );
		int number_of_balls = (args.length/4);
		Ball[] balls = new Ball[(number_of_balls)];
		int ball_number = 0;
		while (ball_number < number_of_balls) {
			balls[ball_number] = new Ball(Double.parseDouble(args[(ball_number*4)]),Double.parseDouble(args[(ball_number*4)+1]),Double.parseDouble(args[(ball_number*4)+2]),Double.parseDouble(args[(ball_number*4)+3]));
			ball_number += 1;
		}
		double tick_rate = 0;
		if ((args.length % 4) == 1) {
			tick_rate = Double.parseDouble(args[(ball_number*4)]);
		}
		else {
			tick_rate = 1;
		}
		Timer game_timer = new Timer(tick_rate);
		Ball pole = new Ball(10,10,0,0);
		String contact_1 = "";
		String contact_2 = "";
		System.out.println("Initial Report at Start of Game:");
		System.out.println(game_timer.show_time());
		ball_number = 0;
		int in_play_and_moving = number_of_balls;
		while (ball_number < number_of_balls) {
			balls[ball_number].is_moving();
			System.out.print("Ball "+ball_number+" ");
			System.out.println(balls[ball_number].show_info());
			if ((balls[ball_number].is_moving == false) || (balls[ball_number].in_play == false)) {
				in_play_and_moving -= 1;
			}			
			ball_number += 1;
		}
		pole.is_moving();
		System.out.println("Pole Location "+pole.show_info());
		System.out.println("");
		while (collision == false && in_play_and_moving > 0) {
			ball_number = 0;
			game_timer.tick();
			outerloop:
			while (ball_number < number_of_balls) {
				if ((balls[ball_number].is_moving == true) && (balls[ball_number].in_play == true)) {
					balls[ball_number].update_x_position(game_timer.tick_size);
					balls[ball_number].update_y_position(game_timer.tick_size);
					balls[ball_number].update_x_vel(game_timer.tick_size);
					balls[ball_number].update_y_vel(game_timer.tick_size);
					balls[ball_number].still_in_play();
					balls[ball_number].is_moving();
					for (int i=0; i < balls.length; i++) {
						if (i != ball_number) {
							collision = balls[ball_number].collision(balls[i].x_position, balls[i].y_position);
							if (collision == true) {
								contact_1 = "ball "+String.valueOf(ball_number);
								contact_2 = "ball "+String.valueOf(i);
								break outerloop;
							}
						}
					}
					collision = balls[ball_number].collision(pole.x_position, pole.y_position);
					if (collision == true) {
						contact_1 = "ball "+String.valueOf(ball_number);
						contact_2 = "pole";
						break outerloop;
					}
					if ((balls[ball_number].is_moving == false) || (balls[ball_number].in_play == false)) {
						in_play_and_moving -= 1;
					}
				}
				ball_number += 1;
			}
			System.out.println(game_timer.show_time());
			if (collision == true) {
				System.out.println("Collision between "+contact_1+" and "+contact_2);
				System.exit(1);
			}
			ball_number = 0;
			while (ball_number < number_of_balls) {
				System.out.print("Ball "+ball_number+" ");
				System.out.println(balls[ball_number].show_info());
				ball_number += 1;
			}

			if ((in_play_and_moving == 0)) {
				System.out.println("No Collision is possible");
				System.exit(1);
			}
			System.out.println("");
		}
	}
}
