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

	private static final double SPEED_PRECISION_DRIVE = 0.6;
	private static final double SPEED_PRECISION_TURN = 0.6;

	private Joystick joystick;

	private boolean precision;

	public DriveArcadeWithJoystick(Joystick joystick) {
		this(joystick, false);
	}

	public DriveArcadeWithJoystick(Joystick joystick, boolean precision){
		requires(Robot.drive);
		this.joystick = joystick;
		this.precision = precision;
	}

	@Override
	protected void initialize() {
		Robot.drive.stop();
	}

	@Override
	protected void execute() {
		if(precision && Robot.oi.getJoystickButton(OI.BUTTON_PRECISION)) {
			Robot.drive.control(joystick, SPEED_PRECISION_DRIVE, SPEED_PRECISION_TURN);
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
