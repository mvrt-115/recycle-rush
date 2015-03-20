package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Closes the intake solenoid
 * @author Lee Mracek
 */
public class IntakeClose extends Command {

	public IntakeClose() {
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
		setTimeout(Intake.TIME_TO_ACTUATE);
	}

	@Override
	protected void execute() {
		Robot.intake.close();
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
