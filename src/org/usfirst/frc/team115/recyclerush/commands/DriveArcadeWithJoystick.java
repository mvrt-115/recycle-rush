package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.OI;
import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the robot using a joystick and specified axis
 *
 * @author Lee Mracek
 */
public class DriveArcadeWithJoystick extends Command {

	private static final double SPEED_PRECISION_DEFAULT = 0.7;

	private Joystick joystick;

	private boolean precision;
	private double precisionSpeed;

	public DriveArcadeWithJoystick(Joystick joystick) {
		this(joystick, false);
	}

	public DriveArcadeWithJoystick(Joystick joystick, boolean precision){
		this(joystick, precision, SPEED_PRECISION_DEFAULT);
	}


	public DriveArcadeWithJoystick(Joystick joystick, boolean precision, double precisionSpeed) {
		requires(Robot.drive);
		this.joystick = joystick;
		this.precision = precision;
		this.precisionSpeed = precisionSpeed;
	}

	@Override
	protected void initialize() {
		Robot.drive.stop();
	}

	@Override
	protected void execute() {
		if(precision && Robot.oi.getJoystickButton(OI.BUTTON_PRECISION)) {
			Robot.drive.control(joystick, precisionSpeed);
		}else{
			Robot.drive.control(joystick);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drive.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
