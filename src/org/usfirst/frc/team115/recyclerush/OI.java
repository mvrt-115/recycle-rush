package org.usfirst.frc.team115.recyclerush;

import edu.wpi.first.wpilibj.Joystick;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 *
 * @author Lee Mracek
 */
public class OI {

	public static final int ROLLER_MOVE_AXIS = 5;
	public static final int ROLLER_ROTATE_AXIS = 4;

	private Joystick driveJoystick, xboxJoystick;

	public OI() {
		driveJoystick = new Joystick(RobotMap.JOYSTICK_DRIVE);
		xboxJoystick = new Joystick(RobotMap.JOYSTICK_XBOX);
	}

	public Joystick getDriveJoystick() {
		return driveJoystick;
	}

	public Joystick getXboxJoystick() {
		return xboxJoystick;
	}
}

