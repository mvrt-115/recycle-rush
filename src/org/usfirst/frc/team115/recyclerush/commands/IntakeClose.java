package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Closes the intake solenoid
 * @author Lee Mracek
 */
public class IntakeClose extends Command {

	private boolean finished = false;

	public IntakeClose() {
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.intake.close();
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
