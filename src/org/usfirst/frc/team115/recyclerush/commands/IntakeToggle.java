package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Closes the intake solenoid
 * @author Lee Mracek
 */
public class IntakeToggle extends Command {

	private boolean wasOpen;

	public IntakeToggle() {
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
		wasOpen = Robot.intake.isOpen();
		setTimeout(Intake.TIME_TO_ACTUATE);
	}

	@Override
	protected void execute() {
		if(wasOpen) {
			Robot.intake.close();
		} else {
			Robot.intake.open();
		}
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