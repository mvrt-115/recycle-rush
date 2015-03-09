package org.usfirst.frc.team115.recyclerush;

/**
 * @author MVRT
<<<<<<< HEAD
 * This class contains final static variables
=======
 * This class should contain final static variables
>>>>>>> 25632b4fc81f76866cc61f68d4769dd00e85c93d
 * corresponding to each physical device on the robot. Naming scheme can
 * be found in the Electrical 2015 Excel Doc
 */
public class RobotMap {
	
	// motors
	public static final int BACK_LEFT_DRIVE = 1;
	public static final int BACK_RIGHT_DRIVE = 10;
	public static final int FRONT_LEFT_DRIVE = 2;
	public static final int FRONT_RIGHT_DRIVE = 9;
	
	// Elevator Motors
    public static final int ELEV_MOTOR_1				= 8;
    public static final int ELEV_MOTOR_2				= 3;
    
    // Roller Motors
    public static final int ROLLER_MOTOR_LEFT		= 4;
    public static final int ROLLER_MOTOR_RIGHT		= 7;
	
	// limit switches
	public static final int ROLLER_SWITCH_LEFT = 4;
	public static final int ROLLER_SWITCH_RIGHT = 7;
	
    public static final int LIMIT_ELEVATOR_RESET	= 1;

    // Solenoids
    public static final int STABILIZER_PORT_A 	= 7;
    public static final int STABILIZER_PORT_B	= 6;
    
    public static final int CLAW_PORT_A 		= 5;
    public static final int CLAW_PORT_B 		= 4;
    
    public static final int BRAKE_PORT_A 		= 2;
    public static final int BRAKE_PORT_B 		= 3;
    
    public static final int ROLLER_PORT_A 		= 0;
    public static final int ROLLER_PORT_B 		= 1;
    
    // Joystick stuff
 	public static final int JOYSTICK 			 = 0;
 	public static final int XBOX                 = 1;
    
    // Joystick buttons
    public static final int JOYSTICK_TRIGGER		= 1;
    public static final int JOYSTICK_THUMB 			= 2;
    
    // Xbox buttons
    public static final int XBOX_A			 		= 1;
    public static final int XBOX_B			 		= 2;
    public static final int XBOX_Y			 		= 4;
    public static final int XBOX_X           		= 3;
    public static final int XBOX_LB			 		= 5;
    public static final int XBOX_RB			 		= 6;
    public static final int XBOX_BACK		 		= 7;
    public static final int XBOX_START 		 		= 8;
    public static final int XBOX_LAXIS_PRESS 		= 9;
    public static final int XBOX_RAXIS_PRESS 		= 10;
    public static final int XBOX_RT		 	 		= 3;
    public static final int XBOX_LT			 		= 2;
    public static final int XBOX_AXIS_LX 	 		= 0;
    public static final int XBOX_AXIS_LY	 		= 1;
    public static final int XBOX_AXIS_RX			= 4;
    public static final int XBOX_AXIS_RY 	 		= 5;
    
    // Ultrasonic analog input ports
	public static final int ULTRASONIC_FRONT 		= 0;
	public static final int ULTRASONIC_RIGHT 		= 1;
	public static final int ULTRASONIC_LEFT 		= 2;
	public static final int ULTRASONIC_BACK 		= 3;

    // Compressor
    public static final int PCM           		    = 1;
    public static final int COMPRESSOR     		    = 1;
}
