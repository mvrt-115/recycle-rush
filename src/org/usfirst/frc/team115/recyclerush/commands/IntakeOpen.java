package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Opens the intake
 * @author Lee Mracek
 */
public class IntakeOpen extends Command {

	private boolean finished = false;

	public IntakeOpen() {
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.intake.open();
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
