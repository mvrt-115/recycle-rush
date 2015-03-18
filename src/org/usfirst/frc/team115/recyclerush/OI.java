package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.commands.DriveArcadeWithJoystick;
import org.usfirst.frc.team115.recyclerush.commands.IntakeClose;
import org.usfirst.frc.team115.recyclerush.commands.IntakeOpen;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 *
 * @author Lee Mracek
 */
public class OI {

	public static final int AXIS_CONTROL_ELEVATOR = RobotMap.XBOX_AXIS_LY;
	public static final int ROLLER_MOVE_AXIS = 5;
	public static final int ROLLER_ROTATE_AXIS = 4;

	public static final double PRECISION_SPEED = 0.7;

	private Joystick driveJoystick, xboxJoystick;

	public OI() {
		driveJoystick = new Joystick(RobotMap.JOYSTICK_DRIVE);
		xboxJoystick = new Joystick(RobotMap.JOYSTICK_XBOX);
	}

	public void initXbox() {
		new XboxTrigger(xboxJoystick, RobotMap.XBOX_LT, 0.6)
		.whenActive(new IntakeOpen());
		new XboxTrigger(xboxJoystick, RobotMap.XBOX_RT, 0.6)
		.whenActive(new IntakeClose());
	}

	public void initDrive() {
		new JoystickButton(driveJoystick, RobotMap.BUTTON_DRIVE_PRECISION)
		.whileHeld(new DriveArcadeWithJoystick(driveJoystick,
				OI.PRECISION_SPEED));
	}

	public Joystick getDriveJoystick() {
		return driveJoystick;
	}

	public Joystick getXboxJoystick() {
		return xboxJoystick;
	}
}

class XboxTrigger extends Trigger {
	private Joystick xboxJoystick;
	private int channel;
	private double threshold;

	public XboxTrigger(Joystick joystick, int channel, double threshold) {
		this.xboxJoystick = joystick;
		this.channel = channel;
		this.threshold = threshold;
	}

	@Override
	public boolean get() {
		return xboxJoystick.getRawAxis(channel) >= threshold;
	}
}

