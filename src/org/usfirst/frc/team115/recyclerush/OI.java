package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.commands.ClawClose;
import org.usfirst.frc.team115.recyclerush.commands.ClawOpen;
import org.usfirst.frc.team115.recyclerush.commands.IntakeClose;
import org.usfirst.frc.team115.recyclerush.commands.IntakeOpen;
import org.usfirst.frc.team115.recyclerush.commands.StabilizerToggle;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 *
 * @author Lee Mracek, Akhil Palla
 */
public class OI {

	public static final int AXIS_CONTROL_ELEVATOR = RobotMap.XBOX_AXIS_LY;
	public static final int BUTTON_PRECISION = RobotMap.JOYSTICK_BUTTON_TRIGGER;
	public static final int ROLLER_MOVE_AXIS = RobotMap.XBOX_AXIS_RY;
	public static final int ROLLER_ROTATE_AXIS = RobotMap.XBOX_AXIS_RX;

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
		new JoystickButton(xboxJoystick, RobotMap.XBOX_LB)
		.whenPressed(new ClawOpen());
		new JoystickButton(xboxJoystick, RobotMap.XBOX_RB)
		.whenPressed(new ClawClose());
		new JoystickButton(xboxJoystick, RobotMap.XBOX_Y)
		.whenPressed(new StabilizerToggle());
	}

	public Joystick getDriveJoystick() {
		return driveJoystick;
	}

	public Joystick getXboxJoystick() {
		return xboxJoystick;
	}

	public boolean getJoystickButton(int button){
		return driveJoystick.getRawButton(button);
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

