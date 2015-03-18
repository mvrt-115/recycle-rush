package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the robot using a joystick and specified axis
 *
 * @author Lee Mracek
 */
public class DriveArcadeWithJoystick extends Command {

	private Joystick joystick;

	private double speed = 1.0;

	public DriveArcadeWithJoystick(Joystick joystick) {
		requires(Robot.drive);

		this.joystick = joystick;
	}

	public DriveArcadeWithJoystick(Joystick joystick, double precisionSpeed) {
		this(joystick);
		speed = precisionSpeed;
	}

	@Override
	protected void initialize() {
		Robot.drive.stop();
	}

	@Override
	protected void execute() {
		Robot.drive.control(joystick, speed);
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
