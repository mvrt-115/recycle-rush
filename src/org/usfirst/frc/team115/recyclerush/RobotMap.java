package org.usfirst.frc.team115.recyclerush;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Drive Motors
	public static final int DRIVE_MOTOR_BACKLEFT = 1;
	public static final int DRIVE_MOTOR_BACKRIGHT = 10;
	public static final int DRIVE_MOTOR_FRONTLEFT = 2;
	public static final int DRIVE_MOTOR_FRONTRIGHT = 9;

	// Elevator Motors
	public static final int ELEV_MOTOR_1 = 8;
	public static final int ELEV_MOTOR_2 = 3;

	// Roller Motors
	public static final int ROLLER_MOTOR_LEFT = 4;
	public static final int ROLLER_MOTOR_RIGHT = 7;

	// Limit Switches
	public static final int ELEV_SWITCH_RESET = 1;

	// Solenoids
	public static final int STABILIZER_SOL_A = 7;
	public static final int STABILIZER_SOL_B = 6;

	public static final int CLAW_SOL_A = 5;
	public static final int CLAW_SOL_B = 4;

	public static final int ELEV_SOL_BRAKEA = 2;
	public static final int ELEV_SOL_BREAKB = 3;

	public static final int ROLLER_SOL_A = 0;
	public static final int ROLLER_SOL_B = 1;

	// Joysticks
	public static final int JOYSTICK_DRIVE = 0;
	public static final int JOYSTICK_XBOX = 1;

	// Joystick Buttons
	public static final int BUTTON_DRIVE_PRECISION = 1;
	public static final int BUTTON_DRIVE_STRAIGHT = 2;

	// Xbox Buttons
	public static final int XBOX_A = 1;
	public static final int XBOX_B = 2;
	public static final int XBOX_Y = 4;
	public static final int XBOX_X = 3;
	public static final int XBOX_LB = 5;
	public static final int XBOX_RB = 6;
	public static final int XBOX_BACK = 7;
	public static final int XBOX_START = 8;
	public static final int XBOX_LAXIS_PRESS = 9;
	public static final int XBOX_RAXIS_PRESS = 10;
	public static final int XBOX_RT = 3;
	public static final int XBOX_LT = 2;
	public static final int XBOX_AXIS_LX = 0;
	public static final int XBOX_AXIS_LY = 1;
	public static final int XBOX_AXIS_RX = 4;
	public static final int XBOX_AXIS_RY = 5;

	// Compressor
	public static final int PCM = 1;
	public static final int COMPRESSOR = 1;

}
