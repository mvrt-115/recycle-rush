package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.commands.ClawClose;
import org.usfirst.frc.team115.recyclerush.commands.ClawOpen;
import org.usfirst.frc.team115.recyclerush.commands.ClawToggle;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorDown;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorHardReset;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorUp;
import org.usfirst.frc.team115.recyclerush.commands.IntakeClose;
import org.usfirst.frc.team115.recyclerush.commands.IntakeOpen;
import org.usfirst.frc.team115.recyclerush.commands.IntakeToggle;
import org.usfirst.frc.team115.recyclerush.commands.OpenAll;
import org.usfirst.frc.team115.recyclerush.commands.StabilizerToggle;
import org.usfirst.frc.team115.recyclerush.commands.indep.AutoIntake;
import org.usfirst.frc.team115.recyclerush.commands.indep.StabilizeTotes;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
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

	private JoystickButton rollerToggle;

	public OI() {
		driveJoystick = new Joystick(RobotMap.JOYSTICK_DRIVE);
		xboxJoystick = new Joystick(RobotMap.JOYSTICK_XBOX);
	}


	public void initXbox(){
		initXboxSchemeTwo();
	}

	public void initXboxSchemeTwo(){
		new XboxTrigger(xboxJoystick, RobotMap.XBOX_LT, 0.6)
		.whenActive(new ClawToggle());
		new XboxTrigger(xboxJoystick, RobotMap.XBOX_RT, 0.6)
		.whenActive(new IntakeToggle());
		new JoystickButton(xboxJoystick, RobotMap.XBOX_Y)
		.whenPressed(new StabilizerToggle());

		//D-Pad Up/Down: Elev presets up and down
		new POVTrigger(xboxJoystick, 0)
		.whenActive(new ElevatorUp());
		new POVTrigger(xboxJoystick, 180)
		.whenActive(new ElevatorDown());

		//D-PAD Right: Stabilize Totes
		new POVTrigger(xboxJoystick, 90)
		.whenActive(new StabilizeTotes());

		//D-Pad Left: Hard Reset
		new POVTrigger(xboxJoystick, 270)
		.whenActive(new ElevatorHardReset());

		//B button: autoIntake with ramp
		new JoystickButton(xboxJoystick, RobotMap.XBOX_B)
		.whenPressed(new AutoIntake(Elevator.PRESET_TOTE_INTAKETOTE + 3, true, true));

		//X button: autoIntake no rollers
		new JoystickButton(xboxJoystick, RobotMap.XBOX_X)
		.whenPressed(new AutoIntake(Elevator.PRESET_TOTE_INTAKETOTE + 3, false, true));

		//A button: open all
		new JoystickButton(xboxJoystick, RobotMap.XBOX_A)
		.whenPressed(new OpenAll());
	}

	public void initXboxSchemeOne() {
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
		new JoystickButton(xboxJoystick, RobotMap.XBOX_A)
		.whenPressed(new OpenAll());
		new POVTrigger(xboxJoystick, 0)
		.whenActive(new ElevatorUp());
		new POVTrigger(xboxJoystick, 180)
		.whenActive(new ElevatorDown());
		new POVTrigger(xboxJoystick, 90)
		.whenActive(new StabilizeTotes());
		new POVTrigger(xboxJoystick, 270)
		.whenActive(new ElevatorHardReset());
		new JoystickButton(xboxJoystick, RobotMap.XBOX_B)
		.whenPressed(new AutoIntake(Elevator.PRESET_TOTE_INTAKETOTE, true, true));
	}

	public boolean rollerButtonPressed() {
		return xboxJoystick.getRawButton(RobotMap.XBOX_RAXIS_PRESS);
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

	/**
	 * Gets the axis values of the joystick
	 * @param axis: The value of the current axis
	 * @param invert: Whether or not to invert the axis
	 * if invert is false, joystick forward = negative
	 * if it's true, joystick forward = positive
	 */
	public double getXboxAxis(int axis, boolean invert){
		int inv = invert?-1:1;
		return inv * xboxJoystick.getRawAxis(axis);
	}

	public void rumbleXbox(final RumbleType type, final double strength, final long millis) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				xboxJoystick.setRumble(type, (float) strength);
				try {
					Thread.sleep(millis);
				} catch (InterruptedException e) {
					System.err.println("Rumble sleep failed");
				}
				xboxJoystick.setRumble(type, 0);
			}
		}).start();
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

class POVTrigger extends Trigger {
	private Joystick xboxJoystick;
	private int angle;

	public POVTrigger(Joystick joystick, int angle) {
		this.xboxJoystick = joystick;
		this.angle = angle;
	}

	@Override
	public boolean get() {
		return xboxJoystick.getPOV() == angle;
	}
}

