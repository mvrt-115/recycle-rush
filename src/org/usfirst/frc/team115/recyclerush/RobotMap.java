package org.usfirst.frc.team115.recyclerush;

/**
 * @author MVRT
 * This class contains final static variables
 * corresponding to each physical device on the robot. Naming scheme can
 * be found in the Electrical 2015 Excel Doc
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

    //joystick
    public static final int JOYSTICK 			 = 0;
    
    // Drive Straight Encoder
    public static final int DRIVE_STRAIGHT_ENCODER_A = 0;
    public static final int DRIVE_STRAIGHT_ENCODER_B = 0;
    //joystick for arcade drive
    
    //joysticks for tank drive
    //public static final int JOYSTICK_LEFT			= 0;
    //public static final int JOYSTICK_RIGHT		= 1;
    
    //joystick for mecanum
    //public static final int JOYSTICK				  = 0;
    //public static final int JOYSTICK_TWIST_CONSTANT = 0.5;
    
    //joystick buttons
    public static final int DRIVE_STRAIGHT_BUTTON	= 1;
    
    //compressor
    public static final int COMPRESSOR				= 0;
    
    //solenoids
    public static final int CLAW_SOLENOID_1			= 0;
    public static final int CLAW_SOLENOID_2			= 1;

    public static final int GRABBER_SOLENOID_1		= 2;
    public static final int GRABBER_SOLENOID_2		= 3;
    
    public static final int ROLLER_SOLENOID_1		= 5;
    public static final int ROLLER_SOLENOID_2		= 6;
    
    public static final int BRAKE_SOLENOID_1		= 7;
    public static final int BRAKE_SOLENOID_2		= 8;
    
    //gyro on drivetrain
    public static final int GYRO					= 0;
    public static final int GYRO_TEMP				= 0;
    
    //LEDS
    public static final int PWM_LED1_RED			= 11;
    public static final int PWM_LED1_GREEN			= 10;
    public static final int PWM_LED1_BLUE			= 12;

    //ultrasonic
    public static final int ULTRASONIC_FRONT 		= 4;
    public static final int ULTRASONIC_BACK 		= 5;
    public static final int ULTRASONIC_LEFT 		= 6;
    public static final int ULTRASONIC_RIGHT		= 7;
}
