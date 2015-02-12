package org.usfirst.frc.team115.recyclerush;

/**
 * This class contains final static variables
 * corresponding to each physical device on the robot. Naming scheme can
 * be found in the Electrical 2015 Excel Doc
 *  @author MVRT
 */
public class RobotMap {

	//Drive Motors
    public static final int BACK_LEFT_DRIVE			= 1;
	public static final int BACK_RIGHT_DRIVE		= 10;
	public static final int FRONT_LEFT_DRIVE		= 2;
	public static final int FRONT_RIGHT_DRIVE		= 9;
	
    //Elevator Motors
    public static final int ELEVATOR				= 4;
    
    //Roller Motors
    public static final int ROLLER_MOTOR_LEFT		= 5;
    public static final int ROLLER_MOTOR_RIGHT		= 6;

    // solenoids
    public static final int CLAW_SOLENOID_1 		= 0;
    public static final int CLAW_SOLENOID_2			= 1;

    public static final int GRABBER_SOLENOID_1 		= 2;
    public static final int GRABBER_SOLENOID_2 		= 3;
    
    public static final int BRAKE_SOLENOID_1 		= 4;
    public static final int BRAKE_SOLENOID_2 		= 5;
    
    public static final int ROLLER_SOLENOID_1 		= 6;
    public static final int ROLLER_SOLENOID_2 		= 7;

    //joystick
    public static final int JOYSTICK 			 	= 0;
    public static final int XBOX                 	= 1;
    
    //joystick buttons
    public static final int JOYSTICK_TRIGGER		= 1;
    public static final int JOYSTICK_THUMB 			= 2;
    
    //xbox buttons
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
    
    //ultrasonic analog input ports
	public static final int ULTRASONIC_FRONT 		= 0;
	public static final int ULTRASONIC_RIGHT 		= 1;
	public static final int ULTRASONIC_LEFT 		= 2;
	public static final int ULTRASONIC_BACK 		= 3;

    // compressor
    public static final int COMPRESSOR           = 0;
}
