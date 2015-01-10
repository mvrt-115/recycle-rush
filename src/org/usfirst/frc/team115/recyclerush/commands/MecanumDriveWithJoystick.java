package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class MecanumDriveWithJoystick extends Command {
	
	public MecanumDriveWithJoystick() {
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
	}

	/**
	 * Drives the robot based on a joystick
	 */
	@Override
	protected void execute() {
		Robot.drive.drive(Robot.oi.getJoystick());
	}

	@Override
	protected boolean isFinished() {
		return false; // run until interrupted
	}

	/**
	 * Stops the drivetrain when the command ends
	 */
	@Override
	protected void end() {
		Robot.drive.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}